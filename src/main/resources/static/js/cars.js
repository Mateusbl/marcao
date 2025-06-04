// filepath: frontend/src/js/cars.js
async function fetchCars() {
    const res = await fetch('/api/cars');
    const cars = await res.json();
    const tbody = document.querySelector('#carsTable tbody');
    tbody.innerHTML = '';    cars.forEach(car => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${car.id}</td>
            <td>${car.marca}</td>
            <td>${car.modelo}</td>
            <td>${car.ano}</td>
            <td>${car.precoVenda}</td>
            <td><button onclick="deleteCar('${car.id}')">Remover</button></td>
        `;
        tbody.appendChild(tr);
    });
}

async function deleteCar(id) {
    await fetch(`/api/cars/${id}`, { method: 'DELETE' });
    fetchCars();
}

document.getElementById('carForm').onsubmit = async function(e) {
    e.preventDefault();
    const placa = document.getElementById('placa').value;
    const marca = document.getElementById('make').value;
    const modelo = document.getElementById('model').value;
    const ano = document.getElementById('year').value;
    const cor = document.getElementById('cor').value;
    const precoVenda = document.getElementById('price').value;
    await fetch('/api/cars', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            idCarro: Math.floor(Math.random() * 1000),
            placa,
            marca,
            modelo,
            ano: parseInt(ano),
            cor,
            precoVenda: parseFloat(precoVenda),
            status: 'disponivel'
        })
    });
    this.reset();
    fetchCars();
};

fetchCars();