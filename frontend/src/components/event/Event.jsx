import React, { useState, useEffect } from "react";
import "./Event.css";

const Event = ({event}) => {

    return (
        <>
            <div className='event'>
                <div className='maleni1'>{event.date}</div>
                <div className='maleni2'>{event.name}</div>
                <div className='maleni3'>{event.defaultPoints}</div>
            </div>
        </>
    );
}

export default Event;