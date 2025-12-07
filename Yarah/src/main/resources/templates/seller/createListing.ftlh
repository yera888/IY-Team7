<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/normalize.css">
    <link rel="stylesheet" href="/css/universalNavStyle.css">
    <link rel="stylesheet" href="/css/sellerOverview.css">
    <link rel="stylesheet" href="/css/createListing.css">
    <title>Create a Listing</title>
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
    <div class="createListingContainer">
        <div class="formContainer">
            <h1>${title}</h1>
            <form id="newListing" action="/sellers/Listing/new" method="post" enctype="multipart/form-data">
            <div class="lineBoxTwo"></div>
                <h2>Photos</h2>
            <div class="photoBox">
                <div class="photoPlaceHolder">
                    <p>add a photo</p>
                    <input type="file" name="listingPhotos" id="photo" accept="image/*">
                </div>
                <div class="photoPlaceHolder">
                    <p>cover photo</p>
                    <input type="file" name="listingPhotos" id="photoOne" accept="image/*">
                </div>
                <div class="photoPlaceHolder">
                    <p>front</p>
                    <input type="file" name="listingPhotos" id="photoTwo" accept="image/*">
                </div>
                <div class="photoPlaceHolder">
                    <p>back</p>
                    <input type="file" name="listingPhotos" id="photoThree" accept="image/*">
                </div>
                <div class="photoPlaceHolder">
                    <p>side</p>
                    <input type="file" name="listingPhotos" id="photoFour" accept="image/*">
                </div>
                <div class="photoPlaceHolder">
                    <p>label</p>
                    <input type="file" name="listingPhotos" id="photoFive" accept="image/*">
                </div>
                <div class="photoPlaceHolder">
                    <p>details</p>
                    <input type="file" name="listingPhotos" id="photoSix" accept="image/*">
                </div>
                <div class="photoPlaceHolder">
                    <p>flaws</p>
                    <input type="file" name="listingPhotos" id="photoSeven" accept="image/*">
                </div>
            </div>
            <h3>Description</h3>
            <div class="descriptionBox">
                <input class="descriptionInput" type="text" name="description" id="description">
            </div>
            <h4>Item Info</h4>
            <div class="itemBox">
                <div class="infoBox">
                    <label for="">Brand</label>
                    <input type="text" name="brand" id="brand">
                </div>
                <div class="space"></div>
                <div class="infoBox">
                    <label for="">Size</label>
                    <input type="text" name="size" id="size">
                </div>
                <div class="space"></div>
                <div class="infoBox">
                    <label for="">Weight</label>
                    <input type="text" name="weight" id="weight">
                </div>
                <div class="space"></div>
                <div class="infoBox">
                    <label for="">Condition</label>
                    <input type="text" name="condition" id="condition">
                </div>
                <div class="space"></div>
                <div class="infoBox">
                    <label for="">Price</label>
                    <input type="text" name="price" id="price">
                </div>
            </div>
            <h5>Location</h5>
            <div class="locationBox">
                <div class="locationInput">
                    <label for="">Country</label>
                    <input type="text" name="" id="">
                </div>
                <div class="space"></div>
                <div class="locationInput">
                    <label for="">City</label>
                    <input type="text" name="" id="">
                </div>
            </div>
            <div class="space"></div>
            <div class="postEditBox">
                <div class="postBox">
                    <button type="submit">Post Now</button>
                </div>
                <div class="editBox">
                    <button type="submit">Save and Edit later</button>
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