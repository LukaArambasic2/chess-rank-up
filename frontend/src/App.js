// src/App.js
import React from 'react';
import './App.css';
import Activity from './pages/activity/Activity';
import Login from './pages/login/Login';
import Reset from './pages/reset/Reset';
import Scoreboard from './pages/scoreboard/ScoreboardList';

import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import Scoreboard from './components/Scoreboard';
import HomePage from './pages/home/HomePage';
import Profile from './pages/profile/Profile';
import ScoreboardTotal from './pages/scoreboard/Scoreboard';
import Registration from './pages/registration/Registration';
import News from './pages/news/News';
import About from './pages/about/About';
import Section from './pages/section/Section';
import Join from './pages/join/Join';
import UpcomingEvents from './pages/upcoming/UpcomingEvents';
import Post from './pages/post/Post';
import Admin from './pages/admin/Admin';
import AddPoints from './pages/admin/addPoints/AddPoints';
import AddEvents from './pages/admin/addEvents/AddEvents';
import AddNews from './pages/admin/addNews/AddNews';
import AllUsers from './pages/admin/allUsers/AllUsers';

const sections = [
  { id: 1, name: 'Šahovska sekcija', description: 'Igramo šah', to:"/profile" },
  { id: 2, name: 'Neka druga sekcija', description: 'Igramo nešto drugo', to:"/profile" },
  // Add more sections as needed
];
const homeForward = [
  { id: 1, name: 'Moje sekcije', description: 'Sekcije u kojima si prijavljen', to: "/my-sections" },
  { id: 2, name: 'Sve sekcije', description: 'Popis svih sekcija na FER-u', to: "/join" },
]

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage linkList={homeForward} />} />
        <Route path="/my-sections" element={<HomePage linkList={sections} />} />
        <Route path="/join" element={<Join />} />
        
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

        <Route path="/news" element={<News />} />
        <Route path="/post" element={<Post />} />
        <Route path="/events" element={<UpcomingEvents />} />
        
        <Route path="/about" element={<About />} />

        <Route path="/section" element={<Section />} />

        <Route path='/admin' element={<Admin />} />
        <Route path='/admin/add-points' element={<AddPoints  />} />
        <Route path='/admin/add-events' element={<AddEvents  />} />
        <Route path='/admin/add-news' element={<AddNews  />} />
        <Route path='/admin/all-users' element={<AllUsers  />} />

      </Routes>
    </Router>
  );
}

export default App;
