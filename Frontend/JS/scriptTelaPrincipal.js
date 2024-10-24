const pokemonList = document.getElementById('pokemon-list');
const searchInput = document.getElementById('search-input');
let currentUserId = localStorage.getItem('currentUserId');
const userButton = document.getElementById('user-btn');
const favButton = document.getElementById('fav-btn');

userButton.addEventListener('click', () => {
    window.location.href = "Perfil.html";
});

// Mapeamento de cores para os tipos de Pokémon
const typeColors = {
    grass: '#78C850',
    poison: '#A040A0',
    fire: '#F08030',
    water: '#6890F0',
    bug: '#A8B820',
    normal: '#A8A878',
    electric: '#F8D030',
    ground: '#E0C068',
    fairy: '#EE99AC',
    fighting: '#C03028',
    psychic: '#F85888',
    rock: '#B8A038',
    ghost: '#705898',
    ice: '#98D8D8',
    dragon: '#7038F8',
    dark: '#705848',
    steel: '#B8B8D0',
    flying: '#A890F0'
};

let pokemons = []; 
let userFavorites = []; // Inicializa a lista de favoritos

const API_URL = 'http://localhost:8080/pokedex';
const API_USER_URL = `http://localhost:8080/user/id/${currentUserId}`;


// Função para criar um cartão de Pokémon
function createPokemonCard(pokemon) {
    const card = document.createElement('div');
    card.setAttribute('id', pokemon.id);
    card.classList.add('pokemon-card');

    const typesHTML = pokemon.types.map(type => {
        const color = typeColors[type] || '#A8A878'; 
        return `<span class="pokemon-type" style="background-color: ${color};">${type}</span>`;
    }).join(' ');

    card.innerHTML = `
        <img src="${pokemon.img}" alt="${pokemon.name}">
        <h3>${capitalizeFirstLetter(pokemon.name)}</h3>
        <p>#${pokemon.id}</p>
        <div>${typesHTML}</div>
    `;

    card.addEventListener('click', () => {
        localStorage.setItem('currentPokemonId', pokemon.id);
        localStorage.setItem('currentUserId', currentUserId);
        window.location.href = "telaPokemon.html";
    });

    return card;
}

// Função para exibir os Pokémon
function displayPokemons(pokemonArray) {
    pokemonList.innerHTML = '';
    pokemonArray.forEach(pokemon => {
        const card = createPokemonCard(pokemon);
        pokemonList.appendChild(card);
    });
}

// Função para buscar os Pokémon da API
function fetchPokemons() {
    fetch(API_URL) // URL da sua API Spring Boot
        .then(response => response.json())
        .then(data => {
            pokemons = data; // Armazena os Pokémon vindos da API
            fetchUserFavorites(); // Busca os favoritos após carregar os Pokémon
        })
        .catch(error => {
            console.error('Erro ao buscar os Pokémon:', error);
        });
}

// Função para buscar os favoritos do usuário
function fetchUserFavorites() {
    fetch(API_USER_URL)
        .then(response => response.json())
        .then(user => {
            userFavorites = user.data.listaFavoritos ? user.data.listaFavoritos.split(',').map(id => id.trim()) : [];
            displayPokemons(pokemons); // Exibe todos os Pokémon após carregar favoritos
        })
        .catch(error => {
            console.error('Erro ao buscar os favoritos do usuário:', error);
        });
}

// Função de filtro de Pokémon
function filterPokemons() {
    const searchText = searchInput.value.toLowerCase();
    const filteredPokemons = pokemons.filter(pokemon => {
        const pokemonName = pokemon.name.toLowerCase();
        const pokemonId = pokemon.id.toString();
        return pokemonName.includes(searchText) || pokemonId.includes(searchText);
    });
    displayPokemons(filteredPokemons);
}


// Função de filtro Pokémon favoritos
function filterFavPokemons() {
    const filteredPokemonsFav = pokemons.filter(pokemon => userFavorites.includes(pokemon.id.toString()));
    displayPokemons(filteredPokemonsFav);
}
// Capitaliza a primeira letra de uma string
function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

// Adiciona o evento de input para filtrar os Pokémon conforme o usuário digita
searchInput.addEventListener('input', filterPokemons);

// Adiciona o evento de clique ao botão de favoritos
favButton.addEventListener('click', filterFavPokemons);

// Busca os Pokémon e os favoritos quando a página é carregada
fetchPokemons();
