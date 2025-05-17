import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Dashboard from './pages/Dashboard';
import Cars from './pages/Cars';
import Customers from './pages/Customers';
import Employees from './pages/Employees';
import Services from './pages/Services';
import Sales from './pages/Sales';
import { AppBar, Toolbar, Typography, Button, Box, Container } from '@mui/material';

function App() {
  const [count, setCount] = useState(0)

  return (
    <Router>
      <AppBar position="static" color="primary">
        <Toolbar>
          <Typography variant="h6" sx={{ flexGrow: 1 }}>Sistema de Venda de Carros</Typography>
          <Button color="inherit" component={Link} to="/">Dashboard</Button>
          <Button color="inherit" component={Link} to="/cars">Carros</Button>
          <Button color="inherit" component={Link} to="/customers">Clientes</Button>
          <Button color="inherit" component={Link} to="/employees">Funcionários</Button>
          <Button color="inherit" component={Link} to="/services">Serviços</Button>
          <Button color="inherit" component={Link} to="/sales">Vendas</Button>
        </Toolbar>
      </AppBar>
      <Box sx={{ background: '#f8f9fa', minHeight: '100vh', py: 4 }}>
        <Container maxWidth="lg">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/cars" element={<Cars />} />
            <Route path="/customers" element={<Customers />} />
            <Route path="/employees" element={<Employees />} />
            <Route path="/services" element={<Services />} />
            <Route path="/sales" element={<Sales />} />
          </Routes>
        </Container>
      </Box>
    </Router>
  )
}

export default App
