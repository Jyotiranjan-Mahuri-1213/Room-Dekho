import { useEffect, useState } from "react";
import api from "../../api/axios";


export default function Rooms(){


    const [rooms,setRooms] = useState([]);



    useEffect(()=>{

        loadRooms();

    },[]);




    const loadRooms = async()=>{

        try{

            const res = await api.get("/admin/rooms");


            setRooms(
                Array.isArray(res.data)
                ?
                res.data
                :
                []
            );


        }
        catch(err){

            console.log(err);

        }

    };





    const deleteRoom = async(id)=>{


        const confirmDelete =
        window.confirm(
            "Are you sure you want to delete this room?"
        );


        if(!confirmDelete)
        return;



        try{


            await api.delete(`/admin/rooms/${id}`);


            alert("Room deleted successfully");


            loadRooms();


        }
        catch(err){

            console.log(err);

        }


    };







    return(


    <div className="
    min-h-screen
    bg-gray-100
    p-8
    ">



        <h1 className="
        text-4xl
        font-bold
        text-red-600
        mb-8
        ">

            Manage Rooms

        </h1>





        {
            rooms.length===0 &&

            <div className="
            bg-white
            rounded-3xl
            shadow
            p-10
            text-center
            ">

                <h2 className="
                text-xl
                font-bold
                ">

                No Rooms Available

                </h2>


                <p className="
                text-gray-500
                mt-2
                ">

                Rooms added by owners will appear here

                </p>


            </div>
        }






        <div className="
        grid
        grid-cols-1
        md:grid-cols-2
        lg:grid-cols-3
        gap-8
        ">



        {
            rooms.map(room=>(


            <div

            key={room.id}

            className="
            bg-white
            rounded-3xl
            shadow-lg
            overflow-hidden
            hover:shadow-2xl
            transition
            "


            >



                {/* IMAGE */}

                <img
    src={
        room.images?.[0]?.imageUrl
        ||
        "https://images.unsplash.com/photo-1560185008-b033106af5c3"
    }
    className="
    w-full
    h-56
    object-cover
    "
    alt={room.title}
/>

                <div className="
                p-6
                ">


                    <h2 className="
                    text-xl
                    font-bold
                    ">

                    {room.title}

                    </h2>





                    <p className="
                    text-gray-500
                    mt-2
                    ">

                    📍 {room.location}

                    </p>

                    <p className="
                    text-indigo-600
                    font-bold
                    text-lg
                    mt-3
                    ">

                    ₹{room.rent}/month

                    </p>

                    <p className="
                    text-sm
                    text-gray-500
                    mt-2
                    ">

                    Type : {room.roomType}

                    </p>

                    <button

                    onClick={()=>deleteRoom(room.id)}

                    className="
                    mt-5
                    w-full
                    bg-red-500
                    hover:bg-red-600
                    text-white
                    py-3
                    rounded-xl
                    font-semibold
                    transition
                    "

                    >

                    Delete Room

                    </button>

                </div>

            </div>

            ))

        }

        </div>

    </div>


    )

}