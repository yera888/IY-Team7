<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Edit Profile — YARAH</title>
  <link rel="stylesheet" href="/css/loginPage.css" />
</head>
<body>
  <div class="logincontainer">
    <div class="loginbox">
      <h1 class="brand"><a href="/">YARAH</a></h1>
      <p class="subhead">Edit your profile</p>

      <#-- optional error message hook -->
      <#if errorMessage??>
        <p style="color:#d00; font-size:1.3rem; text-align:center;">
          ${errorMessage}
        </p>
      </#if>

      <form class="form" method="post" action="/profiles/${profileForm.profileId}/edit">
        <input type="hidden" name="profileId" value="${profileForm.profileId}" />
        <#if profileForm.userId??>
          <input type="hidden" name="userId" value="${profileForm.userId}" />
        </#if>

        <label class="label" for="first-name">First name</label>
        <input
          class="input"
          id="first-name"
          name="firstName"
          type="text"
          placeholder="First name"
          value="${profileForm.firstName!}"
        />

        <label class="label" for="last-name">Last name</label>
        <input
          class="input"
          id="last-name"
          name="lastName"
          type="text"
          placeholder="Last name"
          value="${profileForm.lastName!}"
        />

        <fieldset class="fieldset">
          <legend class="label">Allow location for meet-ups?</legend>
          <div class="radio-row">
            <label class="radio">
              <input
                type="radio"
                name="locationEnabled"
                value="true"
                <#if profileForm.locationEnabled?? && profileForm.locationEnabled>checked</#if>
              />
              <span>Yes</span>
            </label>
            <label class="radio">
              <input
                type="radio"
                name="locationEnabled"
                value="false"
                <#if !profileForm.locationEnabled?? || !profileForm.locationEnabled>checked</#if>
              />
              <span>No</span>
            </label>
          </div>
        </fieldset>

        <div class="actions">
          <button class="btn" type="submit">Save changes</button>
        </div>
      </form>

      <div class="switch">
        <a href="/account">Back to account</a>
      </div>

      <footer class="small">
        Your name and location settings help buyers understand how to shop with you.
      </footer>
    </div>
  </div>
</body>
</html>
