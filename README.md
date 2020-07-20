# [Архитектор ПО](https://otus.ru/lessons/arhitektor-po/)

Домашние задания по курсу Архитектор ПО

---
#### Task 01
Создание минимального сервиса для Kubernetes (minikube)
#### [Репозиторий сервиса](https://github.com/reomor/otus-arch-2020-06-rem/tree/hw01/)
[k8s/manifests](https://github.com/reomor/otus-arch-2020-06-rem/tree/hw01/docker-app/k8s) <br>
[Postman collection](https://github.com/reomor/otus-arch-2020-06-rem/tree/hw01/docker-app/postman) <br>
> k8s Deployment <br>
> k8s Service <br>
> k8s Ingress <br>

#### Task 02
RESTful CRUD для Kubernetes (minikube) и Helm
#### [Репозиторий сервиса](https://github.com/reomor/otus-arch-2020-06-rem/tree/hw02/)
[k8s/manifests](https://github.com/reomor/otus-arch-2020-06-rem/tree/hw02/docker-app/k8s) <br>
[Postman collection](https://github.com/reomor/otus-arch-2020-06-rem/tree/hw02/docker-app/postman) <br>
run order
```
helm install pg bitnami/postgresql -f values.yaml
kubectl apply -f config.yaml
kubectl apply -f job.yaml
kubectl apply -f deployment.yaml -f service.yaml -f ingress.yaml
```
> k8s Deployment <br>
> k8s Service <br>
> k8s Ingress <br>
> k8s Job <br>
> k8s Config <br>
> Helm <br>