import React, {useEffect, useState} from 'react';
import { Link } from "react-router-dom";
import TableRow from '../../components/table-row/TableRow';
import './Activity.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowLeft} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";


const Activity = () => {
    const tests = [
        {
            id: 1,
            date: "2024-07-25",
            name: "Test Event1",
            points: 2
        }, {
            id: 2,
            date: "2024-10-26",
            name: "Test Event2",
            points: 1
        }, {
            id: 3,
            date: "2023-01-11",
            name: "Test Event3",
            points: 1
        }
    ]
    const [activities, setActivities] = useState([]);

    useEffect(() => {
        async function fetchData() {
            await axios.get(`http://localhost:8080/api/sections/${1}/members/${1}/profile/activities`)
                .then(response => {
                    console.log("Activities list: ", response.data.events);
                    setActivities(response.data.events);
                })
                .catch(error => {
                    console.log("Error: ", error);
                })
        }
        fetchData();
    }, []);

    return (
            <div className='container2'>
                <div id='backIcon'>
                    <Link to="/profile"><FontAwesomeIcon icon={faArrowLeft} /></Link>
                </div>

                <div id='title'>Ime Prezime</div>

                <div className='table'>
                    <div className='maleniHeader1'>Datum</div>
                    <div className='maleniHeader2'>Aktivnost</div>
                    <div className='maleniHeader3'>Bodovi</div>
                </div>

                {activities && activities.map(activity => (
                    <TableRow event={activity} />
                ))}
            </div>
    );
};

export default Activity;
