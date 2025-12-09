<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/normalize.css">
    <link rel="stylesheet" href="/css/universalNavStyle.css">
    <link rel="stylesheet" href="/css/sellerOverview.css">
    <link rel="stylesheet" href="/css/sellerSelling.css">
    <title>Selling</title>
</head>
<body>
    <div class="navContainer">
        <div class="menu-burger">
        <input type="checkbox" />
        <svg
            xmlns="http://www.w3.org/2000/svg"
            class="icon icon-tabler icon-tabler-menu-2"
            viewBox="0 0 24 24"
            stroke-width="2"
            stroke="currentColor"
            fill="none"
            stroke-linecap="round"
            stroke-linejoin="round"
        >
        <path stroke="none" d="M0 0h24v24H0z" fill="none" />
        <line x1="4" y1="6" x2="20" y2="6" />
        <line x1="4" y1="12" x2="20" y2="12" />
        <line x1="4" y1="18" x2="20" y2="18" />
        </svg>
        <ul class="menu-burger__item-list">
            <li></li>
            <li></li>
            <li></li>
        </ul>
        </div>
        <div class="navBoxOne">
            <p>YARAH</p>
        </div>
        <div class="navBoxTwo">
            <ul>
                <li>
                    <a href="#">Dropdown</a>
                    <ul>
                        <li><a href="#">Profile</a></li>
                        <li><a href="/sellers/dashboard">Seller Overview</a></li>
                        <li><a href="#">Link 3</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div class="backGroundFlare"></div>
    <div class="playNiceContainer">
        <div class="sellerStatAndBarContainer">
            <div class="sellerQuickBarContainer">
                <div class="quickBarBox">
                    <a href="/sellers/dashboard">Overview</a>
                </div>
                <div class="quickBarBox">
                    <a href="/sellers/sellerSold">Sold</a>
                </div>
                <div class="quickBarBox">
                    <a href="/sellers/sellerSelling">Selling</a>
                </div>
                <div class="quickBarBox">
                    <a href="/sellers/sellerBalance">Balance</a>
                </div>
                <div class="quickBarBox">
                    <a href="/sellers/sellerStats">Stats</a>
                </div>
        </div>
        </div>
        <div class="newListingContainer">
            <#if listings?? && (listings?size > 0)>
                <h2 class="listings-header">Your Active Listings</h2>
                <div class="createListing">
                    <a href="/sellers/createListing/New">Create a Listing!</a>
                </div>
                <div class="listings-grid">
                    <#list listings as listing>
                        <div class="listing-card">
                            <#if listing.listingPhotoPath?? && listing.listingPhotoPath?has_content>
                                <#assign photoUrl = listing.listingPhotoPath?split(",")[0]>
                                <img src="${photoUrl}" alt="${listing.description}" class="listing-image">
                            <#else>
                                <img src="/images/placeholder.jpg" alt="No image" class="listing-image">
                            </#if>
                            <h3 class="listing-title">${listing.description?truncate(50)}</h3>
                            <p class="listing-info">Condition: ${listing.condition}</p>
                            <p class="listing-price">$${listing.price}</p>
                            <div class="listing-actions">
                                <a href="/sellers/editListing/${listing.listingId}" class="listing-link">Edit</a>
                                <span class="listing-divider">|</span>
                                <form action="/sellers/Listing/delete/${listing.listingId}" method="post" style="display: inline;">
                                    <button type="submit" class="delete-link" style="background: none; border: none; padding: 0; font: inherit; cursor: pointer;">Delete</button>
                                </form>
                            </div>
                        </div>
                    </#list>
                </div>
            <#else>
                <div class="no-listings-container">
                    <p class="no-listings-text">You haven't listed anything yet!</p>
                </div>
            </#if>
        </div>
    </div>
    <div class="footer">
        <h1>Placer Holder Footer</h1>
        <p>
            The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds jog, flick quartz, vex nymphs. Waltz, bad nymph, for quick jigs vex! Fox nymphs grab quick-jived waltz. Brick quiz whangs jumpy veldt fox. Bright vixens jump; dozy fowl quack. Quick wafting zephyrs vex bold Jim. Quick zephyrs blow, vexing daft Jim. Sex-charged fop blew my junk TV quiz. How quickly daft jumping zebras vex. Two driven jocks help fax my big quiz. Quick, Baz, get my woven flax jodhpurs! "Now fax quiz Jack!" my brave ghost pled. Five quacking zephyrs jolt my wax bed. Flummoxed by job, kvetching W. zaps Iraq. Cozy sphinx waves quart jug of bad milk. A very bad quack might jinx zippy fowls. Few quips galvanized the mock jury box. Quick brown dogs jump over the lazy fox. The jay, pig, 
        </p>
    </div>
</body>
</html>