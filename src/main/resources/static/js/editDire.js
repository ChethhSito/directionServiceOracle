document.addEventListener('DOMContentLoaded', function () {
  const inputId = document.getElementById('direccionRefe');
  const formulario = document.getElementById('direccionForm');
    
  inputId.addEventListener('input', function () {
    if (inputId.value.trim() !== '') {
      formulario.style.display = 'block';
      btnRegistrarId.addEventListener('click', function () {
      fetch('http://localhost:8082/api/directions/get/' + inputId.value)
        .then(response => {
          if (!response.ok) {
            throw new Error('Cliente no encontrado');
          }
          
          return response.json();
        })
        .then(direccion => {
          console.log('Datos del cliente:', direccion);
          document.getElementById("direccion").value = direccion.direccion;
          document.getElementById("ciudad").value = direccion.ciudad;
          document.getElementById("departamento").value = direccion.departamento;
          document.getElementById("codigoPostal").value = direccion.codigoPostal;
          document.getElementById("tipo").value = direccion.tipo;
          document.getElementById("clienteId").value = direccion.clienteId;
        })
        .catch(error => {
          console.error('Error al obtener el cliente:', error);
          alert('Error al obtener el cliente: ' + error.message);
        });
    });
    
    } else {
      formulario.style.display = 'none';
    }
  });
  formulario.addEventListener('submit', function (event) {
    event.preventDefault(); // Evita el envío del formulario por defecto

    var direccion = {
       "id": inputId.value,
       "direccion": document.getElementById("direccion").value,
       "ciudad": document.getElementById("ciudad").value,
       "departamento": document.getElementById("departamento").value,
       "codigoPostal": document.getElementById("codigoPostal").value,
       "tipo": document.getElementById("tipo").value,
       "clienteId": Number(document.getElementById("clienteId").value)
    };
    

    var url = "http://localhost:8082/api/directions/update/" + direccion.id;
    fetch(url, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(direccion)
    }).then(response => {
      if (response.ok) {
        alert("Direccion actualizada correctamente");
        // Opcional: limpiar formulario o redirigir
      } else {
        alert("Error al actualizar direccion");
      }
    }).catch(error => {
      console.error("Error al actualizar direccion:", error);
      alert("Error del servidor: " + error.message);
    });
    this.reset(); // Limpia el formulario
  });
});