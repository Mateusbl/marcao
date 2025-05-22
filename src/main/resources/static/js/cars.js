// filepath: frontend/src/js/cars.js
async function fetchCars() {
    const res = await fetch('/api/cars');
    const cars = await res.json();
    const tbody = document.querySelector('#carsTable tbody');
    tbody.innerHTML = '';
    cars.forEach(car => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${car.id}</td>
            <td>${car.make}</td>
            <td>${car.model}</td>
            <td>${car.year}</td>
            <td>${car.price}</td>
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
    const make = document.getElementById('make').value;
    const model = document.getElementById('model').value;
    const year = document.getElementById('year').value;
    const price = document.getElementById('price').value;
    await fetch('/api/cars', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            id: Math.random().toString(36).substring(2, 10),
            make, model, year, price
        })
    });
    this.reset();
    fetchCars();
};

fetchCars();