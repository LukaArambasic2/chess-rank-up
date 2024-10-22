import React from 'react';
import { Table, TableBody, TableCell, TableHead, TableRow, Paper } from '@mui/material';
import TitleContainer from '../../../components/titleContainer/TitleContainer';

const AllUsers = () => {
  const users = [
    { name: 'John', surname: 'Doe', id: 1 },
    { name: 'Jane', surname: 'Smith', id: 2 },
    { name: 'Tom', surname: 'Brown', id: 3 }
  ];

  return (
    <div className='container'>
        <TitleContainer title={"Svi korisnici"} description={"Pogledaj listu svih korisnika"} />
        <Paper style={styles.paper}>
        <h2>Sudionici</h2>
        <Table>
            <TableHead>
            <TableRow>
                <TableCell>Ime</TableCell>
                <TableCell>Prezime</TableCell>
                <TableCell>ID</TableCell>
            </TableRow>
            </TableHead>
            <TableBody>
            {users.map((user) => (
                <TableRow key={user.id}>
                <TableCell>{user.name}</TableCell>
                <TableCell>{user.surname}</TableCell>
                <TableCell>{user.id}</TableCell>
                </TableRow>
            ))}
            </TableBody>
        </Table>
        </Paper>
    </div>
  );
};

const styles = {
  paper: {
    padding: '20px',
    backgroundColor: '#f0f4ff',
    borderRadius: '8px',
    maxWidth: '400px',
    margin: '0 auto'
  }
};

export default AllUsers;
