import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import api from "../../api/axios";

export default function PaymentPage() {

  const { id } = useParams(); // bookingId
  const navigate = useNavigate();

  const [paymentMethod, setPaymentMethod] = useState("UPI");
  const [amount, setAmount] = useState("");

const payNow = async () => {

    try{

        await api.post(`/bookings/payment/${id}`,{

            paymentMethod,

            amount:Number(amount),

            transactionId:"TXN"+Date.now()

        });


        alert("Payment Successful");


        navigate("/my-bookings");


    }
    catch(err){

        console.log(
            "Payment error",
            err
        );

        alert("Payment failed");

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

        <input
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          placeholder="Amount"
          className="w-full p-3 border rounded-xl mb-4"
        />

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