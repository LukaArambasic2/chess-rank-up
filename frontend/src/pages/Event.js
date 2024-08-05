import React from 'react';
import '../styles/Event.css';

const Event = () => {
  return (
    <div>
      <div className="header">
        <div className="navbar-icon">
          <div className="bar"></div>
          <div className="bar"></div>
          <div className="bar"></div>
        </div>
        <span className="header-text">Naslov Događaja</span>
        <div className="header-footer">
          <p>datum: xx:xx:xx</p>
          <p>autor: xxx</p>
        </div>
      </div>
      <div className="content">
        <p className="event-text">Ovdje ide tekst o događaju</p>
      </div>
    </div>
  );
};

export default Event;
