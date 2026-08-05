import { useEffect, useState } from "react";
import api from "../../api/axios";


export default function Bookings(){


    const [bookings,setBookings] = useState([]);



    useEffect(()=>{

        loadBookings();

    },[]);






    const loadBookings = async()=>{


        try{


            const res = await api.get("/admin/bookings");


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
        text-4xl
        font-bold
        text-red-600
        mb-8
        ">

        Manage Bookings

        </h1>






        {
            bookings.length===0 &&


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

                No Bookings Found

                </h2>


                <p className="
                text-gray-500
                mt-2
                ">

                User bookings will appear here

                </p>


            </div>


        }









        <div className="
        grid
        grid-cols-1
        lg:grid-cols-2
        gap-6
        ">





        {

        bookings.map(booking=>(



        <div

        key={booking.id}

        className="
        bg-white
        rounded-3xl
        shadow-lg
        p-6
        hover:shadow-xl
        transition
        "


        >




            <div className="
            flex
            justify-between
            items-center
            mb-4
            ">



            <h2 className="
            text-xl
            font-bold
            ">

            Booking #{booking.id}

            </h2>




            <span

            className={`
            px-4
            py-2
            rounded-full
            text-sm
            font-semibold


            ${
                booking.status==="APPROVED"

                ?

                "bg-green-100 text-green-600"


                :

                booking.status==="REJECTED"


                ?

                "bg-red-100 text-red-600"


                :


                "bg-yellow-100 text-yellow-600"

            }

            `}

            >

            {booking.status}

            </span>



            </div>







            <div className="
            space-y-3
            text-gray-600
            ">


            <p>

            👤 User :
            <b className="text-gray-800">
            {" "}
            {booking.userEmail || booking.user?.email || "N/A"}
            </b>

            </p>




            <p>

            🏠 Room ID :
            <b className="text-gray-800">
            {" "}
            {booking.roomId}
            </b>

            </p>





            <p>

            📅 Booking Date :
            <b className="text-gray-800">
            {" "}
            {
            booking.bookingDate
            ?
            new Date(booking.bookingDate)
            .toLocaleDateString()
            :
            "N/A"
            }

            </b>

            </p>





            </div>






        </div>



        ))

        }





        </div>





    </div>


    )


}