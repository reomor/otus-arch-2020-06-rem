Create image
```
docker build --build-arg JAR_FILE=target/*.jar -t otus/docker-app-auth:1.0 .
docker build -t otus/docker-app-auth:1.0 .
docker run --rm -p 8001:8001 otus/docker-app-auth:1.0
```

DockerHub <br>
```
docker login
docker tag f342a3c05940 rimskiy/docker-app-auth:1.0
docker push rimskiy/docker-app-auth:1.0
```
