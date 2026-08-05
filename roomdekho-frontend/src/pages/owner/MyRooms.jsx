import {useEffect,useState} from "react";
import api from "../../api/axios";
import {useNavigate} from "react-router-dom";


export default function MyRooms(){


const [rooms,setRooms]=useState([]);

const navigate=useNavigate();




useEffect(()=>{

loadRooms();

},[]);




const loadRooms=async()=>{

try{

const res=await api.get("/rooms/owner");

setRooms(res.data);

}
catch(err){

console.log(err);

}

};





const deleteRoom=async(id)=>{


if(!window.confirm("Delete this room?"))
return;


try{


await api.delete(`/rooms/${id}`);


alert("Room deleted");


loadRooms();


}
catch(err){

alert("Delete failed");

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
text-indigo-600
mb-8
">

My Rooms

</h1>



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
shadow-lg
overflow-hidden
"


>


<img

src={
room.image1 ||
"https://images.unsplash.com/photo-1560185008-b033106af5c3"
}

className="
h-56
w-full
object-cover
"

/>



<div className="p-6">


<h2 className="
text-xl
font-bold
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
bg-indigo-600
text-white
py-2
rounded-xl
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