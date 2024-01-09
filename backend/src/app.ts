import dotenv from "dotenv";
import express from "express";
import morgan from "morgan";

console.log(process.env.ENV);

dotenv.config({ path: `.env.${process.env.ENV}.local` });

const app = express();

app.use(morgan("dev"));
app.use(express.json());

export default app;
