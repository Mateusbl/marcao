// filepath: frontend/src/js/sales.js
async function fetchSales() {
    const res = await fetch('/api/sales');
    const sales = await res.json();
    const tbody = document.querySelector('#salesTable tbody');
    tbody.innerHTML = '';
    sales.forEach(sale => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${sale.id}</td>
            <td>${sale.car}</td>
            <td>${sale.customer}</td>
            <td>R$ ${sale.price.toLocaleString('pt-BR')}</td>
            <td>${sale.date}</td>
            <td><button onclick="deleteSale('${sale.id}')">Remover</button></td>
        `;
        tbody.appendChild(tr);
    });
}

async function deleteSale(id) {
    await fetch(`/api/sales/${id}`, { method: 'DELETE' });
    fetchSales();
}

document.getElementById('saleForm').onsubmit = async function(e) {
    e.preventDefault();
    const car = document.getElementById('saleCar').value;
    const customer = document.getElementById('saleCustomer').value;
    const price = document.getElementById('salePrice').value;
    const date = document.getElementById('saleDate').value;
    await fetch('/api/sales', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            id: Math.random().toString(36).substring(2, 10),
            car, customer, price: Number(price), date
        })
    });
    this.reset();
    fetchSales();
};

fetchSales();