const usuarioteste = localStorage.getItem('userId');

// URL base da API (ajuste conforme necessário)
const API_URL = "http://localhost:8080/user/id/"; // Corrigido para corresponder à rota correta
const URL_CHANGE = `http://localhost:8080/user/change/${usuarioteste}`;

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
                // Se a senha atual estiver correta, atualizar a senha
                fetch(URL_CHANGE, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        password: new_password,
                    }),
                })
                    .then((response) => {
                        if (response.ok) {
                            return response.json();
                        } else {
                            throw new Error("Erro ao atualizar senha");
                        }
                    })
                    .then((data) => {
                        if (data.data) {
                            alert("Senha atualizada com sucesso!");
                            window.location.href = "../Perfil.html";
                        }
                    })
                    .catch((error) => {
                        erroMsg.textContent = "Erro ao atualizar senha: " + error.message;
                    });
            }
        })
        .catch((error) => {
            erroMsg.textContent = "Erro ao verificar senha: " + error.message;
        });
})

