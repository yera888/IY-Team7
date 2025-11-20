(async function () {
  const avatarEl = document.getElementById("account-avatar");
  const nameEl = document.getElementById("account-name");
  const emailEl = document.getElementById("account-email");
  const typeEl = document.getElementById("account-type");
  const emptyEl = document.getElementById("account-empty");
  const logoutBtn = document.getElementById("account-logout");

  function initials(name) {
    if (!name) return "Y";
    const parts = name.trim().split(/\s+/);
    const first = parts[0]?.[0] || "";
    const second = parts[1]?.[0] || "";
    return (first + second || first || "Y").toUpperCase();
  }

  async function loadAccount() {
    try {
      const meRes = await fetch("/api/users/me", { credentials: "include" });
      if (!meRes.ok) {
        emptyEl.hidden = false;
        nameEl.textContent = "Not signed in";
        emailEl.textContent = "";
        typeEl.textContent = "—";
        return;
      }

      const me = await meRes.json();
      const fullName = me.name || "(no name)";
      avatarEl.textContent = initials(fullName);
      nameEl.textContent = fullName;
      emailEl.textContent = me.email || "";

      const profileRes = await fetch(`/api/profiles/by-user/${me.userId}`, {
        credentials: "include",
      });
      if (profileRes.ok) {
        const profile = await profileRes.json();
        typeEl.textContent = profile.accountType || "CUSTOMER";
      } else {
        typeEl.textContent = "CUSTOMER";
      }
    } catch (err) {
      console.error(err);
      emptyEl.hidden = false;
      nameEl.textContent = "Error loading account";
    }
  }

  if (logoutBtn) {
    logoutBtn.addEventListener("click", async () => {
      try {
        await fetch("/logout", {
          method: "POST",
          credentials: "include",
        });
      } catch (e) {
        console.error(e);
      } finally {
        window.location.href = "/Client-Side/login.html";
      }
    });
  }

  loadAccount();
})();
