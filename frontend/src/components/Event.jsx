import React, { useState, useEffect } from "react";
import "./Event.css";

const Event = ({event}) => {
    console.log("Event prop:", event);

    const getBackgroundColor = (id) => {
        return id % 2 === 0 ? '#C9D3FF' : '#EEF1FF';  
    };

    
    const getTopValue = (id) => {
        return `${7.5 * id + 11}vh`; 
    };

    const dynamicStyles = {
        backgroundColor: getBackgroundColor(event.id),
        top: getTopValue(event.id),
    };

    return (
        <>
            <div className='event' style={dynamicStyles}>
                <div className='maleni1'>{event.date}</div>
                <div className='maleni2'>{event.name}</div>
                <div className='maleni3'>{event.points}</div>
                </div>
        </>
    );
}

export default Event;