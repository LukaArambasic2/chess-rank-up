import React, {useEffect, useState} from "react";
import './Profile.css';
import Curved from "../Curved";
import { Link } from "react-router-dom";
import Navigation from "../../components/navigation/Navigation";
import api from "../../api";
import QRCode from 'qrcode';

const Profile = () => {
    const [member,setMember] = useState({});
    const [qr, setQr] = useState();

    useEffect(() => {
        async function fetchData() {
            await api.get(`sections/${1}/members/${3}/profile/general`)
                .then(response => {
                    setMember(response.data);
                    QRCode.toDataURL(response.data.jmbag, {width: 300, margin: 2}).then(url => setQr(url));
                    console.log("General profile info: ", response.data);
                })
                .catch(error => console.error(error));
        }
        fetchData();
    }, []);
    
    return (
        <div className="container">
            <div style={{width:"100%", padding: "2vh 5vw 0 5vw", boxSizing:"border-box", zIndex:1000}}>
                <Navigation />
            </div>
            <div id="profile" >{member.section} Profil</div>
            <div id="circles">
                <div id="bigCircle" />
                <div id="smallCircle" />
            </div>
            <div id="curved"><Curved /></div>

            <div className="whiteBackgroundContainer"> 
                <p className="profileText">{member.firstName} {member.lastName}, {member.jmbag}</p>
                <div className="twoContainer">
                    <div className="pointsContainer" id="lijevi">
                        <p className="pointsTitle">Bodovi <br /> u semestru </p>
                        <p className="pointsNumber">{member.pointsSemester}</p>
                        <p className="pointsPass">Do prolaza: <span className="pointsMini">{member.additionalPointsNeeded}</span></p>
                    </div>

                    <div className="pointsContainer" id="desni">
                        <p className="pointsTitle" id="ri">Ukupan <br /> broj bodova</p>
                        <p className="pointsNumber">{member.pointsTotal}</p>
                    </div>
                </div>

                <div className="twoContainer">

                    <Link to="/profile/activity">
                        <div className="activity">
                            Aktivnost
                        </div>
                    </Link>
                </div>
             
                <img src={qr} className="slikica" alt="JMBAG" style={{width: "300px"}}/>
          
            </div>

            

        </div>
    );
}

export default Profile;