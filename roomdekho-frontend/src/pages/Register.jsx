import { useState } from "react";
import api from "../api/axios";
import { useNavigate, Link } from "react-router-dom";


export default function Register(){

    const navigate = useNavigate();


    const [form,setForm] = useState({

        name:"",
        email:"",
        phone:"",
        password:""

    });


    const [loading,setLoading] = useState(false);



    const handleChange=(e)=>{

        setForm({

            ...form,

            [e.target.name]:e.target.value

        });

    };



    const handleRegister=async(e)=>{

        e.preventDefault();

        setLoading(true);


        try{


            await api.post(
                "/users/register",
                form
            );


            alert(
                "Registration successful"
            );


            navigate("/login");


        }
        catch(error){

            alert(
                error.response?.data ||
                "Registration failed"
            );

        }
        finally{

            setLoading(false);

        }

    };



return(

<div className="
min-h-screen
flex
items-center
justify-center
bg-gradient-to-br
from-purple-600
via-indigo-600
to-blue-500
">


<div className="
bg-white
w-full
max-w-lg
rounded-3xl
shadow-2xl
p-8
">


<h1 className="
text-3xl
font-bold
text-center
text-indigo-600
">

🏠 RoomDekho

</h1>


<p className="
text-center
text-gray-500
mt-2
">

Create your account

</p>




<form

onSubmit={handleRegister}

className="
mt-8
space-y-5
"

>



{/* Name */}

<div>

<label className="font-medium text-gray-700">

Full Name

</label>


<input

name="name"

value={form.name}

onChange={handleChange}

placeholder="Enter your name"

className="
w-full
mt-2
px-5
py-4
rounded-xl
border
border-gray-300
text-lg
outline-none
focus:ring-2
focus:ring-indigo-500
"

/>


</div>





{/* Email */}

<div>

<label className="font-medium text-gray-700">

Email

</label>


<input

name="email"

type="email"

value={form.email}

onChange={handleChange}

placeholder="Enter email address"

className="
w-full
mt-2
px-5
py-4
rounded-xl
border
border-gray-300
text-lg
outline-none
focus:ring-2
focus:ring-indigo-500
"

/>


</div>





{/* Phone */}

<div>

<label className="font-medium text-gray-700">

Phone Number

</label>


<input

name="phone"

value={form.phone}

onChange={handleChange}

placeholder="Enter phone number"

className="
w-full
mt-2
px-5
py-4
rounded-xl
border
border-gray-300
text-lg
outline-none
focus:ring-2
focus:ring-indigo-500
"

/>


</div>






{/* Password */}

<div>

<label className="font-medium text-gray-700">

Password

</label>


<input

name="password"

type="password"

value={form.password}

onChange={handleChange}

placeholder="Create password"

className="
w-full
mt-2
px-5
py-4
rounded-xl
border
border-gray-300
text-lg
outline-none
focus:ring-2
focus:ring-indigo-500
"

/>


</div>






<button

disabled={loading}

className="
w-full
bg-indigo-600
hover:bg-indigo-700
text-white
py-4
rounded-xl
font-bold
text-lg
shadow-lg
transition
"

>


{
loading
?
"Creating account..."
:
"Register"
}


</button>



</form>




<p className="
text-center
mt-6
text-gray-600
">


Already have an account?


<Link

to="/login"

className="
text-indigo-600
font-semibold
ml-2
hover:underline
"

>

Login

</Link>


</p>




</div>


</div>


);

}