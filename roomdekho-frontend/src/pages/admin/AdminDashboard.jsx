import { useEffect, useState } from "react";
import api from "../../api/axios";
import { useNavigate } from "react-router-dom";

export default function AdminDashboard() {
  const navigate = useNavigate();

  const [stats, setStats] = useState({
    users: 0,
    rooms: 0,
    bookings: 0,
    requests: 0
  });

  // SAFE HANDLER
  const getArray = (data) => {
    if (Array.isArray(data)) return data;
    if (Array.isArray(data?.content)) return data.content;
    if (Array.isArray(data?.data)) return data.data;
    return [];
  };

  useEffect(() => {
    loadStats();
  }, []);

  const loadStats = async () => {
    try {
      const [users, rooms, bookings, requests] = await Promise.all([
        api.get("/admin/users"),
        api.get("/admin/rooms"),
        api.get("/admin/bookings"),
        api.get("/owner-requests/all")
      ]);

      setStats({
        users: getArray(users.data).length,
        rooms: getArray(rooms.data).length,
        bookings: getArray(bookings.data).length,
        requests: getArray(requests.data).length
      });

    } catch (err) {
      console.log("Admin Dashboard Error:", err);
    }
  };

  // ================= LOGOUT =================
    // ================= LOGOUT =================
const logout = () => {
  localStorage.clear();
  navigate("/");
};

// ================= BACK =================
const goBack = () => {
  navigate("/login");
};
  return (
    <div className="min-h-screen bg-gray-100 p-8">

      {/* HEADER */}
      <div className="bg-white rounded-3xl shadow-lg p-8 mb-8 flex justify-between items-center">

        <div>
          <h1 className="text-4xl font-bold text-red-600">
            Admin Dashboard
          </h1>
          <p className="text-gray-500 mt-2">
            Manage RoomDekho system
          </p>
        </div>

        {/* ACTION BUTTONS */}
        <div className="flex gap-4">

          <button
            onClick={goBack}
            className="bg-gray-200 hover:bg-gray-300 px-5 py-2 rounded-xl font-semibold"
          >
            ⬅ Back
          </button>

          <button
            onClick={logout}
            className="bg-red-500 hover:bg-red-600 text-white px-5 py-2 rounded-xl font-semibold"
          >
            Logout
          </button>

        </div>

      </div>

      {/* STATS */}
      <div className="grid grid-cols-1 md:grid-cols-4 gap-6 mb-10">

        <Card title="Users" value={stats.users} />
        <Card title="Rooms" value={stats.rooms} />
        <Card title="Bookings" value={stats.bookings} />
        <Card title="Owner Requests" value={stats.requests} />

      </div>

      {/* MODULE BUTTONS */}
      <div className="grid md:grid-cols-2 gap-6">

        <Button
          title="Manage Users"
          click={() => navigate("/admin/users")}
        />

        <Button
          title="Manage Rooms"
          click={() => navigate("/admin/rooms")}
        />

        <Button
          title="Manage Bookings"
          click={() => navigate("/admin/bookings")}
        />

        <Button
          title="Owner Requests"
          click={() => navigate("/admin/requests")}
        />

      </div>

    </div>
  );
}

/* ================= UI COMPONENTS ================= */

function Card({ title, value }) {
  return (
    <div className="bg-white rounded-3xl shadow p-6">
      <p className="text-gray-500">{title}</p>
      <h2 className="text-4xl font-bold mt-3">{value}</h2>
    </div>
  );
}

function Button({ title, click }) {
  return (
    <button
      onClick={click}
      className="
        bg-white
        shadow
        rounded-3xl
        p-8
        text-xl
        font-bold
        hover:shadow-xl
        transition
      "
    >
      {title}
    </button>
  );
}