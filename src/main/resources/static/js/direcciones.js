document.addEventListener('DOMContentLoaded', function() {
fetch('api/directions')
.then(response => response.json())
.then(data => {
    const tbody = document.getElementById('direccion_table');
    data.forEach(direccion => {
        const fila = document.createElement('tr');
        fila.innerHTML = `
            <td>${direccion.id}</td>
            <td>${direccion.direccion}</td>
            <td>${direccion.ciudad}</td>
            <td>${direccion.departamento}</td>
            <td>${direccion.codigoPostal}</td>
            <td>${direccion.tipo}</td>
            <td>${direccion.clienteId.id}</td>`;
            tbody.appendChild(fila);
    });}).catch(error => {
    console.error('Error fetching direccions:', error);
    });});
    