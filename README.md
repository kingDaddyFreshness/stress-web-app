# stress-web-app

A simple docker-based web service for stressing and testing infrastructure.  The service has 4 endpoints - 2 of which can have their responses manipulated by one of the other endpoints.

## Endpoints

### Version

```
/test-service-webapp/webapi/version
```

Returns the version of the web service.  This response cannot be manipulated by the other endpoints

### HealthCheck

```
/test-service-webapp/webapi/healthCheck
```

Returns the healthCheck of the web service.  This http response code is 200 by default but can be modified via the Parameters endpoint (see below).

### DynamicResource

```
/test-service-webapp/webapi/dynamicResource
```

This is the primary endpoint for manipulation via the Parameters endpoint (see below).  By default, the endpoint returns an http response code of 200 and a body containing the ipAddress of the server node on which the service is running.  Both of these properties (as well as others) can be modified via the Parameters endpoint (see below).

### Parameters

```
/test-service-webapp/webapi/parameters?cpuBurnTimeMS=30&responseTimeMS=10&responseCode=203&healthCheck=201&responseBodyB64=eyJhIjogNX0=
```

This endpoint is used to modify the behavior of 2 of the other endpoints: HealthCheck and DynamicResource.  These 2 endpoints are modified via 5 query parameters:

1. healthCheck=201 - set the http response code returned by the healthCheck endpoint.
2. responseCode=203 - set the http response code returned by the dynamicResource endpoint.
3. responseBodyB64 - set the response body returned by the dynamicResource endpoint.  The value of the response body must be base64 encoded valid json.
4. responseTimeMS=10 - set the response time of the dynamicResource endpoint by causing calls to dynamicResource to sleep for the given number of milliseconds.
5. cpuBurnTimeMS - does not modify the "behavior" of the dynamicResource endpoint - but, when the dynamicResource endpoint is invoked, a cpu "burn" is triggered to max-out the cpu usage on the underlying server node.  The cpu "burn" will last for the provided number of milliseconds.


