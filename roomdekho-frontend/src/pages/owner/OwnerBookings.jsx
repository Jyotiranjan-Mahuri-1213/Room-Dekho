import { useEffect, useState } from "react";
import api from "../../api/axios";


export default function OwnerBookings(){


    const [bookings,setBookings]=useState([]);

    const [loading,setLoading]=useState(true);



    useEffect(()=>{

        loadBookings();

    },[]);



    const loadBookings=async()=>{

        try{

            const owner =
            JSON.parse(localStorage.getItem("user"));


            if(!owner){
                return;
            }


            const res = await api.get(
                `/bookings/owner?email=${owner.email}`
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

            console.log(
                "Owner booking error:",
                err
            );

        }
        finally{

            setLoading(false);

        }

    };





    const confirmBooking = async(id)=>{

        try{


            await api.put(
                `/bookings/approve/${id}`
            );


            alert(
                "Booking Confirmed Successfully"
            );


            loadBookings();


        }
        catch(err){

            console.log(
                "Confirm booking error",
                err
            );

            alert(
                "Unable to confirm booking"
            );

        }

    };





return(

<div className="min-h-screen bg-gray-100 p-8">


<h1 className="text-3xl font-bold mb-6 text-indigo-600">

🧾 Owner Bookings

</h1>



{
loading &&

<p>
Loading bookings...
</p>

}




{
!loading && bookings.length===0 &&

<div className="bg-white p-6 rounded-xl shadow text-center">

No bookings found

</div>

}





<div className="grid gap-6">


{

bookings.map((b)=>(


<div
key={b.id}
className="bg-white p-6 rounded-2xl shadow"
>


<h2 className="text-xl font-bold">

Booking ID : {b.id}

</h2>



<p>
<b>User Email:</b> {b.userEmail}
</p>


<p>
<b>Room ID:</b> {b.roomId}
</p>



<p>

<b>Payment:</b>

<span className="ml-2 text-green-600 font-semibold">

{b.paymentStatus}

</span>

</p>




<p>

<b>Status:</b>

<span className="ml-2 text-indigo-600 font-semibold">

{b.status}

</span>

</p>






{/* OWNER CONFIRM */}

{

b.status==="APPROVED" &&


<button

onClick={()=>confirmBooking(b.id)}

className="
mt-4
bg-green-600
text-white
px-5
py-2
rounded-xl
hover:bg-green-700
"

>

Confirm Booking

</button>


}






{

b.status==="BOOKED" &&

<p className="
mt-4
text-green-600
font-bold
">

✔ Booking Confirmed

</p>


}



</div>


))

}



</div>



</div>


);


}