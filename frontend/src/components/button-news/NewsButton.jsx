import React from 'react';
import './NewsButton.css'; // Optional, if you want to style it in a separate CSS file

const NewsButton = ({ item, onClick }) => {

    return (
        <div id="descriptive-card" className="listElement" onClick={()=>onClick(item)}>
            <h1 id="news-title">{item.title}</h1>
            <p id="news-author">{item.author.firstName} {item.author.lastName}</p>
            <p id="news-created-at">Objavljeno: {item.dateCreated}</p>
        </div>
    );
};

export default NewsButton;
