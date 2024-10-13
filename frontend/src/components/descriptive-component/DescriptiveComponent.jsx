import React from 'react';
import './DescriptiveComponent.css'; // Optional, if you want to style it in a separate CSS file

const DescriptiveComponent = ({ item, onClick }) => {

    return (
        <div className="descriptive-card" onClick={()=>onClick(item)}>
            <h1 className="title">{item.title}</h1>
            <p className="author">{item.author}</p>
            <p className="created-at">Kreirana: {item.createdAt}</p>
        </div>
    );
};

export default DescriptiveComponent;
