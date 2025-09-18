# Software Requirements Specification
## For <EdotCOM>

Version 0.1  
Prepared by Isaac Hollaway, Yeraldine Tamayo  
CSC-340  
September 16, 2025

Table of Contents
=================
* [Revision History](#revision-history)
* 1 [Introduction](#1-introduction)
  * 1.1 [Document Purpose](#11-document-purpose)
  * 1.2 [Product Scope](#12-product-scope)
  * 1.3 [Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
  * 1.4 [References](#14-references)
  * 1.5 [Document Overview](#15-document-overview)
* 2 [Product Overview](#2-product-overview)
  * 2.1 [Product Functions](#21-product-functions)
  * 2.2 [Product Constraints](#22-product-constraints)
  * 2.3 [User Characteristics](#23-user-characteristics)
  * 2.4 [Assumptions and Dependencies](#24-assumptions-and-dependencies)
* 3 [Requirements](#3-requirements)
  * 3.1 [Functional Requirements](#31-functional-requirements)
    * 3.1.1 [User Interfaces](#311-user-interfaces)
    * 3.1.2 [Hardware Interfaces](#312-hardware-interfaces)
    * 3.1.3 [Software Interfaces](#313-software-interfaces)
  * 3.2 [Non-Functional Requirements](#32-non-functional-requirements)
    * 3.2.1 [Performance](#321-performance)
    * 3.2.2 [Security](#322-security)
    * 3.2.3 [Reliability](#323-reliability)
    * 3.2.4 [Availability](#324-availability)
    * 3.2.5 [Compliance](#325-compliance)
    * 3.2.6 [Cost](#326-cost)
    * 3.2.7 [Deadline](#327-deadline)

## Revision History
| Name      | Date    | Reason For Changes  | Version   |
| Yeraldine | 9/17    | Initial SRS         |       1.0 |
| Isaac     | 9/18    | Completed SRS       |       1.0 |

## 1. Introduction

### 1.1 Document Purpose
The purpose of this SRS is to show both the users-view and developer-view requirements for the EdotCOM webpage. This page is an E-Commerse website that is an online second hand thift store. The requirements for the users deal with all the interactions the user can have with the website which could be interacted with as a buyer or a seller. The developer-oriented requirments specify on the developmental aspects and constraints that must be known in detail to provide the best outputs or functional features to meet the users needs. 

### 1.2 Product Scope
The purpose of EdotCom is act as an online boutique for customers, and retailers in your area. Giving retailers the ability to easily manage their listings, and customers the ability to easily find them. EdotCom will be a web based application, which will hopefully simplify the nature of storing and selling large quantities, and varieties of clothing. Our main goal is to create a welcoming online community for users that enjoy good deals on quality clothing.

### 1.3 Definitions, Acronyms and Abbreviations
| #Tag       | A set of curated label users can add that gives a description to categorize an item. Tags make searching through different sections/styles of clothing easier to help users have a more curated search. Examples are #coquette #punk #streetwear |

|Meet-Up     | In-person exchange at a public place and time chosen in the app.|

|Stripe      | Payment processor we use for authorize then capture.|

|Health Check| Simple endpoint we ping to know if the app is up.|

| Java       | A programming language originally developed by James Gosling at Sun Microsystems. We will be using this language to build the backend service for LocalHarvest Hub                 |
| Postgresql | Open-source relational database management system.                                                                                                                                 |
| SpringBoot | An open-source Java-based framework used to create a micro Service. This will be used to create and run our application.                                                           |
| Spring MVC | Model-View-Controller. This is the architectural pattern that will be used to implement our system.                                                                                |
| Spring Web | Will be used to build our web application by using Spring MVC. This is one of the dependencies of our system.                                                                      |
| API        | Application Programming Interface. This will be used to interface the backend and the fronted of our application.                                                                  |
| HTML       | Hypertext Markup Language. This is the code that will be used to structure and design the web application and its content.                                                         |
| CSS        | Cascading Style Sheets. Will be used to add styles and appearance to the web app.                                                                                                  |
| JavaScript | An object-oriented computer programming language commonly used to create interactive effects within web browsers.Will be used in conjunction with HTML and CSS to make the web app.|
| VS Code    | An integrated development environment (IDE) for Java. This is where our system will be created.                                                                                    |

### 1.4 References
https://spring.io/guides
https://necolas.github.io/normalize.css

### 1.5 Document Overview
Section 1 contains a detailed introduction of the document to give readers a good general idea of what it is about. Section 2 has information on the functions/features for all the suggested roles and ways to interact with the webpage. Section 3 gives the requiements both functional and non-functional with aadded nitty-gritty information about the webpage mostly for the developer.

## 2. Product Overview
EdotCOM will be a web platform catered to users that shop online often, are fashion savvy, and would like to buy clothing in an eco-friendly way. Instead of shopping at thrift stores that suffer from inflated prices and filthy workspaces. Customers can use EdotCOM to find local sellers, view listings and have the ability to leave reviews based on their experience with other retailers. Retailers will have the ability to create listings, modify those listings, and create limited time discounts. EdotCOM will also give local customers and retailers the option for local pick-up/drop-off skirting the need to use expensive mail services.

### 2.1 Product Functions

* Create Listing of each product with detail description of all its attributes.
* Interact with interface easily to navigate through filtered options such as by price, popularity, etc.
* Have the option to enable location so locals can either pick up or drop off products.
* A feature for the buyer and seller to communicate with one another about an individual product.
* #Tag system that you can apply to each product for neater organization and categorized sections to search through.
* #Tags only shown inside the search bar and can be clicked on from there no free word tags.
* Filtering option by popularity, price as well as a sorting option for low to high or high to low prices etc.
* Seller Rating and review System 
* Feed of listed items presented as a photo gallery
* Saving feature which would be a Like or Love albumn both in separate webpage locations.
* Recieved notifications for both the seller and buyers for things like when a product has been shipped, how many views a listiing recieved, if a buyer sent a chat message to the seller, meet up updates, refunds or cancels. 
* Stats shown to seller of their most viewed items.
* Discount UX if a product has reduced in price it shows the product is on sale.
* Create an account with username and password and requires both an email and a phone number. 
* Banning option for potential scammers which doesnt allow scammers to re-signup through email and number.

### 2.2 Product Constraints

Fraud risk: Some users may try to scam by not delivering the products and recieving payments. We use payment holds, simple dispute reports, and bans.

 Big clothing drops that have been scheduled and promoted can slow the site due to traffic. We may need basic caching and limits later.

Delivery mistakes: Wrong addresses cause problems. We’ll validate addresses and keep meet-ups to public places.

Third-party services: We rely on payments, email, maps, and photo storage. If they go down or have issues, parts of our site are affected.

Free plan limits. Starter tiers cap database, storage, and API calls. We might need to upgrade as we grow.

Tag catalog upkee: Only system generated tags (created by us) are allowed. We must update the list to follow trends.

Payment timing for security/reliability purposes: We hold money at checkout and capture later. Holds can expire if no one ships or confirms.

Meet-up safety: We show a short safety note and only allow public locations. We don’t supervise in-person exchanges and have nothing to do with what happens at these times.

Logging and privacy: We keep basic logs for payments and bans which is tracked by phone number and emails but people can still find loop-holes.
  
### 2.3 User Characteristics

Our website application does not expect our users to have any abnormal computer knowledge. Our system functions were designed using other similar or widely used websites. As long as users are familiar with online shopping, creating playlists, and filling out forms they should have no issues navigating, and using the application.  

Though we do plan on catering more specifically to users that are interested in selling, and buying second hand clothing. Users that are unfamiliar with the process should be able to easily ascertain how the application functions.

### 2.4 Assumptions and Dependencies
Our system will be dependant on Java, Spring, Spring Boot, RestAPI, and VSCode for the back end of our application. We will be dependant on HTML, CSS, JavaScript, and VSCode for the front end of our application. We are likely to reuse some of our CSS, and HTML from previous assignments. We are also relying on PostgreSQL which means we will be limited by the constraints of it's free database.

## 3. Requirements

### 3.1 Functional Requirements 
- FR0: The system shall allow users to create accounts as customers with email, password, and a unique account ID; a customer shall be able to transition to a retailer account while keeping the same ID.


- FR1: The system shall allow retailers to create, modify, and delete listings with description, condition, price, size, weight, photos, purchasedStatus, and location if enabled; listings shall be searchable and filterable.

- FR2: The system shall let customers “Like” or “Heart” listings to save them into Liked and Loved albums, and remove items from those albums at any time. These will be displayed on separate webpages. 

- FR3 : The seller must add at least one system-generated #Tag on each listing no unknown or free-text tag. The full #Tag catalog is displayed inside the search bar for the user/seller to pick.

- FR4: The system shall let sellers and buyers enable Delivery and Meet-Up, per listing. 

- FR5:  For Delivery, the system shall capture the authorized payment when the seller marks an order Shipped and shall email tracking to the buyer.

- FR6:  The system shall allow buyers to rate sellers from 1 to 5 and write a short review.

- FR7. The system shall send in-app and email notifications for likes/loves, chat messages, meet-up updates, shipped, confirm-received, and refunds/cancellations.

- FR8. The system shall provide the options to refund or void an authorization, pay the seller, request more information through emailing EdotCOM, and ban users.

- FR9. The system shall prevent login for banned accounts and block re-signup using the same email or phone.

- FR10. The system shall support product-based chat initiated from an item page between buyer and seller



#### 3.1.1 User interfaces
Webpages will be built with HTML/CSS/JavaScript. Sign-up, Login, Profile, Photo Gallery of filtered items, individual item details, Albums (Liked/Loved), Cart/Checkout will all have their own pages. It will use two simple option to choose between Delivery or Meet-Up. Order Details will provide a confirmation email. There will be a Chat box button for each product. All the Seller details like the listings, views analytics, and notifications will be displayed on separate pages.

#### 3.1.2 Hardware interfaces
Our system will work on Desktop Devices with web browser access. I also hope to have it working on mobile devices, and tablets that have web browser access.

#### 3.1.3 Software interfaces
Using Java 25 for the backend, Bootstrap 5, html, css for the front end and PostgreSQL for the database.

### 3.2 Non Functional Requirements 


#### 3.2.1 Performance

* NFR0: The E-COM system shall use less than 150 MB of user RAM.
* NFR1: E-COM customers shall transition to a retailer in less than 10 minutes.
* NFR2: When actively viewing a listings the status of the listing will update every second.
* NFR3. Order confirmation and shipped emails shall be queued within 10 seconds of the trigger and delivered within 1 minute.

#### 3.2.2 Security
* NFR4: Users payment information will not be stored in our database. This will be managed by the payment processors(Stripe, Paypal, etc...)
* NFR5  Users email, real name, and order/payment details shall only be visible to that user, nothing sensitive is public by default.
#### 3.2.3 Reliability
* NFR6: For our system to be reliable at time of delivery(by December), we will need to have a functional database, functional HTML pages, a functional spring server, a functional external API, and functional hardware capable of running our web application in the classroom.

#### 3.2.4 Availability
* NFR7. The app should have a health-check and auto-restart itself if it crashes.
* NFR8 If the site goes down, send an alert within five minutes to the on-call part-timers.
* NFR9. Do maintenance outside demo times and let users know ahead of time the part-timers will handle this.


#### 3.2.5 Compliance

* NFR610 Our web application will comply with the CCPA(California Consumer Privacy Act), GDPR(General Data Protection Regulation), WCAG(Web Content Accessibility Guidelines), and all other consumer privacy and accessibility requirements.

#### 3.2.6 Cost

To begin using a demo version, the costs would start out like this.

One-time Purchase 

Domain name. $10–$20 per year.

Website rules and policies templates. $0–$100 one-time.

Monthly (Beta infrastructure)

$150–$250 per month. Hosting, storage, email, monitoring.

Managed database with encryption. $25–$50 per month. Either included in the line above or added on top, depending on your provider.

Moderators. 2 part-timers × 15 hours/month × $30/hour = $900 per month.

Payment processing 

Per-transaction fee. About $0.30–$0.50 each payment.

Percentage fee around 3% of the order amount.

If you process $5,000 in orders in a month: 3% ≈ $150 in percentage fees, plus the per-transaction fees 

#### 3.2.7 Deadline
- NFR7: The final product will be finished by the first or second week of December 2025. 
