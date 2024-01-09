import dotenv from "dotenv";
dotenv.config({ path: `.env.${process.env.ENV}.local` });
import express from "express";
import morgan from "morgan";
import usersRoute from "./routes/users";
import errorHandler from "./middlewares/errorHandler";
import session from "express-session";
import sessionConfig from "./config/session";
import passport from "passport";
import "./config/passport";

const app = express();

app.use(morgan("dev"));
app.use(session(sessionConfig));
app.use(passport.authenticate("session"));
app.use(express.json());
app.use("/users", usersRoute);
app.use(errorHandler);

export default app;
