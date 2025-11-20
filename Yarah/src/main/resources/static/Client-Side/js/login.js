(function () {
  const form = document.getElementById("login-form");
  const errorEl = document.getElementById("login-error");
  if (!form) return;

  form.addEventListener("submit", async (e) => {
    e.preventDefault();
    errorEl.textContent = "";

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;

    if (!email || !password) {
      errorEl.textContent = "Please enter your email and password.";
      return;
    }

    const data = new URLSearchParams();
    data.append("username", email);
    data.append("password", password);

    try {
      const res = await fetch("/login", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: data.toString(),
        credentials: "include",
      });

      if (!res.ok) {
        errorEl.textContent = "Invalid email or password.";
        return;
      }

      window.location.href = "/Client-Side/account.html";
    } catch (err) {
      console.error(err);
      errorEl.textContent = "Network error. Please try again.";
    }
  });
})();
