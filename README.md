



## SSL

Keystore (Self Signed)

```
keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
```

P12 - PKCS12
Bundles private key
 
 WSDL
 * http://localhost:8080/ws/dives.wsdl redirects to
 * https://localhost:8443/ws/dives.wsdl
 
 
## Docker

### Build

```console
mvn clean install dockerfile:build
```

### Run

```
docker run -p 8443:8443 mcarr/divedetails-soap-server:latest
```
 
 