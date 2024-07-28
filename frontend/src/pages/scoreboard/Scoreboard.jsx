import React from "react";
import { Link } from "react-router-dom";
import'./Scoreboard.css';

const Scoreboard = () => {
    return (
        <>
           <div className="biraj">
                <div><Link to="/scoreboard/semestar">semestar</Link></div>
                <div><Link to="/scoreboard/godina">godina</Link></div>
                <div><Link to="/scoreboard/ukupno">ukupno</Link></div>
           </div>
        </>
    );
}

export default Scoreboard;