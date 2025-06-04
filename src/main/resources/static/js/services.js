// filepath: frontend/src/js/services.js
async function fetchServices() {
    const res = await fetch('/api/services');
    const services = await res.json();
    const tbody = document.querySelector('#servicesTable tbody');
    tbody.innerHTML = '';    services.forEach(service => {
        const tr = document.createElement('tr');        tr.innerHTML = `
            <td>${service.idServico}</td>
            <td>${service.nomeServico}</td>
            <td>${service.descricao}</td>
            <td>${service.custoBase}</td>
            <td><button onclick="deleteService('${service.idServico}')">Remover</button></td>
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
    const nomeServico = document.getElementById('serviceName').value;
    const descricao = document.getElementById('serviceDescription').value;
    const custoBase = document.getElementById('serviceCost').value;
    await fetch('/api/services', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            nomeServico,
            descricao,
            custoBase: parseFloat(custoBase)
        })
    });
    this.reset();
    fetchServices();
};

fetchServices();