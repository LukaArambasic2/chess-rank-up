import React, {useEffect, useState} from "react";
import { Link } from "react-router-dom";
import'./Scoreboard2.css';
import RankOrder from "../../components/rank-order/RankOrder";
import TitleContainer from "../../components/titleContainer/TitleContainer";
import RankPosition from "../../components/rank-position/RankPosition";
import api from "../../api";

const Scoreboard = ({name, description}) => {

    const [scoreboard, setScoreboard] = useState([]);
    const [topThree, setTopThree] = useState([]);

    useEffect(() => {
        async function fetchData() {
            await api.get(`/sections/${1}/scoreboard/${name.toLowerCase()}`)
                .then(response => {
                    console.log("Scoreboard: ", response.data);
                    setScoreboard(response.data);
                    setTopThree(response.data.slice(0,3));
                })
                .catch(error => {
                    console.log("Error fetching data: ", error);
                });
        }
        fetchData();
    }, []);

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

    return (
        <>
            <div className="container">
                <TitleContainer title={`Scoreboard ${name}`} description={description}/>
                    {scoreboard.map((member, index) => (
                        <RankOrder  mjesto={member} place={index+1} />
                    ))}
            </div>
        </>
    );
}

export default Scoreboard;