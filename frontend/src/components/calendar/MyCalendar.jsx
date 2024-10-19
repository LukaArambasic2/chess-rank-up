import React, { useState } from 'react';
import Calendar from 'react-calendar';
import './MyCalendar.css';
import 'react-calendar/dist/Calendar.css';

const MyCalendar = () => {
  const [value, setValue] = useState(new Date());

  // Get today's date
  const today = new Date();

  // Dummy list of events around today's date
  const events = [
    {
      date: new Date(today.getFullYear(), today.getMonth(), today.getDate() - 3), // 3 days ago
      authorColor: '#ff7675',  // Author 1's color
    },
    {
      date: new Date(today.getFullYear(), today.getMonth(), today.getDate() + 2), // 2 days from now
      authorColor: '#74b9ff',  // Author 2's color
    },
    {
      date: new Date(today.getFullYear(), today.getMonth(), today.getDate() + 5), // 5 days from now
      authorColor: '#55efc4',  // Author 3's color
    },
    {
      date: new Date(today.getFullYear(), today.getMonth(), today.getDate() + 7), // 7 days from now
      authorColor: '#fdcb6e',  // Author 4's color
    },
    // Add more events if needed
  ];

  // Function to dynamically set inline styles based on author color
  const tileContent = ({ date, view }) => {
    if (view === 'month') {
      const event = events.find(ev => ev.date.getTime() === date.getTime());
      if (event) {
        return (
          <div
            style={{
              backgroundColor: event.authorColor,
              color: 'white',
              borderRadius: '8%',
              width: '100%',
              height: '100%',
              display: 'flex',
              alignSelf: 'center',
              justifyContent: 'center',
            }}
          >
            {date.getDate()}
          </div>
        );
      }
    }
    return <div>{date.getDate()}</div>; // Default display for non-event days
  };

  return (
    <div className="my-calendar">
      <Calendar
        onChange={setValue}
        value={value}
        tileContent={tileContent} // Use tileContent for dynamic content rendering
        tileClassName={"react-calendar__tile"}
      />
    </div>
  );
};

export default MyCalendar;
