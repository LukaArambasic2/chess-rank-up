import React from "react";
import { Link } from "react-router-dom";
import TitleContainer from "../../components/titleContainer/TitleContainer";

const Login = () => {



    return (
        <div className="container">
            <TitleContainer title={"Prijava"} description={"Prijavi se!"} />
            <form className="forma" action="">
                <input type="text" className="inputStyle" placeholder="Email" required/>
                <input type="text" className="inputStyle" placeholder="Loznika" required/>
                <input type="submit" id="submitButton" onClick={()=>{}} value="Prijavi se"/>

                <div className="prijava-link">
                    <p>Nemate korisnički račun? <br /> <Link to="/register"> Registriraj se</Link> </p>
                    <Link to="/reset"> <span className="ak">Zaboravili ste lozinku?</span></Link>
                </div>
            </form> 
        </div>
    );
}

export default Login;