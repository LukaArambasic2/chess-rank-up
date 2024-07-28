import React from 'react';
import { Link } from "react-router-dom";
import'./Aktivnost.css';
import Event from '../components/Event';
import nazad from '../images/nazad.png';


const Aktivnost = () => {
    const testEvent1 = {
        id: 1,
        date: "2024-07-25",
        name: "Test Event1",
        points: 2
    };
    const testEvent2 = {
        id: 2,
        date: "2024-10-26",
        name: "Test Event2",
        points: 1
    };
    const testEvent3 = {
        id: 3,
        date: "2023-01-11",
        name: "Test Event3",
        points: 1
    };

    return (
       <>  
       <div className='bodycontainer'>
            <Link to="/profil"><img src={nazad} className="nazad" alt="nazad"/></Link>
            
            <div className='bold'>Ime Prezime</div>

            <div className='dab'>
                <div className='maleni1'>Datum</div>
                <div className='maleni2'>Aktivnost</div>
                <div className='maleni3'>Bodovi</div>
            </div>

            <Event event={testEvent1} />
            <Event event={testEvent2} />
            <Event event={testEvent3} />
       </div> 
       
       
       </>
    );
};

export default Aktivnost;
