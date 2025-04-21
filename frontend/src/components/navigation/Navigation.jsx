import React, { useEffect } from 'react';
import { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faTimes, faHome, faUser, faInfoCircle, faNewspaper, faCalendar, faTrophy, faList, faQuestion} from "@fortawesome/free-solid-svg-icons";
import { Link, useNavigate } from 'react-router-dom'
import "./Navigation.css"
import {useSection} from "../../contexts/SectionProvider";


const Navigation = () => {
    const [isOpen, setIsOpen] = useState(false);
    const [isLoggedIn, setLoggedIn] = useState(true);
    const navigate = useNavigate();
    const {sectionId} = useSection();

    const toggleMenu = () => {
        setIsOpen(!isOpen);
    };

    const handleLogout = () => {
        console.log("Handeling logout...");
        localStorage.removeItem("idMember");
        localStorage.removeItem("firstName");
        localStorage.removeItem("lastName");
        localStorage.removeItem("idSection");
        localStorage.removeItem("token");

        //provjeriti treba li još nešto obrisati prilikom odjave
        navigate('/');
    }

    const handleLogin = () => {
        navigate("login")
    }

    const handleNavigate = (path) => {
        if (sectionId) {
            navigate(path);
        } else {
            navigate('/my-sections')
        }
    }

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
                            <li><a onClick={()=>navigate("/")}><FontAwesomeIcon icon={faHome} /> Home</a></li>
                            <li><a onClick={()=>handleNavigate(`/profile`)}><FontAwesomeIcon icon={faUser} /> Profil</a></li>
                            <li><a onClick={()=>handleNavigate(`/scoreboard`)} ><FontAwesomeIcon icon={faTrophy} /> Scoreboard</a></li>
                            <li><a onClick={()=>handleNavigate(`/news`)}><FontAwesomeIcon icon={faNewspaper} /> Obavijesti</a></li>
                            <li><a onClick={()=>navigate("/about")}><FontAwesomeIcon icon={faQuestion} /> O aplikaciji</a></li>
                            <li><button id='navbarButton' onClick={handleLogout}>Odjava</button></li>    
                        </ul>
                    )}
                    {!isLoggedIn && (
                        <ul id='navbarList'>
                            <li><a onClick={()=>navigate("/")}><FontAwesomeIcon icon={faHome} /> Home</a></li>
                            <li><a onClick={()=>navigate("/about")}><FontAwesomeIcon icon={faQuestion} /> O aplikaciji</a></li>
                            <li><button id='navbarButton' onClick={handleLogin}>Prijava</button></li>    
                        </ul>
                    )}
                </div>
            )}
        </div>
    );
}

export default Navigation;