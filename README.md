## TH2 Book Service

This component exposes REST api for getting information about books
``
### Configuration
For application to run you need following configuration added in `main/java/resources`:
```yaml
micronaut:
  application:
    name: th2-storage-services
  server:
    context-path: /api
  router:
    static-resources:
      swagger:
        paths: classpath:META-INF/swagger
        mapping: /swagger/**
netty:
  default:
    allocator:
      max-order: 3
cassandra:
  dataCenter: -DATACENTER
  host: -HOST # Host for connection
  port: 9042 # Port for Connection
  username: -USERNAME # Username for Authentication
  password: -PASSWORD # Password for Authentication
  timeout: 5000 # Timeout for cassandra queries

```

`log4j2.properties` should also be provided in same folder.

Project exposes swagger ui endpoint at `/api/swagger/views/swagger-ui?contextPath=/api`. Schemas and controller endpoints can be viewed there.