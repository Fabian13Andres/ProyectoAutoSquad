// Verificar si el usuario tiene sesión activa
async function comprobarSesion() {

    try {

        const response = await fetch("dashboard", {
            method: "GET",
            credentials: "include"
        });

        // Si el servlet redirige al login, significa que no hay sesión
        if (response.redirected) {
            window.location.href = "login.html";
        }

    } catch (error) {
        console.error("Error comprobando sesión:", error);
        window.location.href = "login.html";
    }
}

// Ejecutar al cargar la página
window.addEventListener("load", comprobarSesion);