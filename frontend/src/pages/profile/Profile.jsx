import React, { useState } from "react";
import './Profile.css';
import Curved from "../Curved";
import { Link } from "react-router-dom";
import Navigation from "../../components/navigation/Navigation";

const Profile = ({member}) => {
    const [attributes, setAttributes] = useState([
        {attribute:"Lichess account", value:"username123"}, 
        {attribute:"Rank", value:"pijun"}, 
    ]);
    
    return (
        <div className="container">
            <div style={{width:"100%", padding: "2vh 5vw 0 5vw", boxSizing:"border-box", zIndex:1000}}>
                <Navigation />
            </div>
            <div id="profile" >Profil</div>
            <div id="circles">
                <div id="bigCircle" />
                <div id="smallCircle" />
            </div>
            <div id="curved"><Curved /></div>

            <div className="whiteBackgroundContainer"> 
                <p className="profileText">Ime Prezime</p>
                <div className="twoContainer">
                    <div className="pointsContainer" id="lijevi">
                        <p className="pointsTitle">Bodovi <br /> u semestru </p>
                        <p className="pointsNumber">10</p>
                        <p className="pointsPass">Do prolaza: <span className="pointsMini">2</span></p>    
                    </div>

                    <div className="pointsContainer" id="desni">
                        <p className="pointsTitle" id="ri">Ukupan <br /> broj bodova</p>
                        <p className="pointsNumber">23</p>
                    </div>
                </div>

                <div className="twoContainer">
                    <div className="attributes">
                        {attributes.map(attribute => (
                            <p className="attribute">{attribute.attribute} <br /> <span className="value">{attribute.value}</span></p>
                        ))}
                    </div>

                    <Link to="/profile/activity">
                        <div className="activity">
                            Aktivnost
                        </div>
                    </Link>
                </div>
             
                <img src="/jmbag.jpg" className="slikica" alt="JMBAG"/>
          
            </div>

            

        </div>
    );
}

export default Profile;