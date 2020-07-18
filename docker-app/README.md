```
docker build --build-arg JAR_FILE=target/*.jar -t otus/docker-app:1.0 .
docker build -t otus/docker-app:1.0 .
docker run -p 8000:8000 otus/docker-app:1.0
```