#!/bin/bash

kubectl apply -f jaeger/jaeger-deployment.yaml
kubectl apply -f jaeger/jaeger-service.yaml

kubectl apply -f zookeeper/zookeeper-deployment.yaml
kubectl apply -f zookeeper/zookeeper-service.yaml

kubectl apply -f kafka/kafka-deployment.yaml
kubectl apply -f kafka/kafka-service.yaml

kubectl apply -f postgres/postgres-config.yaml
kubectl apply -f postgres/postgres-volume.yaml
kubectl apply -f postgres/postgres-deployment.yaml
kubectl apply -f postgres/postgres-service.yaml

kubectl apply -f keycloak/keycloak-config.yaml
kubectl apply -f keycloak/keycloak-deployment.yaml
kubectl apply -f keycloak/keycloak-service.yaml

kubectl apply -f oauth2-proxy/oauth2-config.yaml
kubectl apply -f oauth2-proxy/oauth2-deployment.yaml
kubectl apply -f oauth2-proxy/oauth2-service.yaml

kubectl apply -f redis/redis-config.yaml
kubectl apply -f redis/redis-deployment.yaml
kubectl apply -f redis/redis-service.yaml

kubectl apply -f discovery/discovery-deployment.yaml
kubectl apply -f discovery/discovery-service.yaml

kubectl apply -f configuration-server/configuration-deployment.yaml
kubectl apply -f configuration-server/configuration-service.yaml

kubectl apply -f first-service/first-deployment.yaml
kubectl apply -f first-service/first-service.yaml

kubectl apply -f second-service/second-deployment.yaml
kubectl apply -f second-service/second-service.yaml

kubectl apply -f third-service/third-deployment.yaml
kubectl apply -f third-service/third-service.yaml

kubectl apply -f fourth-service/fourth-deployment.yaml
kubectl apply -f fourth-service/fourth-service.yaml

kubectl apply -f fifth-service/fifth-deployment.yaml
kubectl apply -f fifth-service/fifth-service.yaml

kubectl apply -f sixth-service/sixth-deployment.yaml
kubectl apply -f sixth-service/sixth-service.yaml

kubectl apply -f seventh-service/seventh-deployment.yaml
kubectl apply -f seventh-service/seventh-service.yaml

kubectl apply -f eighth-service/eighth-deployment.yaml
kubectl apply -f eighth-service/eighth-service.yaml

kubectl apply -f ninth-service/ninth-deployment.yaml
kubectl apply -f ninth-service/ninth-service.yaml

kubectl apply -f tenth-service/tenth-deployment.yaml
kubectl apply -f tenth-service/tenth-service.yaml

kubectl apply -f twelfth-service/twelfth-deployment.yaml
kubectl apply -f twelfth-service/twelfth-service.yaml

kubectl apply -f thirteenth-service/thirteenth-deployment.yaml
kubectl apply -f thirteenth-service/thirteenth-service.yaml

kubectl apply -f fourteenth-service/fourteenth-deployment.yaml
kubectl apply -f fourteenth-service/fourteenth-service.yaml

kubectl apply -f fifteenth-service/fifteenth-deployment.yaml
kubectl apply -f fifteenth-service/fifteenth-service.yaml

kubectl apply -f sixteenth-service/sixteenth-deployment.yaml
kubectl apply -f sixteenth-service/sixteenth-service.yaml