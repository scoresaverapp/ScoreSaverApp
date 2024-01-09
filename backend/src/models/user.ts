import { InferSchemaType, Schema, model } from "mongoose";

const userSchema = new Schema(
  {
    username: { type: String, unique: true, sparse: true },
    email: {
      type: String,
      unique: true,
      required: true,
      sparse: true,
      select: false,
    },
    firstName: { type: String },
    lastName: { type: String },
    height: { type: Number },
    weight: { type: Number },
    age: { type: Number },
    password: { type: String, select: false },
    googleId: { type: String, unique: true, sparse: true, select: false },
    facebookId: { type: String, unique: true, sparse: true, select: false },
  },
  { timestamps: true }
);

userSchema.pre("validate", function (next) {
  if (!this.email && !this.googleId && !this.facebookId) {
    return next(new Error("User must have an email or social provider id"));
  }
  next();
});

type User = InferSchemaType<typeof userSchema>;

export default model<User>("User", userSchema);
