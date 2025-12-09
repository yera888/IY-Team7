<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/normalize.css">
    <link rel="stylesheet" href="/css/universalNavStyle.css">
    <link rel="stylesheet" href="/css/sellerOverview.css">
    <link rel="stylesheet" href="/css/editListing.css">
    <title>Edit a Listing</title>
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
    <div class="editListingContainer">
        <div class="formContainer">
            <h1>${title}</h1>
            <form id="editListing" action="/sellers/Listing/edit/${listing.listingId}" method="post" enctype="multipart/form-data">
                
                <div class="lineBoxTwo"></div>
                <h2>Current Photos</h2>
                
                <#if listing.listingPhotoPath?? && listing.listingPhotoPath != "/images/placeholder.jpg">
                    <div class="current-photos-section">
                        <p class="current-photos-label">Current photos (upload new ones to replace):</p>
                        <div class="current-photos-grid">
                            <#list listing.listingPhotoPath?split(",") as photoUrl>
                                <img src="${photoUrl?trim}" alt="Listing photo" class="current-photo-thumbnail">
                            </#list>
                        </div>
                    </div>
                </#if>

                <h2>Upload New Photos (Optional)</h2>
                <div class="photoBox">
                    <div class="photoPlaceHolder">
                        <p>add a photo</p>
                        <input type="file" name="photos" accept="image/*">
                    </div>
                    <div class="photoPlaceHolder">
                        <p>cover photo</p>
                        <input type="file" name="photos" accept="image/*">
                    </div>
                    <div class="photoPlaceHolder">
                        <p>front</p>
                        <input type="file" name="photos" accept="image/*">
                    </div>
                    <div class="photoPlaceHolder">
                        <p>back</p>
                        <input type="file" name="photos" accept="image/*">
                    </div>
                    <div class="photoPlaceHolder">
                        <p>side</p>
                        <input type="file" name="photos" accept="image/*">
                    </div>
                    <div class="photoPlaceHolder">
                        <p>label</p>
                        <input type="file" name="photos" accept="image/*">
                    </div>
                    <div class="photoPlaceHolder">
                        <p>details</p>
                        <input type="file" name="photos" accept="image/*">
                    </div>
                    <div class="photoPlaceHolder">
                        <p>flaws</p>
                        <input type="file" name="photos" accept="image/*">
                    </div>
                </div>
                
                <h3>Description</h3>
                <div class="descriptionBox">
                    <input class="descriptionInput" type="text" name="description" id="description" 
                           value="${listing.description}" required>
                </div>
                
                <h4>Item Info</h4>
                <div class="itemBox">
                    <div class="infoBox">
                        <label for="size">Size</label>
                        <input type="number" step="0.01" name="size" id="size" 
                               value="${listing.size}" required>
                    </div>
                    <div class="space"></div>
                    <div class="infoBox">
                        <label for="weight">Weight</label>
                        <input type="number" step="0.01" name="weight" id="weight" 
                               value="${listing.weight}" required>
                    </div>
                    <div class="space"></div>
                    <div class="infoBox">
                        <label for="condition">Condition</label>
                        <select name="condition" id="condition" required>
                            <option value="Like New" <#if listing.condition == "Like New">selected</#if>>Like New</option>
                            <option value="Lightly Used" <#if listing.condition == "Lightly Used">selected</#if>>Lightly Used</option>
                            <option value="Used" <#if listing.condition == "Used">selected</#if>>Used</option>
                            <option value="Roughly Used" <#if listing.condition == "Roughly Used">selected</#if>>Roughly Used</option>
                        </select>
                    </div>
                    <div class="space"></div>
                    <div class="infoBox">
                        <label for="price">Price ($)</label>
                        <input type="number" step="0.01" name="price" id="price" 
                               value="${listing.price}" required>
                    </div>
                    <div class="space"></div>
                    <div class="infoBox">
                        <label for="available">Available</label>
                        <select name="available" id="available">
                            <option value="true" <#if listing.available>selected</#if>>Yes</option>
                            <option value="false" <#if !listing.available>selected</#if>>No</option>
                        </select>
                    </div>
                    <div class="space"></div>
                    <div class="infoBox">
                        <label for="sold">Sold</label>
                        <select name="sold" id="sold">
                            <option value="false" <#if !listing.sold>selected</#if>>No</option>
                            <option value="true" <#if listing.sold>selected</#if>>Yes</option>
                        </select>
                    </div>
                </div>
                
                <div class="space"></div>
                <div class="postEditBox">
                    <div class="postBox">
                        <button type="submit">Update Listing</button>
                    </div>
                    <div class="editBox">
                        <a href="/sellers/sellerSelling">
                            <button type="button">Cancel</button>
                        </a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="footerTwo">
        <h6>Placer Holder Footer</h6>
        <p>
            The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds jog, flick quartz, vex nymphs. Waltz, bad nymph, for quick jigs vex! Fox nymphs grab quick-jived waltz. Brick quiz whangs jumpy veldt fox. Bright vixens jump; dozy fowl quack. Quick wafting zephyrs vex bold Jim. Quick zephyrs blow, vexing daft Jim. Sex-charged fop blew my junk TV quiz. How quickly daft jumping zebras vex. Two driven jocks help fax my big quiz. Quick, Baz, get my woven flax jodhpurs! "Now fax quiz Jack!" my brave ghost pled. Five quacking zephyrs jolt my wax bed. Flummoxed by job, kvetching W. zaps Iraq. Cozy sphinx waves quart jug of bad milk. A very bad quack might jinx zippy fowls. Few quips galvanized the mock jury box. Quick brown dogs jump over the lazy fox. The jay, pig, 
        </p>
    </div>
</body>
</html>