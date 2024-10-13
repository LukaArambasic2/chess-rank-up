import React from "react";
import "./TitleContainer.css";
import Navigation from "../navigation/Navigation";

const TitleContainer = ({title, description}) => {
    return (
        <div className="welcome-message">
          <Navigation />
          <h1>{title}</h1>
          <p>{description}</p>
        </div>
    )
}

export default TitleContainer;