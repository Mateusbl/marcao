// filepath: frontend/src/js/sales.js
async function fetchSales() {
    const res = await fetch('/api/sales');
    const sales = await res.json();
    const tbody = document.querySelector('#salesTable tbody');
    tbody.innerHTML = '';    sales.forEach(sale => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${sale.idVenda}</td>
            <td>${sale.dataVenda}</td>
            <td>${sale.enderecoEntrega}</td>
            <td>R$ ${sale.valorTotal ? sale.valorTotal.toLocaleString('pt-BR') : '0,00'}</td>
            <td><button onclick="deleteSale('${sale.idVenda}')">Remover</button></td>
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
    const enderecoEntrega = document.getElementById('saleAddress').value;
    await fetch('/api/sales', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            idVenda: Math.floor(Math.random() * 1000),
            dataVenda: new Date().toISOString(),
            enderecoEntrega,
            itens: [],
            pagamento: null
        })
    });
    this.reset();
    fetchSales();
};

fetchSales();