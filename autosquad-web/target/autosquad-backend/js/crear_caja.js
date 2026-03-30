async function crearCaja() {

    const idUsuario = sessionStorage.getItem("id");
    const idJuego = document.getElementById("juego").value;
    const requisitos = document.getElementById("requisitos").value;
    const jugadores = document.getElementById("jugadores").value;

    const params = new URLSearchParams();
    params.append("id_creador", idUsuario);
    params.append("id_juego", idJuego);
    params.append("requisitos", requisitos);
    params.append("jugadores", jugadores);

    try {

        const response = await fetch("crearCaja", {
            method: "POST",
            body: params,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            credentials: "include"
        });

        const data = await response.json();

        const resultado = document.getElementById("resultado");

        if (data.success) {
            resultado.textContent = "Caja creada correctamente";
        } else {
            resultado.textContent = data.error;
        }

    } catch (error) {

        document.getElementById("resultado").textContent =
            "Error de conexión";
    }
}

function volver() {
    window.location.href = "home.html";
}