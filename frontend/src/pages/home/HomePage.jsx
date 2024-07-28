// Zakomentirano dok se ne implementira DropDown //

import React, { useState } from 'react';
// import HamburgerMenu from '../components/HamburgerMenu'; //
import './HomePage.css';

const HomePage = ({ sections }) => {
  const [selectedSection, setSelectedSection] = useState(sections[0]);

  const handleSectionClick = (section) => {
    if (selectedSection.id === section.id) {
      return;
    } else {
      setSelectedSection(section);
    }
  };

  return (
    <div className="home-page">
      {/* <HamburgerMenu /> */}
      <div className="welcome-message">
        <h1>{selectedSection.heading}</h1>
        <p>{selectedSection.description}</p>
      </div>
      <div className="section-list">
        {sections.map((section) => (
          <button key={section.id} onClick={() => handleSectionClick(section)}>
            {section.name}
          </button>
        ))}
      </div>
    </div>
  );
};

export default HomePage;
