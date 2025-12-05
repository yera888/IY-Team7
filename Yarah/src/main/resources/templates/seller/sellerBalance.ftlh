<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/normalize.css">
    <link rel="stylesheet" href="/css/universalNavStyle.css">
    <link rel="stylesheet" href="/css/sellerOverview.css">
    <link rel="stylesheet" href="/css/sellerSold.css">
    <link rel="stylesheet" href="/css/sellerBalance.css">
    <title>Customer Balance</title>
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
                        <li><a href="#">Link 1</a></li>
                        <li><a href="#">Link 2</a></li>
                        <li><a href="#">Link 3</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div class="backGroundFlare"></div>
    <div class="sellerStatAndBarContainer">
        <div class="sellerQuickBarContainer">
            <div class="quickBarBox">
                <a href="/dashboard.ftlh">Overview</a>
            </div>
            <div class="quickBarBox">
                <a href="/selllerSold.ftlh">Sold</a>
            </div>
            <div class="quickBarBox">
                <a href="/sellerSelling.ftlh">Selling</a>
            </div>
            <div class="quickBarBox">
                <a href="/sellerBalance.ftlh">Balance</a>
            </div>
            <div class="quickBarBox">
                <a href="/sellerStats.ftlh">Stats</a>
            </div>
        </div>
        <div class="sellerWeeklyStatOverviewContainer">
            <div class="balanceBox">
                <h1>Here is your <span class="specific">YARAH</span> Balance!</h1>
                <div class="cardBox">
                    <p>
                        US$0.00
                    </p>
                    <div class="line"></div>
                    <h2>Customer Name</h2>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <h1>Placer Holder Footer</h1>
        <p>
            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Magni nulla officiis reiciendis ad necessitatibus, quibusdam, optio, repudiandae quae quisquam labore fugit error aut maiores officia saepe atque nobis aspernatur et. Necessitatibus, illum! Et officiis iure cum, porro culpa voluptatem magnam perspiciatis nisi soluta veniam exercitationem dolorum. Labore eum nisi voluptatem!
        </p>
    </div>
</body>
</html>