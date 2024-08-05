// src/pages/NewPage.js
import React from 'react';
import '../styles/PocetnaNeLogirana.css'; // Import the CSS file

const PocetnaNeLogirana = () => {
  return (
    <div className="new-page">
      <div className="header">
        <div className="button-container">
          <button className="custom-button">Registracija</button>
          <button className="custom-button">Prijava</button>
        </div>
        <span className="header-text">Dobro Došli</span>
      </div>
      <div className="below-header-button">
        <button className="custom-button">Popis sekcija</button>
      </div>
      <div className="cards-container">
        <div className="card">
          <button className="card-button top-button">Sekcija</button>
          <div className="bottom-buttons">
            <button className="card-button bottom-button">O sekciji</button>
            <button className="card-button bottom-button">Pridruži se</button>
          </div>
        </div>
        <div className="card">
          <button className="card-button top-button">Sekcija</button>
          <div className="bottom-buttons">
            <button className="card-button bottom-button">O sekciji</button>
            <button className="card-button bottom-button">Pridruži se</button>
          </div>
        </div>
        <div className="card">
          <button className="card-button top-button">Sekcija</button>
          <div className="bottom-buttons">
            <button className="card-button bottom-button">O sekciji</button>
            <button className="card-button bottom-button">Pridruži se</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PocetnaNeLogirana;
