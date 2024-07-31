import React, { useState, useEffect } from "react";
import "./Mjesto.css";

const Mjesto = ({mjesto}) => {

    return (
        <>
            <div className='mjesto'>
                <div className='maleniM1'>{mjesto.place}.</div>
                <div className='maleniM2'>{mjesto.firstName} {mjesto.lastName}</div>
                <div className='maleniM3'>{mjesto.points}</div>
                </div>
        </>
    );
}

export default Mjesto;