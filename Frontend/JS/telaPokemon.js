let currentPokemonId = localStorage.getItem('currentPokemonId');

const API_URL = "http://localhost:8080/pokedex/id/";


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
    document.getElementById("pokemon-name").textContent = `${pokemon.name} #${String(pokemon.id).padStart(4, '0')}`;
    document.getElementById("pokemon-height").textContent = `${pokemon.height / 10} m`; 
    document.getElementById("pokemon-weight").textContent = `${pokemon.weight / 10} kg`; 
    document.getElementById("pokemon-abilities").textContent = pokemon.abilities.join(', ');
    document.getElementById("pokemon-evolves-to").textContent = pokemon.evolves_to.join(', ');
    document.getElementById("pokemon-types").textContent = pokemon.types.join(', ');
    const pokemonImage = document.querySelector(".pokemon-image");
    pokemonImage.src = pokemon.img;
    pokemonImage.alt = pokemon.name;
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


document.addEventListener("DOMContentLoaded", () => {
    console.log('DOM totalmente carregado. Carregando Pokémon inicial...');
    loadPokemon(currentPokemonId);
});
