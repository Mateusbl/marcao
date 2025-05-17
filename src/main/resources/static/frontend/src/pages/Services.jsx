import React from 'react';
import { Box, Typography, Paper, Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, Stack } from '@mui/material';
import { useLocation, useNavigate } from 'react-router-dom';

const mockServices = [
  { id: 'S1', description: 'Revisão', cost: 500 },
  { id: 'S2', description: 'Troca de óleo', cost: 200 },
];

export default function Services() {
  const [services, setServices] = React.useState(mockServices);
  const [form, setForm] = React.useState({ description: '', cost: '' });
  const location = useLocation();
  const navigate = useNavigate();
  const navLinks = [
    { label: 'Carros', path: '/cars' },
    { label: 'Clientes', path: '/customers' },
    { label: 'Funcionários', path: '/employees' },
    { label: 'Serviços', path: '/services' },
    { label: 'Vendas', path: '/sales' },
  ];

  const handleChange = e => setForm({ ...form, [e.target.name]: e.target.value });
  const handleAdd = e => {
    e.preventDefault();
    setServices([...services, { ...form, id: Math.random().toString(36).substring(2, 10) }]);
    setForm({ description: '', cost: '' });
  };
  const handleDelete = id => setServices(services.filter(s => s.id !== id));

  return (
    <Box sx={{
      p: { xs: 1, md: 4 },
      minHeight: '100vh',
      minWidth: '100vw',
      height: '100vh',
      width: '100vw',
      maxHeight: '100vh',
      maxWidth: '100vw',
      bgcolor: '#101522',
      color: '#e3f2fd',
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'stretch',
      justifyContent: 'stretch',
      boxSizing: 'border-box',
      animation: 'fadeIn 1.2s',
      overflow: 'hidden',
      position: 'fixed',
      top: 0,
      left: 0,
      zIndex: 0,
    }}>
      <style>{`
        @keyframes fadeIn {
          from { opacity: 0; transform: translateY(40px); }
          to { opacity: 1; transform: translateY(0); }
        }
        .services-card {
          background: linear-gradient(135deg, #232526 0%, #0d47a1 100%);
          color: #fff;
          border-radius: 18px;
          box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
          transition: transform 0.3s cubic-bezier(.4,2,.6,1), box-shadow 0.3s;
        }
        .services-card:hover {
          transform: translateY(-8px) scale(1.03);
          box-shadow: 0 16px 40px 0 rgba(31, 38, 135, 0.47);
        }
        .navbar {
          display: flex;
          justify-content: center;
          align-items: center;
          gap: 32px;
          margin-bottom: 32px;
          background: rgba(16,21,34,0.95);
          border-radius: 16px;
          box-shadow: 0 4px 24px 0 rgba(31,38,135,0.18);
          padding: 0.5rem 2rem;
          animation: fadeIn 1.2s;
          max-width: 900px;
          width: 100%;
          margin-left: auto;
          margin-right: auto;
        }
        .nav-link {
          color: #90caf9;
          font-weight: 600;
          font-size: 1.15rem;
          letter-spacing: 1px;
          text-decoration: none;
          padding: 0.7rem 1.5rem;
          border-radius: 12px;
          background: linear-gradient(90deg, rgba(33,150,243,0.10) 0%, rgba(33,150,243,0.18) 100%);
          box-shadow: 0 2px 8px 0 rgba(33,150,243,0.08);
          transition: background 0.3s, color 0.3s, transform 0.2s, box-shadow 0.3s;
          position: relative;
          overflow: hidden;
          cursor: pointer;
          min-width: 120px;
          text-align: center;
        }
        .nav-link.active, .nav-link:focus {
          background: linear-gradient(90deg, #1976d2 0%, #1565c0 100%);
          color: #fff;
          box-shadow: 0 4px 24px 0 rgba(33,150,243,0.18);
          transform: scale(1.07);
        }
        .nav-link:hover {
          background: linear-gradient(90deg, #2196f3 0%, #1976d2 100%);
          color: #fff;
          transform: scale(1.04);
        }
        .MuiTable-root {
          background: #181c2a;
        }
        .MuiTableCell-root {
          color: #e3f2fd;
        }
      `}</style>
      <nav className="navbar">
        {navLinks.map(({ label, path }) => (
          <span
            key={label}
            className={
              'nav-link' + (location.pathname === path ? ' active' : '')
            }
            tabIndex={0}
            onClick={() => navigate(path)}
            onKeyDown={e => { if (e.key === 'Enter' || e.key === ' ') navigate(path); }}
            role="button"
            aria-current={location.pathname === path ? 'page' : undefined}
          >
            {label}
          </span>
        ))}
      </nav>
      <Typography variant="h4" color="#90caf9" gutterBottom fontWeight={700} letterSpacing={2} textShadow="0 2px 8px #0008">Gerenciar Serviços</Typography>
      <Paper className="services-card" sx={{ p: 3, mb: 4, width: '100%', maxWidth: 900 }}>
        <form onSubmit={handleAdd} style={{ width: '100%' }}>
          <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2} sx={{ width: '100%' }}>
            <TextField fullWidth label="Descrição" name="description" value={form.description} onChange={handleChange} required InputProps={{ style: { color: '#90caf9' } }} />
            <TextField fullWidth label="Custo" name="cost" value={form.cost} onChange={handleChange} required type="number" InputProps={{ style: { color: '#90caf9' } }} />
            <Button type="submit" variant="contained" sx={{ bgcolor: '#1976d2', color: '#fff', fontWeight: 700, boxShadow: '0 2px 8px #0008', minWidth: 120 }}>Adicionar</Button>
          </Stack>
        </form>
      </Paper>
      <TableContainer component={Paper} className="services-card" sx={{ maxWidth: 900, width: '100%' }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Descrição</TableCell>
              <TableCell>Custo</TableCell>
              <TableCell>Ações</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {services.map(service => (
              <TableRow key={service.id}>
                <TableCell>{service.id}</TableCell>
                <TableCell>{service.description}</TableCell>
                <TableCell>R$ {Number(service.cost).toLocaleString('pt-BR')}</TableCell>
                <TableCell><Button color="error" onClick={() => handleDelete(service.id)}>Remover</Button></TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
}
