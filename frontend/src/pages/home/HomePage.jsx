// Zakomentirano dok se ne implementira DropDown //

import React, { useState } from 'react';
// import HamburgerMenu from '../components/HamburgerMenu'; //
import './HomePage.css';
import TitleContainer from '../../components/titleContainer/TitleContainer';
import Button from '../../components/button/Button';

const HomePage = ({ sections }) => {
  const [selectedSection, setSelectedSection] = useState(null);

  const handleSectionClick = (section) => {
    console.log(section);
    
    if (selectedSection!==null && selectedSection.id === section.id) {
      return;
    } else {
      setSelectedSection(section);
    }
  };

  return (
    <div className="home-page">
      {/* <HamburgerMenu /> */}
      <TitleContainer title={"Dobrodošli!"} description={"Izaberi sekciju"} />
      <div className="buttonList">
        {sections.map((section) => (
          <Button key={section.id} item={section} onClick={handleSectionClick}/>
        ))}
      </div>
    </div>
  );
};

export default HomePage;
