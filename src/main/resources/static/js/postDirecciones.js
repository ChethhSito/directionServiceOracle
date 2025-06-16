
document.getElementById('direccionForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita el envío del formulario por defecto
    
    var direccion = {
        "clienteId": Number(document.getElementById("clienteId").value),
       "direccion": document.getElementById("direccion").value,
       "ciudad": document.getElementById("ciudad").value,
       "departamento": document.getElementById("departamento").value,
       "codigoPostal": document.getElementById("codigoPostal").value,
       "tipo": document.getElementById("tipo").value
    };
    
    var url= "http://localhost:8082/api/directions/add";
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(direccion)
    }).then(response => {
        if (response.ok) {
            
            Swal.fire("Direccion agregado correctamente", "", "success");
            // Opcional: limpiar formulario o redirigir
        } else {
            Swal.fire('Error al agregar', 'No se pudo agregar la dirección', 'error');
        }
    }).catch(error => {
    Swal.fire('Error al agregar', error.message, 'error');});
    this.reset(); // Limpia el formulario
}
);