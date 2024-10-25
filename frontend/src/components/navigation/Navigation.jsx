import React, { useEffect } from 'react';
import { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faTimes, faHome, faUser, faInfoCircle, faNewspaper, faCalendar, faTrophy, faList, faQuestion} from "@fortawesome/free-solid-svg-icons";
import { Link, useNavigate } from 'react-router-dom'
import "./Navigation.css"


const Navigation = () => {
    const [isOpen, setIsOpen] = useState(false);
    const [isLoggedIn, setLoggedIn] = useState(true);
    const navigate = useNavigate();

    const toggleMenu = () => {
        setIsOpen(!isOpen);
    };

    const handleLogout = () => {
        console.log("Handeling logout...");
        localStorage.removeItem("idMember");
        localStorage.removeItem("firstName");
        localStorage.removeItem("lastName");
        localStorage.removeItem("idSection");
        //provjeriti treba li još nešto obrisati prilikom odjave
        navigate('/');
    }

    const handleLogin = () => {
        navigate("login")
    }

    const id = localStorage.getItem("idSection");
    return (
        <div id='navbarContainer'>
            <div id="navbarIcon" onClick={toggleMenu}>
                <FontAwesomeIcon icon={faBars} size={'lg'}/>
            </div>
            {isOpen && (
                <div className='openNavBar'>
                    <div id="navbarIcon2" onClick={toggleMenu}>
                        <FontAwesomeIcon icon={faTimes} size={'lg'}/>
                    </div>
                    {isLoggedIn && (
                        <ul id='navbarList'>
                            <li><Link to="/"><FontAwesomeIcon icon={faHome} /> Home</Link></li>
                            <li><Link to="/profile"><FontAwesomeIcon icon={faUser} /> Profil</Link></li>
                            <li><Link to="/scoreboard"><FontAwesomeIcon icon={faTrophy} /> Scoreboard</Link></li>
                            <li><Link to="/news"><FontAwesomeIcon icon={faNewspaper} /> Obavijesti</Link></li>
                            <li><Link to="/events"><FontAwesomeIcon icon={faCalendar} /> Događaji</Link></li>
                            <li><Link to="/about"><FontAwesomeIcon icon={faQuestion} /> O aplikaciji</Link></li>
                            <li><button id='navbarButton' onClick={handleLogout}>Odjava</button></li>    
                        </ul>
                    )}
                    {!isLoggedIn && (
                        <ul id='navbarList'>
                            <li><Link to="/"><FontAwesomeIcon icon={faHome} /> Home</Link></li>
                            <li><Link to="/about"><FontAwesomeIcon icon={faQuestion} /> O aplikaciji</Link></li>
                            <li><button id='navbarButton' onClick={handleLogin}>Prijava</button></li>    
                        </ul>
                    )}
                </div>
            )}
        </div>
    );
}

export default Navigation;