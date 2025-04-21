import React from 'react';
import './JoinButton.css';

const JoinButton = ({ item, onClick, onJoinClick }) => {
  return (
    <div id="join-button-container" className='listElement'>
      <div className="join-button-content" onClick={()=>onClick(item.id)}>
        <h2 className="join-button-title">{item.name}</h2>
        <p className="join-button-description">Click to see more...</p>
      </div>
        {item && !item.enrolled && (
            <button className="join-button-action" onClick={()=>onJoinClick(item.id)}>Join</button>
        )}
    </div>
  );
};

export default JoinButton;

