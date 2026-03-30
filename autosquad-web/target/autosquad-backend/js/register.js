const form = document.getElementById("registerForm");
const resultado = document.getElementById("resultado");

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const formData = new URLSearchParams(new FormData(form));

    try {
        const response = await fetch("registro", {
            method: "POST",
            body: formData
        });

        const data = await response.json();

        if (data.success) {
            resultado.textContent = "Usuario registrado correctamente";
        } else {
            resultado.textContent = data.error;
        }

    } catch (err) {
        resultado.textContent = "Error al conectar con el servidor";
    }
});