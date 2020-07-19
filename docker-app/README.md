Create image
```
docker build --build-arg JAR_FILE=target/*.jar -t otus/docker-app:1.0 .
docker build -t otus/docker-app:1.0 .
docker run --rm -p 8000:8000 otus/docker-app:1.0
```

DockerHub <br>
- create repository
- then
```
docker login
docker tag f342a3c05940 rimskiy/docker-app:1.0
docker push rimskiy/docker-app:1.0
```

K8S
```
kubectl apply -f deployment.yaml
kubectl apply -f deployment.yaml -f service.yaml
```

K8S Ingress
```
minikube addons list
minikube addons enable ingress
kubectl delete ingress app-ingress
```

Run
```
kubectl apply -f .
```