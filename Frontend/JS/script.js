const url = "http://localhost:8080/user/login1";

function criarConta() {
    window.location.href = "../HTML/criarconta.html";
}

function enviarFormulario(event) {
    event.preventDefault(); // Impede o envio padrão do formulário

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let errorElement = document.createElement("p"); // Cria um elemento para mensagens de erro

    // Enviar requisição POST ao backend para login
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    })
    .then(response => {
        if (response.ok) {
            return response.json(); // Se a resposta for OK, retorna o JSON
        } else {
            throw new Error("Credenciais inválidas");
        }
    })
    .then(data => {
        // Redireciona para a nova tela após login bem-sucedido
        window.location.href = "telaPrincipal.html"; // Altere para o caminho da sua nova tela
    })
    .catch(error => {
        // Exibe uma mensagem de erro
        const errorElement = document.createElement("p");
        errorElement.style.color = "red";
        errorElement.innerText = "Erro ao fazer login: " + error.message;
        document.body.appendChild(errorElement); // Adiciona a mensagem ao corpo do documento
    });
}