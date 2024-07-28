import React from 'react';
import './App.css';
import {createBrowserRouter, RouterProvider} from 'react-router-dom';
import ExamplePage from './pages/examplePage/ExamplePage';
import Registracija from './pages/Registracija';
import Prijava from './pages/Prijava';
import Reset from './pages/Reset';
import Profil from './pages/Profil';
import Aktivnost from './pages/Aktivnost';
import Scoreboard from './pages/Scoreboard';
import SBukupno from './pages/SBukupno';
import SBsemestar from './pages/SBsemestar';
import SBgodina from './pages/SBgodina';
import OnlineLiga from './pages/OnlineLiga';


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


  return (
    <React.StrictMode>
      <RouterProvider router={router} />
    </React.StrictMode>
  );
}

export default App;