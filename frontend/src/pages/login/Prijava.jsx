import React from "react";
import { Link } from "react-router-dom";

const Prijava = () => {
    return (
        <>
           <div className="container">
                <div className="up">
                    <span className="txt">PRIJAVA</span>
                </div>

                <div className="forma">
                    <form action="">
                        <div class="ib">
                            <input type="text" placeholder="Email" required/>
                        </div>

                        <div className="ib">
                            <input type="text" placeholder="Loznika" required/>
                        </div>

                        <div className="cvet">
                            <Link to="/resetiranjelozinke"> <span className="ak">Zaboravili ste lozinku?</span></Link>
                        </div>
                        <div className="gumb">
                            <button type="submit" class="btn">PRIJAVI SE</button>
                        </div>

                        <div className="prijava-link">
                            <p>Nemate korisnički račun? <br /> <Link to="/registracija"> Registriraj se</Link> </p>
                            
                        </div>
                    </form> 
                </div>
            </div>
        </>
    );
}

export default Prijava;