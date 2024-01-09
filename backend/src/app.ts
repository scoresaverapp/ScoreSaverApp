import dotenv from "dotenv";
import express from "express";
import morgan from "morgan";
import usersRoute from "./routes/users";
import errorHandler from "./middlewares/errorHandler";

dotenv.config({ path: `.env.${process.env.ENV}.local` });

const app = express();

app.use(morgan("dev"));
app.use(express.json());
app.use("/users", usersRoute);
app.use(errorHandler);

export default app;
