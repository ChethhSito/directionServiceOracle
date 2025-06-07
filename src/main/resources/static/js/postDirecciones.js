
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
            
            alert("Direccion agregado correctamente");
            // Opcional: limpiar formulario o redirigir
        } else {
            alert("Error al agregar direccion");
        }
    }).catch(error => {
    console.error("Error al agregar direccion:", error);
    alert("Error del servidor: " + error.message);});
    this.reset(); // Limpia el formulario
}
);