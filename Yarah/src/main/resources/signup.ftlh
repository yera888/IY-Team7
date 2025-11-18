<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Sign Up — YARAH</title>
  <link rel="stylesheet" href="/css/loginPage.css" />
</head>
<body>
  <div class="logincontainer">
    <div class="loginbox">
      <h1 class="brand"><a href="/">YARAH</a></h1>
      <p class="subhead">Create your account</p>

      <#if errorMessage??>
        <p style="color:#d00; font-size:1.3rem; text-align:center;">
          ${errorMessage}
        </p>
      </#if>

      <form class="form" action="/signup" method="post">
        <label class="label" for="name">Full name</label>
        <input
          class="input"
          id="name"
          name="name"
          type="text"
          placeholder="First Last"
          value="${user.name!}"
          required
        />

        <label class="label" for="signup-email">Email</label>
        <input
          class="input"
          id="signup-email"
          name="email"
          type="email"
          placeholder="you@example.com"
          value="${user.email!}"
          required
        />

        <label class="label" for="phone">Phone number</label>
        <input
          class="input"
          id="phone"
          name="phoneNumber"
          type="tel"
          placeholder="(555) 555-5555"
          value="${user.phoneNumber!}"
          required
        />

        <label class="label" for="password">Password</label>
        <input
          class="input"
          id="password"
          name="password"
          type="password"
          placeholder="••••••••"
          required
        />

        <div class="actions">
          <button class="btn" type="submit">Create account</button>
        </div>
      </form>

      <div class="switch">
        Already have an account?
        <a href="/login">Log in</a>
      </div>

      <footer class="small">
        By creating an account you agree to our Terms &amp; Privacy.
      </footer>
      <footer class="small">
        You can <strong>NOT</strong> buy or sell unless you have an account.
      </footer>
    </div>
  </div>
</body>
</html>
