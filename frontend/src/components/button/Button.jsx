import React from "react";
import "./Button.css";


const Button = ({item, onClick}) => {
    return (
        <button id="customButton" onClick={() => onClick(item)}>
            {item.name}
        </button>
    )
}

export default Button;