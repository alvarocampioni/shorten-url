
# URL Shortener
This is a simple URL shortening service that allows users to create short URLs and retrieve the original URL, as well as track the usage of each shortened URL. The short URL has a lifespan of 30 minutes.




## Technologies
- Java – Primary backend language.

- Spring Boot – Builds the API.

- MongoDB – Utilized via Spring Data MongoDB for flexible NoSQL data management to store the URLs.

- Swagger – Used for API documentation for better use of the API.

- Docker – Facilitates dependency management and ensures portability by containerizing the service.

## Example Usage

Original URL: https://www.example.com/this/is/a/really/long/path/that/goes/on/and/on/with/multiple/sections

Shortened URL: http://localhost:3000/ZT7Mv1

# Run Locally

## Prerequisites
- Docker
- Git

### 1. Clone the Repository
```
git clone https://github.com/your-username/shorten-url.git
cd shorten-url
```
### 2. Run
```
docker compose up --build
```
- The API Documentation will be accessible on : `http://localhost:3000/swagger-ui/index.html`








## Contact
Email: alvarocampioni@usp.br


