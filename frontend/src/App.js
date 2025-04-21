// src/App.js
import React from 'react';
import './App.css';
import Activity from './pages/activity/Activity';
import Login from './pages/login/Login';
import Reset from './pages/reset/Reset';
import ScoreboardList from './pages/scoreboard/ScoreboardList';

import {Route, BrowserRouter as Router, Routes, Outlet, Navigate} from 'react-router-dom';
import HomePage from './pages/home/HomePage';
import Profile from './pages/profile/Profile';
import Scoreboard from './pages/scoreboard/Scoreboard';
import Registration from './pages/registration/Registration';
import News from './pages/news/News';
import About from './pages/about/About';
import Section from './pages/section/Section';
import Join from './pages/join/Join';
import Post from './pages/post/Post';
import Admin from './pages/admin/Admin';
import AddPoints from './pages/admin/addPoints/AddPoints';
import AddEvents from './pages/admin/addEvents/AddEvents';
import AddNews from './pages/admin/addNews/AddNews';
import AllUsers from './pages/admin/allUsers/AllUsers';
import EnrolledSections from "./pages/enrolled/EnrolledSections";
import {AuthProvider, useAuth} from "./contexts/AuthProvider";
import {SectionProvider} from "./contexts/SectionProvider";

const sections = [
  { id: 1, name: 'Šahovska sekcija', description: 'Igramo šah', to:"/profile" },
  { id: 2, name: 'Neka druga sekcija', description: 'Igramo nešto drugo', to:"/profile" },
  // Add more sections as needed
];

const PrivateRoute = () => {
  const {user} = useAuth();
  return user ? <Outlet /> : <Navigate to='/login' replace />
}

function App() {

  return (
      <AuthProvider>
      <SectionProvider>
        <Router>
          <Routes>


            <Route path="/register" element={<Registration />} />
            <Route path="/login" element={<Login />} />
            <Route path="/reset" element={<Reset />} />
            <Route path="/about" element={<About />} />

            <Route element={<PrivateRoute />}>
              <Route path="/" element={<HomePage />} />
              <Route path="/my-sections" element={<EnrolledSections />} />
              <Route path="/join" element={<Join />} />

              <Route path="/profile" element={<Profile />} />
              <Route path="/profile/activity" element={<Activity />} />

              <Route path="/scoreboard" element={<ScoreboardList />} />
              <Route path="/scoreboard/semester" element={<Scoreboard name={"Semester"} description={"Bodovi u semestru"} />} />
              <Route path="/scoreboard/year" element={<Scoreboard name={"Year"} description={"Bodovi ove godine"} />} />
              <Route path="/scoreboard/total" element={<Scoreboard name={"Total"} description={"Svi bodovi ikad"}/>} />
              <Route path="/scoreboard/league" element={<Scoreboard name={"Liga"} description={"Bodovi u Ligi"}/>} />

              <Route path="/news" element={<News />} />
              <Route path="/post/:id" element={<Post />} />

              <Route path="/section/:id" element={<Section />} />
            </Route>




            <Route path='/admin' element={<Admin />} />
            <Route path='/admin/add-points' element={<AddPoints  />} />
            <Route path='/admin/add-events' element={<AddEvents  />} />
            <Route path='/admin/add-news' element={<AddNews  />} />
            <Route path='/admin/all-users' element={<AllUsers  />} />

          </Routes>
        </Router>
      </SectionProvider>
      </AuthProvider>
  );
}

export default App;
