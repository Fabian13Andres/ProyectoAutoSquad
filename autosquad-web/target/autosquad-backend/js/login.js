const form = document.getElementById("loginForm");
const resultado = document.getElementById("resultado");

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const formData = new URLSearchParams(new FormData(form));

    const response = await fetch("login", {
        method: "POST",
        body: formData,
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    });

    const data = await response.json();

    if (data.success) {
        window.location.href = "home.html";
    } else {
        resultado.textContent = "Error de login";
    }
});