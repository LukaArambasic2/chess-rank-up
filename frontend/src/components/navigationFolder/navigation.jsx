import React from 'react';
import { useState } from 'react';
import { FaBars, FaTimes, FaHome, FaUser, FaInfoCircle, FaNewspaper, FaCalendarAlt, FaTrophy, FaListAlt, FaQuestionCircle } from 'react-icons/fa';
import { Link, useNavigate } from 'react-router-dom'
import "./Navigation.css"
import ButtonComponent from '../buttonComponentFolder/ButtonComponent';



const Navigation = () => {
    const [isOpen, setIsOpen] = useState(false);
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

    const id = localStorage.getItem("idSection");
    return (
        <div>
            <div className="navBar">
                <div className="hamburgerIcon" onClick={toggleMenu}>
                    {isOpen ? <FaTimes size={30} /> :  <FaBars size={30} />}
                </div>
            </div>
            {isOpen && (
                <div className='openNavBar'>
                    <ul className='navBarList'>
                        <li><Link to="/"><FaHome /> Home</Link></li>
                        <li><Link to="/profile"><FaUser/> Profil</Link></li>
                        <li><Link to={`/sectioninfo/${id}`}><FaInfoCircle /> O sekciji</Link></li>
                        <li><Link to="/news"><FaNewspaper/> Obavijesti</Link></li>
                        <li><Link to="/events"><FaCalendarAlt/> Događaji</Link></li>
                        <li><Link to="/scoreboard"><FaTrophy/> Scoreboard</Link></li>
                        <li><Link to="/league"><FaListAlt/> League</Link></li>
                        <li><Link to="/about"><FaQuestionCircle/> O aplikaciji</Link></li>
                        <li> <div className="logout"><ButtonComponent btnTitle={"Odjavi se"} onClick={handleLogout} /></div></li>    
                    </ul>
                    
                </div>

            )}
        </div>
    );
}

export default Navigation;