import React from 'react';
import './App.css';
import {createBrowserRouter, RouterProvider} from 'react-router-dom';
import ExamplePage from './pages/examplePage/ExamplePage';

function App() {
  
  const router = createBrowserRouter([
    {
      path: "/",
      element: <ExamplePage />
    }
  ])


  return (
    <React.StrictMode>
      <RouterProvider router={router} />
    </React.StrictMode>
  );
}

export default App;