import React from "react";
import './Admin.css';
import TitleContainer from "../../components/titleContainer/TitleContainer";
import { useNavigate } from "react-router-dom";
import Button from "../../components/button/Button";

const Admin = () => {
    const nav = useNavigate();
    const timeScales = [
        {id: 1, name: "Dodaj Bodove", to:"add-points"},
        {id: 3, name: "Dodaj Događanje", to:"add-events"},
        {id: 4, name: "Dodaj Obavijest", to:"add-news"},
        {id: 2, name: "Svi Članovi", to:"all-users"},
        
    ]

    const handleClick = (time) => {
        nav(`${(time.to)}`)
    }
    
    return (
        <>
           <div className="container">
                <TitleContainer title={"Admin"} description={"Odaberi što želiš napraviti"}/>
                
                <div className="buttonList">
                    {timeScales.map(time => (
                        <Button key={time.id} item={time} onClick={handleClick}/>
                    ))}
                </div>
            </div>
        </>
    );
}

export default Admin;