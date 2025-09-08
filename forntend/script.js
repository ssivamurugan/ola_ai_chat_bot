async function getAnswer(message) {
      let text = encodeURIComponent(message);
      let api = await fetch(`http://localhost:8080/api/${text}`, { method: 'GET'});
      let ans = await api.text();
      return ans;
    }

    const messagesDiv = document.getElementById("messages");
    const userInput = document.getElementById("userInput");
    const sendBtn = document.getElementById("sendBtn");

    function addMessage(role, text) {
      const div = document.createElement("div");
      div.classList.add("message", role);
      div.textContent = text;
      messagesDiv.appendChild(div);
      messagesDiv.scrollTop = messagesDiv.scrollHeight;
    }

    async function handleSend() {
      const msg = userInput.value.trim();
      if (!msg) return;
      addMessage("user", msg);
      userInput.value = "";
      const reply = await getAnswer(msg);
      addMessage("bot", reply);
    }

    sendBtn.addEventListener("click", handleSend);
    userInput.addEventListener("keydown", e => {
      if (e.key === "Enter") handleSend();
    });