import React from "react";
import { Link } from "react-router-dom";

const ExamplePage = () => {
    return (
        <>
            <Link to="/registracija"> registriraj se</Link>
            <p><Link to="/profil">profil</Link></p>
            <p><Link to="/scoreboard">scoreboard</Link></p>
            <p><Link to="/onlineLiga">online liga</Link></p>
        </>
    );
}

export default ExamplePage;