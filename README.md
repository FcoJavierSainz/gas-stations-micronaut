# mx-gas-stations-micronaut

## Install
1. Run command `docker-compose up`
1. Modify the key in the line `api-key: <My Google maps key>` from file `gas-stations/src/main/resources/application.yml`
1. Run all main Class from  `gas-stations`, `locations` and `prices` thought the IDE or r
1. Endpoint `http://localhost/api/v1/gasStations/near?longitude=-103.39282&latitude=20.67524&radio=1500`
1. Access to ZipKin `http://localhost:9411/zipkin/`
1. Consul (service discovery) `localhost:8500/ui/dc1/services`
1. Grafana `http://localhost:3000/`, add the datasource of type `Prometheus` and url `http://prometheus:9090`, then import the dashboards `4701` and `5373`
1. Prometheus `http://localhost:9090/`