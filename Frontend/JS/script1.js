document
  .getElementById("formCadastro")
  .addEventListener("submit", function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const cpassword = document.getElementById("cpassword").value;
    const erroMsg = document.getElementById("erro");

    if (password !== cpassword) {
      erroMsg.textContent = "As senhas não coincidem!";
      return;
    }
    erroMsg.textContent = "";
    fetch("http://localhost:8080/user/create1", {
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
          throw new Error("Erro ao cadastrar. Por favor, tente novamente.");
        }
      })
      .then((data) => {
        erroMsg.style.color = "green";
        erroMsg.innerText = "Usuário criado com sucesso!";
        document.getElementById("formCadastro").reset();
      })
      .catch((error) => {
        erroMsg.textContent = error.message;
        console.error("Erro na requisição:", error);
      });
  });
