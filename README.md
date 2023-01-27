# READIT
A web application project that uses REST API and websocket to allow users to create accounts, publish posts and comments, upvote and downvote, and participate in a multi-user chat.

## Features
* Create account
* Login / Logout
* Manage Profile (Change username and password)
* Multi-User Chat
* Show current online users in the chat
* Publish Posts / Delete Posts
* Upvote / Downvote Posts
* Comment on Posts / Delete Comments

## Technologies
* Java
* Java Spring Boot (REST API)
* JWT Authentication
* Spring Security
* Websockets (Used for live chat)
* Angular and Angular Material
* Typescript
* MySQL Database

## How to set up

### Back End
1. Navigate to the "readitJavaProject (Back End)" directory and run the command "mvn clean install" to build the back-end.
2. In the "readitJavaProject (Back End)" directory, run the command "mvn spring-boot:run" to start the back-end.

#### Front End
1. Have Angular installed.
2. Navigate to the "readitAngularProject (Front End)" directory and run the command "npm install" to install the necessary dependencies for the front-end.
3. Once the dependencies are installed, run the command "ng serve" to start the development server for the front-end.
4. The application should now be running on http://localhost:4200/ .
5. If you want to use your own database, change the database connection details in the "application.yml" file in the "readitJavaProject (Back End)" directory.

*Note: Make sure you have Java, Maven, Node.js and Angular CLI installed on your machine.*

## Author
Simon Lajoie
