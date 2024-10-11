import React from 'react';
import "./ButtonComponent.css"


const ButtonComponent = ({btnTitle, onClick}) => {
    return (
        <div className="buttonComp">
            <button onClick = {onClick}>{btnTitle}</button>
        </div>
    );
};

export default ButtonComponent;