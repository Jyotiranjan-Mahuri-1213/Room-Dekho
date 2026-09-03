import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div className="min-h-screen flex flex-col bg-gradient-to-br from-indigo-600 via-purple-600 to-pink-500">

      {/* ================= NAVBAR ================= */}
      <nav className="
        sticky top-0 z-50
        flex justify-between items-center
        px-10 py-5

        bg-white
        shadow-lg
        border-b
      ">

        <h1 className="text-3xl font-bold text-indigo-600">
          🏠 RoomDekho
        </h1>

        <div className="space-x-4">

          <Link to="/login">
            <button className="
              px-6 py-2
              rounded-full
              bg-indigo-600
              text-white
              font-semibold
              hover:bg-indigo-700
              transition
            ">
              Login
            </button>
          </Link>

          <Link to="/register">
            <button className="
              px-6 py-2
              rounded-full
              border border-indigo-600
              text-indigo-600
              font-semibold
              hover:bg-indigo-600
              hover:text-white
              transition
            ">
              Register
            </button>
          </Link>

        </div>

      </nav>

      {/* ================= MAIN CONTENT (SCROLLABLE AREA) ================= */}
      <main className="flex-1 overflow-y-auto">

        {/* HERO */}
        <section className="
          flex flex-col md:flex-row
          items-center justify-between
          px-10 mt-20
        ">

          <div className="max-w-xl text-white">

            <h1 className="text-5xl font-extrabold leading-tight">
              Find Your Perfect Room With RoomDekho
            </h1>

            <p className="mt-6 text-lg text-gray-200">
              A smart room booking platform where users can find rooms,
              owners manage properties, and admins control everything.
            </p>

            <div className="mt-8">
              <Link to="/register">
                <button className="
                  bg-white text-indigo-600
                  px-8 py-3
                  rounded-full
                  font-bold
                  shadow-lg
                  hover:scale-105
                  transition
                ">
                  Get Started
                </button>
              </Link>
            </div>

          </div>

          {/* SIDE CARD */}
          <div className="
            mt-10 md:mt-0
            bg-white rounded-3xl
            p-8 shadow-2xl
            w-96
          ">

            <h2 className="text-2xl font-bold text-gray-800">
              Why RoomDekho?
            </h2>

            <div className="mt-5 space-y-4">

              <div className="bg-indigo-50 p-4 rounded-xl">
                🏠 Find verified rooms
              </div>

              <div className="bg-purple-50 p-4 rounded-xl">
                📅 Easy booking system
              </div>

              <div className="bg-pink-50 p-4 rounded-xl">
                ⭐ Review and rating system
              </div>

              <div className="bg-green-50 p-4 rounded-xl">
                🔐 Secure user management
              </div>

            </div>

          </div>

        </section>

        {/* ABOUT SECTION */}
        <section className="
          mt-24 bg-white
          rounded-t-3xl
          p-10
        ">

          <h2 className="
            text-3xl font-bold
            text-center text-gray-800
          ">
            About RoomDekho
          </h2>

          <p className="
            text-center mt-5
            text-gray-600
            max-w-3xl mx-auto
          ">
            RoomDekho is a complete room rental management system built by RoomDekho Communitys.
            It connects users, owners, and admins in one platform.
          </p>

        </section>

        {/* ================= FOOTER (NEW) ================= */}
        <footer className="
          bg-gray-900 text-white
          px-10 py-8
          text-center
        ">

          <h3 className="text-xl font-bold">
            RoomDekho
          </h3>

          <p className="text-gray-400 mt-2">
            Find your perfect stay anywhere, anytime.
          </p>

          <p className="text-gray-500 mt-4 text-sm">
            © {new Date().getFullYear()} RoomDekho. All rights reserved.
          </p>

        </footer>

      </main>
    </div>
  );
}