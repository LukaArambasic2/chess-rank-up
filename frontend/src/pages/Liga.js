import React from 'react';
import '../styles/Liga.css'; 

const Liga = () => {
  return (
    <div className="new-page-two">
      <div className="header">
        <div className="navbar-icon">
          <div className="bar"></div>
          <div className="bar"></div>
          <div className="bar"></div>
        </div>
        <div className="header-text">
          <h1>Online League</h1>
          <p>Opisni tekst u kojem se objašnjava što je to liga i kako funkcionira</p>
        </div>
      </div>
      <div className="cards-section">
        <div className="card">
          <div className="card-number">2</div>
          <div className="card-title">Ime Prezime</div>
          <div className="card-content">
            {/* Add content for the first card */}
            <p>Content</p>
          </div>
        </div>
        <div className="card">
          <div className="card-number">1</div>
          <div className="card-title">Ime Prezime</div>
          <div className="card-content">
            {/* Add content for the second card */}
            <p>Content</p>
          </div>
        </div>
        <div className="card">
          <div className="card-number">3</div>
          <div className="card-title">Ime Prezime</div>
          <div className="card-content">
            {/* Add content for the third card */}
            <p>Content</p>
          </div>
        </div>
      </div>
      <div className="small-cards-section">
        <div className="small-card">
          <div className="small-card-number">4</div>
          <div className="small-card-title">Ime i Prezime</div>
          <div className="small-card-right-number">23</div>
        </div>
        <div className="small-card">
          <div className="small-card-number">5</div>
          <div className="small-card-title">Ime i Prezime</div>
          <div className="small-card-right-number">21</div>
        </div>
        <div className="small-card">
          <div className="small-card-number">6</div>
          <div className="small-card-title">Ime i Prezime</div>
          <div className="small-card-right-number">20</div>
        </div>
        <div className="small-card">
          <div className="small-card-number">7</div>
          <div className="small-card-title">Ime i Prezime</div>
          <div className="small-card-right-number">18</div>
        </div>
        <div className="small-card">
          <div className="small-card-number">8</div>
          <div className="small-card-title">Ime i Prezime</div>
          <div className="small-card-right-number">17</div>
        </div>
        <div className="small-card">
          <div className="small-card-number">9</div>
          <div className="small-card-title">Ime i Prezime</div>
          <div className="small-card-right-number">13</div>
        </div>
        <div className="small-card">
          <div className="small-card-number">10</div>
          <div className="small-card-title">Ime i Prezime</div>
          <div className="small-card-right-number">11</div>
        </div>
      </div>
    </div>
  );
};

export default Liga;
