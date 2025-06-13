// filepath: frontend/src/js/employees.js
async function fetchEmployees() {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    const res = await fetch('/api/employees');
    const employees = await res.json();
    const tbody = document.querySelector('#employeesTable tbody');
    tbody.innerHTML = '';
    employees.forEach(employee => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${employee.id}</td>
            <td>${employee.name}</td>
            <td>${employee.position}</td>
            <td><button onclick="deleteEmployee('${employee.id}')">Remover</button></td>
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
  const res = await fetch("/api/employees");
  const employees = await res.json();
  const tbody = document.querySelector("#employeesTable tbody");
  tbody.innerHTML = "";
  employees.forEach((employee) => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
            <td>${employee.id}</td>
            <td>${employee.nome}</td>
            <td>${employee.cpf}</td>
            <td>${employee.email}</td>
            <td>${employee.endereco}</td>
            <td>${employee.telefone}</td>
            <td>${employee.cargo}</td>
            <td>${
              employee.salario != null
                ? employee.salario.toLocaleString("pt-BR", {
                    style: "currency",
                    currency: "BRL",
                  })
                : ""
            }</td>
            <td>${
              employee.dataContratacao
                ? new Date(employee.dataContratacao).toLocaleDateString("pt-BR")
                : ""
            }</td>
            <td><button onclick="deleteEmployee('${
              employee.id
            }')">Remover</button></td>
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
        `;
    tbody.appendChild(tr);
  });
}

async function deleteEmployee(id) {
  await fetch(`/api/employees/${id}`, { method: "DELETE" });
  fetchEmployees();
}

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
document.getElementById('employeeForm').onsubmit = async function(e) {
    e.preventDefault();
    const name = document.getElementById('employeeName').value;
    const position = document.getElementById('employeePosition').value;
    await fetch('/api/employees', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            id: Math.random().toString(36).substring(2, 10),
            name, position
        })
    });
    this.reset();
    fetchEmployees();
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
document.getElementById("employeeForm").onsubmit = async function (e) {
  e.preventDefault();
  const nome = document.getElementById("employeeName").value;
  const cargo = document.getElementById("employeePosition").value;
  const cpf = document.getElementById("employeeCpf").value;
  const endereco = document.getElementById("employeeEndereco").value;
  const telefone = document.getElementById("employeeTelefone").value;
  const email = document.getElementById("employeeEmail").value;
  const salario = document.getElementById("employeeSalario").value;
  await fetch("/api/employees", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      cpf,
      nome,
      endereco,
      telefone,
      email,
      cargo,
      salario: parseFloat(salario),
      dataContratacao: new Date().toISOString(),
    }),
  });
  this.reset();
  fetchEmployees();
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
};

fetchEmployees();
