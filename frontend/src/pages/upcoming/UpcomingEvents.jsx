import React, { useState } from 'react';
import Calendar from 'react-calendar'; // You can install this using `npm install react-calendar`
import './UpcomingEvents.css';
import TitleContainer from '../../components/titleContainer/TitleContainer';
import NewsButton from '../../components/button-news/NewsButton';
import MyCalendar from '../../components/calendar/MyCalendar';

const UpcomingEvents = () => {
  // Sample event data (can come from props or an API call)
  const events = [
    { title: "Naslov Događaja", date: new Date(2024, 8, 11), author: "AAAAA" },
    { title: "Naslov Događaja", date: new Date(2021, 8, 19), author: "AAAAA" }
  ];

  return (
    <div className="container">
      {/* TitleContainer */}
      <TitleContainer title={"Upcoming Events"} description={"See what's coming your way!"}/>

      <MyCalendar />

      {/* Upcoming events list */}
      <div className="buttonList">
        <h3>Nadolazeći događaji:</h3>
        {events.map((event, index) => (
          <NewsButton key={index} item={event} onClick={()=>{}} />
        ))}
      </div>
    </div>
  );
};

export default UpcomingEvents;
