import React from "react";
import "./RankPosition.css";

const RankPosition = ({item}) => {
    return (
        <div className="rankPosition" id={"rankPosition"+item.points}>
            <div className="rankBox"/>
            <div className="rankNumber">{item.points}</div>
        </div>
    );
}

export default RankPosition;