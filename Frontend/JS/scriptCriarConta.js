const url = "http://localhost:8080/user/create1"

document.getElementById("formCadastro").addEventListener("submit", function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const cpassword = document.getElementById("cpassword").value;
    const erroMsg = document.getElementById("erro");
    erroMsg.style.color = "red";
    if (password !== cpassword) {
      erroMsg.textContent = "As senhas não coincidem!";
      return;
    }
    erroMsg.textContent = "";
    fetch(url, {
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
          erroMsg.innerText = "Falha na conexão";

        }
      })
      .then((data) => {
        if (data.message == "Usuario já existe"){
          erroMsg.innerText = "Usuário já existe";
        }
        else{
          erroMsg.style.color = "green";
          erroMsg.innerText = "Usuário criado com sucesso!";
          document.getElementById("formCadastro").reset();
          window.location.href = "login.html";
        }
      })
      .catch((error) => {
        erroMsg.textContent = "Falha na conexão com a API";
        console.error("Erro na requisição:", error);
      });
  });
