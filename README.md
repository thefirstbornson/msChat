# Getting Started

### Сборка 
Чтобы собрать проект выполните команду:

```gradle build bootRun```

Для запуска через bootRun аргументы передаются через свойство args:

```gradle bootRun "-Pargs=--application.virtdep.ws.uri=http://localhost:4545,--application.virtdep.ws.stub.enabled=false"```


### Swagger
* http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config