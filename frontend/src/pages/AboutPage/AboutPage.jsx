import React from "react";
import Navigation from "../../components/navigationFolder/navigation";
import "./AboutPage.css"

const AboutPage = () => {



    return (
        <>
            <div className="AboutHeader">
               <Navigation /> 
               <h1>O aplikaciji</h1>
            </div>
            <div className="aboutText">
                <h2>Što je TZKRankUp i čemu služi?</h2>

                <article>
                    TZKRankUp je aplikacija koju smo napravili kako bismo olakšali praćenje obavijesti i osvojenih bodova na sekcijama.
                </article>

                <h2>Naš tim</h2>
                
                <ul>
                <li>Luka Arambašić</li>
                <li>Marija Bilješko</li>
                <li>Marin Čičak</li>
                <li>Marin Denić</li>
                <li>Mila Podrug</li>
                <li>Petar Štika</li>
                <li>Roko Vrdoljak</li>
                <li>Elma Vuran</li>
                </ul>
            
               
            </div>            
        </>
    );
}

export default AboutPage;