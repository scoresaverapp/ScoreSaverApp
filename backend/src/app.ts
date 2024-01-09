import dotenv from "dotenv";
import express from "express";
import morgan from "morgan";

dotenv.config({ path: `.env.${process.env.ENV}.local` });

const app = express();

app.use(morgan("dev"));
app.use(express.json());

export default app;
