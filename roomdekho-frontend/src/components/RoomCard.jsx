import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function RoomCard({ room }) {

  const navigate = useNavigate();

  const [currentImage, setCurrentImage] = useState(0);
  const [favorite, setFavorite] = useState(false);

  useEffect(() => {
    if (!room.images || room.images.length <= 1) return;

    const slider = setInterval(() => {
      setCurrentImage((prev) => (prev + 1) % room.images.length);
    }, 3000);

    return () => clearInterval(slider);
  }, [room.images]);

  return (
    <div className="bg-white rounded-3xl overflow-hidden shadow-md hover:shadow-2xl transition duration-300 hover:-translate-y-2">

      <div className="relative">

        <img
          src={room.images?.[currentImage] || "https://images.unsplash.com/photo-1560185008-b033106af5c3"}
          className="w-full h-72 object-cover"
        />

        <button
          onClick={() => setFavorite(!favorite)}
          className="absolute top-4 right-4 bg-white w-10 h-10 rounded-full"
        >
          {favorite ? "❤️" : "🤍"}
        </button>

      </div>

      <div className="p-6">

        <h2 className="text-xl font-bold mb-2">{room.title}</h2>
        <p className="text-gray-500 mb-3">📍 {room.location}</p>

        <h3 className="text-indigo-600 font-bold text-xl">
          ₹{room.rent} <span className="text-sm text-gray-500">/month</span>
        </h3>

        <div className="flex gap-3 mt-6">

          <button
            onClick={() => navigate(`/room/${room.id}`)}
            className="flex-1 border border-indigo-600 text-indigo-600 py-3 rounded-xl"
          >
            View
          </button>

          {/* ✅ FIXED FLOW (NO CHANGE UI) */}
          <button
            onClick={() => navigate(`/room/${room.id}`)}
            className="flex-1 bg-indigo-600 text-white py-3 rounded-xl"
          >
            Book
          </button>

        </div>

      </div>
    </div>
  );
}