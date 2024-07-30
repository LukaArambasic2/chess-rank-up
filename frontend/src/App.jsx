// src/App.js
import React from 'react';
import { createBrowserRouter } from 'react-router-dom';
import './App.css';
import Aktivnost from './pages/activity/Aktivnost';
import ExamplePage from './pages/examplePage/ExamplePage';
import OnlineLiga from './pages/leaderboard/OnlineLiga';
import Prijava from './pages/login/Prijava';
import Profil from './pages/profile/Profil';
import Registracija from './pages/registration/Registracija';
import Reset from './pages/reset/Reset';
import Scoreboard from './pages/scoreboard/Scoreboard';

import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import HomePage from './pages/home/HomePage';

const sections = [
  { id: 'section1', name: 'Šahovska sekcija', heading: 'DOBRO DOŠLI', description: 'Igramo šah' },
  { id: 'section2', name: 'Neka druga sekcija', heading: 'DOBRO DOŠLI', description: 'Igramo nešto drugo' },
  // Add more sections as needed
];


function App() {
  
  const router = createBrowserRouter([
    {
      path: "/",
      element: <ExamplePage />
    },

    {
      path: "/registracija",
      element: <Registracija />
    },

    {
      path: "/prijava",
      element: <Prijava />
    },

    {
      path: "/resetiranjeLozinke",
      element: <Reset />
    },

    {
      path: "/profil",
      element: <Profil />
    },

    {
      path: "/profil/aktivnost",
      element: <Aktivnost />
    },

    {
      path: "/scoreboard",
      element: <Scoreboard />
    },

    {
      path: "/onlineLiga",
      element: <OnlineLiga />
    }
  ])


  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage sections={sections} />} />
        <Route path="/scoreboard" element={<Scoreboard />} />
        <Route path="/prijava" element={<Prijava />} />
        <Route path="/registracija" element={<Registracija />} />
        <Route path="/onlineLiga" element={<OnlineLiga />} />
        <Route path="/profil" element={<Profil />} />
        <Route path="/profil/aktivnost" element={<Aktivnost />} />
        <Route path="/resetiranjeLozinke" element={<Reset />} />
      </Routes>
    </Router>
  );
}

export default App;
