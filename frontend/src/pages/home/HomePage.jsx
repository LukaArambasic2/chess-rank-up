// Zakomentirano dok se ne implementira DropDown //

import React, { useState } from 'react';
// import HamburgerMenu from '../components/HamburgerMenu'; //
import './HomePage.css';
import TitleContainer from '../../components/titleContainer/TitleContainer';
import Button from '../../components/button/Button';
import { useNavigate } from 'react-router-dom';

const HomePage = ({ linkList }) => {
  const [selectedSection, setSelectedSection] = useState(null);
  const nav = useNavigate();

  const handleSectionClick = (linkList) => {
    console.log(linkList);
    
    nav(linkList.to);
    if (selectedSection !== null && selectedSection.id === linkList.id) {
      return;
    } else {
      setSelectedSection(linkList)
    }
  };

  return (
    <div className="container">
      <TitleContainer title={"Dobrodošli!"} description={"Izaberi sekciju u koju se želite učlaniti"} />
      <div className="buttonList">
        {linkList.map((linkList) => (
          <Button key={linkList.id} item={linkList} onClick={handleSectionClick}/>
        ))}
      </div>
    </div>
  );
};

export default HomePage;
