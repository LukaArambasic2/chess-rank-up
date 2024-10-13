import React from "react";
import "./News.css";
import TitleContainer from "../../components/titleContainer/TitleContainer";
import { useNavigate } from "react-router-dom";
import DescriptiveComponent from "../../components/descriptive-component/DescriptiveComponent";

const News = () => {
    const nav = useNavigate();

    const newsList = [
        {id: 1, title: "Obavijest 1", description: "U prvoj obavijesti ćemo...", time: "2024-10-12 10:00", author: "Autor 1"},
        {id: 2, title: "Obavijest 2", description: "U drugoj obavijesti ćemo...", time: "2024-10-12 11:00", author: "Autor 2"},
        {id: 3, title: "Obavijest 3", description: "U trećoj obavijesti ćemo...", time: "2024-10-12 12:00", author: "Autor 3"},
        {id: 4, title: "Obavijest 4", description: "U četvrtoj obavijesti ćemo...", time: "2024-10-12 13:00", author: "Autor 4"},
        {id: 5, title: "Obavijest 5", description: "U petoj obavijesti ćemo...", time: "2024-10-12 14:00", author: "Autor 5"},
        {id: 6, title: "Obavijest 6", description: "U šestoj obavijesti ćemo...", time: "2024-10-12 15:00", author: "Autor 6"},
        {id: 7, title: "Obavijest 7", description: "U sedmoj obavijesti ćemo...", time: "2024-10-12 16:00", author: "Autor 7"},
        {id: 8, title: "Obavijest 8", description: "U osmoj obavijesti ćemo...", time: "2024-10-12 17:00", author: "Autor 8"},
        {id: 9, title: "Obavijest 9", description: "U devetoj obavijesti ćemo...", time: "2024-10-12 18:00", author: "Autor 9"},
        {id: 10, title: "Obavijest 10", description: "U desetoj obavijesti ćemo...", time: "2024-10-12 19:00", author: "Autor 10"}
    ];
    
    
    const handleClick = ({item}) => {
        nav(`/post/${item.id}`)
    }

    return (
        <div className="container">
            <TitleContainer title={"News page"} description={"Najnovije vijesti o sekciji!"} />

            <div className="listContainer">
            {newsList.map(post => (
                <DescriptiveComponent item={post} onClick={handleClick}/>
            ))}
            </div>
        </div>
    )
}

export default News;