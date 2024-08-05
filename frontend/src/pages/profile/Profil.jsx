import React from "react";
import'./Profil.css';
import Curved from "./Curved";
import JMBAG from '../images/jmbag.jpg';
import { Link } from "react-router-dom";

const Profil = ({member}) => {
    return (
        <>
        <div className="container">
            <div className="profil">PROFIL </div>
            <div className="krugV"></div>

            <div className="krugM">
            </div>
            
            <div className="curved">
                <Curved />
            </div>

            <div className="beli"> 

                <p className="ime">Ime Prezime</p>

                <div className="kockice">
                    <div className="kockica" id="lijevi">
                        <p className="b">Bodovi <br /> u semestru </p>
                        <p className="bodovi">10</p>
                        <p className="doprolaza">Do prolaza: <span className="minibodovi">2</span></p>    
                    </div>

                    <div className="kockica" id="desni">
                        <p className="b" id="ri">Ukupan <br /> broj bodova</p>
                        <p className="bodovi">23</p>
                    </div>
                </div>

                <div className="atributi">
                    <p className="atribut">Lichess account <br /> <span className="vrijednost">username123</span></p>

                    <p className="atribut">Rank <br /> <span className="vrijednost">figura</span></p>
                </div>

                <Link to="/profil/aktivnost">
                <div className="aktivnost">
                    <p className="mhm">AKTIVNOST</p>
                </div>
                </Link>
             
                <img src={JMBAG} className="slikica" alt="JMBAG"/>
          
            </div>

            

        </div>


        </>
    );
}

export default Profil;