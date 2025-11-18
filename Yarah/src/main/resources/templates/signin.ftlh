<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="Yarah/src/main/resources/static/css/normalize.css">
    <link rel="stylesheet" href="Yarah/src/main/resources/static/css/universalNavStyle.css">
    <link rel="stylesheet" href="Yarah/src/main/resources/static/css/sellerOverview.css">
    <title>Sign In</title>
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
                        <li><a href="/high-fidelity-prototype/seller/sellerOverview.html">Seller Overview</a></li>
                        <li><a href="#">Link 3</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div class="backGroundFlare"></div>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <ul class="nav nav-tabs card-header-tabs">
                            <li class="nav-item">
                                <a class="nav-link active" id="user-tab" data-bs-toggle="tab"
                                    href="#user">User</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="seller-tab" data-bs-toggle="tab" href="#seller">Farmer</a>
                            </li>
                        </ul>
                    </div>
                    <div class="card-body">
                        <div class="tab-content">
                            <div class="tab-pane fade show active" id="user">
                                <h3 class="card-title mb-4">User Sign In</h3>
                                <form action="/users/signin" method="post">
                                    <div class="mb-3">
                                        <label for="userEmail" class="form-label">Email</label>
                                        <input type="email" class="form-control" id="userEmail" name="email"
                                            required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="userPassword" class="form-label">Password</label>
                                        <input type="password" class="form-control" id="userPassword"
                                            name="password" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Sign In</button>
                                </form>
                                <div class="mt-3">
                                    <a href="/users/signup">Need an account? Sign up as a user</a>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="seller">
                                <h3 class="card-title mb-4">Farmer Sign In</h3>
                                <form action="/sellers/signin" method="post">
                                    <div class="mb-3">
                                        <label for="sellerEmail" class="form-label">Email</label>
                                        <input type="email" class="form-control" id="sellerEmail" name="email" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="sellerPassword" class="form-label">Password</label>
                                        <input type="password" class="form-control" id="sellerPassword" name="password"
                                            required>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Sign In</button>
                                </form>
                                <div class="mt-3">
                                    <a href="/sellers/signup">Need an account? Sign up as a seller</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>