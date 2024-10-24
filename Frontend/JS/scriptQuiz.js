const API_URL = "http://localhost:8080/quiz/pokemons";

let currentQuizIndex = 0;
let correctAnswerId = null;
let selectedAlternativeId = null;
let quitbutton = document.getElementById("quit-button");

function exitQuiz() {
    window.location.href = "telaPrincipal.html";
}
function loadQuizQuestion(index) {
    console.log(`Carregando pergunta do quiz: ${index}`);
    fetch(API_URL)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status}`);
            }
            return response.json();
        })
        .then(questions => {
            if (index >= questions.length) {
                console.error('Índice fora do limite de perguntas disponíveis.');
                return;
            }
            console.log('Dados da pergunta recebidos:', questions[index]);
            updateQuizInfo(questions[index]);
        })
        .catch(error => {
            console.error('Erro ao carregar a pergunta do quiz:', error);
        });
}

// Recarrega as informações do quiz
function updateQuizInfo(quiz) {
    document.getElementById("pokemonImage").src = quiz.imgUrl;
    document.getElementById("pokemonImage").alt = "Imagem do Pokémon";
    
    const alternatives = quiz.alternatives;
    document.getElementById("alt1").textContent = alternatives[0].name;
    document.getElementById("alt2").textContent = alternatives[1].name;
    document.getElementById("alt3").textContent = alternatives[2].name;
    document.getElementById("alt4").textContent = alternatives[3].name;

    correctAnswerId = quiz.correctAnswerId;

    // Reseta o estado dos botões e define o clique novamente
    const buttons = document.querySelectorAll(".alternative");
    buttons.forEach((button, index) => {
        button.classList.remove('disabled-button', 'right', 'wrong'); 
        button.onclick = () => handleButtonClick(button, alternatives[index].id, alternatives, buttons);
    });
    
    console.log('Informações da pergunta do quiz atualizadas na página.');
}

// Função chamada ao clicar em um botão de alternativa
function handleButtonClick(button, selectedId, alternatives, buttons) {
    // Armazena o ID da alternativa selecionada
    selectedAlternativeId = selectedId;

    // Verifica e aplica as classes de correta ou incorreta
    if (selectedAlternativeId == correctAnswerId) {
        button.classList.add('right'); 
    } else {
        button.classList.add('wrong'); 
        
        // Destaca a resposta correta
        const correctButton = Array.from(buttons).find((b, idx) => alternatives[idx].id == correctAnswerId);
        if (correctButton) correctButton.classList.add('right');
    }

    // Desabilita todos os botões exceto o correto e o selecionado
    buttons.forEach((b, idx) => {
        if (alternatives[idx].id !== correctAnswerId && alternatives[idx].id !== selectedAlternativeId) {
            b.classList.add("disabled-button"); 
            b.disabled = true; 
        }
    });
}

function resetButtonState() {
    const buttons = document.querySelectorAll(".alternative");
    buttons.forEach(button => {
        button.classList.remove('disabled-button', 'right', 'wrong');
        button.disabled = false; 
    });
    selectedAlternativeId = null; 
}

// Funções para navegar entre as perguntas do quiz
function nextQuizQuestion() {
    resetButtonState();
    currentQuizIndex++;
    loadQuizQuestion(currentQuizIndex);
}

function previousQuizQuestion() {
    if (currentQuizIndex > 0) {
        resetButtonState();
        currentQuizIndex--;
        loadQuizQuestion(currentQuizIndex);
    }
}

// Carregar a primeira pergunta do quiz ao carregar a página
document.addEventListener("DOMContentLoaded", () => {
    console.log('DOM totalmente carregado. Carregando primeira pergunta do quiz...');
    loadQuizQuestion(currentQuizIndex);
});
