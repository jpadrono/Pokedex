let currentPokemonId = localStorage.getItem('currentPokemonId');
let currentUserId = localStorage.getItem('currentUserId');

const API_URL = "http://localhost:8080/pokedex/id/";
const API_USER_URL = "http://localhost:8080/user/id/";

console.log('Current Pokemon ID:', currentPokemonId);
console.log('Current User ID:', currentUserId);

function loadPokemon(id) {
    console.log(`Carregando Pokémon com ID: ${id}`);
    fetch(`${API_URL}${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status}`);
            }
            return response.json();
        })
        .then(pokemon => {
            console.log('Dados do Pokémon recebidos:', pokemon);
            updatePokemonInfo(pokemon);
        })
        .catch(error => {
            console.error('Erro ao carregar o Pokémon:', error);
        });
}

function updatePokemonInfo(pokemon) {
    const stats = parseStats(pokemon.stats);
    document.getElementById("pokemon-name").textContent = `${pokemon.name} #${String(pokemon.id).padStart(4, '0')}`; 
    document.getElementById("pokemon-height").textContent = `${pokemon.height / 10} m`; 
    document.getElementById("pokemon-weight").textContent = `${pokemon.weight / 10} kg`; 
    document.getElementById("pokemon-abilities").textContent = pokemon.abilities.join(', ');
    document.getElementById("pokemon-evolves-to").textContent = pokemon.evolves_to.join(', ');
    document.getElementById("pokemon-types").textContent = pokemon.types.join(', ');
    document.getElementById("hp-value").textContent = stats.hp;
    document.getElementById("attack-value").textContent = stats.attack || 0;
    document.getElementById("defense-value").textContent = stats.defense || 0;
    document.getElementById("special-attack-value").textContent = stats['special_attack'] || 0;
    document.getElementById("special-defense-value").textContent = stats['special_defense'] || 0;
    document.getElementById("speed-value").textContent = stats.speed || 0;
    const pokemonImage = document.querySelector(".pokemon-image");
    pokemonImage.src = pokemon.img;
    pokemonImage.alt = pokemon.name;

    // Obter os dados do usuário e verificar se o Pokémon está favoritado
    fetch(`${API_USER_URL}${currentUserId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro ao obter o usuário: ${response.status}`);
            }
            return response.json();
        })
        .then(user => {
            console.log("Dados do usuário:\n")
            console.log(user);
            const button = document.getElementById("favorite-button");
            const heartIcon = document.getElementById("heart-icon");

            // Inicializa listaFavoritos como uma string vazia se não existir
            user.data.listaFavoritos = user.data.listaFavoritos || "";

            // Verificar se o Pokémon atual está nos favoritos
            let favoritesArray = user.data.listaFavoritos.split(',').map(id => id.trim()).filter(id => id !== "");
            //favoritesArray.sort();

            if (favoritesArray.includes(currentPokemonId.toString())) {
                console.log("Esse pokemon está favoritado!")
                heartIcon.classList.remove("fa-regular");
                heartIcon.classList.add("fa-solid");
            } else {
                console.log("Esse pokemon não  está favoritado")
                button.classList.remove("favorited");
                heartIcon.classList.remove("fa-solid");
                heartIcon.classList.add("fa-regular");
            }
        })
        .catch(error => {
            console.error('Erro ao verificar favoritos:', error);
        });

    console.log('Informações do Pokémon atualizadas na página.');
}

function nextPokemon() {
    currentPokemonId++;
    loadPokemon(currentPokemonId);
}

function previousPokemon() {
    if (currentPokemonId > 1) {
        currentPokemonId--;
        loadPokemon(currentPokemonId);
    }
}

function goBack() {
    window.location.href = "telaPrincipal.html";
}

function toggleFavorite() {
    const button = document.getElementById("favorite-button");
    const heartIcon = document.getElementById("heart-icon");
    fetch(`${API_USER_URL}${currentUserId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro ao obter o usuário: ${response.status}`);
            }
            return response.json();
        })
        .then(user => {
            console.log('Vou alterar o seguinte usuário:', user);
            user.data.listaFavoritos = user.data.listaFavoritos || "";
            let favoritesArray = user.data.listaFavoritos.split(',').map(id => id.trim()).filter(id => id !== "");
            const index = favoritesArray.indexOf(currentPokemonId.toString());
            if (index === -1) {
                favoritesArray.push(currentPokemonId);
                heartIcon.classList.remove("fa-regular");
                heartIcon.classList.add("fa-solid");
            } else {
                favoritesArray.splice(index, 1);
                heartIcon.classList.remove("fa-solid");
                heartIcon.classList.add("fa-regular");
            }
            // Atualiza apenas a lista de favoritos de forma ordenada
            favoritesArray.sort();
            user.data.listaFavoritos = favoritesArray.join(',');
            // Cria um novo objeto contendo apenas os campos que queremos atualizar
            const userUpdate = {
                listaFavoritos: user.data.listaFavoritos,
            };
            return fetch(`${API_USER_URL}${currentUserId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(userUpdate), // Envia apenas a lista de favoritos
            });
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro ao atualizar o usuário: ${response.status}`);
            }
            console.log('Usuário atualizado com sucesso!');
            button.classList.toggle("favorited");
        })
        .catch(error => {
            console.error('Erro ao favoritar/desfavoritar o Pokémon:', error);
        });
}




function parseStats(statsString) {
    // Cria um objeto para armazenar os stats extraídos
    const stats = {};

    // Divide a string em partes usando a vírgula como separador
    const parts = statsString.split(', ');

    // Itera por cada parte e separa a chave (stat) e o valor
    parts.forEach(part => {
        const [key, value] = part.split(': ');
        stats[key.trim()] = parseInt(value.trim());
    });

    return stats;
}

document.addEventListener("DOMContentLoaded", () => {
    console.log('DOM totalmente carregado. Carregando Pokémon inicial...');
    loadPokemon(currentPokemonId);
});
