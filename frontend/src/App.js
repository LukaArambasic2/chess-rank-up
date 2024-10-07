// src/App.js
import React from 'react';
import './App.css';
import Activity from './pages/activity/Activity';
import Login from './pages/login/Login';
import Reset from './pages/reset/Reset';
import Scoreboard from './pages/scoreboard/ScoreboardList';

import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import HomePage from './pages/home/HomePage';
import Profile from './pages/profile/Profile';
import ScoreboardTotal from './pages/scoreboard/Scoreboard';
import Registration from './pages/registration/Registration';

const sections = [
  { id: 1, name: 'Šahovska sekcija', heading: 'DOBRO DOŠLI', description: 'Igramo šah' },
  { id: 2, name: 'Neka druga sekcija', heading: 'DOBRO DOŠLI', description: 'Igramo nešto drugo' },
  // Add more sections as needed
];

function App() {
  return (
    <Router >
      <Routes>
        <Route path="/" element={<HomePage sections={sections} />} />
        
        <Route path="/register" element={<Registration />} />
        <Route path="/login" element={<Login />} />
        <Route path="/reset" element={<Reset />} />
        
        <Route path="/profile" element={<Profile />} />
        <Route path="/profile/activity" element={<Activity />} />

        <Route path="/scoreboard" element={<Scoreboard />} />
        <Route path="/scoreboard/semester" element={<ScoreboardTotal name={"Semester"} description={"Bodovi u semestru"} />} />
        <Route path="/scoreboard/year" element={<ScoreboardTotal name={"Year"} description={"Bodovi ove godine"} />} />
        <Route path="/scoreboard/total" element={<ScoreboardTotal name={"Total"} description={"Svi bodovi ikad"}/>} />
        <Route path="/scoreboard/league" element={<ScoreboardTotal name={"Liga"} description={"Bodovi u Ligi"}/>} />

      </Routes>
    </Router>
  );
}

export default App;
