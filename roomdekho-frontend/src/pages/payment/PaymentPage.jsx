import { useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import api from "../../api/axios";

export default function PaymentPage() {

  const { id } = useParams(); // bookingId
  const navigate = useNavigate();
    const location = useLocation();

const amount = location.state?.amount;

  const [paymentMethod, setPaymentMethod] = useState("UPI");
    const payNow = async () => {

  try {

    // 1. Create Razorpay order from backend
    const orderResponse = await api.post(
      `/payments/create-order?bookingId=${id}&amount=${amount}`
    );

    const order = orderResponse.data;

    console.log("Razorpay order:", order);

    // 2. Open Razorpay Checkout

    console.log(
    "Frontend Razorpay Key:",
    import.meta.env.VITE_RAZORPAY_KEY_ID
);
    const options = {
      key: import.meta.env.VITE_RAZORPAY_KEY_ID,

      amount: order.amount,

      currency: order.currency,

      name: "RoomDekho",

      description: "Room Booking Payment",

      order_id: order.id,

      handler: function (response) {

        console.log("Payment response:", response);

      },

      theme: {
        color: "#4f46e5"
      }
    };

  console.log("Razorpay constructor:", window.Razorpay);
console.log("Razorpay options:", options);

const razorpay = new window.Razorpay(options);

razorpay.open();

  } catch (err) {

    console.log("Payment error:", err);

    alert("Unable to start payment");

  }

};
  return (
    <div className="min-h-screen flex items-center justify-center bg-indigo-600">

      <div className="bg-white p-10 rounded-3xl w-[450px]">

        <h1 className="text-3xl font-bold mb-6 text-center">
          Payment
        </h1>

        <select
          value={paymentMethod}
          onChange={(e) => setPaymentMethod(e.target.value)}
          className="w-full p-3 border rounded-xl mb-4"
        >
          <option>UPI</option>
          <option>CARD</option>
          <option>CASH</option>
        </select>

                <div className="w-full p-3 border rounded-xl mb-4 bg-gray-100">
            Amount: ₹{amount}
          </div>

        <button
          onClick={payNow}
          className="w-full bg-indigo-600 text-white py-3 rounded-xl"
        >
          Pay Now
        </button>

      </div>
    </div>
  );
}