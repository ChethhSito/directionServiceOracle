document.addEventListener("DOMContentLoaded", () => {
    const inputID = document.getElementById("direccionId");
    const btnBuscar = document.getElementById("btnBuscar");
    const btnEliminar = document.getElementById("btnEliminar");
    const tablaCont = document.getElementById("tablaCont");
    const tbody = document.getElementById("direccion_table");
    const progressBar = document.getElementById("progress-bar");
    const progressFill = document.querySelector(".progress-bar-fill");

    let direccionEncontrada = null;

    btnBuscar.addEventListener("click", async () => {
        const id = inputID.value.trim();
        if (!id) return alert("Por favor, ingrese un ID válido.");

        // Mostrar barra de carga
        progressBar.style.display = "block";
        progressFill.style.width = "0%";
        setTimeout(() => progressFill.style.width = "100%", 10);

        await new Promise(res => setTimeout(res, 1000)); // Simulación de espera

        try {
            const res = await fetch(`/api/directions/get/${id}`);
            if (!res.ok) throw new Error("Dirección no encontrada");

            const data = await res.json();
            direccionEncontrada = data;

            // Mostrar tabla con los datos de la dirección
            tablaCont.style.display = "block";
            tbody.innerHTML = `
                <tr>
                    <td>${data.id}</td>
                    <td>${data.direccion}</td>
                    <td>${data.ciudad}</td>
                    <td>${data.departamento}</td>
                    <td>${data.codigoPostal}</td>
                    <td>${data.clienteId}</td>
                    <td>${data.tipo}</td>
                </tr>
            `;
        } catch (err) {
            alert(err.message);
            tablaCont.style.display = "none";
            tbody.innerHTML = "";
            direccionEncontrada = null;
        } finally {
            progressBar.style.display = "none";
            progressFill.style.width = "0%";
        }
    });

    btnEliminar.addEventListener("click", async () => {
        if (!direccionEncontrada) {
            alert("Debe buscar una dirección primero.");
            return;
        }

        const confirmar = confirm("¿Está seguro de eliminar esta dirección?");
        if (!confirmar) return;

        try {
            const res = await fetch(`/api/directions/delete/${direccionEncontrada.id}`, {
                method: "DELETE"
            });

            if (!res.ok) throw new Error("Error al eliminar la dirección");

            alert("Dirección eliminada correctamente.");
            inputID.value = "";
            tablaCont.style.display = "none";
            tbody.innerHTML = "";
            direccionEncontrada = null;

        } catch (err) {
            alert("Error: " + err.message);
        }
    });
});
