import React from 'react';
import { Button, TextField, Checkbox, FormControlLabel } from '@mui/material';
import "./AddEvents.css";
import TitleContainer from '../../../components/titleContainer/TitleContainer';

const AddEvents = () => {
  return (
    <div className='container'>
      <TitleContainer title={"Novi event"} description={"Dodavanje novih evenata"} />
      <TextField label="Naslov" fullWidth className='formControl' />
      <TextField label="Datum" type="date" fullWidth className='formControl' />
      <TextField label="Broj bodova" fullWidth className='formControl' />
      <FormControlLabel control={<Checkbox />} label="Automatski postavi obavijest" />
      <Button variant="contained" color="primary" className='formControl'>Create Event</Button>
    </div>
  );
};

export default AddEvents;
