import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Events.css';

const Events = () => {
  return (
    <div>
      <div className="header">
        <div className="navbar-icon">
          <div className="bar"></div>
          <div className="bar"></div>
          <div className="bar"></div>
        </div>
        <span className="header-text">Događaji</span>
      </div>
      <div className="cards-container">
        <Link to="/event" className="card-link">
          <div className="card">
            <h2 className="card-title">Naslov Događaja 1</h2>
            <p className="card-content">Content of the first event goes here.</p>
          </div>
        </Link>
        <Link to="/event" className="card-link">
          <div className="card">
            <h2 className="card-title">Naslov Događaja 2</h2>
            <p className="card-content">Content of the second event goes here.</p>
          </div>
        </Link>
        <Link to="/event" className="card-link">
          <div className="card">
            <h2 className="card-title">Naslov Događaja 3</h2>
            <p className="card-content">Content of the third event goes here.</p>
          </div>
        </Link>
      </div>
    </div>
  );
};

export default Events;
