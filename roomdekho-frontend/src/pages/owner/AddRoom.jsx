import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../../api/axios";


export default function AddRoom(){


const navigate = useNavigate();



const [room,setRoom] = useState({

    title:"",
    location:"",
    rent:"",
    description:"",
    roomType:"",
    images:""

});




const handleChange=(e)=>{

    setRoom({

        ...room,

        [e.target.name]:e.target.value

    });

};





const handleSubmit=async(e)=>{

    e.preventDefault();


    try{


        await api.post("/rooms/add",{

            ...room,

            images: room.images
            .split(",")

        });



        alert("Room added successfully 🎉");


        navigate("/owner");


    }
    catch(error){

        console.log(error);

        alert(
            error.response?.data ||
            "Failed to add room"
        );

    }


};






return(


<div className="
min-h-screen

bg-gray-100

p-8
">





<div className="
max-w-3xl

mx-auto

bg-white

rounded-3xl

shadow-xl

p-10
">



<h1 className="
text-4xl

font-bold

text-indigo-600

mb-2
">

Add New Room

</h1>



<p className="
text-gray-500

mb-8
">

List your room and reach more users

</p>






<form

onSubmit={handleSubmit}

className="
space-y-5
"

>






<input

name="title"

placeholder="Room Title"

value={room.title}

onChange={handleChange}

className="
w-full

px-5

py-4

rounded-xl

border

outline-none

focus:ring-2

focus:ring-indigo-500

text-lg

"

/>






<input

name="location"

placeholder="Location"

value={room.location}

onChange={handleChange}

className="
w-full

px-5

py-4

rounded-xl

border

outline-none

focus:ring-2

focus:ring-indigo-500

text-lg

"

/>








<input

name="rent"

type="number"

placeholder="Monthly Rent"

value={room.rent}

onChange={handleChange}

className="
w-full

px-5

py-4

rounded-xl

border

outline-none

focus:ring-2

focus:ring-indigo-500

text-lg

"

/>








<select

name="roomType"

value={room.roomType}

onChange={handleChange}

className="
w-full

px-5

py-4

rounded-xl

border

outline-none

focus:ring-2

focus:ring-indigo-500

text-lg

"

>


<option value="">
Select Room Type
</option>


<option value="SINGLE">
Single Room
</option>


<option value="DOUBLE">
Double Room
</option>


<option value="APARTMENT">
Apartment
</option>


<option value="PG">
PG
</option>



</select>







<textarea

name="description"

placeholder="Room Description"

value={room.description}

onChange={handleChange}

rows="5"

className="
w-full

px-5

py-4

rounded-xl

border

outline-none

focus:ring-2

focus:ring-indigo-500

text-lg

"

/>







<input

name="images"

placeholder="Image URLs (comma separated)"

value={room.images}

onChange={handleChange}

className="
w-full

px-5

py-4

rounded-xl

border

outline-none

focus:ring-2

focus:ring-indigo-500

text-lg

"

/>






<button

type="submit"

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
"

>

Add Room

</button>




</form>



</div>



</div>



)


}