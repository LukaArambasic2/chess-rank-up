import React, { useState, useEffect } from "react";
import "./RankOrder.css";

const RankOrder = ({mjesto}) => {


    return (
        <>
            <div className='mjesto'>
                <div className='maleniM1'>{mjesto.place}.</div>
                <div className='maleniM2'>{mjesto.name}</div>
                <div className='maleniM3'>{mjesto.points}</div>
            </div>
        </>
    );
}

export default RankOrder;