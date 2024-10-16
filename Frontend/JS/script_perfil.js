const usuarioteste = 502;

        // URL base da API (ajuste conforme necessário)
        const API_URL = "http://localhost:8080/user/";

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
                .then(usuario => {
                    console.log('Dados do usuario recebidos:', usuario);
                    updateUsuarioInfo(usuario);
                })
                .catch(error => {
                    console.error('Erro ao carregar o usuario:', error);
                });
        }

        // Função para atualizar a página com os detalhes do usuário
        function updateUsuarioInfo(usuario) {
            document.getElementById("usuario-name").textContent = `${usuario.username} #${String(usuario.id).padStart(4, '0')}`;
            
            const usuarioImage = document.querySelector(".usuario-image");
            // Se você tiver um campo 'imgUrl' no seu UserEntity, descomente a linha abaixo
            // usuarioImage.src = usuario.imgUrl; // Altere isso se tiver a URL da imagem no objeto usuário
            console.log('Informações do usuario atualizadas na página.');
        }

        // Carregar usuário ao carregar a página
        document.addEventListener("DOMContentLoaded", () => {
            console.log('DOM totalmente carregado. Carregando usuário inicial...');
            loadUsuario(usuarioteste);
        });