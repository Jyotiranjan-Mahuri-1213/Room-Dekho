import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../../api/axios";


export default function ManageRooms(){


const [rooms,setRooms] = useState([]);

const navigate = useNavigate();





useEffect(()=>{

    loadRooms();

},[]);






const loadRooms=async()=>{


try{


const res = await api.get("/rooms/my");


setRooms(

Array.isArray(res.data)

?
res.data

:
[]

);



}
catch(error){

console.log(error);

}


};







const deleteRoom=async(id)=>{


const confirmDelete = window.confirm(
"Are you sure you want to delete this room?"
);


if(!confirmDelete)
return;



try{


await api.delete(`/rooms/${id}`);


alert("Room deleted");


loadRooms();


}
catch(error){

console.log(error);

alert("Delete failed");

}


};








return(


<div className="
min-h-screen

bg-gray-100

p-8
">





{/* HEADER */}


<div className="
flex

justify-between

items-center

mb-8
">


<div>


<h1 className="
text-4xl

font-bold

text-indigo-600
">

My Rooms

</h1>


<p className="
text-gray-500

mt-2
">

Manage your listed rooms

</p>


</div>





<button

onClick={()=>navigate("/owner/add-room")}

className="
bg-indigo-600

text-white

px-6

py-3

rounded-xl

font-semibold
"

>

➕ Add Room

</button>



</div>








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

No rooms added yet

</h2>


<p className="
text-gray-500

mt-2
">

Add your first room

</p>


</div>


}








<div className="
grid

grid-cols-1

md:grid-cols-3

gap-8
">






{

rooms.map(room=>(


<div

key={room.id}

className="
bg-white

rounded-3xl

overflow-hidden

shadow-lg

hover:shadow-xl

transition
"

>





<img


src={

room.images?.[0]

||

"https://images.unsplash.com/photo-1560185008-b033106af5c3"

}


className="
h-56

w-full

object-cover
"

/>






<div className="
p-6
">


<h2 className="
text-xl

font-bold

mb-2
">

{room.title}

</h2>





<p className="
text-gray-500
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






<div className="
flex

gap-3

mt-5
">



<button


onClick={()=>navigate(`/owner/edit-room/${room.id}`)}


className="
flex-1

border

border-indigo-600

text-indigo-600

py-2

rounded-xl

hover:bg-indigo-50
"

>

Edit

</button>







<button


onClick={()=>deleteRoom(room.id)}


className="
flex-1

bg-red-500

text-white

py-2

rounded-xl

hover:bg-red-600
"

>

Delete

</button>



</div>





</div>





</div>


))


}





</div>




</div>



)


}