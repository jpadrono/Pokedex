const usuarioteste = localStorage.getItem('currentUserId');

// URL base da API (ajuste conforme necessário)
const API_URL = "http://localhost:8080/user/id/"; // Corrigido para corresponder à rota correta
const URL_CHANGE = "http://localhost:8080/user/change";

// Função para carregar os dados do usuário
function loadUsuario(id) {
    console.log(`Carregando usuario com ID: ${id}`);
    fetch(`${API_URL}${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status}`);
            }
            return response.json();
        })
        .then(apiResponse => {
            // Extrair os dados do usuário da resposta da API
            const usuario = apiResponse.data;
            console.log('Dados do usuario recebidos:', usuario);
            updateUsuarioInfo(usuario);
        })
        .catch(error => {
            console.error('Erro ao carregar o usuario:', error);
        });
}

// Função para atualizar a página com os detalhes do usuário
function updateUsuarioInfo(usuario) {
    // Atualiza o nome do usuário
    document.getElementById("usuario-name").textContent = `${usuario.username}`;

    // Atualiza a imagem do usuário, se disponível
    const usuarioFoto = document.getElementById("usuario-foto");

    // Se você tiver um campo 'imgUrl' no seu UserEntity, descomente a linha abaixo e ajuste o campo
    // usuarioFoto.src = usuario.imgUrl || '../img/foto_default.jpg'; // Altere se houver URL da imagem
    // Informações do usuario atualizadas na página
}

// Carregar usuário ao carregar a página
document.addEventListener("DOMContentLoaded", () => {
    loadUsuario(usuarioteste); 
});


document.getElementById("troca_senha").addEventListener("submit", function (event) {
    event.preventDefault();

    const password = document.getElementById("password").value;
    const new_password = document.getElementById("new_password").value;
    const new_cpassword = document.getElementById("new_cpassword").value;
    const erroMsg = document.getElementById("erro");
    erroMsg.style.color = 'red';

    if (new_password !== new_cpassword) {
        erroMsg.textContent = "As senhas não conferem.";
        return;
    }
    // Verificar se a senha atual está correta
    fetch(URL_CHANGE, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            id: usuarioteste,      // ID obtido do localStorage
            password: password,    // Senha atual
            newPassword: new_password,  // Nova senha
        }),
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error(`Erro ao alterar senha. Status: ${response.status}`);
        }
    })
    .then(data => {
        if (data.message === "Senha incorreta") {
            erroMsg.innerText = "Senha incorreta";
        } else {
            erroMsg.style.color = "green";
            erroMsg.innerText = "Senha alterada com sucesso";
            document.getElementById("troca_senha").reset();
            // Esperar 1 segundo antes de redirecionar para outra página
            setTimeout(function () {
                window.location.href = "../HTML/Perfil.html";
            }, 1500);
        }
    })
    .catch(error => {
        erroMsg.textContent = "Erro ao verificar senha: " + error.message;
        console.error("Erro no fetch:", error);  // Log do erro no console
    });
});
    
