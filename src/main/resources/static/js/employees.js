// filepath: frontend/src/js/employees.js
async function fetchEmployees() {
    const res = await fetch('/api/employees');
    const employees = await res.json();
    const tbody = document.querySelector('#employeesTable tbody');
    tbody.innerHTML = '';    employees.forEach(employee => {
        const tr = document.createElement('tr');        tr.innerHTML = `
            <td>${employee.id}</td>
            <td>${employee.nome}</td>
            <td>${employee.cargo}</td>
            <td><button onclick="deleteEmployee('${employee.id}')">Remover</button></td>
        `;
        tbody.appendChild(tr);
    });
}

async function deleteEmployee(id) {
    await fetch(`/api/employees/${id}`, { method: 'DELETE' });
    fetchEmployees();
}

document.getElementById('employeeForm').onsubmit = async function(e) {
    e.preventDefault();
    const nome = document.getElementById('employeeName').value;
    const cargo = document.getElementById('employeePosition').value;
    const cpf = document.getElementById('employeeCpf').value;
    const endereco = document.getElementById('employeeEndereco').value;
    const telefone = document.getElementById('employeeTelefone').value;
    const email = document.getElementById('employeeEmail').value;
    const salario = document.getElementById('employeeSalario').value;
    await fetch('/api/employees', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            cpf,
            nome,
            endereco,
            telefone,
            email,
            cargo,
            salario: parseFloat(salario),
            dataContratacao: new Date().toISOString()
        })
    });
    this.reset();
    fetchEmployees();
};

fetchEmployees();