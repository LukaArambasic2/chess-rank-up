import React from "react";
import "./Button.css";


const Button = ({item, onClick}) => {
    return (
        <button id="customButton" className="listElement" onClick={() => onClick(item)}>
            {item.name}
        </button>
    )
}

export default Button;