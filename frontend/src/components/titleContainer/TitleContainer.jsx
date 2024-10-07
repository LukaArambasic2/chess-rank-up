import React from "react";
import "./TitleContainer.css";

const TitleContainer = ({title, description}) => {
    return (
        <div className="welcome-message">
          <h1>{title}</h1>
          <p>{description}</p>
        </div>
    )
}

export default TitleContainer;