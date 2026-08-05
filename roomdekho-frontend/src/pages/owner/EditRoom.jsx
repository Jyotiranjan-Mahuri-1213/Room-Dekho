import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../../api/axios";

export default function EditRoom() {

  const { id } = useParams();
  const navigate = useNavigate();

  const [room, setRoom] = useState({
    title: "",
    location: "",
    rent: "",
    description: "",
    images: []
  });

  // ================= LOAD ROOM =================
  useEffect(() => {
    loadRoom();
  }, []);

  const loadRoom = async () => {
    try {
      const res = await api.get(`/rooms/${id}`);

      setRoom({
        title: res.data.title,
        location: res.data.location,
        rent: res.data.rent,
        description: res.data.description,
        images: res.data.images || []
      });

    } catch (err) {
      console.log(err);
    }
  };

  // ================= INPUT CHANGE =================
  const handleChange = (e) => {
    setRoom({
      ...room,
      [e.target.name]: e.target.value
    });
  };

  // ================= FILE TO BASE64 =================
  const convertToBase64 = (file) => {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = error => reject(error);
    });
  };

  // ================= HANDLE DROP =================
  const handleDrop = async (e) => {
    e.preventDefault();

    const files = Array.from(e.dataTransfer.files);

    const imagesBase64 = await Promise.all(
      files.map(file => convertToBase64(file))
    );

    setRoom({
      ...room,
      images: [...room.images, ...imagesBase64]
    });
  };

  // ================= HANDLE FILE SELECT =================
  const handleFileSelect = async (e) => {
    const files = Array.from(e.target.files);

    const imagesBase64 = await Promise.all(
      files.map(file => convertToBase64(file))
    );

    setRoom({
      ...room,
      images: [...room.images, ...imagesBase64]
    });
  };

  // ================= REMOVE IMAGE =================
  const removeImage = (index) => {
    const updated = room.images.filter((_, i) => i !== index);

    setRoom({
      ...room,
      images: updated
    });
  };

  // ================= UPDATE ROOM =================
  const updateRoom = async (e) => {
    e.preventDefault();

    try {
      await api.put(`/rooms/${id}`, {
        title: room.title,
        location: room.location,
        rent: room.rent,
        description: room.description,
        images: room.images
      });

      alert("Room updated successfully 🚀");
      navigate("/owner/rooms");

    } catch (err) {
      console.log(err);
      alert("Update failed");
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 p-8">

      <div className="max-w-3xl mx-auto bg-white rounded-3xl shadow-xl p-8">

        <h1 className="text-3xl font-bold text-indigo-600 mb-8">
          Edit Room
        </h1>

        <form onSubmit={updateRoom} className="space-y-5">

          {/* TITLE */}
          <input
            name="title"
            value={room.title}
            onChange={handleChange}
            className="w-full p-4 border rounded-xl"
            placeholder="Room title"
          />

          {/* LOCATION */}
          <input
            name="location"
            value={room.location}
            onChange={handleChange}
            className="w-full p-4 border rounded-xl"
            placeholder="Location"
          />

          {/* RENT */}
          <input
            name="rent"
            value={room.rent}
            onChange={handleChange}
            className="w-full p-4 border rounded-xl"
            placeholder="Rent"
          />

          {/* DESCRIPTION */}
          <textarea
            name="description"
            value={room.description}
            onChange={handleChange}
            className="w-full p-4 border rounded-xl h-32"
            placeholder="Description"
          />

          {/* ================= DRAG & DROP ================= */}
          <div>
            <h2 className="font-bold mb-3">Upload Images</h2>

            <div
              onDrop={handleDrop}
              onDragOver={(e) => e.preventDefault()}
              className="
                border-2 border-dashed border-indigo-400
                rounded-xl p-8 text-center
                bg-indigo-50 cursor-pointer
              "
            >
              <p className="text-gray-600">
                Drag & Drop images here
              </p>

              <p className="text-sm text-gray-500 mt-2">
                OR click to select files
              </p>

              <input
                type="file"
                multiple
                accept="image/*"
                onChange={handleFileSelect}
                className="mt-4"
              />
            </div>
          </div>

          {/* ================= IMAGE PREVIEW ================= */}
          <div>
            <h2 className="font-bold mb-2">Existing Images</h2>

            <div className="grid grid-cols-2 gap-3">
              {room.images.map((img, index) => (
                <div key={index} className="relative">

                  <img
                    src={img}
                    className="w-full h-32 object-cover rounded-xl border"
                  />

                  <button
                    type="button"
                    onClick={() => removeImage(index)}
                    className="absolute top-2 right-2 bg-red-500 text-white px-2 rounded"
                  >
                    X
                  </button>

                </div>
              ))}
            </div>
          </div>

          {/* BUTTON */}
          <button
            type="submit"
            className="w-full bg-indigo-600 hover:bg-indigo-700 text-white py-4 rounded-xl font-bold"
          >
            Update Room
          </button>

        </form>

      </div>
    </div>
  );
}