// filepath: frontend/src/js/employees.js
async function fetchEmployees() {
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
};

fetchEmployees();