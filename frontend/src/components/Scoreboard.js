/* NE TREBA VIŠE */

import React from 'react';
import './Scoreboard.css';

const Scoreboard = () => {
  const topThree = [
    { rank: 2, name: 'Zlatko Dalić', score: 32 },
    { rank: 1, name: 'Izbornik Dena', score: 69 },
    { rank: 3, name: 'Ime Prezime', score: 26 },
  ];

  return (
    <div className="scoreboard">
      {topThree.map((contestant) => (
        <div
          key={contestant.rank}
          className={`contestant-container ${contestant.rank === 1 ? 'first' : ''}`}
        >
          <div className="contestant">
            <h3>{contestant.name.split(' ').join('\n')}</h3>
            <p className="score">{contestant.score}</p>
          </div>
          <div className="rank-circle">
            <p className="rank">{contestant.rank}</p>
          </div>
        </div>
      ))}
    </div>
  );
};

export default Scoreboard;
