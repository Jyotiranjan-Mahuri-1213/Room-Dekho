import { useState } from "react";
import api from "../../api/axios";
import { useNavigate } from "react-router-dom";

export default function BecomeOwner() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    name: "",
    phone: "",
    address: "",
    roomTitle: "",
    roomType: "SINGLE",
    location: "",
    rent: "",
    description: ""
  });

  const [images, setImages] = useState([]);

  // ================= FORM INPUT =================
  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  // ================= FILE HANDLING =================
  const handleFiles = (files) => {
    const imageFiles = files.filter(file => file.type.startsWith("image/"));

    imageFiles.forEach(file => {
      const reader = new FileReader();

      reader.onload = () => {
        setImages(prev => [...prev, reader.result]);
      };

      reader.readAsDataURL(file);
    });
  };

  const handleDrop = (e) => {
    e.preventDefault();
    const files = Array.from(e.dataTransfer.files);
    handleFiles(files);
  };

  const removeImage = (index) => {
    setImages(images.filter((_, i) => i !== index));
  };

  // ================= SUBMIT =================
  const submitRequest = async () => {
    try {
      await api.post("/owner-requests/requests", {
        ...form,
        image1: images[0] || "",
        image2: images[1] || "",
        image3: images[2] || ""
      });

      alert("Request sent successfully 🚀");
      navigate("/user");
    } catch (err) {
      alert(err.response?.data || "Request failed");
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 p-8">

      <div className="max-w-3xl mx-auto bg-white p-8 rounded-3xl shadow-lg">

        {/* TITLE */}
        <h1 className="text-3xl font-bold mb-6 text-indigo-600">
          Become an Owner
        </h1>

        {/* PERSONAL DETAILS */}
        <h2 className="font-semibold mb-3">Personal Details</h2>

        <div className="grid gap-4 mb-6">

          <input
            name="name"
            placeholder="Full Name"
            onChange={handleChange}
            className="p-3 border rounded-xl"
          />

          <input
            name="phone"
            placeholder="Phone"
            onChange={handleChange}
            className="p-3 border rounded-xl"
          />

          <input
            name="address"
            placeholder="Address"
            onChange={handleChange}
            className="p-3 border rounded-xl"
          />

        </div>

        {/* ROOM DETAILS */}
        <h2 className="font-semibold mb-3">Room Details</h2>

        <div className="grid gap-4 mb-6">

          <input
            name="roomTitle"
            placeholder="Room Title"
            onChange={handleChange}
            className="p-3 border rounded-xl"
          />

          <select
            name="roomType"
            onChange={handleChange}
            className="p-3 border rounded-xl"
          >
            <option value="SINGLE">SINGLE</option>
            <option value="DOUBLE">DOUBLE</option>
            <option value="FLAT">FLAT</option>
            <option value="PG">PG</option>
          </select>

          <input
            name="location"
            placeholder="Location"
            onChange={handleChange}
            className="p-3 border rounded-xl"
          />

          <input
            name="rent"
            type="number"
            placeholder="Rent"
            onChange={handleChange}
            className="p-3 border rounded-xl"
          />

          <textarea
            name="description"
            placeholder="Description"
            onChange={handleChange}
            className="p-3 border rounded-xl"
          />

        </div>

        {/* IMAGES DRAG & DROP */}
        <h2 className="font-semibold mb-3">Images</h2>

        <div
          onDrop={handleDrop}
          onDragOver={(e) => e.preventDefault()}
          className="
            border-2
            border-dashed
            border-indigo-400
            rounded-2xl
            p-8
            text-center
            bg-indigo-50
            cursor-pointer
          "
        >

          <p className="text-gray-600 font-medium">
            Drag & Drop images here
          </p>

          <p className="text-sm text-gray-400 mt-1">
            or select multiple images
          </p>

          <input
            type="file"
            multiple
            accept="image/*"
            onChange={(e) => handleFiles(Array.from(e.target.files))}
            className="mt-4"
          />

        </div>

        {/* PREVIEW */}
        {images.length > 0 && (
          <div className="grid grid-cols-3 gap-3 mt-5">

            {images.map((img, index) => (
              <div key={index} className="relative">

                <img
                  src={img}
                  className="h-24 w-full object-cover rounded-xl shadow"
                />

                <button
                  onClick={() => removeImage(index)}
                  className="
                    absolute
                    top-1
                    right-1
                    bg-red-500
                    text-white
                    text-xs
                    px-2
                    rounded-full
                  "
                >
                  ✕
                </button>

              </div>
            ))}

          </div>
        )}

        {/* SUBMIT */}
        <button
          onClick={submitRequest}
          className="
            w-full
            mt-6
            bg-indigo-600
            text-white
            py-3
            rounded-xl
            font-semibold
            hover:bg-indigo-700
            transition
          "
        >
          Submit Request
        </button>

      </div>
    </div>
  );
}