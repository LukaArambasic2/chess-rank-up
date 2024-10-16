import React, { useEffect, useState } from "react";
import TitleContainer from "../../components/titleContainer/TitleContainer";
import JoinButton from "../../components/button-join/JoinButton";
import { useNavigate } from "react-router-dom";

const Join = () => {
    const [sections, setSections] = useState([
        { id: 1, title: 'Chess Club', description: 'Click to see more...' },
        { id: 2, title: 'Music Band', description: 'Click to see more...' },
        { id: 3, title: 'Art Club', description: 'Click to see more...' },
        { id: 4, title: 'Football Team', description: 'Click to see more...' }
    ]);
    const nav = useNavigate();

    useEffect(() => {
        // TODO: Add axios to fetch sections from an API
        // Example: axios.get('/api/sections').then(response => setSections(response.data));
    }, []);

    const handleSectionClick = (section) => {
        // TODO: Handle what happens when a section is clicked
        console.log(`Section clicked: ${section.title}`);
        nav("/section")
    };

    const handleJoinClick = (section) => {
        // TODO: Handle what happens when a section is clicked
        console.log(`Section clicked: ${section.title}`);
        nav("/profile")
    };

    return (
        <div className="container">
            <TitleContainer title={"Dobrodošli!"} description={"Izaberi sekciju u koju se želite učlaniti"} />
            <div className="buttonList buttonList2">
                {sections.map((section) => (
                    <JoinButton key={section.id} item={section} onClick={handleSectionClick} onJoinClick={handleJoinClick} />
                ))}
            </div>
        </div>
    );
};

export default Join;