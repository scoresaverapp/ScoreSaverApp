import { RequestHandler } from "express";
import UserModel from "../models/user";
import bcrypt from "bcrypt";
import createHttpError from "http-errors";

interface SignUpBody {
  email: string;
  password: string;
}

export const signUp: RequestHandler<
  unknown,
  unknown,
  SignUpBody,
  unknown
> = async (req, res, next) => {
  const { email, password: passwordRaw } = req.body;

  try {
    const existingUsername = await UserModel.findOne({ username: email })
      .collation({
        locale: "en",
        strength: 2,
      })
      .exec();

    if (existingUsername) {
      throw createHttpError(409, "User already exist");
    }

    const passwordHashed = await bcrypt.hash(passwordRaw, 10);

    const result = await UserModel.create({
      username: email,
      email,
      password: passwordHashed,
    });

    const newUser = result.toObject();
    delete newUser.password;

    req.logIn(newUser, (error) => {
      if (error) {
        throw error;
      }
      res.status(201).json(newUser);
    });
  } catch (error) {
    next(error);
  }
};
