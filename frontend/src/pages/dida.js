import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Dida.css';

const dida = () => {
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
            <h2 className="card-title">Naslov Događaja</h2>
            <p className="card-content">Content of the first event goes here.</p>
            <div className="card-footer">
              <p>Datum: 11.9.2021</p>
              <p>Autor: AAAAA</p>
            </div>
          </div>
        </Link>
        <Link to="/event" className="card-link">
          <div className="card">
            <h2 className="card-title">Naslov Događaja</h2>
            <p className="card-content">Content of the second event goes here.</p>
            <div className="card-footer">
              <p>Datum: 12.9.2021</p>
              <p>Autor: BBBBB</p>
            </div>
          </div>
        </Link>
        <Link to="/event" className="card-link">
          <div className="card">
            <h2 className="card-title">Naslov Događaja</h2>
            <p className="card-content">Content of the third event goes here.</p>
            <div className="card-footer">
              <p>Datum: 13.9.2021</p>
              <p>Autor: CCCCC</p>
            </div>
          </div>
        </Link>
      </div>
    </div>
  );
};

export default dida;
