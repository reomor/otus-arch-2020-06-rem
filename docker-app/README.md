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
.\prometheus.exe --config.file="prometheus.yml"
docker run -d -p 9090:9090 -v <path-to-prometheus.yml>:/etc/prometheus/prometheus.yml prom/prometheus
docker run -d -p 9090:9090 -v prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus
```

Prometheus Operator
```
kubectl config set-context --current --namespace=default
helm repo add stable https://kubernetes-charts.storage.googleapis.com
helm repo update
kubectl create namespace monitoring
kubectl config set-context --current --namespace=monitoring
helm install prom stable/prometheus-operator -f .\docker-app\infra\helm\prometheus_value.yaml --atomic
kubectl config view --minify --output jsonpath='{..namespace}'

kubectl port-forward service/prom-grafana 9000:80 
http://localhost:9000 (admin:prom-operator)

kubectl port-forward service/prom-prometheus-operator-prometheus 9090
http://localhost:9090

helm upgrade app-chart .\docker-app\helm\app-chart --dry-run
kubectl get servicemonitor.monitoring.coreos.com -n default
helm install nginx stable/nginx-ingress -f ./docker-app/infra/helm/nginx-ingress.yaml
helm upgrade nginx stable/nginx-ingress -f ./docker-app/infra/helm/nginx-ingress.yaml
```

Apache Benchmark requests
```shell script
while true ; do ab -n 50 -c 3 http://172.17.198.19:30464/version ; sleep 3 ; done 
```

https://kubernetes.github.io/ingress-nginx/troubleshooting/

