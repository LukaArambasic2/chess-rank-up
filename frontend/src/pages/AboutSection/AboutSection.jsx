import React from "react";
import Navigation from "../../components/navigationFolder/navigation";
import "./AboutSection.css"
import axios from 'axios';
import { useState, useEffect } from "react";

const AboutSection = () => {

    const [sectionName, setSectionName] = useState("");
    const [sectionDescription, setSectionDescription] = useState("");


    useEffect(() => {
        const sectionId = localStorage.getItem("sectionId");
        if (sectionId) {
            axios.get(`/${sectionId}`) //promijeniti u tocan path
                .then(response => {
                    const sectionData = response.data;
                    setSectionName(sectionData.name);
                    if(sectionData.description){
                        axios.get(sectionData.description)
                        .then(fileResponse => {
                            setSectionDescription = fileResponse.data;
                        })
                        .catch(fileError => {
                            console.error("Error fetching description file:", fileError)
                        })
                    }
                })
                .catch(error => {
                    console.error("Error fetching section data:", error);
                });
        } else {
            console.error("No sectionId found in localStorage");
        }
    }, []);

    return (
        <>
            <div className="AboutHeader">
               <Navigation /> 
               <h1>{ sectionName }</h1>
            </div>
            <div className="aboutText">
                { sectionDescription }
            </div>            
        </>
    );
}

export default AboutSection;