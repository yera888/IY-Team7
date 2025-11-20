(function () {
  const form = document.getElementById("signup-form");
  const errorEl = document.getElementById("signup-error");
  if (!form) return;

  form.addEventListener("submit", async (e) => {
    e.preventDefault();
    errorEl.textContent = "";

    const firstName = document.getElementById("first-name").value.trim();
    const lastName = document.getElementById("last-name").value.trim();
    const email = document.getElementById("signup-email").value.trim();
    const phone = document.getElementById("phone").value.trim();
    const password = document.getElementById("password").value;

    if (!firstName || !lastName || !email || !phone || !password) {
      errorEl.textContent = "Please fill out all fields.";
      return;
    }

    const payload = {
      name: firstName + " " + lastName,
      email: email,
      phoneNumber: phone,
      password: password,
    };

    try {
      const res = await fetch("/api/signup", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      });

      if (res.ok) {
        // no need to store userId – just send them to login
        window.location.href = "/Client-Side/login.html";
        return;
      }

      let msg = "Something went wrong. Please try again.";
      if (res.status === 400 || res.status === 409) {
        try {
          const data = await res.json();
          if (data && data.message) msg = data.message;
          else {
            const text = await res.text();
            if (text) msg = text;
          }
        } catch (_) {}
      }
      errorEl.textContent = msg;
    } catch (err) {
      console.error(err);
      errorEl.textContent = "Network error. Please try again.";
    }
  });
})();
