<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Your Account — YARAH</title>
  <link rel="stylesheet" href="/css/loginPage.css" />
  <style>
    .profile-summary {
      display: grid;
      gap: 1rem;
      margin-top: 0.8rem;
      font-size: 1.4rem;
      color: #222;
    }
    .profile-row {
      display: flex;
      justify-content: space-between;
      gap: 1rem;
    }
    .profile-label {
      font-weight: 600;
      color: #444;
    }
    .profile-value {
      text-align: right;
      word-break: break-word;
    }
    .profile-links {
      display: grid;
      gap: 0.8rem;
      margin-top: 1.6rem;
      font-size: 1.4rem;
      text-align: center;
    }
    .profile-links a {
      text-decoration: none;
    }
    .profile-links a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
  <div class="logincontainer">
    <div class="loginbox">
      <h1 class="brand"><a href="/">YARAH</a></h1>
      <p class="subhead">Your account</p>

      <section class="profile-summary">
        <div class="profile-row">
          <span class="profile-label">Name</span>
          <span class="profile-value">
            ${profile.firstName!""} ${profile.lastName!""}
          </span>
        </div>

        <div class="profile-row">
          <span class="profile-label">Email</span>
          <span class="profile-value">${user.email}</span>
        </div>

        <div class="profile-row">
          <span class="profile-label">Phone</span>
          <span class="profile-value">${user.phoneNumber}</span>
        </div>

        <div class="profile-row">
          <span class="profile-label">Account type</span>
          <span class="profile-value">${profile.accountType!"CUSTOMER"}</span>
        </div>

        <div class="profile-row">
          <span class="profile-label">Location sharing</span>
          <span class="profile-value">
            ${profile.locationEnabled?string("On","Off")}
          </span>
        </div>
      </section>

      <div class="actions">
        <a class="btn" href="/profiles/${profile.profileId}/edit">Edit profile</a>
      </div>

      <div class="profile-links">
        <#if profile.accountType?upper_case != "SELLER">
          <a href="/seller/register">Become a seller</a>
        </#if>
        <a href="/likes">Your likes</a>
        <a href="/orders">Your orders</a>
        <a href="/cart">Your cart</a>
        <a href="/logout">Log out</a>
      </div>

      <footer class="small">
        Keep your email and phone up to date so buyers can reach you.
      </footer>
    </div>
  </div>
</body>
</html>
