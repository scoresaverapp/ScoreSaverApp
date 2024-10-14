package com.scoresaver.app.wear.features.game.calories

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.android.gms.fitness.request.DataReadRequest
import com.scoresaver.app.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class CaloriesTrackingService : Service() {

    private var isRunning = false
    private var calories = 0f
    private var serviceJob = Job()
    private lateinit var coroutineScope: CoroutineScope
    private val caloriesBroadcastReceiver = CaloriesBroadcastReceiver()
    private var googleAccount: GoogleSignInAccount? = null

    override fun onCreate() {
        super.onCreate()
        val intentFilter = IntentFilter("CALORIES_TICK")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(caloriesBroadcastReceiver, intentFilter, RECEIVER_NOT_EXPORTED)
        }
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        googleAccount = intent?.getParcelableExtra("account")
        val notification = NotificationCompat.Builder(this, "CHANNEL")
            .setContentTitle("Calories Tracking Service")
            .setContentText("Tracking calories...")
            .setSmallIcon(R.drawable.ic_clock)
            .setOngoing(true)
            .build()

        startForeground(1, notification)

        intent?.let {
            when (it.action) {
                "START_CALORIES" -> startTrackingCalories()
                "STOP_CALORIES" -> stopTrackingCalories()
                "RESET_CALORIES" -> resetTrackingCalories()
            }
        }

        return START_STICKY
    }

    private fun resetTrackingCalories() {
        if(isRunning) {
            stopTrackingCalories()
        }
        calories = 0f
    }

    private fun stopTrackingCalories() {
        if(isRunning) {
            isRunning = false
            serviceJob.cancel()
        }
    }

    private fun startTrackingCalories() {
        if (!isRunning) {
            isRunning = true
            serviceJob = Job()
            coroutineScope = CoroutineScope(Dispatchers.Main + serviceJob)
            coroutineScope.launch {
                while (isRunning) {
                    googleAccount?.let { readCalories(it) }
                    delay(60000)
                }
            }
        }
    }

    private suspend fun readCalories(account: GoogleSignInAccount) {
        val endTime = System.currentTimeMillis()
        val startTime = endTime - TimeUnit.MINUTES.toMillis(1) // Ultimo minuto

        val readRequest = DataReadRequest.Builder()
            .aggregate(DataType.TYPE_CALORIES_EXPENDED, DataType.AGGREGATE_CALORIES_EXPENDED)
            .bucketByTime(1, TimeUnit.MINUTES)
            .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
            .build()

        withContext(Dispatchers.IO) {
            Fitness.getHistoryClient(this@CaloriesTrackingService, account)
                .readData(readRequest)
                .addOnSuccessListener { response ->
                    var totalCalories = 0f

                    response.buckets.forEach { bucket ->
                        bucket.dataSets.forEach { dataSet ->
                            dataSet.dataPoints.forEach { dataPoint ->
                                totalCalories += dataPoint.getValue(Field.FIELD_CALORIES).asFloat()
                            }
                        }
                    }
                    val formattedCalories = String.format("%.2f", totalCalories)
                    calories = formattedCalories.toFloat()
                    val intent = Intent("CALORIES_TICK")
                    intent.putExtra("calories", calories)
                    Log.d("LOLO", "Caloreis read $calories")
                    sendBroadcast(intent)
                }
                .addOnFailureListener { e ->
                    Log.e("LOLO", "Error reading calories: ${e.message}")
                }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        unregisterReceiver(caloriesBroadcastReceiver)
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            "CHANNEL",
            "Calories Tracking Service",
            NotificationManager.IMPORTANCE_LOW
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }
}
