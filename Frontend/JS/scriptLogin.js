const url = "http://localhost:8080/user/login1";

function criarConta() {
  window.location.href = "../HTML/criarconta.html";
}

function enviarFormulario(event) {
  event.preventDefault();

  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
  fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      username: username,
      password: password,
    }),
  })
    .then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error("Credenciais inválidas");
      }
    })
    .then((data) => {
      if (data.data) {
        localStorage.setItem('currentUserId', data.data.id); 
        window.location.href = "telaPrincipal.html";
      } else {
        throw new Error(data.message || "Credenciais inválidas");
      }
    })
    .catch((error) => {
      const errorElement = document.getElementById("erro");
      errorElement.style.color = "red";
      errorElement.innerText = error.message;
    });
}
