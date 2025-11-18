<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Seller Registration — YARAH</title>
  <link rel="stylesheet" href="/css/loginPage.css" />
  <link rel="stylesheet" href="/css/sellerRegistration.css" />
</head>
<body>
  <div class="logincontainer">
    <div class="loginbox">
      <h1 class="brand"><a href="/">YARAH</a></h1>
      <p class="subhead">
        <#if sellerForm.sellerProfileId??>
          Edit your seller profile
        <#else>
          Register as a seller
        </#if>
      </p>

      <#if errorMessage??>
        <p style="color:#d00; font-size:1.3rem; text-align:center;">
          ${errorMessage}
        </p>
      </#if>

      <form class="form" method="post" action="/seller/register">
        <#if sellerForm.sellerProfileId??>
          <input type="hidden" name="sellerProfileId" value="${sellerForm.sellerProfileId}" />
        </#if>
        <#if sellerForm.userId??>
          <input type="hidden" name="userId" value="${sellerForm.userId}" />
        </#if>

        <label class="label" for="shop-name">Shop name</label>
        <input
          class="input"
          id="shop-name"
          name="shopName"
          type="text"
          placeholder="Your shop name"
          value="${sellerForm.shopName!}"
          required
        />

        <label class="label" for="bio">Shop bio</label>
        <textarea
          class="input"
          id="bio"
          name="bio"
          rows="3"
          placeholder="Tell buyers what your shop is about"
        >${sellerForm.bio!}</textarea>

        <fieldset class="fieldset">
          <legend class="label">Enable location for meet-ups?</legend>
          <div class="radio-row">
            <label class="radio">
              <input
                type="radio"
                name="locationEnabled"
                value="true"
                <#if sellerForm.locationEnabled?? && sellerForm.locationEnabled>checked</#if>
                required
              />
              <span>Yes</span>
            </label>
            <label class="radio">
              <input
                type="radio"
                name="locationEnabled"
                value="false"
                <#if !sellerForm.locationEnabled?? || !sellerForm.locationEnabled>checked</#if>
              />
              <span>No</span>
            </label>
          </div>
        </fieldset>

        <label class="label" for="payout">Payout details</label>
        <textarea
          class="input"
          id="payout"
          name="payoutDetails"
          rows="3"
          placeholder='Example: {"bank":"Chase","type":"Zelle","account_last4":"1234"}'
        >${sellerForm.payoutDetails!}</textarea>

        <div class="actions">
          <button class="btn" type="submit">
            <#if sellerForm.sellerProfileId??>
              Save changes
            <#else>
              Save seller profile
            </#if>
          </button>
        </div>
      </form>

      <div class="switch">
        <a href="/account">Back to account</a>
      </div>

      <footer class="small">
        Buyers will see your shop name and bio. Payout details are private.
      </footer>
      <footer class="small">
        You can change this later in your account settings.
      </footer>
    </div>
  </div>
</body>
</html>
