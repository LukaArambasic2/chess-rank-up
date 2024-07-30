import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import './Aktivnost.css';
import Event from '../components/Event';
import nazad from '../images/nazad.png';

const Aktivnost = () => {
    const initialEvents = [
        {
            id: 1,
            date: "2024-07-25",
            name: "Test Event1",
            defaultPoints: 2
        },
        {
            id: 2,
            date: "2024-10-26",
            name: "Test Event2",
            defaultPoints: 1
        },
        {
            id: 3,
            date: "2023-01-11",
            name: "Test Event3",
            defaultPoints: 1
        }
    ];

    const novi = {
        id: 4,
        date: "2024-08-24",
        name: "NOVI",
        defaultPoints: 1
    };

    const sortEvents = (events) => {
        return [...events].sort((a, b) => {
            const dateA = new Date(a.date);
            const dateB = new Date(b.date);
            return dateB - dateA; 
        });
    };

    const [events, setEvents] = useState([]);

    useEffect(() => {
        setEvents(sortEvents(initialEvents));
    }, []);

    useEffect(() => {
        setEvents(prevEvents => sortEvents([...prevEvents, novi]));
    }, []);

    return (
        <div className='bodycontainer'>
            <Link to="/profil"><img src={nazad} className="nazad" alt="nazad" /></Link>
            
            <div className='bold'>Ime Prezime</div>

            <div className='dab'>
                <div className='maleni1'>Datum</div>
                <div className='maleni2'>Aktivnost</div>
                <div className='maleni3'>Bodovi</div>
            </div>

            <div className='eventi'>
                {events.map(event => (
                    <Event event={event} />
                ))}
            </div>
        </div>
    );
};

export default Aktivnost;
