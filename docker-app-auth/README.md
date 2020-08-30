Create image
```
docker build --build-arg JAR_FILE=target/*.jar -t otus/docker-app-auth:1.0 .
docker build -t otus/docker-app-auth:1.0 .
docker run --rm -p 9000:9000 otus/docker-app-auth:1.0
```

DockerHub <br>
```
docker login
docker tag f342a3c05940 rimskiy/docker-app-auth:1.0
docker push rimskiy/docker-app-auth:1.0
```

[Redis in k8s](https://kubernetes.io/docs/tutorials/configuration/configure-redis-using-configmap/)
```
kubectl apply -k .\docker-app-auth\infra\helm\.
kubectl exec -it redis -- redis-cli
CONFIG GET maxmemory
CONFIG GET maxmemory-policy
```

```
helm delete app-chart
kubectl delete -f ./docker-app-auth/k8s
kubectl delete -k ./docker-app-auth/infra/helm/redis
```
