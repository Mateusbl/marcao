// filepath: frontend/src/js/customers.js
document.addEventListener('DOMContentLoaded', function() {
    fetchCustomers();

    document.getElementById('customerForm').onsubmit = async function(e) {
        e.preventDefault();
        const name = document.getElementById('customerName').value;
        const email = document.getElementById('customerEmail').value;
        await fetch('/api/customers', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                id: Math.random().toString(36).substring(2, 10),
                name,
                email
            })
        });
        this.reset();
        fetchCustomers();
    };
});

async function fetchCustomers() {
    const res = await fetch('/api/customers');
    const customers = await res.json();
    const tbody = document.querySelector('#customersTable tbody');
    tbody.innerHTML = '';
    customers.forEach(customer => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.email}</td>
            <td><button onclick="deleteCustomer('${customer.id}')">Remover</button></td>
        `;
        tbody.appendChild(tr);
    });
}

async function deleteCustomer(id) {
    await fetch(`/api/customers/${id}`, { method: 'DELETE' });
    fetchCustomers();
}