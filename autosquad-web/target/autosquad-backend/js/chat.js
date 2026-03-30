



async function cargarMensajes() {

    const params = new URLSearchParams();
    params.append("id_caja", idCaja);

    const response = await fetch("listarMensajes", {
        method: "POST",
        body: params,
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    });

    const data = await response.json();

    const contenedor = document.getElementById("mensajes");
    contenedor.innerHTML = "";

    data.forEach(m => {

        const div = document.createElement("div");
        div.textContent = m.nombre_usuario + ": " + m.texto;

        contenedor.appendChild(div);
    });
}

async function enviarMensaje() {

    const texto = document.getElementById("texto").value;

    const params = new URLSearchParams();
    params.append("id_usuario", idUsuario);
    params.append("id_caja", idCaja);
    params.append("texto", texto);

    await fetch("enviarMensaje", {
        method: "POST",
        body: params,
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    });

    document.getElementById("texto").value = "";
    cargarMensajes();
}

function volver() {
    window.location.href = "home.html";
}

setInterval(cargarMensajes, 3000);
window.onload = cargarMensajes;