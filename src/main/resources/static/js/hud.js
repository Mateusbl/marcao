document.addEventListener("DOMContentLoaded", function () {
  const hud = `
    <header>
      <nav class="navbar">
        <ul>
          <li><a href="index.html">Início</a></li>
          <li><a href="cars.html">Gerenciar Carros</a></li>
          <li><a href="customers.html">Gerenciar Clientes</a></li>
          <li><a href="employees.html">Gerenciar Funcionários</a></li>
          <li><a href="sales.html">Gerenciar Vendas</a></li>
          <li><a href="services.html">Gerenciar Serviços</a></li>
        </ul>
      </nav>
    </header>
  `;
  const hudDiv = document.getElementById("hud");
  if (hudDiv) {
    hudDiv.innerHTML = hud;
  }
});
