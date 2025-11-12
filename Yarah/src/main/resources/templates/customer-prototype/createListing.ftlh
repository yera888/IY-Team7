<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/high-fidelity-prototype/customer-prototype/css/normalize.css">
    <link rel="stylesheet" href="/high-fidelity-prototype/customer-prototype/css/universalNavStyle.css">
    <link rel="stylesheet" href="/high-fidelity-prototype/customer-prototype/sellerOverview.css">
    <link rel="stylesheet" href="/high-fidelity-prototype/customer-prototype/css/createListing.css">
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
                        <li><a href="/high-fidelity-prototype/customer-prototype/sellerOverview.html">Seller Overview</a></li>
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
            <form id="newListing" action="/create" method="post" enctype="multipart/form-data">
            <div class="lineBoxTwo"></div>
                <h2>Photos</h2>
            <div class="photoBox">
                <div class="photoPlaceHolder">
                    <p>add a photo</p>
                    <input type="file" name="photo" id="photo">
                </div>
                <div class="photoPlaceHolder">
                    <p>cover photo</p>
                    <input type="file" name="photo" id="photo">
                </div>
                <div class="photoPlaceHolder">
                    <p>front</p>
                    <input type="file" name="photo" id="photo">
                </div>
                <div class="photoPlaceHolder">
                    <p>back</p>
                    <input type="file" name="photo" id="photo">
                </div>
                <div class="photoPlaceHolder">
                    <p>side</p>
                    <input type="file" name="photo" id="photo">
                </div>
                <div class="photoPlaceHolder">
                    <p>label</p>
                    <input type="file" name="photo" id="photo">
                </div>
                <div class="photoPlaceHolder">
                    <p>details</p>
                    <input type="file" name="photo" id="photo">
                </div>
                <div class="photoPlaceHolder">
                    <p>flaws</p>
                    <input type="file" name="photo" id="photo">
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
            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Magni nulla officiis reiciendis ad necessitatibus, quibusdam, optio, repudiandae quae quisquam labore fugit error aut maiores officia saepe atque nobis aspernatur et. Necessitatibus, illum! Et officiis iure cum, porro culpa voluptatem magnam perspiciatis nisi soluta veniam exercitationem dolorum. Labore eum nisi voluptatem!
        </p>
    </div>
</body>
</html>