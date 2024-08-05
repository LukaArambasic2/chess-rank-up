import React from 'react';
import '../styles/NewNovi.css'; 

const NewNovi = () => {
    return (
        <div>
            <div className="header">
                <div className="navbar-icon">
                    <div className="bar"></div>
                    <div className="bar"></div>
                    <div className="bar"></div>
                </div>
                <span className="header-text">Naslov Obavijesti</span>
                <div className="header-footer">
                    <p>autor</p>
                    <p>Kreirana: datum</p>
                    <p>Posljednja promjena: datum</p>
                </div>
            </div>
            <div className="content">
                <p className="event-text">Ovdje ide tekst o obavijesti</p>
            </div>
        </div>
    );
};

export default NewNovi;
