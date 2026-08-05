import {useEffect,useState} from "react";
import api from "../../api/axios";


export default function Users(){


const [users,setUsers]=useState([]);




useEffect(()=>{

loadUsers();

},[]);





const loadUsers=async()=>{


try{


const res=await api.get("/admin/users");


setUsers(
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







const deleteUser=async(id)=>{


const confirmDelete =
window.confirm(
"Are you sure you want to delete this user?"
);


if(!confirmDelete)
return;



try{


await api.delete(
`/admin/users/${id}`
);



alert("User deleted successfully");


loadUsers();


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

Manage Users

</h1>






<div className="
bg-white
rounded-3xl
shadow-lg
overflow-hidden
">





<table className="
w-full
text-left
">



<thead className="
bg-gray-50
">

<tr>


<th className="
p-5
">

Name

</th>


<th className="
p-5
">

Email

</th>



<th className="
p-5
">

Phone

</th>


<th className="
p-5
">

Role

</th>


<th className="
p-5
">

Action

</th>


</tr>

</thead>







<tbody>


{


users.map(user=>(



<tr

key={user.id}

className="
border-t
hover:bg-gray-50
transition
"

>


<td className="
p-5
font-semibold
">

{user.name}

</td>




<td className="
p-5
text-gray-600
">

{user.email}

</td>





<td className="
p-5
">

{user.phone}

</td>





<td className="
p-5
">


<span

className={

`
px-4
py-2
rounded-full
text-sm
font-semibold

${
user.role==="ADMIN"

?
"bg-red-100 text-red-600"

:

user.role==="OWNER"

?
"bg-green-100 text-green-600"

:

"bg-blue-100 text-blue-600"

}

`

}

>

{user.role}

</span>


</td>







<td className="
p-5
">


<button

onClick={()=>deleteUser(user.id)}

className="
bg-red-500
hover:bg-red-600
text-white
px-5
py-2
rounded-xl
"

>

Delete

</button>


</td>





</tr>



))


}



</tbody>






</table>






</div>





</div>


)


}