import React, { useState, useEffect } from "react";
import "./TopTri.css";

const TopTri = ({mjesta}) => {

    const getPlaceData = (place) => {
        return mjesta.find(mjesto => mjesto.place === place) || {};
    };

    const mjesto1 = getPlaceData(1);
    const mjesto2 = getPlaceData(2);
    const mjesto3 = getPlaceData(3);

    return (
        <>
            
            <div className="top3">

                <div className="jedan">
                    <div className="info">
                        <p className="ip">{mjesto1.firstName} <br /> {mjesto1.lastName}</p>
                        <p className="brbod">{mjesto1.points}</p>
                    </div>
                </div>

                <div className="dva">
                    <div className="info">
                        <p className="ip">{mjesto2.firstName} <br /> {mjesto2.lastName}</p>
                        <p className="brbod">{mjesto2.points}</p>
                    </div>
                </div>

                <div className="tri">
                    <div className="info">
                        <p className="ip">{mjesto3.firstName} <br /> {mjesto3.lastName}</p>
                        <p className="brbod">{mjesto3.points}</p>
                    </div>
                </div>

                <div className="pomocniJedan">
                    <div className="jedanK">1</div>
                </div>


                <div className="pomocniDva">
                    <div className="dvaK">2</div>
                </div>

                <div className="pomocniTri">        
                    <div className="triK">3</div>     
                </div>
             </div>
        </>
    );
}

export default TopTri;
