import React from 'react';
import { Link } from "react-router-dom";
import TableRow from '../../components/table-row/TableRow';
import './Activity.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowLeft} from "@fortawesome/free-solid-svg-icons";


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

                {tests.map(test => (
                    <TableRow event={test} />
                ))}
            </div>
    );
};

export default Activity;
