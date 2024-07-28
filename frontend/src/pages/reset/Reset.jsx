import React from "react";
import { Link } from "react-router-dom";
import'./Reset.css';

const Reset = () => {
    return (
        <>
           <div className="container">
                <div className="up">
                    <span className="txt" id="res">RESETIRANJE LOZINKE</span>
                </div>

                <div className="forma">
                    <form action="">
                        <div class="ib">
                            <input type="password" placeholder="Unesite novu lozinku" required/>
                        </div>

                        <div className="ib">
                            <input type="password" placeholder="Potvrdite novu lozinku" required/>
                        </div>

                        <div className="cvet"></div>

                        <div className="gumb">
                        <Link to="/prijava"><button type="submit" class="btn">SPREMI</button></Link>
                        </div>

                    </form> 
                </div>
            </div>
        </>
    );
}

export default Reset;