
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
Describe the purpose of the SRS and its intended audience.

### 1.2 Product Scope
Identify the product whose software requirements are specified in this document, including the revision or release number. Explain what the product that is covered by this SRS will do, particularly if this SRS describes only part of the system or a single subsystem. 
Provide a short description of the software being specified and its purpose, including relevant benefits, objectives, and goals. Relate the software to corporate goals or business strategies. If a separate vision and scope document is available, refer to it rather than duplicating its contents here.

### 1.3 Definitions, Acronyms and Abbreviations
| Reference  | Definition                                                                                                                                                                         |
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
Describe what the rest of the document contains and how it is organized.

## 2. Product Overview
EdotCOM will be used by people who shop online often, are fashion savvy, and eco-friendly. Instead of shopping at thrift stores which have inflated prices and filthy workspaces. Customers can use EdotCOM to find local sellers, view listings and the ability to leave reviews based on their experience with other retailers. EdotCOM will also give local customers, and sellers the option for local pickup skirting the need to use expensive mail services.

### 2.1 Product Functions

* Create Listing of each product with detail description of all its attributes.
* Interact with interface easily to navigate through filtered options such as by price, popularity, etc.
* Have the option to enable location so locals can either pick up or drop off products.
* A feature to communicate with one another about an individual product 

Summarize the major functions the product must perform or must let the user perform. Details will be provided in Section 3, so only a high level summary (such as a bullet list) is needed here. Organize the functions to make them understandable to any reader of the SRS. A picture of the major groups of related requirements and how they relate, such as a top level data flow diagram or object class diagram, is often effective.

### 2.2 Product Constraints
This subsection should provide a general description of any other items that will limit the developer’s options. These may include:  

* Interfaces to users, other applications or hardware.  
* Quality of service constraints.  
* Standards compliance.  
* Constraints around design or implementation.
  
### 2.3 User Characteristics

Our website application does not expect our users to have any abnormal computer knowledge. Our system functions were designed using other similar or widely used websites. As long as users are familiar with online shopping, creating playlists, and filling out forms they should have no issues navigating, and using the application.  

Though we do plan on catering more specifically to users that are interested in selling, and buying second hand clothing. Users that are unfamiliar with the process should be able to easily ascertain how the application functions.

### 2.4 Assumptions and Dependencies
Our system will be dependant on Java, Spring, Spring Boot, RestAPI, and VSCode for the back end of our application. We will be dependant on HTML, CSS, JavaScript, and VSCode for the front end of our application. We are likely to reuse some of our CSS, and HTML from previous assignments. We are also relying on PostgreSQL which means we will be limited by the constraints of it's free database.

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
Define the software components for which a user interface is needed. Describe the logical characteristics of each interface between the software product and the users. This may include sample screen images, any GUI standards or product family style guides that are to be followed, screen layout constraints, standard buttons and functions (e.g., help) that will appear on every screen, keyboard shortcuts, error message display standards, and so on. Details of the user interface design should be documented in a separate user interface specification.

Could be further divided into Usability and Convenience requirements.

#### 3.1.2 Hardware interfaces
Describe the logical and physical characteristics of each interface between the software product and the hardware components of the system. This may include the supported device types, the nature of the data and control interactions between the software and the hardware, and communication protocols to be used.

#### 3.1.3 Software interfaces
Describe the connections between this product and other specific software components (name and version), including databases, operating systems, tools, libraries, and integrated commercial components. Identify the data items or messages coming into the system and going out and describe the purpose of each. Describe the services needed and the nature of communications. Refer to documents that describe detailed application programming interface protocols. Identify data that will be shared across software components. If the data sharing mechanism must be implemented in a specific way (for example, use of a global data area in a multitasking operating system), specify this as an implementation constraint.

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
Specify the factors required to guarantee a defined availability level for the entire system such as checkpoint, recovery, and restart.

#### 3.2.5 Compliance
Our web application will comply with the CCPA(California Consumer Privacy Act), GDPR(General Data Protection Regulation), WCAG(Web Content Accessibility Guidelines), and all other consumer privacy and accessibility requirements.

#### 3.2.6 Cost
Specify monetary cost of the software product.

#### 3.2.7 Deadline
NFR4: The final product will be finished by the first or second week of December. 
  