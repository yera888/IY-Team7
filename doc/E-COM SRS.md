
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
| Name | Date    | Reason For Changes  | Version   |
| Yeraldine | 9/17 | Initial SRS |       1.0 |
|       |         |                     |           |
|      |         |                     |           |
|      |         |                     |           |

## 1. Introduction

### 1.1 Document Purpose
The purpose of this SRS is to show both the users-view and developer-view requirements for the EdotCOM webpage. This page is an E-Commerse website that is an online second hand thift store. The requirements for the users deal with all the interactions the user can have with the website which could be interacted with as a buyer or a seller. The developer-oriented requirments specify on the developmental aspects and constraints that must be known in detail to provide the best outputs or functional features to meet the users needs. 

### 1.2 Product Scope
Identify the product whose software requirements are specified in this document, including the revision or release number. Explain what the product that is covered by this SRS will do, particularly if this SRS describes only part of the system or a single subsystem. 
Provide a short description of the software being specified and its purpose, including relevant benefits, objectives, and goals. Relate the software to corporate goals or business strategies. If a separate vision and scope document is available, refer to it rather than duplicating its contents here.

### 1.3 Definitions, Acronyms and Abbreviations
| #Tag  | A set of curated label users can add that gives a description to categorize an item. Tags make searching through different sections/styles of clothing easier to help users have a more curated search. Examples are #coquette #punk #streetwear |

|------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|

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
EdotCOM will be used by people who shop online often, are fashion savvy, and eco-friendly. Instead of shopping at thrift stores which have inflated prices and filthy workspaces. Customers can use EdotCOM to find local sellers, view listings and the ability to leave reviews based on their experience with other retailers. EdotCOM will also give local customers, and retailers two options: to drop off or pick up from the retailer or customer.

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

Scamming can be a big issue on these websites.


If there are too many people on when a popular drop happens it can cause lagging


 packages being sent to the wrong location/adress, since they are one.


 Uses outside services for payments, emails, maps, and image storage.

 
 Free or beta plans may limit how much we can store or how many requests we can make.


 Use a fixed set of tags provided by the system, will have to keep updating with new trends.


 Hold the payment at checkout and finish it only after shipping or meet up confirmation


 Meet ups happen at public places and show a short safety note


 keeping logs of people who get banned 


  
### 2.3 User Characteristics

Our website application does not expect our users to have any abnormal computer knowledge. Our system functions were designed using other similar or widely used websites. As long as users are familiar with online shopping, creating playlists, and filling out forms they should have no issues navigating, and using the application.  

Though we do plan on catering more specifically to users that are interested in selling, and buying second hand clothing. Users that are unfamiliar with the process should be able to easily ascertain how the application functions.

### 2.4 Assumptions and Dependencies
List any assumed factors (as opposed to known facts) that could affect the requirements stated in the SRS. These could include third-party or commercial components that you plan to use, issues around the development or operating environment, or constraints. The project could be affected if these assumptions are incorrect, are not shared, or change. Also identify any dependencies the project has on external factors, such as software components that you intend to reuse from another project, unless they are already documented elsewhere (for example, in the vision and scope document or the project plan).

## 3. Requirements

### 3.1 Functional Requirements 
- FR0: The system will allow users to create accounts as customers. All user accounts will have a unique account id. 
  - All customer accounts will have the option to transition into a retailer account maintaining it's unique id.
- FR1: The system will allow for retailers to create Listings. Listings will have the following attributes: 
  a description, condition, price, size, weight, photos, purchasedStatus, and location(if enabled).
  - Listings will be searchable, and filterable.
- FR2: Customers will be able to "Like", and "Heart" listings they are interested in effectively saving them 
  for later in an "Album".
  - All listing can be removed from user Albums at any time.

#### 3.1.1 User interfaces
Webpages will be built with HTML/CSS/JavaScript. Sign-up, Login, Profile, Photo Gallery of filtered items, individual item details, Albums (Liked/Loved), Cart/Checkout will all have their own pages. It will use two simple option to choose between Delivery or Meet-Up. Order Details will provide a confirmation email. There will be a Chat box button for each product. All the Seller details like the listings, views analytics, and notifications will be displayed on separate pages.

#### 3.1.2 Hardware interfaces
Describe the logical and physical characteristics of each interface between the software product and the hardware components of the system. This may include the supported device types, the nature of the data and control interactions between the software and the hardware, and communication protocols to be used.

#### 3.1.3 Software interfaces
Using Java 25 for the backend, Bootstrap 5, html, css for the front end and PostgreSQL for the database.

### 3.2 Non Functional Requirements 


#### 3.2.1 Performance
- NFR0: The E-COM system will attempt to use less than 150mbs of user RAM.
- NFR1: E-COM customers will be able to transition to a retailer in less than 10 minutes. 
- NFR2: When actively viewing a listings the status of the listing will update every second.

#### 3.2.2 Security
- NFR3: Users emails, real name, and payment data will only be visible to users.

#### 3.2.3 Reliability
Specify the factors required to establish the required reliability of the software system at time of delivery.

#### 3.2.4 Availability
-Use automated health checks and auto restart the app if it crashes.

-Log errors and set uptime alerts so someone is notified within five minutes.

-Schedule maintenance outside demo times and notify users beforehand this would be handled during the 2 part-timers shifts.

#### 3.2.5 Compliance
Specify the requirements derived from existing standards or regulations

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
Specify schedule for delivery of the software product.
  