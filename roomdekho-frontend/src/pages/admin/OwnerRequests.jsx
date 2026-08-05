import {useEffect,useState} from "react";
import api from "../../api/axios";


export default function OwnerRequests(){


const [requests,setRequests]=useState([]);




useEffect(()=>{

loadRequests();

},[]);





const loadRequests=async()=>{


try{

const res = await api.get("/owner-requests/all");

setRequests(
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






const approveRequest=async(id)=>{


try{


await api.put(
`/owner-requests/${id}/approve`
);


alert("Owner approved");


loadRequests();


}
catch(err){

console.log(err);

}

};







const rejectRequest=async(id)=>{


try{


await api.put(
`/owner-requests/${id}/reject`
);



alert("Request rejected");


loadRequests();



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

Owner Requests

</h1>








<div className="
grid
grid-cols-1
lg:grid-cols-2
gap-8
">



{

requests.map(req=>(



<div

key={req.id}

className="
bg-white
rounded-3xl
shadow-lg
p-6
"


>





<h2 className="
text-2xl
font-bold
mb-3
">

{req.name}

</h2>





<p className="text-gray-500">

📧 {req.email}

</p>


<p className="text-gray-500">

📞 {req.phone}

</p>


<p className="text-gray-500">

📍 {req.address}

</p>





<hr className="my-5"/>





<h3 className="
text-xl
font-bold
">

Room Details

</h3>




<p>

🏠 {req.roomTitle}

</p>


<p>

Type: {req.roomType}

</p>


<p>

Location: {req.location}

</p>


<p>

Rent: ₹{req.rent}

</p>




<p className="
mt-3
text-gray-600
">

{req.description}

</p>







<div className="
flex
gap-4
mt-6
">



<button

onClick={()=>approveRequest(req.id)}

className="
flex-1
bg-green-600
hover:bg-green-700
text-white
py-3
rounded-xl
font-semibold
"

>

Approve

</button>






<button

onClick={()=>rejectRequest(req.id)}

className="
flex-1
bg-red-500
hover:bg-red-600
text-white
py-3
rounded-xl
font-semibold
"

>

Reject

</button>




</div>






<div className="
mt-4
text-center
text-sm
font-semibold
">


Status:

<span className={

req.status==="APPROVED"

?

"text-green-600"

:

req.status==="REJECTED"

?

"text-red-600"

:

"text-yellow-600"

}

>

 {req.status}

</span>



</div>





</div>



))


}



</div>






</div>


)


}