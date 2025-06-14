document.addEventListener('DOMContentLoaded', function () {
  const inputId = document.getElementById('direccionRefe');
  const formulario = document.getElementById('direccionForm');
  const btnRegistrarId = document.getElementById('btnRegistrarId');
  const barraCarga = document.getElementById('progress-bar');
  const barraRelleno = barraCarga?.querySelector('.progress-bar-fill');

  function mostrarBarraCarga() {
    if (!barraCarga || !barraRelleno) return Promise.resolve(); // Seguridad

    barraCarga.style.display = 'block';
    barraRelleno.style.width = '0%';

    setTimeout(() => {
      barraRelleno.style.width = '100%';
    }, 10);

    return new Promise((resolve) => {
      setTimeout(() => {
        barraCarga.style.display = 'none';
        barraRelleno.style.width = '0%';
        resolve();
      }, 1000);
    });
  }

  btnRegistrarId.addEventListener('click', async function () {
    if (inputId.value.trim() === '') {
      alert("Ingrese un ID de dirección válido");
      return;
    }

    await mostrarBarraCarga(); // Muestra la barra de carga

    fetch('http://localhost:8082/api/directions/get/' + inputId.value)
      .then(response => {
        if (!response.ok) {
          throw new Error('Dirección no encontrada');
        }
        return response.json();
      })
      .then(direccion => {
        formulario.style.display = 'block';

        document.getElementById("direccion").value = direccion.direccion;
        document.getElementById("ciudad").value = direccion.ciudad;
        document.getElementById("departamento").value = direccion.departamento;
        document.getElementById("codigoPostal").value = direccion.codigoPostal;
        document.getElementById("tipo").value = direccion.tipo;
        document.getElementById("clienteId").value = direccion.clienteId;
      })
      .catch(error => {
        formulario.style.display = 'none';
        console.error('Error al obtener la dirección:', error);
        alert('Error al obtener la dirección: ' + error.message);
      });
  });

  formulario.addEventListener('submit', function (event) {
    event.preventDefault(); // Evita el envío del formulario

    const direccion = {
      id: Number(inputId.value),
      direccion: document.getElementById("direccion").value,
      ciudad: document.getElementById("ciudad").value,
      departamento: document.getElementById("departamento").value,
      codigoPostal: document.getElementById("codigoPostal").value,
      tipo: document.getElementById("tipo").value,
      clienteId: Number(document.getElementById("clienteId").value)
    };

    const url = "http://localhost:8082/api/directions/update/" + direccion.id;

    fetch(url, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(direccion)
    })
      .then(response => {
        if (response.ok) {
          alert("Dirección actualizada correctamente");
          formulario.reset();
          formulario.style.display = 'none';
          inputId.value = "";
        } else {
          alert("Error al actualizar dirección");
        }
      })
      .catch(error => {
        console.error("Error al actualizar dirección:", error);
        alert("Error del servidor: " + error.message);
      });
  });
});
