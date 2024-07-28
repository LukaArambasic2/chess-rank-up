// src/App.js
import React from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import Scoreboard from './components/Scoreboard';
import HomePage from './pages/HomePage';

const sections = [
  { id: 'section1', name: 'Šahovska sekcija', heading: 'DOBRO DOŠLI', description: 'Igramo šah' },
  { id: 'section2', name: 'Neka druga sekcija', heading: 'DOBRO DOŠLI', description: 'Igramo nešto drugo' },
  // Add more sections as needed
];

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage sections={sections} />} />
        <Route path="/scoreboard" element={<Scoreboard />} />
      </Routes>
    </Router>
  );
}

export default App;
