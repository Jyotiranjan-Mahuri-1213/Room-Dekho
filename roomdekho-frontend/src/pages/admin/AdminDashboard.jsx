
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
      const [statsResponse, requestsResponse] = await Promise.all([
        api.get("/admin/stats"),
        api.get("/owner-requests/all")
      ]);

      const requests = getArray(requestsResponse.data);

      setStats({
        users: statsResponse.data.users,
        rooms: statsResponse.data.rooms,
        bookings: statsResponse.data.bookings,
        requests: requests.length
      });
    } catch (err) {
      console.log("Admin Dashboard Error:", err);
    }
  };

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
    <div className="min-h-screen bg-slate-100 p-4 md:p-8">

      {/* ================= HEADER ================= */}
      <div className="bg-white rounded-3xl shadow-sm border border-slate-200 p-6 md:p-8 mb-8">

        <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-6">

          {/* TITLE */}
          <div className="flex items-center gap-4">

            <div className="w-14 h-14 rounded-2xl bg-red-600 flex items-center justify-center shadow-lg">
              <span className="text-2xl text-white">⚡</span>
            </div>

            <div>
              <h1 className="text-3xl md:text-4xl font-extrabold text-slate-800">
                Admin Dashboard
              </h1>

              <p className="text-slate-500 mt-1">
                Manage and monitor your RoomDekho system
              </p>
            </div>

          </div>

          {/* ACTION BUTTONS */}
          <div className="flex gap-3">

            <button
              onClick={goBack}
              className="
                flex items-center gap-2
                bg-slate-100
                hover:bg-slate-200
                text-slate-700
                px-5 py-3
                rounded-xl
                font-semibold
                transition-all
                duration-200
              "
            >
              <span>←</span>
              Back
            </button>

            <button
              onClick={logout}
              className="
                flex items-center gap-2
                bg-red-600
                hover:bg-red-700
                text-white
                px-5 py-3
                rounded-xl
                font-semibold
                shadow-md
                hover:shadow-lg
                transition-all
                duration-200
              "
            >
              <span>↪</span>
              Logout
            </button>

          </div>

        </div>

      </div>

      {/* ================= STATS TITLE ================= */}
      <div className="mb-5">

        <h2 className="text-xl font-bold text-slate-800">
          System Overview
        </h2>

        <p className="text-sm text-slate-500 mt-1">
          Current RoomDekho platform statistics
        </p>

      </div>

      {/* ================= STATS ================= */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-5 mb-10">

        <Card
          title="Total Users"
          value={stats.users}
          icon="👥"
          description="Registered users"
        />

        <Card
          title="Total Rooms"
          value={stats.rooms}
          icon="🏠"
          description="Listed rooms"
        />

        <Card
          title="Total Bookings"
          value={stats.bookings}
          icon="📅"
          description="Room bookings"
        />

        <Card
          title="Owner Requests"
          value={stats.requests}
          icon="📋"
          description="Pending requests"
        />

      </div>

      {/* ================= MANAGEMENT ================= */}
      <div className="mb-5">

        <h2 className="text-xl font-bold text-slate-800">
          Management
        </h2>

        <p className="text-sm text-slate-500 mt-1">
          Manage different sections of RoomDekho
        </p>

      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-5">

        <Button
          title="Manage Users"
          description="View and manage registered users"
          icon="👥"
          click={() => navigate("/admin/users")}
        />

        <Button
          title="Manage Rooms"
          description="View and manage listed rooms"
          icon="🏠"
          click={() => navigate("/admin/rooms")}
        />

        <Button
          title="Manage Bookings"
          description="View and manage room bookings"
          icon="📅"
          click={() => navigate("/admin/bookings")}
        />

        <Button
          title="Owner Requests"
          description="Review owner registration requests"
          icon="📋"
          click={() => navigate("/admin/requests")}
        />

      </div>

    </div>
  );
}

/* ================= STAT CARD ================= */

function Card({ title, value, icon, description }) {
  return (
    <div
      className="
        group
        bg-white
        border border-slate-200
        rounded-3xl
        p-6
        shadow-sm
        hover:shadow-xl
        hover:-translate-y-1
        transition-all
        duration-300
      "
    >

      <div className="flex items-start justify-between">

        <div>

          <p className="text-sm font-medium text-slate-500">
            {title}
          </p>

          <h2 className="text-4xl font-extrabold text-slate-800 mt-3">
            {value}
          </h2>

          <p className="text-xs text-slate-400 mt-2">
            {description}
          </p>

        </div>

        <div
          className="
            w-12 h-12
            rounded-2xl
            bg-red-50
            flex items-center justify-center
            text-2xl
            group-hover:scale-110
            transition-transform
            duration-300
          "
        >
          {icon}
        </div>

      </div>

    </div>
  );
}

/* ================= MANAGEMENT BUTTON ================= */

function Button({ title, description, icon, click }) {
  return (
    <button
      onClick={click}
      className="
        group
        bg-white
        border border-slate-200
        rounded-3xl
        p-6
        text-left
        shadow-sm
        hover:shadow-xl
        hover:-translate-y-1
        transition-all
        duration-300
        w-full
      "
    >

      <div className="flex items-center justify-between">

        <div className="flex items-center gap-4">

          <div
            className="
              w-14 h-14
              rounded-2xl
              bg-slate-100
              group-hover:bg-red-50
              flex items-center justify-center
              text-2xl
              group-hover:scale-110
              transition-all
              duration-300
            "
          >
            {icon}
          </div>

          <div>

            <h3 className="text-lg font-bold text-slate-800">
              {title}
            </h3>

            <p className="text-sm text-slate-500 mt-1">
              {description}
            </p>

          </div>

        </div>

        <span
          className="
            text-xl
            text-slate-400
            group-hover:text-red-600
            group-hover:translate-x-1
            transition-all
            duration-300
          "
        >
          →
        </span>

      </div>

    </button>
  );
}

