const url = "http://localhost:8080/user/login1";

function criarConta() {
    window.location.href = "../HTML/criarconta.html";
}

function enviarFormulario(event) {
    event.preventDefault(); // Impede o envio padrão do formulário

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

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
        // Verifica se a resposta contém um usuário válido
        if (data.data) {
            // Login realizado com sucesso
            window.location.href = "telaPrincipal.html"; // Altere para o caminho da sua nova tela
        } else {
            // Se não houver dados, significa que o login falhou
            throw new Error(data.message || "Credenciais inválidas");
        }
    })
    .catch(error => {
        // Exibe uma mensagem de erro
        const errorElement = document.getElementById("erro");
        errorElement.style.color = "red";
        errorElement.innerText = error.message; // Exibe a mensagem de erro no elemento com id "erro"
    });
}