# WhatsThatMovie
A movie database website that follows rich web application development architecture made using Java, JAX-RS, AJAX, PostgreSQL, and Heroku.

## Live Demo Site
Visit the live demo site deployed on Heroku [here](https://whats-that-movie.herokuapp.com/)

## Database
The database attached is a postgresql database deployed on Heroku online. A postgresql client should be used to inspect the database content such as `pgAdmin` or a similar one.

## Machine Learning API Integration
In the admin UI, A machine learning API has been attached so the genres of the movies can be predicted using the plot of the movie along with the movie name. The API used here has been developed and deployed by a group of undergraduates at SLIIT and written concent has been obtained to attach it with this project. please find the original repo and a copy of API documentation [here](https://github.com/akalankasakalasooriya/FDM_Mini_Project_Backend)

credits for the Machine Learning API goes to all of the original contributors of the project and their names have been mentioned in the repo provided above.

## CRUD Operations
### Users-related CRUD operations
- Add a admin user
- Add a non-previledged user
- View a complete list of users
- Edit user profiles
- Update the user role
- Remove a user
- Log in as admin/regular user (Automated Identification of the role)
- Sign up manually

### Movies related CRUD
- Insert a Movie
- Remove a Movie
- Update Movie Details
- View all movies

### API Calls
- Get Movie Genre predictions (External API call)

## Future Improvements
- Add movie recommendation engine
- Optimize for faster loading times
- Improve build-in notifications
- Add features to comment/like on movies
- Add new movie suggestions

## Deployment
Easy to deploy as this is directly deployed to heroku via git hub repo. Any changes made to the main branch can be deployed right-away with one click or can be automated if necessary using heroku.

## Dev Requirements
### Languagegs and Frameworks
- jdk 8
- jre 1.8
- jersey
- JAX-RS (for API calls)
- servelets
- jQuerry
- Bootstrap
- PostgreSQL

### Tools and IDEs
- pgAdmin 4 or above
- eclipse for Java EE developers

Please request permission from @oscarx3s before using the code available here in this repo if you wish to republish or modify.
