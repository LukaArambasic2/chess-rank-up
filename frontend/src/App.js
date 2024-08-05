// src/App.js
import React from 'react';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import News from './pages/News';
import Events from './pages/Events';
import Event from './pages/Event';
import NewNovi from './pages/NewNovi';
import PocetnaNeLogirana from './pages/PocetnaNeLogirana'; // Import the new page
import Liga from './pages/Liga'; // Import the new page

function App() {
  const router = createBrowserRouter([
    {
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

  return (
    <React.StrictMode>
      <RouterProvider router={router} />
    </React.StrictMode>
  );
}

export default App;
