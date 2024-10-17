import React, { useState } from 'react';
import Calendar from 'react-calendar'; // You can install this using `npm install react-calendar`
import './UpcomingEvents.css';
import TitleContainer from '../../components/titleContainer/TitleContainer';
import NewsButton from '../../components/button-news/NewsButton';

const UpcomingEvents = () => {
  // Sample event data (can come from props or an API call)
  const events = [
    { title: "Naslov Događaja", date: new Date(2021, 8, 11), author: "AAAAA" },
    { title: "Naslov Događaja", date: new Date(2021, 8, 19), author: "AAAAA" }
  ];

  // State to manage selected date
  const [selectedDate, setSelectedDate] = useState(new Date());

  // Function to check if a date has an event
  const tileContent = ({ date, view }) => {
    if (view === 'month') {
      const eventDates = events.map(event => event.date.toDateString());
      if (eventDates.includes(date.toDateString())) {
        return <span className="highlighted-date" style={{ backgroundColor: 'purple', color: 'white' }}>●</span>;
      }
    }
    return null;
  };

  return (
    <div className="container">
      {/* TitleContainer */}
      <TitleContainer title={"Upcoming Events"} description={"See what's coming your way!"}/>

      {/* Calendar Component */}
      <div className="calendar-container">
        <Calendar
          onChange={setSelectedDate}
          value={selectedDate}
          tileContent={tileContent} // To highlight dates with events
        />
      </div>

      {/* Upcoming events list */}
      <div className="event-list">
        <h3>Nadolazeći događaji:</h3>
        {events.map((event, index) => (
          <NewsButton key={index} item={event} />
        ))}
      </div>
    </div>
  );
};

export default UpcomingEvents;
