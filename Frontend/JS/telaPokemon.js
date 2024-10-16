// ID inicial do Pokémon a ser carregado
let currentPokemonId = 2;

// URL base da API (ajuste conforme necessário)
const API_URL = "http://localhost:8080/pokedex/id/";

// Função para carregar os detalhes do Pokémon
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

// Função para atualizar a página com os detalhes do Pokémon
function updatePokemonInfo(pokemon) {
    document.getElementById("pokemon-name").textContent = `${pokemon.name} #${String(pokemon.id).padStart(4, '0')}`;
    document.getElementById("pokemon-height").textContent = `${pokemon.height / 10} m`; // Ajustando altura em metros
    document.getElementById("pokemon-weight").textContent = `${pokemon.weight / 10} kg`; // Ajustando peso em quilogramas
    document.getElementById("pokemon-abilities").textContent = pokemon.abilities.join(', ');
    document.getElementById("pokemon-evolves-to").textContent = pokemon.evolves_to.join(', ');
    document.getElementById("pokemon-types").textContent = pokemon.types.join(', ');
    const pokemonImage = document.querySelector(".pokemon-image");
    pokemonImage.src = pokemon.imgUrl;
    pokemonImage.alt = pokemon.name;
    console.log('Informações do Pokémon atualizadas na página.');
}

// Funções para navegar entre os Pokémons
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

// Carregar Pokémon ao carregar a página
document.addEventListener("DOMContentLoaded", () => {
    console.log('DOM totalmente carregado. Carregando Pokémon inicial...');
    loadPokemon(currentPokemonId);
});
