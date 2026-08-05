import { useEffect, useState } from "react";
import api from "../../api/axios";
import { useNavigate } from "react-router-dom";
import RoomCard from "../../components/RoomCard";

export default function UserDashboard() {

  const [rooms, setRooms] = useState([]);

  const [filters, setFilters] = useState({
    location: "",
    roomType: "",
    minRent: "",
    maxRent: ""
  });

  const navigate = useNavigate();

  const user = JSON.parse(localStorage.getItem("user"));

  // ================= LOAD ROOMS =================
  const loadRooms = async () => {
    try {

      let res;

      // CASE 1: ONLY LOCATION SEARCH
      if (filters.location && !filters.minRent && !filters.maxRent) {
        res = await api.get("/rooms/search", {
          params: { location: filters.location }
        });
      }

      // CASE 2: RENT FILTER ONLY
      else if (filters.minRent || filters.maxRent) {
        res = await api.get("/rooms/filter", {
          params: {
            min: filters.minRent || 0,
            max: filters.maxRent || 999999
          }
        });
      }

      // CASE 3: DEFAULT (ALL ROOMS)
      else {
        res = await api.get("/rooms");
      }

      let data = res.data;

      // LOCAL FILTER FOR ROOM TYPE (backend missing this filter)
      if (filters.roomType) {
        data = data.filter(r => r.roomType === filters.roomType);
      }

      setRooms(Array.isArray(data) ? data : []);

    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    loadRooms();
  }, []);

  // ================= SEARCH =================
  const handleSearch = () => {
    loadRooms();
  };

  const logout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <div className="min-h-screen bg-gray-100">

      {/* NAVBAR */}
      <nav className="sticky top-0 z-50 bg-white/80 backdrop-blur-lg border-b px-8 py-4 flex justify-between items-center shadow-sm">

        <div onClick={() => navigate("/user")} className="flex items-center gap-3 cursor-pointer">

          <div className="w-12 h-12 rounded-2xl bg-gradient-to-r from-indigo-600 to-purple-600 flex items-center justify-center text-white text-2xl">
            🏠
          </div>

          <div>
            <h1 className="text-3xl font-extrabold text-indigo-600">
              RoomDekho
            </h1>
            <p className="text-xs text-gray-500">Find your perfect stay</p>
          </div>

        </div>

        <div className="flex items-center gap-5">

          <button
            onClick={() => navigate("/become-owner")}
            className="bg-gradient-to-r from-purple-600 to-indigo-600 text-white px-5 py-2 rounded-xl"
          >
            Become Owner
          </button>

          <button
            onClick={() => navigate("/my-bookings")}
            className="bg-indigo-600 text-white px-6 py-3 rounded-2xl"
          >
            📖 My Bookings
          </button>

          <button
            onClick={logout}
            className="bg-red-500 text-white px-6 py-3 rounded-2xl"
          >
            Logout
          </button>

        </div>

      </nav>

      {/* HERO */}
      <div className="p-8">

        <div className="bg-gradient-to-r from-indigo-600 to-purple-600 p-10 rounded-3xl text-white mb-10">

          <h1 className="text-5xl font-bold mb-3">
            Find Your Perfect Room 🏠
          </h1>

          <p className="mb-6">
            Search by location, type and rent range
          </p>

          {/* SEARCH */}
          <div className="bg-white p-5 rounded-2xl grid md:grid-cols-4 gap-4">

            <input
              placeholder="Location"
              value={filters.location}
              onChange={(e) =>
                setFilters({ ...filters, location: e.target.value })
              }
              className="p-3 border rounded-xl"
            />

            <select
              value={filters.roomType}
              onChange={(e) =>
                setFilters({ ...filters, roomType: e.target.value })
              }
              className="p-3 border rounded-xl"
            >
              <option value="">All Types</option>
              <option value="SINGLE">SINGLE</option>
              <option value="DOUBLE">DOUBLE</option>
              <option value="FLAT">FLAT</option>
              <option value="PG">PG</option>
            </select>

            <input
              type="number"
              placeholder="Min Rent"
              value={filters.minRent}
              onChange={(e) =>
                setFilters({ ...filters, minRent: e.target.value })
              }
              className="p-3 border rounded-xl"
            />

            <input
              type="number"
              placeholder="Max Rent"
              value={filters.maxRent}
              onChange={(e) =>
                setFilters({ ...filters, maxRent: e.target.value })
              }
              className="p-3 border rounded-xl"
            />

          </div>

          <button
            onClick={handleSearch}
            className="mt-5 bg-white text-indigo-600 px-6 py-3 rounded-xl font-bold"
          >
            Search
          </button>

        </div>

        {/* ROOMS */}
        <h2 className="text-3xl font-bold mb-6">
          Available Rooms
        </h2>

        {rooms.length === 0 && (
          <div className="bg-white p-10 rounded-2xl text-center">
            No rooms available
          </div>
        )}

        <div className="grid md:grid-cols-3 gap-6">
          {rooms.map(room => (
            <RoomCard key={room.id} room={room} />
          ))}
        </div>

      </div>
    </div>
  );
}