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

Debug
```
kubectl describe pod/app-deployment-7b4b97bdb7-9lwxb
kubectl logs pod/app-deployment-7b4b97bdb7-9lwxb
```

OpenAPI
```
http://localhost:8000/swagger-ui.html
http://localhost:8000/api-docs
http://localhost:8000/api-docs.yaml
```

Helm

[Postgres helm chart values.yaml](https://github.com/bitnami/charts/blob/master/bitnami/postgresql/values.yaml)

```
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update
```
```
helm install pg bitnami/postgresql
helm upgrade pg bitnami/postgresql --dry-run
helm install pg bitnami/postgresql -f values.yaml
kubectl delete pvc data-pg-postgresql-0
```

Helm custom chart
```
helm create app-chart
helm install app-chart ./app-chart --dry-run
helm install app-chart ./app-chart
helm delete app-chart
```

Prometheus
```
docker run -d -p 9090:9090 -v <path-to-prometheus.yml>:/etc/prometheus/prometheus.yml prom/prometheus
docker run -d -p 9090:9090 -v prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus
```