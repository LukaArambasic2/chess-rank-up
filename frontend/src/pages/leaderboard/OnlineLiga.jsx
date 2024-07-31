import React from "react";
import { Link } from "react-router-dom";
import TopTri from "../../components/TopTri";
import Mjesto from "../../components/ranking/Mjesto";
import './OnlineLiga.css';

const OnlineLiga = () => {
    const mjesta = [
        { place: 1, firstName: "Ime", lastName: "Prezime", points: 10 },
        { place: 2, firstName: "Ime", lastName: "Prezime", points: 8 },
        { place: 3, firstName: "Ime", lastName: "Prezime", points: 6 }
    ];

    const topTriMjesta = mjesta.slice(0, 3);
    const preostalaMjesta = mjesta.slice(3);

    return (
        <>
                <div className="up2">
                    <div className="onlineliga">ONLINE LIGA</div>
                    <TopTri mjesta={topTriMjesta} />
                </div>

                <div className="content">
                    <div className="mjesta">
                        {preostalaMjesta.map(mjesto => (
                            <Mjesto mjesto={mjesto} />
                        ))}
                    </div>
                </div>
        </>
    );
}

export default OnlineLiga;