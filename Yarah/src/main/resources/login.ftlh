<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Login — YARAH</title>
  <link rel="stylesheet" href="/css/loginPage.css" />
</head>
<body>
  <div class="logincontainer">
    <div class="loginbox">
      <h1 class="brand"><a href="/">YARAH</a></h1>
      <p class="subhead">Welcome back</p>

      <#if errorMessage??>
        <p style="color:#d00; font-size:1.3rem; text-align:center;">
          ${errorMessage}
        </p>
      </#if>

      <form class="form" action="/login" method="post">
        <label class="label" for="email">Email</label>
        <input
          class="input"
          id="email"
          name="email"
          type="email"
          placeholder="you@example.com"
          value="${loginForm.email!}"
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
          <button class="btn" type="submit">Continue</button>
        </div>
      </form>

      <div class="switch">
        Don’t have an account?
        <a href="/signup">Sign up</a>
      </div>

      <footer class="small">
        By continuing you agree to our Terms &amp; Privacy.
      </footer>
      <footer class="small">
        You can <strong>NOT</strong> buy or sell unless you have an account.
      </footer>
    </div>
  </div>
</body>
</html>
