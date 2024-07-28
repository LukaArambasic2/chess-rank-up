import React from "react";
import { Link } from "react-router-dom";
import'./Registracija.css';

const Registracija = () => {
    return (
        <>
           <div className="container">
                <div className="up">
                    <span className="txt">REGISTRACIJA</span>
                </div>

                <div className="forma">
                    <form action="">
                        <div class="ib">
                            <input type="text" placeholder="Ime" required/>
                        </div>

                        <div className="ib">
                            <input type="text" placeholder="Prezime" required/>
                        </div>

                        <div className="ib">
                            <input type="email" placeholder="Email" required/>
                        </div>

                        <div className="ib">
                            <input type="text" placeholder="JMBAG" required/>
                        </div>

                        <div className="ib">
                            <input type="password" placeholder="Lozinka" required/>
                        </div>

                        <div className="ib">
                            <input type="password" placeholder="Ponovi lozinku" required/>
                        </div>

                        <div className="cvet"></div>

                        <div className="gumb">
                            <Link to="/prijava"><button type="submit" class="btn">REGISTRIRAJ SE</button></Link>
                        </div>

                        <div className="prijava-link">
                            <p>Već imate račun? <br /> <Link to="/prijava"> Prijavi se</Link></p>
                        </div>
                    </form> 
                </div>
            </div>
        </>
    );
}

export default Registracija;