import React from "react";
import { Link } from "react-router-dom";
import'./Scoreboard2.css';
import RankOrder from "../../components/rank-order/RankOrder";
import TitleContainer from "../../components/titleContainer/TitleContainer";
import RankPosition from "../../components/rank-position/RankPosition";

const ScoreboardTotal = ({name, description}) => {
    

    const mjesto4 = {
        place: 4,
        name: "Ime Prezime",
        points: 2
        
    };

    const mjesto5 = {
        place: 5,
        name: "Ime Prezime",
        points: 2
    };

    const mjesto6 = {
        place: 6,
        name: "Ime Prezime",
        points: 2
    };

    const mjesta = [
        mjesto4, mjesto5, mjesto6
    ]

    const topThree = [
        {
            place: 2,
            name: "Ime Prezime",
            points: 7
        },
        {
            place: 1,
            name: "Ime Prezime",
            points: 9
        },
        {
            place: 3,
            name: "Ime Prezime",
            points: 5
        }
    ]

    return (
        <>
            <div className="container">
                <TitleContainer title={`Scoreboard ${name}`} description={description}/>
                <div className="topThree">
                    {topThree.map(position => (
                        <RankPosition item={position}/>
                    ))}
                </div>

                    {/*tu dodavat mjesta 4. nadalje*/}

                    {mjesta.map(mjesto => (
                        <RankOrder  mjesto={mjesto} />
                    ))}
            </div>
        </>
    );
}

export default ScoreboardTotal;