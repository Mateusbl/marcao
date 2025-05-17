import React from 'react';
import { BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer, PieChart, Pie, Cell, Legend } from 'recharts';
import { Box, Typography, Grid, Paper } from '@mui/material';
import { useLocation, useNavigate } from 'react-router-dom';

const salesData = [
  { name: 'Jan', sales: 5, revenue: 120000 },
  { name: 'Feb', sales: 8, revenue: 180000 },
  { name: 'Mar', sales: 12, revenue: 250000 },
  { name: 'Apr', sales: 7, revenue: 140000 },
  { name: 'May', sales: 10, revenue: 210000 },
];

const carPieData = [
  { name: 'Fiat Uno', value: 8 },
  { name: 'VW Gol', value: 6 },
  { name: 'Chevrolet Onix', value: 4 },
  { name: 'Hyundai HB20', value: 2 },
];

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];

export default function Dashboard() {
  const location = useLocation();
  const navigate = useNavigate();
  const navLinks = [
    { label: 'Carros', path: '/cars' },
    { label: 'Clientes', path: '/customers' },
    { label: 'Funcionários', path: '/employees' },
    { label: 'Serviços', path: '/services' },
    { label: 'Vendas', path: '/sales' },
  ];
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
        .dashboard-card {
          background: linear-gradient(135deg, #232526 0%, #414345 100%);
          color: #fff;
          border-radius: 18px;
          box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
          transition: transform 0.3s cubic-bezier(.4,2,.6,1), box-shadow 0.3s;
          height: 100%;
          width: 100%;
          min-height: 340px;
          max-width: 700px;
          max-height: 500px;
          margin: 0 auto;
          display: flex;
          flex-direction: column;
          justify-content: center;
        }
        .dashboard-card:hover {
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
      <Typography variant="h3" align="center" gutterBottom sx={{ color: '#90caf9', fontWeight: 700, letterSpacing: 2, textShadow: '0 2px 8px #0008', maxWidth: 900, mx: 'auto' }}>
        Dashboard de Vendas
      </Typography>
      <Grid container spacing={4} justifyContent="center" alignItems="stretch" sx={{ mb: 4, width: '100%', flex: 1, height: '100%', maxWidth: 1200, mx: 'auto' }}>
        <Grid item xs={12} md={8} sx={{ height: '100%', maxWidth: 700, mx: 'auto' }}>
          <Paper className="dashboard-card" sx={{ p: 3, height: '100%', minHeight: 400, maxHeight: 500, width: '100%', maxWidth: 700, mx: 'auto', display: 'flex', flexDirection: 'column', justifyContent: 'stretch' }}>
            <Typography variant="h6" gutterBottom sx={{ color: '#b2dfdb' }}>Vendas Mensais</Typography>
            <Box sx={{ width: '100%', maxWidth: 600, mx: 'auto', height: 320, maxHeight: 320 }}>
              <ResponsiveContainer width="100%" height={300}>
                <BarChart data={salesData} barCategoryGap={30}>
                  <XAxis dataKey="name" stroke="#b2dfdb" />
                  <YAxis yAxisId={0} orientation="left" stroke="#b2dfdb" />
                  <YAxis yAxisId={1} orientation="right" hide />
                  <Tooltip contentStyle={{ background: '#232526', border: 'none', color: '#fff' }} />
                  <Legend wrapperStyle={{ color: '#b2dfdb' }} />
                  <Bar yAxisId={0} dataKey="sales" fill="#90caf9" name="Vendas" radius={[8,8,0,0]}>
                    {salesData.map((_, i) => (
                      <Cell key={i} fill={`url(#barGradient${i})`} />
                    ))}
                  </Bar>
                  <Bar yAxisId={1} dataKey="revenue" fill="#b2dfdb" name="Receita" radius={[8,8,0,0]} />
                  <defs>
                    {salesData.map((_, i) => (
                      <linearGradient key={i} id={`barGradient${i}`} x1="0" y1="0" x2="0" y2="1">
                        <stop offset="0%" stopColor="#90caf9" stopOpacity={0.9} />
                        <stop offset="100%" stopColor="#232526" stopOpacity={0.7} />
                      </linearGradient>
                    ))}
                  </defs>
                </BarChart>
              </ResponsiveContainer>
            </Box>
          </Paper>
        </Grid>
        <Grid item xs={12} md={4} sx={{ height: '100%', maxWidth: 400, mx: 'auto' }}>
          <Paper className="dashboard-card" sx={{ p: 3, height: '100%', minHeight: 400, maxHeight: 500, width: '100%', maxWidth: 400, mx: 'auto', display: 'flex', flexDirection: 'column', justifyContent: 'stretch' }}>
            <Typography variant="h6" gutterBottom sx={{ color: '#b2dfdb' }}>Carros Mais Vendidos</Typography>
            <Box sx={{ width: '100%', maxWidth: 350, mx: 'auto', height: 320, maxHeight: 320 }}>
              <ResponsiveContainer width="100%" height={300}>
                <PieChart>
                  <Pie data={carPieData} dataKey="value" nameKey="name" cx="50%" cy="50%" outerRadius={90} label>
                    {carPieData.map((entry, index) => (
                      <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                    ))}
                  </Pie>
                  <Tooltip contentStyle={{ background: '#232526', border: 'none', color: '#fff' }} />
                  <Legend wrapperStyle={{ color: '#b2dfdb' }} />
                </PieChart>
              </ResponsiveContainer>
            </Box>
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
}
