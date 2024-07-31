import React from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import'./Reset.css';

const Reset = () => {
    const navigate = useNavigate(); 

    const handleSubmit = (event) => {
        event.preventDefault(); 
         navigate("/prijava");
    };

    return (
        <>
           <div className="container">
                <div className="up">
                    <span className="txt" id="res">RESETIRANJE LOZINKE</span>
                </div>

                <div className="forma">
                    <form onSubmit={handleSubmit}>
                        <div class="ib">
                            <input type="password" placeholder="Unesite novu lozinku" required/>
                        </div>

                        <div className="ib">
                            <input type="password" placeholder="Potvrdite novu lozinku" required/>
                        </div>

                        <div className="cvet"></div>

                        <div className="gumb">
                            <button type="submit" class="btn">SPREMI</button>
                        </div>

                    </form> 
                </div>
            </div>
        </>
    );
}

export default Reset;