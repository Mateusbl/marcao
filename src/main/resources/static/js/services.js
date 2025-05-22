// filepath: frontend/src/js/services.js
async function fetchServices() {
    const res = await fetch('/api/services');
    const services = await res.json();
    const tbody = document.querySelector('#servicesTable tbody');
    tbody.innerHTML = '';
    services.forEach(service => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${service.id}</td>
            <td>${service.description}</td>
            <td>${service.cost}</td>
            <td><button onclick="deleteService('${service.id}')">Remover</button></td>
        `;
        tbody.appendChild(tr);
    });
}

async function deleteService(id) {
    await fetch(`/api/services/${id}`, { method: 'DELETE' });
    fetchServices();
}

document.getElementById('serviceForm').onsubmit = async function(e) {
    e.preventDefault();
    const description = document.getElementById('serviceDescription').value;
    const cost = document.getElementById('serviceCost').value;
    await fetch('/api/services', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            id: Math.random().toString(36).substring(2, 10),
            description, cost
        })
    });
    this.reset();
    fetchServices();
};

fetchServices();