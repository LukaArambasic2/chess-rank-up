import React from "react";
import "./About.css"
import TitleContainer from "../../components/titleContainer/TitleContainer";

const About = () => {



    return (
        <div className="container">
            <TitleContainer title={"O aplikaciji"} description={"Upoznaj članove tima"} />
            <div className="aboutText">
                <h2>Sport na FER-u</h2>
                <article>
                    FER je najbolji fakultet u Hrvatskoj po uspjesima u raznim sportovima, i uprava FER-a jako potiče svoje studente da nastave aktivno napredovati u sportu te razvijati sportski duh. <br/><br/>
                    Predmet Tjelesna i zdravstvena kultura (1, 2, 3 i 4) na Fakultetu elektrotehnike i računarstva, koji vodi prof. Nera Žigić, je obavezan predmet za sve studente preddiplomskog studija. <br/> <br/>
                </article>

                <h2>FER sekcije i kako funkcioniraju</h2>
                <article>
                    Jedan od načina promicanja sporta na FER-u su sportske sekcije. Sekcije su poput studentskih udruga, operiraju samostalno, a voditelji su studenti koji su već položili tjelesni. <br /> <br />
                    Na FER-u, sekcije su osmišljene za sve studente zainteresirane za određeni sport, ali također imaju mogućnost oslobađanja studenata od tjelesnog. 
                </article>
                
                <h2>Kako je nastao TzkRankUp?</h2>
                <article>
                    Studenti koji pohađaju predmet Tjelesna i zdravstvena kultura mogu vidjeti koliko su prikupili bodova na FER Intranet stranici. <br /> <br />
                    Problem je da studenti oslobođeni tjelesnog preko sekcija ne mogu vidjeti broj bodova, odnosno, pisat će im 0 prikupljenih bodova. Razlog je što voditelji sekcija nemaju pravo dodavanja bodova na Intranet stranici. <br /> <br /> 
                    Da bi studenti oslobođeni tjelesnog preko sekcija znali koliko imaju bodova, šahovska sekcija je osmislila aplikaciju TzkRankUp. <br /><br /> 
                    Aplikacija služi praćenju broja bodova, ali i mnogo toga više. Sekcije mogu objaviti buduća događanja i obavijesti o prethodnim, napravljen je sistem rangiranja unutar sekcije, i mnogo toga drugog.
                </article>

                <h2>Naš tim</h2>
                <ul>
                <li>Luka Arambašić</li>
                <li>Petar Štika</li>
                <li>Marija Bilješko</li>
                <li>Marin Čičak</li>
                <li>Elma Vuran</li> 
                <li>Marin Denić</li>
                </ul>
            
               
            </div>            
        </div>
    );
}

export default About;