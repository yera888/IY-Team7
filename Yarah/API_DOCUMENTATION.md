# Yarah

## Seller API Endpoints

### Create Seller
```
POST /api/sellers
Content-Type: application/json

{
  "sellerId": 21,
  "name": "joe shmelly two",
  "email": "joeshmellytwo@email.com",
  "password": "oneshmellyguytwo",
  "address": "big smelly lane drive",
  "phoneNumber": "1800shmellytwo"
}
```

### Update Seller
```
Doesn't work 
```

### Get Seller
```
GET /api/sellers/{sellerId}
Content-Type: application/json

{
  "sellerId": 21,
  "name": "joe shmelly two",
  "email": "joeshmellytwo@email.com",
  "password": "oneshmellyguytwo",
  "address": "big smelly lane drive",
  "phoneNumber": "1800shmellytwo"
}
```

### Create Listing
```
POST /api/listings
Content-Type: application/json

{
  "listingId": 1,
  "seller": {
    "sellerId": 770212,
    "name": null,
    "email": null
  },
  "description": "Some filler text",
  "condition": "Good",
  "imgUrl": "new hot image link",
  "size": 10.0,
  "weight": 22.0,
  "price": 34.00,
  "available": true,
  "sold": false
}
```

### Get Listing
```
GET /api/listings/{id}
Content-Type: application/json

{
  "listingId": 2,
  "seller": {
    "sellerId": 770212,
    "name": "test guy",
    "email": "testguy@gmail.com"
  },
  "description": "Some filler text two",
  "condition": "Fair",
  "imgUrl": "new hot image link two",
  "size": 12.0,
  "weight": 26.00,
  "price": 38.00,
  "available": true,
  "sold": true
}
```

### Get All Listings
```
GET /api/listings
Content-Type: application/json

[
  {
    "listingId": 1,
    "seller": {
      "sellerId": 770212,
      "name": "test guy",
      "email": "testguy@gmail.com"
    },
    "description": "Some filler text",
    "condition": "Good",
    "imgUrl": "new hot image link",
    "size": 10.0,
    "weight": 22.00,
    "price": 34.00,
    "available": true,
    "sold": false
  },
  {
    "listingId": 2,
    "seller": {
      "sellerId": 770212,
      "name": "test guy",
      "email": "testguy@gmail.com"
    },
    "description": "Some filler text two",
    "condition": "Fair",
    "imgUrl": "new hot image link two",
    "size": 12.0,
    "weight": 26.00,
    "price": 38.00,
    "available": true,
    "sold": true
  }
]
```

### Update Listing
```
PUT /api/listings/{id}
Content-Type: application/json

{
  "listingId": 2,
  "seller": {
    "sellerId": 770212,
    "name": "test guy",
    "email": "testguy@gmail.com"
  },
  "description": "Some filler text two updated",
  "condition": "Fair",
  "imgUrl": "new hot image link two updated",
  "size": 14.0,
  "weight": 72.00,
  "price": 80.00,
  "available": false,
  "sold": true
}
```

### Delete Listing
```
DELETE /api/listings/{id}
Content-Type: application/json

[
  {
    "listingId": 1,
    "seller": {
      "sellerId": 770212,
      "name": "test guy",
      "email": "testguy@gmail.com"
    },
    "description": "Some filler text",
    "condition": "Good",
    "imgUrl": "new hot image link",
    "size": 10.0,
    "weight": 22.00,
    "price": 34.00,
    "available": true,
    "sold": false
  }
]

## Customer API Endpoints

### Sign up




```
