./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=<IMAGE-NAME>

./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=jackc90/jobappimage"

export JAVA_HOME="/c/Program Files/Eclipse Adoptium/jdk-17.0.6.10-hotspot"

docker push jackc90/jobappimage

docker ps

docker images

<!-- Run JobApp -->
docker run -p 8080:8080 jackc90/jobappimage

<!-- Run PostgreSQL database -->
docker run -d --name db -e POSTGRES_PASSWORD=embarx postgres

<!-- Run PgAdmin -->
docker run -d --name pgadmin -e PGADMIN_DEFAULT_EMAIL=admin@admin.com -e PGADMIN_DEFAULT_PASSWORD=embarkx dpage/pgadmin4

docker stop jackc90/jobappimage

<!-- Remove containers: db and admin -->
docker rm -f db pgadmin


<!-- Run with Network -->
docker network create my-network

docker exec -it pgadmin ping db

<!-- Run PostgreSQL database -->
docker run -d \ 
    --name postgres_container \
    -e POSTGRES_USER=embarx \
    -e POSTGRES_PASSWORD=embarx \
    -e PGDATA=/data/postgres \
    -v postgres:/data/postgres \
    -p 5432:5432 \
    --network postgres \
    --restart unless-stopped \
    postgres

docker run -d \ 
    --name pgadmin_container \
    -e PGADMIN_DEFAULT_EMAIL=pgadmin4@pgadmin.org \
    -e PGADMIN_DEFAULT_PASSWORD=admin \
    -e PGADMIN_CONFIG_SERVER_MODE=False \
    -v pgadmin:/var/lib/pgadmin \
    -p 5050:80 \
    --network postgres \
    --restart unless-stopped \
    dpage/pgadmin4

<!-- Docker Compose -->
docker compose up -d