import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../../api/axios";

export default function OwnerDashboard() {

  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user"));

  const [stats, setStats] = useState({
    totalRooms: 0,
    totalBookings: 0,
    revenue: 0
  });

  useEffect(() => {
    loadStats();
  }, []);

  const loadStats = async () => {
    try {
      const res = await api.get("/owner/stats");
      setStats(res.data);
    } catch (error) {
      console.log(error);
    }
  };

  const logout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <div className="min-h-screen bg-gray-100 p-8">

      {/* HEADER */}
      <div className="bg-white rounded-3xl shadow-lg p-8 flex justify-between items-center mb-8">

        <div>
          <h1 className="text-4xl font-bold text-indigo-600">
            Owner Dashboard
          </h1>
          <p className="text-gray-500 mt-2">
            Welcome {user?.name}, manage your rooms & bookings
          </p>
        </div>

        <button
          onClick={logout}
          className="bg-red-500 hover:bg-red-600 text-white px-6 py-3 rounded-xl"
        >
          Logout
        </button>

      </div>

      {/* STATS */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-10">

        <StatCard title="Total Rooms" value={stats.totalRooms} color="indigo" />
        <StatCard title="Total Bookings" value={stats.totalBookings} color="green" />
        <StatCard title="Revenue" value={`₹${stats.revenue}`} color="purple" />

      </div>

      {/* ACTIONS */}
      <div className="grid md:grid-cols-3 gap-6">

        <button
          onClick={() => navigate("/owner/add-room")}
          className="bg-indigo-600 text-white rounded-3xl p-8 text-xl font-bold"
        >
          ➕ Add Room
        </button>

        <button
          onClick={() => navigate("/owner/rooms")}
          className="bg-white border border-indigo-600 text-indigo-600 rounded-3xl p-8 text-xl font-bold"
        >
          🏠 Manage Rooms
        </button>

        {/* ✅ NEW */}
 <button
  onClick={() => navigate("/owner/bookings")}
  className="bg-green-600 text-white rounded-3xl p-8 text-xl font-bold"
>
  📑 Manage Bookings
</button>

      </div>

    </div>
  );
}

/* ================= COMPONENT ================= */

function StatCard({ title, value, color }) {
  return (
    <div className="bg-white rounded-3xl shadow p-6">
      <p className="text-gray-500">{title}</p>
      <h2 className={`text-4xl font-bold mt-2 text-${color}-600`}>
        {value}
      </h2>
    </div>
  );
}