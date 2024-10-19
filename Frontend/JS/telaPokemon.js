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
    button.classList.toggle("favorited"); // Alterna a classe 'favorited'
    
    // Muda a classe do ícone de coração
    if (button.classList.contains("favorited")) {
        heartIcon.classList.remove("fa-regular"); // Remove a classe regular
        heartIcon.classList.add("fa-solid");       // Adiciona a classe sólida
    } else {
        heartIcon.classList.remove("fa-solid");     // Remove a classe sólida
        heartIcon.classList.add("fa-regular");      // Adiciona a classe regular
    }
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
