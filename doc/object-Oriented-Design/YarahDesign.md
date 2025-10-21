# Yarah Online Boutique - Software Design 

Version .25  
Prepared by Isaac Hollaway, Yeraldine Tamayo\
Yarah Hub\
Oct 20, 2025

Table of Contents
=================
* [Revision History](#revision-history)
* 1 [Product Overview](#1-product-overview)
* 2 [Use Cases](#2-use-cases)
  * 2.1 [Use Case Model](#21-use-case-model)
  * 2.2 [Use Case Descriptions](#22-use-case-descriptions)
    * 2.2.1 [Actor: Seller](#221-actor-Seller)
    * 2.2.2 [Actor: Customer](#222-actor-Customer) 
* 3 [UML Class Diagram](#3-uml-class-diagram)
* 4 [Database Schema](#4-database-schema)

## Revision History
| Name | Date    | Reason For Changes  | Version   |
| ---- | ------- | ------------------- | --------- |
|Isaac |10/20    | Initial Design      |    .25    |
|      |         |                     |           |
|      |         |                     |           |

## 1. Product Overview
 Yarah is an online second hand clothing boutique for customers, and retailers in your area. Giving retailers the ability to easily manage their listings, and customers the ability to easily find them. Yarah will be a web based application, which will simplify the nature of trying to buy and sell second hand, and new clothing. Our main goal is to create a welcoming online community for users that enjoy good deals on quality clothing.

## 2. Use Cases
### 2.1 Use Case Model
![Use Case Model](https://github.com/yera888/IY-Team7/blob/a09dd5cc24649df55c6719c2018ff56cb3e141fc/doc/object-Oriented-Design/use-case-model.png)

### 2.2 Use Case Descriptions

#### 2.2.1 Actor: Seller
##### 2.2.1.1 Sign Up
A seller can sign up first as a customer, and then once they have created their profile apply to be a seller.
##### 2.2.1.2 Log In
A seller will be able to sign in using their registered email and password. After logging in sellers will be directed to the homepage.
##### 2.2.1.3 Create Listings
Sellers will be able to create new listings. These listings will contain weight, price, description, img, size, and condition
##### 2.2.1.4 Modify Listings
Sellers will be able to modify all the above listing attributes.
##### 2.2.1.4 Remove Listing
Sellers will be able to delete listings that either have not sold or are no longer willing to sell.
##### 2.2.1.5 View Stats
Sellers will be able to view an overview of all their relevant statistics. Such as: Listing views, Revenue, and Completed shipments.

#### 2.2.2 Actor: Customer
##### 2.2.2.1 Sign Up
A customer shall sign up with a name, email, password, and phone number. Emails and phone numbers will be one time use and will be directly connected to their profile.

##### 2.2.2.2 Log In
A customer shall log in with their registered email and password. After login, they will be directed to the homepage.

##### 2.2.2.3 Delivery Checkout
A costumer will enter their shipping and payment information when they choose the delivery checkout option. Once the order is placed they will receive a confirmation email with their cart information and unique order number.

##### 2.2.1.4 Like/Heart Product
A customer can save products to either a “Liked” or “Loved” album by pressing the heart or thumbs up button on each specific product listing. These saved products then appear on the chosen album page that shows of all the "Liked" or "Loved" products.

##### 2.2.1.5 Leave Review
A customer can rate one seller 1–5 stars with a short review (optional). Reviews are allowed after a product has been delivered or a meet-up has occured.

##### 2.2.1.6 Local Pickup
If a customer chooses not to have their product delivered the customer requests a public meet-up location and time for an "Able for pick-up" listing that is within the radius range the seller has allowed for. The transaction must fully go through in order for them to request the public meet-up. Then the customer meets up with the seller to finish the full transaction and confirms a sucessful exchange.

##### 2.2.1.7 Browse Clothes/Sales. 
The customer can browse through the clothes by different categorys such as sale, or product types such as skirt, dresses, shoes or by the #tags provided.

##### 2.2.1.8 Message Seller
A customer can send a message for each specific listing to the seller.

##### 2.2.1.9 Manage Profile
A customer can choose to update/edit their profile photo, and username on their profile page.

## 3. UML Class Diagram
![UML Class Diagram](https://github.com/csc340-uncg/f25-team0/blob/main/doc/Object-Oriented-Design/class-diagram.png)
## 4. Database Schema
![UML Class Diagram](https://github.com/csc340-uncg/f25-team0/blob/main/doc/Object-Oriented-Design/schema.png)
