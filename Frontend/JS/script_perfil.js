const usuarioteste = 552; //mudar para localStorage.getItem

// URL base da API (ajuste conforme necessário)
const API_URL = "http://localhost:8080/user/id/"; // Corrigido para corresponder à rota correta

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

    localStorage.setItem('userId', usuario.id);
    
    // Se você tiver um campo 'imgUrl' no seu UserEntity, descomente a linha abaixo e ajuste o campo
    // usuarioFoto.src = usuario.imgUrl || '../img/foto_default.jpg'; // Altere se houver URL da imagem
    // Informações do usuario atualizadas na página
}

// Carregar usuário ao carregar a página
document.addEventListener("DOMContentLoaded", () => {
    loadUsuario(usuarioteste);
    // Adiciona o evento de clique no botão "Alterar senha"
    const alterarSenhaBtn = document.querySelector("#ir_para_alt_senha");
    alterarSenhaBtn.addEventListener("click", () => {
        // Redireciona para a URL de alteração de senha

        
        window.location.href = "../HTML/Trocar_senha.html";  // Substitua pela URL real da página de alteração de senha
    });
});


