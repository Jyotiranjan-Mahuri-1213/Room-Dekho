import {useEffect,useState} from "react";
import api from "../../api/axios";
import {useNavigate} from "react-router-dom";


export default function MyBookings(){


const [bookings,setBookings]=useState([]);

const navigate=useNavigate();



useEffect(()=>{

loadBookings();

},[]);



const loadBookings=async()=>{


try{


const user=
JSON.parse(localStorage.getItem("user"));


const res=
await api.get(
`/bookings/my?email=${user.email}`
);


setBookings(
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





return(


<div className="
min-h-screen
bg-gray-100
p-8
">


<h1 className="
text-3xl
font-bold
mb-6
">

My Bookings

</h1>




{

bookings.length===0

?

<div className="
bg-white
p-8
rounded-xl
">

No bookings found

</div>


:


<div className="
grid
gap-5
">


{

bookings.map(b=>(


<div

key={b.id}

className="
bg-white
p-6
rounded-xl
shadow
"


>


<p>
<b>Booking ID:</b> {b.id}
</p>


<p>
<b>Room ID:</b> {b.roomId}
</p>


<p>
<b>Status:</b>

<span className="text-indigo-600 ml-2">

{b.status}

</span>

</p>


<p>
<b>Payment:</b> {b.paymentStatus}
</p>



{

b.status==="PENDING"

&&


<button

onClick={()=>navigate(`/payment/${b.id}`)}

className="
mt-4
bg-yellow-500
text-white
px-5
py-2
rounded-xl
"

>

Pay Now

</button>


}



{

b.status==="APPROVED"

&&

<p className="text-blue-600 mt-3">

Waiting for owner confirmation

</p>


}




{

b.status==="BOOKED"

&&

<p className="
text-green-600
font-bold
mt-3
">

Booking Confirmed 🎉

</p>


}




</div>


))


}


</div>


}



</div>


)

}