import { BrowserRouter, Routes, Route } from "react-router-dom";


import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";


import UserDashboard from "./pages/user/UserDashboard";
import OwnerDashboard from "./pages/owner/OwnerDashboard";
import AdminDashboard from "./pages/admin/AdminDashboard";
import RoomDetails from "./pages/RoomDetails";
import MyBookings from "./pages/user/MyBookings";
import AddRoom from "./pages/owner/AddRoom";
import ManageRooms from "./pages/owner/ManageRooms";
import MyRooms from "./pages/owner/MyRooms";
import EditRoom from "./pages/owner/EditRoom";
import OwnerRequests from "./pages/admin/OwnerRequests";
import AdminUsers from "./pages/admin/Users";
import AdminRooms from "./pages/admin/Rooms";
import AdminBookings from "./pages/admin/Bookings";
import BecomeOwner from "./pages/user/BecomeOwner";
import PaymentPage from "./pages/payment/PaymentPage";
import OwnerBookings from "./pages/owner/OwnerBookings";

function App() {


  return (

    <BrowserRouter>


      <Routes>


        {/* PUBLIC PAGES */}

        <Route 
          path="/" 
          element={<Home />} 
        />


        <Route 
          path="/login" 
          element={<Login />} 
        />


        <Route 
          path="/register" 
          element={<Register />} 
        />



        {/* ROLE DASHBOARDS */}


        <Route
          path="/user"
          element={<UserDashboard />}
        />


        <Route
          path="/owner"
          element={<OwnerDashboard />}
        />


        <Route
          path="/admin"
          element={<AdminDashboard />}
        />

        <Route
 path="/room/:id"
 element={<RoomDetails />}
/>
        <Route
 path="/my-bookings"
 element={<MyBookings />}
/>

<Route
path="/owner/add-room"
element={<AddRoom />}
/>

<Route
path="/owner/rooms"
element={<ManageRooms />}
/>

<Route
path="/owner/rooms"
element={<MyRooms/>}
/>

<Route

path="/owner/edit-room/:id"

element={<EditRoom/>}

/>
<Route
path="/admin/requests"
element={<OwnerRequests/>}
/>

<Route
 path="/admin/users"
 element={<AdminUsers />}
/>

<Route
 path="/admin/rooms"
 element={<AdminRooms />}
/>

<Route
 path="/admin/bookings"
 element={<AdminBookings />}
/>

<Route path="/become-owner" element={<BecomeOwner />} />
<Route path="/payment/:id" element={<PaymentPage />} />
<Route
  path="/owner/bookings"
  element={<OwnerBookings />}
/>


      </Routes>


    </BrowserRouter>

  );

}


export default App;