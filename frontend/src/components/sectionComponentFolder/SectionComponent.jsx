import React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';
import "./SectionComponent.css"
import { Link } from 'react-router-dom';
import ButtonComponent from '../buttonComponentFolder/ButtonComponent';


const SectionComponent = ({ id }) => {

    const [sectionData, setSectionData] = useState(null);

    useEffect(() => {
        axios.get(` `) //Dodati odakle se dohvacaju podaci
            .then(response => setSectionData(response.data))
            .catch(error => console.error('Error fetching section data:', error));
    }, []);

    const { name, logo, acceptNewMembers } = sectionData;

    const handleJoin = (id) => {
        alert(`Učlanili ste se u ${name}`)

    }

    return (
        <div className='all'>
            <div className="container">
                <div className="firstrow">
                    <div className="sectionName">{name}</div>
                    <img src={logo} alt='sectionLogo' className='sectionLogo'></img>
                </div>
                

                <div className="secondRow">
                    <div className="aboutSection"><Link to={`/sectioninfo/${id}`} className='linkTo'>O sekciji</Link></div>
                    <div className="join">
                        {acceptNewMembers ? <ButtonComponent btnTitle="Pridruži se" onClick={() => {handleJoin(id)}} /> 
                        : <ButtonComponent btnTitle="Prijave zatvorene" disabled={true}/>}
                        </div>
                </div>
            </div>

        </div>
    );
};

export default SectionComponent;