import { useState } from "react";
import api from "../api/axios";
import { useNavigate, Link } from "react-router-dom";


export default function Login(){

    const [email,setEmail] = useState("");
    const [password,setPassword] = useState("");
    const [loading,setLoading] = useState(false);

    const navigate = useNavigate();



    const handleLogin = async(e)=>{

        e.preventDefault();

        setLoading(true);


        try{

            const response = await api.post("/users/login",{
                email,
                password
            });


            const token = response.data.token;
            const user = response.data.user;


            // save login details

            localStorage.setItem(
                "token",
                token
            );


            localStorage.setItem(
                "user",
                JSON.stringify(user)
            );



            // role based navigation

            if(user.role==="ADMIN"){

                navigate("/admin");

            }
            else if(user.role==="OWNER"){

                navigate("/owner");

            }
            else{

                navigate("/user");

            }



        }
        catch(error){

            alert(
                error.response?.data ||
                "Login failed"
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
from-indigo-600
via-purple-600
to-pink-500
">


<div className="
bg-white
w-full
max-w-md
rounded-3xl
shadow-2xl
p-8
">


{/* Logo */}

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

Login to continue

</p>



<form
onSubmit={handleLogin}
className="
mt-8
space-y-5
"
>



{/* Email */}

<div>

<label className="
text-gray-700
font-medium
">

Email

</label>


<input

type="email"

placeholder="Enter your email"

value={email}

onChange={(e)=>setEmail(e.target.value)}

className="
w-full
mt-2
px-5
py-4
rounded-xl
border
border-gray-300
outline-none
focus:ring-2
focus:ring-indigo-500
text-lg
"

/>

</div>




{/* Password */}


<div>


<label className="
text-gray-700
font-medium
">

Password

</label>



<input

type="password"

placeholder="Enter password"

value={password}

onChange={(e)=>setPassword(e.target.value)}

className="
w-full
mt-2
px-5
py-4
rounded-xl
border
border-gray-300
outline-none
focus:ring-2
focus:ring-indigo-500
text-lg
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
transition
shadow-lg
"

>


{
loading
?
"Logging in..."
:
"Login"
}


</button>



</form>




<div className="
text-center
mt-6
text-gray-600
">


Don't have an account?


<Link
to="/register"
className="
text-indigo-600
font-semibold
ml-2
hover:underline
"
>

Register

</Link>


</div>



</div>


</div>


);


}