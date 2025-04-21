import React from 'react';
import { Button, TextField } from '@mui/material';
import "./AddNews.css";
import TitleContainer from '../../../components/titleContainer/TitleContainer';

const AddNews = () => {
  return (
    <div className='container'>
      <TitleContainer title={"Nova obavijest"} description={"Dodaj personaliziranu obavijest"} />
        <TextField label="Naslov" fullWidth className='formControl' />
        <TextField label="Tekst" multiline rows={4} fullWidth className='formControl' />
        <Button variant="contained" color="primary" className='formControl'>Post News</Button>
    </div>
  );
};

export default AddNews;
