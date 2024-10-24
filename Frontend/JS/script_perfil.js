const currentUserId = localStorage.getItem('currentUserId');

// URL base da API (ajuste conforme necessário)
const API_URL = "http://localhost:8080/user/id/"; // Rota para carregar dados do usuário
const API_FOTO = "http://localhost:8080/user/upload-photo";
const IMG_PATH = "../img/Users/"; // Caminho onde as imagens são armazenadas
const DEFAULT_IMG = "../img/foto_default.jpg"; // Caminho da imagem padrão

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

// Função para verificar se a imagem existe
function checkImageExists(url) {
    return fetch(url, { method: 'HEAD' })
        .then(response => response.ok)
        .catch(() => false);
}

// Função para atualizar a página com os detalhes do usuário
function updateUsuarioInfo(usuario) {
    // Atualiza o nome do usuário
    document.getElementById("usuario-name").textContent = `${usuario.username}`;

    // Atualiza a imagem do usuário
    const usuarioFoto = document.getElementById("usuario-foto");
    const userId = usuario.id;

    // Construir a URL da imagem do usuário
    const userImageUrl = `${IMG_PATH}${userId}.jpg`; // Altere para .png se necessário

    // Verifica se a imagem existe e define a imagem correspondente ou a padrão
    checkImageExists(userImageUrl).then(exists => {
        usuarioFoto.src = exists ? userImageUrl : DEFAULT_IMG;
    });

    // Armazenar o ID do usuário no localStorage
    localStorage.setItem('currentUserId', userId);
}

// Função para enviar a imagem em Base64 ao backend
function uploadFotoBase64(userId, base64Image) {
    fetch(API_FOTO, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            userId: userId,  // Corrigido para userId, conforme esperado no backend
            fotoBase64: base64Image  // Corrigido para fotoBase64, conforme esperado no backend
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Erro ao enviar a foto: ${response.statusText}`);
        }
        return response.json();
    })
    .then(data => {
        console.log("Imagem enviada com sucesso:", data);

        // Atualize a interface com a nova imagem do usuário após o upload
        const usuarioFoto = document.getElementById("usuario-foto");
        usuarioFoto.src = `${IMG_PATH}${userId}.png`; // Atualizar a imagem após o upload
    })
    .catch(error => {
        console.error("Erro ao enviar a foto:", error);
    });
}

function goBack() {
    window.location.href = "telaPrincipal.html";
}

// Carregar usuário ao carregar a página
document.addEventListener("DOMContentLoaded", () => {
    loadUsuario(currentUserId);

    const alterarFotoBtn = document.getElementById("alterar_foto");
    const uploadInput = document.getElementById("upload-foto");
    const usuarioFoto = document.getElementById("usuario-foto");

    // Quando clicar no botão "Alterar Foto", abre o seletor de arquivos
    alterarFotoBtn.addEventListener("click", () => {
        uploadInput.click();  // Abre o seletor de arquivos
    });

    // Evento quando o usuário escolhe um arquivo
    uploadInput.addEventListener("change", (event) => {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                // Atualiza a imagem no perfil
                usuarioFoto.src = e.target.result;  // Mostra a pré-visualização da imagem no <img>
                
                // Converte para Base64
                const base64Image = e.target.result.split(',')[1];  // Pega o conteúdo da imagem sem o prefixo "data:image/*;base64,"
                console.log("Imagem Base64:", base64Image);

                // Envia a imagem em Base64 ao backend
                uploadFotoBase64(currentUserId, base64Image);
            };
            reader.readAsDataURL(file);  // Converte o arquivo em Data URL
        }
    });

    const alterarSenhaBtn = document.querySelector("#ir_para_alt_senha");
    alterarSenhaBtn.addEventListener("click", () => {
        // Redireciona para a URL de alteração de senha
        window.location.href = "../HTML/Trocar_senha.html";  // Substitua pela URL real da página de alteração de senha
    });
});
