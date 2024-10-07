import React from "react";
import { Link } from "react-router-dom";
import'./Registration.css';
import TitleContainer from "../../components/titleContainer/TitleContainer";

const Registration = () => {
    return (
        <>
           <div className="container">
                <TitleContainer title="Registracija" description="Registriraj se da bi se mogao pridružiti sekcijama"/>

                <form className="forma" action="">
                    <input type="text" className="inputStyle" placeholder="Ime" required/>
                    <input type="text" className="inputStyle" placeholder="Prezime" required/>
                    <input type="email" className="inputStyle" placeholder="Email" required/>
                    <input type="text" className="inputStyle" placeholder="JMBAG" required/>
                    <input type="password" className="inputStyle" placeholder="Lozinka" required/>
                    <input type="password" className="inputStyle" placeholder="Ponovi lozinku" required/>
                    <input type="submit" id="submitButton" onClick={()=>{}} value="Registriraj se"/>

                    <div className="prijava-link">
                        <p>Već imate račun? <br /> <Link to="/login"> Prijavi se</Link></p>
                    </div>
                </form> 
            </div>
        </>
    );
}

export default Registration;