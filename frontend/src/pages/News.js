import React from 'react';
import '../styles/News.css';
import { Link } from 'react-router-dom';

const News = () => {
  return (
    <div>
      <div className="header">
        <div className="navbar-icon">
          <div className="bar"></div>
          <div className="bar"></div>
          <div className="bar"></div>
        </div>
        <span className="header-text">Obavijesti</span>
      </div>
      <div className="cards-container">
        <Link to="/NewNovi" className="card">
          <h2 className="card-title">Naslov Obavijesti</h2>
          <p className="card-content">Content of the first card goes here.</p>
          <div className="card-footer">
            <p>autor</p>
            <p>Kreirana: datum</p>
            <p>Posljednja promjena: datum</p>
          </div>
        </Link>
        <Link to="/NewNovi" className="card">
          <h2 className="card-title">Naslov Obavijesti</h2>
          <p className="card-content">Content of the second card goes here.</p>
          <div className="card-footer">
            <p>autor</p>
            <p>Kreirana: datum</p>
            <p>Posljednja promjena: datum</p>
          </div>
        </Link>
        <Link to="/NewNovi" className="card">
          <h2 className="card-title">Naslov Obavijesti</h2>
          <p className="card-content">Content of the third card goes here.</p>
          <div className="card-footer">
            <p>autor</p>
            <p>Kreirana: datum</p>
            <p>Posljednja promjena: datum</p>
          </div>
        </Link>
      </div>
    </div>
  );
};

export default News;
