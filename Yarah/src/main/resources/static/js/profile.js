(function () {
  const avatarEl = document.getElementById("profile-avatar");
  const nameEl = document.getElementById("profile-name");
  const handleEl = document.getElementById("profile-handle");
  const typeEl = document.getElementById("profile-type");
  const locationEl = document.getElementById("profile-location");
  const bioEl = document.getElementById("profile-bio");
  const shopNameEl = document.getElementById("profile-shop-name");
  const sellerBadgeEl = document.getElementById("profile-seller-badge");
  const emptyEl = document.getElementById("profile-empty");
  const errorEl = document.getElementById("profile-error");

  const becomeSellerBtn = document.getElementById("become-seller-btn");

  function showError(msg) {
    if (errorEl) {
      errorEl.textContent = msg;
      errorEl.hidden = false;
    } else {
      console.error(msg);
    }
  }

  function initials(name) {
    if (!name) return "Y";
    const parts = name.trim().split(/\s+/);
    const first = parts[0]?.[0] || "";
    const second = parts[1]?.[0] || "";
    return (first + second || first || "Y").toUpperCase();
  }

  function setAvatar(name) {
    if (!avatarEl) return;
    avatarEl.textContent = initials(name);
  }

  function setBasicProfile(user, profile) {
    const fullName = user?.name || "(no name)";
    setAvatar(fullName);

    if (nameEl) nameEl.textContent = fullName;
    if (handleEl) {
      const base =
        (user?.email || fullName || "yarah_user")
          .split("@")[0]
          .replace(/\s+/g, "_")
          .toLowerCase() || "yarah_user";
      handleEl.textContent = "@" + base;
    }

    if (typeEl) typeEl.textContent = profile?.accountType || "CUSTOMER";

    if (locationEl) {
      if (profile && profile.locationEnabled && profile.location) {
        locationEl.textContent = profile.location;
      } else {
        locationEl.textContent = "";
      }
    }
  }

  function setSellerInfo(sellerProfile) {
    if (!sellerProfile) {
      if (sellerBadgeEl) sellerBadgeEl.hidden = true;
      if (shopNameEl) shopNameEl.textContent = "";
      if (bioEl) bioEl.textContent = "";
      return;
    }

    if (sellerBadgeEl) sellerBadgeEl.hidden = false;
    if (shopNameEl) shopNameEl.textContent = sellerProfile.shopName || "";
    if (bioEl) bioEl.textContent = sellerProfile.bio || "";
  }

  async function fetchCurrentUser() {
    const res = await fetch("/api/users/me", { credentials: "include" });
    if (!res.ok) {
      throw new Error("Not logged in");
    }
    return res.json();
  }

  async function fetchProfileByUserId(userId) {
    const res = await fetch(`/api/profiles/by-user/${userId}`, {
      credentials: "include",
    });
    if (!res.ok) {
      throw new Error("Profile not found");
    }
    return res.json();
  }

  async function fetchSellerProfileByUserId(userId) {
    // Adjust path if your mapping is different
    const res = await fetch(`/api/seller-profiles/by-user/${userId}`, {
      credentials: "include",
    });
    if (!res.ok) {
      return null;
    }
    return res.json();
  }

  async function loadProfile() {
    try {
      const params = new URLSearchParams(window.location.search);
      const urlUserId = params.get("userId");
      let targetUserId;
      let user;

      if (urlUserId) {
        // Viewing someone else’s profile by ?userId=123
        targetUserId = urlUserId;

        user = { userId: Number(urlUserId), name: "", email: "" };
      } else {
        user = await fetchCurrentUser();
        targetUserId = user.userId;
      }

      const profile = await fetchProfileByUserId(targetUserId);

      setBasicProfile(user, profile);

      const sellerProfile = await fetchSellerProfileByUserId(targetUserId);
      setSellerInfo(sellerProfile);

      if (emptyEl) emptyEl.hidden = true;
      if (errorEl) errorEl.hidden = true;
    } catch (err) {
      console.error(err);
      if (emptyEl) emptyEl.hidden = false;
      showError("Unable to load profile.");
    }
  }

  if (becomeSellerBtn) {
    becomeSellerBtn.addEventListener("click", async () => {
      try {
        const me = await fetchCurrentUser();
        const payload = {
          userId: me.userId,
          shopName: "My Shop",
          bio: "",
          payoutDetails: "",
        };
        const res = await fetch("/api/seller-profiles", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          credentials: "include",
          body: JSON.stringify(payload),
        });

        if (!res.ok) {
          showError("Unable to create seller profile.");
          return;
        }

        const newSeller = await res.json();
        setSellerInfo(newSeller);
        if (typeEl) typeEl.textContent = "SELLER";
      } catch (e) {
        console.error(e);
        showError("Error creating seller profile.");
      }
    });
  }

  loadProfile();
})();
