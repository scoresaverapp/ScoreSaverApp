import bcrypt from "bcrypt";
import passport from "passport";
import { Strategy as LocalStrategy } from "passport-local";
import UserModel from "../models/user";
import mongoose from "mongoose";

passport.serializeUser((user, callback) => {
  callback(null, user._id);
});

passport.deserializeUser((userId: string, callback) => {
  callback(null, { _id: new mongoose.Types.ObjectId(userId) });
});

passport.use(
  new LocalStrategy(async (username, password, callback) => {
    try {
      const existingUser = await UserModel.findOne({ username })
        .select("+email +password")
        .exec();

      if (!existingUser || !existingUser.password) {
        return callback(null, false);
      }

      const passwordMatch = await bcrypt.compare(
        password,
        existingUser.password
      );

      if (!passwordMatch) {
        return callback(null, false);
      }

      const user = existingUser.toObject();
      delete user.password;
      return callback(null, user);
    } catch (error) {
      callback(error);
    }
  })
);
