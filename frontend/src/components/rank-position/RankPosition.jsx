import React from "react";
import "./RankPosition.css";

const RankPosition = ({item}) => {
    return (
        <div className="rankPosition" id={"rankPosition"+item.place}>
            <div className="rankBox"/>
            <div className="rankNumber">{item.place}</div>
        </div>
    );
}

export default RankPosition;