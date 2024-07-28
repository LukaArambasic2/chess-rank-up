import React from "react";
import { Link } from "react-router-dom";
import Mjesto from '../components/Mjesto';
import'./SBukupno.css';

const SBukupno = ({name}) => {
    

    const mjesto4 = {
        place: 4,
        name: "Ime Prezime",
        points: 2
        
    };

    const mjesto5 = {
        place: 5,
        name: "Ime Prezime",
        points: 2
    };

    const mjesto6 = {
        place: 6,
        name: "Ime Prezime",
        points: 2
    };

    const mjesta = [
        mjesto4, mjesto5, mjesto6
    ]

    return (
        <>
            <div className="container">
                <div className="up2">
                    <div className="sbb">SCOREBOARD - {name}</div>
                    <div className="top3">
                        <div className="jedan">
                        </div>

                        <div className="pomocniJedan">
                            <div className="jedanK">1</div>
                        </div>

                        <div className="dva">

                        </div>

                        <div className="pomocniDva">
                            <div className="dvaK">2</div>
                        </div>

                        <div className="tri">
                            
                        </div>

                        <div className="pomocniTri">        
                            <div className="triK">3</div>     
                        </div>
                    </div>

                    {/*tu dodavat mjesta 4. nadalje*/}

                    {mjesta.map(mjesto => (
                        <>
                            <Mjesto  mjesto={mjesto} />
                        </>
                    )) 

                    }
                </div>
            </div>
        </>
    );
}

export default SBukupno;