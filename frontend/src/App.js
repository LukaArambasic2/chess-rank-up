// src/App.js
import React from 'react';
<<<<<<< HEAD
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import News from './pages/News';
import Events from './pages/Events';
import Event from './pages/Event';
import NewNovi from './pages/NewNovi';
import PocetnaNeLogirana from './pages/PocetnaNeLogirana'; // Import the new page
import Liga from './pages/Liga'; // Import the new page
=======
import { createBrowserRouter } from 'react-router-dom';
import './App.css';
import Aktivnost from './pages/activity/Aktivnost';
import ExamplePage from './pages/examplePage/ExamplePage';
import OnlineLiga from './pages/leaderboard/OnlineLiga';
import Prijava from './pages/login/Prijava';
import Profil from './pages/Profil';
import Registracija from './pages/registration/Registracija';
import Reset from './pages/reset/Reset';
import SBgodina from './pages/SBgodina';
import SBsemestar from './pages/SBsemestar';
import SBukupno from './pages/SBukupno';
import Scoreboard from './pages/scoreboard/Scoreboard';

import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import HomePage from './pages/home/HomePage';

const sections = [
  { id: 'section1', name: 'Šahovska sekcija', heading: 'DOBRO DOŠLI', description: 'Igramo šah' },
  { id: 'section2', name: 'Neka druga sekcija', heading: 'DOBRO DOŠLI', description: 'Igramo nešto drugo' },
  // Add more sections as needed
];
>>>>>>> upstream/frontend

function App() {
  const router = createBrowserRouter([
    {
<<<<<<< HEAD
      path: '/news',
      element: <News />,
    },
    {
      path: '/NewNovi',
      element: <NewNovi />,
    },
    {
      path: '/event',
      element: <Event />,
    },
    {
      path: '/events',
      element: <Events />,
    },
    {
      path: '/PocetnaNeLogirana', // Define the route for the new page
      element: <PocetnaNeLogirana />,
    },
    {
      path: '/Liga', // Define the route for the new page
      element: <Liga />,
    },
  ]);
=======
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
      path: "/scoreboard/semestar",
      element: <SBsemestar />
    },

    {
      path: "/scoreboard/godina",
      element: <SBgodina />
    },

    {
      path: "/scoreboard/ukupno",
      element: <SBukupno />
    },

    {
      path: "/onlineLiga",
      element: <OnlineLiga />
    }
  ])

>>>>>>> upstream/frontend

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
