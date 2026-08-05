import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../api/axios";

export default function RoomDetails() {

    const { id } = useParams();
    const navigate = useNavigate();

    const [room, setRoom] = useState(null);
    const [currentImage, setCurrentImage] = useState(0);


    useEffect(() => {
        loadRoom();
    }, []);


    const loadRoom = async () => {

        try {

            const res = await api.get(`/rooms/${id}`);

            setRoom(res.data);

        } catch(err){

            console.log("Room loading error",err);

        }

    };


    // AUTO SLIDER

    useEffect(()=>{

        if(!room?.images || room.images.length <= 1)
            return;


        const timer=setInterval(()=>{

            setCurrentImage(
                prev => 
                (prev+1)%room.images.length
            );


        },3000);


        return ()=>clearInterval(timer);


    },[room]);





    // BOOK ROOM

    const bookRoom = async()=>{

        try{

            const user =
            JSON.parse(localStorage.getItem("user"));


            if(!user){

                alert("Please login first");
                navigate("/login");
                return;

            }


            const res = await api.post(
                "/bookings/create",
                {
                    roomId:room.id,
                    userEmail:user.email,
                    bookingDate:new Date()
                }
            );


            console.log(res.data);


            navigate(
                `/payment/${res.data.id}`
            );


        }
        catch(err){

            console.log(
                "Booking error",
                err
            );

            alert("Booking failed");

        }

    };




    if(!room){

        return(
            <div className="min-h-screen flex items-center justify-center">
                Loading...
            </div>
        );

    }



return (

<div className="min-h-screen bg-gray-100 p-8">


{/* BACK BUTTON */}

<button

onClick={()=>navigate(-1)}

className="
mb-5
bg-gray-800
text-white
px-5
py-2
rounded-xl
"

>

← Back

</button>



<div className="
max-w-6xl
mx-auto
bg-white
rounded-3xl
shadow-xl
overflow-hidden
">


{/* MAIN IMAGE */}

<img

src={
room.images?.[currentImage]
||
"https://images.unsplash.com/photo-1560185008-b033106af5c3"
}

className="
w-full
h-[450px]
object-cover
"

/>



{/* IMAGE GALLERY */}

<div className="
flex
gap-4
p-5
overflow-x-auto
">


{

room.images?.map((img,index)=>(


<img

key={index}

src={img}

onClick={()=>setCurrentImage(index)}

className={`
h-24
w-32
object-cover
rounded-xl
cursor-pointer
border-4

${
currentImage===index
?
"border-indigo-600"
:
"border-transparent"
}

`}

/>


))

}


</div>





<div className="p-8">


<h1 className="
text-4xl
font-bold
">

{room.title}

</h1>



<p className="
text-gray-500
text-lg
mt-3
">

📍 {room.location}

</p>



<h2 className="
text-3xl
font-bold
text-indigo-600
mt-5
">

₹{room.rent}

<span className="text-gray-500 text-base">
 /month
</span>


</h2>



<p className="
mt-5
text-gray-600
">

{room.description}

</p>




<button

onClick={bookRoom}

className="
mt-8
bg-indigo-600
text-white
px-10
py-4
rounded-xl
font-semibold
"

>

Book This Room

</button>



</div>


</div>


</div>

);


}