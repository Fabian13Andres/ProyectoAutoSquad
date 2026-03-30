

async function cargarCajas() {

    const texto = document.getElementById("busqueda").value;

    const params = new URLSearchParams();
    params.append("texto", texto);

    try {

        const response = await fetch("listarCajas", {
            method: "POST",
            body: params,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            }
        });

        const data = await response.json();

        mostrarCajas(data);

    } catch (error) {
        document.getElementById("listaCajas").innerHTML =
            "Error al cargar cajas";
    }
}

function mostrarCajas(cajas) {

    const contenedor = document.getElementById("listaCajas");
    contenedor.innerHTML = "";

    if (cajas.length === 0) {
        contenedor.innerHTML = "No hay cajas disponibles";
        return;
    }

    cajas.forEach(caja => {

        const div = document.createElement("div");
        div.className = "caja";

        div.innerHTML =
            "<h3>Requisitos: " + caja.requisitos + "</h3>" +
            "<p>Jugadores: " + caja.jugadores + "</p>" +
            "<p>Creador: " + caja.creador + "</p>";

        contenedor.appendChild(div);
    });
}

function volver() {
    window.location.href = "home.html";
}

// Cargar automáticamente al entrar
window.onload = cargarCajas;