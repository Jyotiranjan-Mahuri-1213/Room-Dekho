
import { useState } from "react";
import api from "../api/axios";
import { useNavigate, Link } from "react-router-dom";

export default function ForgotPassword() {

    const [step, setStep] = useState(1);

    const [email, setEmail] = useState("");
    const [otp, setOtp] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    const [loading, setLoading] = useState(false);

    const navigate = useNavigate();


    // STEP 1 - SEND OTP
    const handleSendOtp = async (e) => {

        e.preventDefault();

        if (!email) {
            alert("Please enter your email");
            return;
        }

        setLoading(true);

        try {

            await api.post("/users/forgot-password", {
                email
            });

            alert("OTP sent successfully");

            setStep(2);

        } catch (error) {

            alert(
                error.response?.data ||
                "Failed to send OTP"
            );

        } finally {

            setLoading(false);

        }
    };


    // STEP 2 - VERIFY OTP
    const handleVerifyOtp = async (e) => {

        e.preventDefault();

        if (!otp) {
            alert("Please enter OTP");
            return;
        }

        setLoading(true);

        try {

            await api.post("/users/verify-otp", {
                email,
                otp
            });

            alert("OTP verified successfully");

            setStep(3);

        } catch (error) {

            alert(
                error.response?.data ||
                "Invalid OTP"
            );

        } finally {

            setLoading(false);

        }
    };


    // STEP 3 - RESET PASSWORD
    const handleResetPassword = async (e) => {

        e.preventDefault();

        if (!newPassword || !confirmPassword) {
            alert("Please enter both passwords");
            return;
        }

        if (newPassword !== confirmPassword) {
            alert("Passwords do not match");
            return;
        }

        setLoading(true);

        try {

            await api.post("/users/reset-password", {
                email,
                newPassword,
                confirmPassword
            });

            alert("Password reset successfully");

            navigate("/login");

        } catch (error) {

            alert(
                error.response?.data ||
                "Password reset failed"
            );

        } finally {

            setLoading(false);

        }
    };


    return (

        <div className="
            min-h-screen
            flex
            items-center
            justify-center
            bg-gradient-to-br
            from-indigo-600
            via-purple-600
            to-pink-500
            px-4
        ">

            <div className="
                bg-white
                w-full
                max-w-md
                rounded-3xl
                shadow-2xl
                p-8
            ">


                {/* LOGO */}

                <h1 className="
                    text-3xl
                    font-bold
                    text-center
                    text-indigo-600
                ">

                    🏠 RoomDekho

                </h1>


                <p className="
                    text-center
                    text-gray-500
                    mt-2
                ">

                    {step === 1 && "Reset your password"}

                    {step === 2 && "Verify your email"}

                    {step === 3 && "Create a new password"}

                </p>


                {/* STEP 1 */}

                {step === 1 && (

                    <form
                        onSubmit={handleSendOtp}
                        className="mt-8 space-y-5"
                    >

                        <div>

                            <label className="
                                text-gray-700
                                font-medium
                            ">

                                Email

                            </label>

                            <input
                                type="email"
                                placeholder="Enter your registered email"
                                value={email}
                                onChange={(e) =>
                                    setEmail(e.target.value)
                                }
                                className="
                                    w-full
                                    mt-2
                                    px-5
                                    py-4
                                    rounded-xl
                                    border
                                    border-gray-300
                                    outline-none
                                    focus:ring-2
                                    focus:ring-indigo-500
                                    text-lg
                                "
                            />

                        </div>


                        <button
                            disabled={loading}
                            className="
                                w-full
                                bg-indigo-600
                                hover:bg-indigo-700
                                text-white
                                py-4
                                rounded-xl
                                font-bold
                                text-lg
                                transition
                                shadow-lg
                            "
                        >

                            {loading
                                ? "Sending OTP..."
                                : "Send OTP"
                            }

                        </button>

                    </form>

                )}


                {/* STEP 2 */}

                {step === 2 && (

                    <form
                        onSubmit={handleVerifyOtp}
                        className="mt-8 space-y-5"
                    >

                        <p className="
                            text-center
                            text-gray-500
                        ">

                            OTP sent to

                            <br />

                            <span className="
                                font-semibold
                                text-gray-700
                            ">

                                {email}

                            </span>

                        </p>


                        <div>

                            <label className="
                                text-gray-700
                                font-medium
                            ">

                                OTP

                            </label>

                            <input
                                type="text"
                                maxLength="6"
                                placeholder="Enter 6-digit OTP"
                                value={otp}
                                onChange={(e) =>
                                    setOtp(e.target.value)
                                }
                                className="
                                    w-full
                                    mt-2
                                    px-5
                                    py-4
                                    rounded-xl
                                    border
                                    border-gray-300
                                    outline-none
                                    focus:ring-2
                                    focus:ring-indigo-500
                                    text-lg
                                    text-center
                                    tracking-widest
                                "
                            />

                        </div>


                        <button
                            disabled={loading}
                            className="
                                w-full
                                bg-indigo-600
                                hover:bg-indigo-700
                                text-white
                                py-4
                                rounded-xl
                                font-bold
                                text-lg
                                transition
                                shadow-lg
                            "
                        >

                            {loading
                                ? "Verifying..."
                                : "Verify OTP"
                            }

                        </button>


                        <button
                            type="button"
                            onClick={() => setStep(1)}
                            className="
                                w-full
                                text-indigo-600
                                font-semibold
                                hover:underline
                            "
                        >

                            Change Email

                        </button>

                    </form>

                )}


                {/* STEP 3 */}

                {step === 3 && (

                    <form
                        onSubmit={handleResetPassword}
                        className="mt-8 space-y-5"
                    >

                        <div>

                            <label className="
                                text-gray-700
                                font-medium
                            ">

                                New Password

                            </label>

                            <input
                                type="password"
                                placeholder="Enter new password"
                                value={newPassword}
                                onChange={(e) =>
                                    setNewPassword(e.target.value)
                                }
                                className="
                                    w-full
                                    mt-2
                                    px-5
                                    py-4
                                    rounded-xl
                                    border
                                    border-gray-300
                                    outline-none
                                    focus:ring-2
                                    focus:ring-indigo-500
                                    text-lg
                                "
                            />

                        </div>


                        <div>

                            <label className="
                                text-gray-700
                                font-medium
                            ">

                                Confirm Password

                            </label>

                            <input
                                type="password"
                                placeholder="Confirm new password"
                                value={confirmPassword}
                                onChange={(e) =>
                                    setConfirmPassword(e.target.value)
                                }
                                className="
                                    w-full
                                    mt-2
                                    px-5
                                    py-4
                                    rounded-xl
                                    border
                                    border-gray-300
                                    outline-none
                                    focus:ring-2
                                    focus:ring-indigo-500
                                    text-lg
                                "
                            />

                        </div>


                        <button
                            disabled={loading}
                            className="
                                w-full
                                bg-indigo-600
                                hover:bg-indigo-700
                                text-white
                                py-4
                                rounded-xl
                                font-bold
                                text-lg
                                transition
                                shadow-lg
                            "
                        >

                            {loading
                                ? "Resetting..."
                                : "Reset Password"
                            }

                        </button>

                    </form>

                )}


                {/* BACK TO LOGIN */}

                <div className="
                    text-center
                    mt-6
                    text-gray-600
                ">

                    <Link
                        to="/login"
                        className="
                            text-indigo-600
                            font-semibold
                            hover:underline
                        "
                    >

                        ← Back to Login

                    </Link>

                </div>

            </div>

        </div>

    );
}

