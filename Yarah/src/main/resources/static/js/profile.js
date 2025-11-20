// src/main/resources/static/js/profile.js
(function () {
  const listingsGrid = document.getElementById("profile-listings-grid");
  const reviewsList = document.getElementById("profile-reviews-list");
  const emptyState = document.getElementById("profile-empty-state");

  const nameEl = document.getElementById("profile-name");
  const usernameEl = document.getElementById("profile-username");
  const avatarEl = document.getElementById("profile-avatar");
  const bioEl = document.getElementById("profile-bio");
  const emailEl = document.getElementById("profile-email");
  const accountTypeEl = document.getElementById("profile-account-type");
  const locationEl = document.getElementById("profile-location");

  const listingCountEl = document.getElementById("profile-listing-count");
  const ratingEl = document.getElementById("profile-rating");
  const reviewsCountEl = document.getElementById("profile-reviews-count");

  const actionsEl = document.getElementById("profile-actions");
  const switchSellerBtn = document.getElementById("btn-switch-seller");

  const tabs = document.querySelectorAll(".profile-tab");
  const listingsSection = document.getElementById("profile-listings");
  const reviewsSection = document.getElementById("profile-reviews");

  // Determine which user profile to show:
  //  - ?userId=123 => view that user
  //  - otherwise use logged-in user from localStorage
  const params = new URLSearchParams(location.search);
  const paramUserId = params.get("userId");
  const ownUserId = localStorage.getItem("yarahUserId");
  const userId = paramUserId || ownUserId;

  if (!userId) {
    nameEl.textContent = "Sign in to view your profile";
    usernameEl.textContent = "";
    bioEl.textContent =
      'You are not signed in. Please log in or create an account first.';
    actionsEl.innerHTML =
      '<a class="btn-outline" href="/login.html">Log in</a>';
    listingCountEl.textContent = "0";
    ratingEl.textContent = "–";
    reviewsCountEl.textContent = "0";
    emptyState.hidden = false;
    return;
  }

  // Tabs behavior
  tabs.forEach((tab) => {
    tab.addEventListener("click", () => {
      tabs.forEach((t) => t.classList.remove("active"));
      tab.classList.add("active");

      const which = tab.dataset.tab;
      if (which === "listings") {
        listingsSection.classList.add("profile-section--active");
        reviewsSection.classList.remove("profile-section--active");
      } else {
        reviewsSection.classList.add("profile-section--active");
        listingsSection.classList.remove("profile-section--active");
      }
    });
  });

  // Helper: format cents to dollars
  function formatPrice(cents) {
    if (cents == null) return "—";
    const num = Number(cents);
    if (Number.isNaN(num)) return "—";
    return "$" + (num / 100).toFixed(2);
  }

  // Helper: initials for avatar
  function initialsFromName(name) {
    if (!name) return "Y";
    const parts = String(name).trim().split(/\s+/);
    const first = parts[0] ? parts[0][0] : "";
    const second = parts[1] ? parts[1][0] : "";
    return (first + second || first || "Y").toUpperCase();
  }

  async function loadProfile() {
    // 1) Fetch profile (name, account type, user, etc.)
    const profileRes = await fetch(`/api/profiles/by-user/${userId}`, {
      credentials: "include",
    });

    if (!profileRes.ok) {
      nameEl.textContent = "Profile not found";
      bioEl.textContent = "We could not find this user.";
      return null;
    }

    const profile = await profileRes.json();
    const user = profile.user || {};

    const fullName =
      [profile.firstName, profile.lastName].filter(Boolean).join(" ") ||
      user.name ||
      "(no name)";

    nameEl.textContent = fullName;
    avatarEl.textContent = initialsFromName(fullName);

    // Use email as "username"-ish handle
    const handle =
      (user.email && user.email.split("@")[0]) || `user${user.userId || ""}`;
    usernameEl.textContent = "@" + handle;

    bioEl.textContent =
      (profile.bio && profile.bio.trim()) ||
      "No bio yet. Tell buyers about your style, fit, and shipping preferences.";

    emailEl.textContent = user.email || "–";
    accountTypeEl.textContent = profile.accountType || "CUSTOMER";
    locationEl.textContent = profile.locationEnabled ? "On" : "Hidden";

    // If this is your own profile, allow seller actions
    if (ownUserId && ownUserId === String(userId)) {
      actionsEl.innerHTML = `
        <button class="btn-outline" type="button" id="btn-edit-profile">
          Edit profile
        </button>
        <button class="btn-outline" type="button" id="btn-switch-seller">
          Become a seller
        </button>
      `;

      document
        .getElementById("btn-switch-seller")
        .addEventListener("click", onBecomeSellerClick);

      document
        .getElementById("btn-edit-profile")
        .addEventListener("click", () => {
          window.location.href = "/account.html";
        });
    } else {
      // Viewing someone else's profile
      actionsEl.innerHTML = "";
    }

    return profile;
  }

  async function loadSellerProfile() {
    // Seller profile is optional: 404 means they aren't a seller yet
    const res = await fetch(`/api/seller-profiles/by-user/${userId}`, {
      credentials: "include",
    });

    if (!res.ok) return null;
    return res.json();
  }

  async function loadListingsAndReviews(sellerProfile) {
    // Listings: we don't have a /by-seller endpoint, so get all active and filter
    const listingsRes = await fetch("/api/listings?activeOnly=true", {
      credentials: "include",
    });
    const allListings = listingsRes.ok ? await listingsRes.json() : [];

    let sellerListings = [];
    if (sellerProfile) {
      const spId = sellerProfile.sellerProfileId;
      sellerListings = allListings.filter(
        (l) => l.sellerProfile && l.sellerProfile.sellerProfileId === spId
      );
    }

    listingCountEl.textContent = sellerListings.length.toString();

    if (!sellerListings.length) {
      listingsGrid.innerHTML = "";
      emptyState.hidden = false;
    } else {
      emptyState.hidden = true;
      listingsGrid.innerHTML = sellerListings
        .map((l) => {
          const img =
            l.imageUrl ||
            "/images/greenblueshoes.jpg"; /* simple fallback from your images */
          return `
            <article class="profile-card">
              <a href="/products.html?slug=${encodeURIComponent(
                l.listingId
              )}">
                <div class="profile-card-img-wrap">
                  <img src="${img}" alt="${l.title || "Listing"}" />
                </div>
              </a>
              <div class="profile-card-body">
                <h3 class="profile-card-title">${l.title || "(untitled)"}</h3>
                <div class="profile-card-price">${formatPrice(
                  l.priceCents
                )}</div>
              </div>
            </article>
          `;
        })
        .join("");
    }

    // Reviews: for now we pull all reviews and filter by seller
    const reviewsRes = await fetch("/api/reviews", { credentials: "include" });
    const allReviews = reviewsRes.ok ? await reviewsRes.json() : [];

    let sellerReviews = [];
    if (sellerProfile) {
      const spId = sellerProfile.sellerProfileId;
      sellerReviews = allReviews.filter(
        (r) =>
          r.listing &&
          r.listing.sellerProfile &&
          r.listing.sellerProfile.sellerProfileId === spId
      );
    }

    reviewsCountEl.textContent = sellerReviews.length.toString();

    if (!sellerReviews.length) {
      ratingEl.textContent = "–";
      reviewsList.innerHTML =
        '<p class="profile-empty">No reviews yet.</p>';
    } else {
      const avg =
        sellerReviews.reduce((sum, r) => sum + (r.rating || 0), 0) /
        sellerReviews.length;
      ratingEl.textContent = avg.toFixed(1);

      reviewsList.innerHTML = sellerReviews
        .map((r) => {
          const stars = "★".repeat(r.rating || 0);
          const date =
            r.createdAt && String(r.createdAt).slice(0, 10);
          const listingTitle =
            r.listing && r.listing.title ? r.listing.title : "Listing";
          return `
            <article class="review-card">
              <div class="review-rating">${stars}</div>
              <div class="review-meta">
                ${listingTitle}${date ? " · " + date : ""}
              </div>
              <div class="review-comment">${r.comment || ""}</div>
            </article>
          `;
        })
        .join("");
    }
  }

  async function onBecomeSellerClick() {
    // Simple flow:
    //  1) If seller profile already exists, go to some seller settings page
    //  2) Otherwise, create a basic seller profile and reload
    try {
      const existing = await loadSellerProfile();
      if (existing) {
        // TODO: you can point to sellerReg.html for editing
        window.location.href = "/sellerReg.html";
        return;
      }

      const shopName = prompt(
        "Choose a shop name (you can change it later):",
        "My YARAH Shop"
      );
      if (!shopName) return;

      const body = {
        userId: Number(userId),
        shopName: shopName,
        bio: "",
        payoutDetails: "{}",
      };

      const res = await fetch("/api/seller-profiles", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body),
        credentials: "include",
      });

      if (!res.ok) {
        alert("Could not create seller profile (status " + res.status + ")");
        return;
      }

      // Reload the page to reflect seller status
      location.reload();
    } catch (err) {
      console.error(err);
      alert("Network error creating seller profile.");
    }
  }

  (async function init() {
    try {
      const profile = await loadProfile();
      const sellerProfile = await loadSellerProfile();
      await loadListingsAndReviews(sellerProfile);
    } catch (err) {
      console.error(err);
      nameEl.textContent = "Error loading profile";
      bioEl.textContent =
        "Something went wrong while loading this profile. Please try again.";
    }
  })();
})();
