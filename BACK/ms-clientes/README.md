# Template Base Microservicios Quarkus

Este Template de Quarkus es un ejemplo de una API Rest que ejecuta un CRUD sobre una base de datos de ejemplo, tomando como referencia la tabla PAIS en el esquema PERSONA existente en el ambiente Oracle.

Para conocer más de Quarkus, puedes revisar su sitio web https://quarkus.io/ .

Este template utiliza las siguientes extensiones de Quarkus:
```
>hibernate-orm, hibernate-orm-panache: Como ORM
>jdbc-h2, jdbc-mysql, jdbc-oracle: Como conectores ya habilitados a bases de datos
>resteasy, resteasy-jsonb: Para la fácil y rápida generación de endpoints HTTP
>smallrye-fault-tolerance: Para incorporar patrones de resilencia
>smallrye-health: Para incorporar patrón de health check
>smallrye-openapi, swagger-ui: Para la generación automática de un Swagger
```

## Configuración de PC para ambiente de desarrollo

Stack Base:
```shell script
JAVA versión 17
GraalVM para Java versión 17
Maven 3.9+
Docker
```

Deben estar configuradas las variables de entorno:
```shell script
➜  ~ java --version
java 17.0.7 2023-04-18 LTS
Java(TM) SE Runtime Environment (build 17.0.7+8-LTS-224)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.7+8-LTS-224, mixed mode, sharing)
➜  ~ echo $JAVA_HOME
/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home
➜  ~ echo $GRAALVM_HOME
/Library/Java/JavaVirtualMachines/graalvm-ce-java17-22.3.2/Contents/Home
➜  ~ $GRAALVM_HOME/bin/java -version
openjdk version "17.0.7" 2023-04-18
OpenJDK Runtime Environment GraalVM CE 22.3.2 (build 17.0.7+7-jvmci-22.3-b18)
OpenJDK 64-Bit Server VM GraalVM CE 22.3.2 (build 17.0.7+7-jvmci-22.3-b18, mixed mode, sharing)
➜  ~ $GRAALVM_HOME/bin/native-image --version
GraalVM 22.3.2 Java 17 CE (Java Version 17.0.7+7-jvmci-22.3-b18)
```

**IMPORTANTE:** Quarkus incluye una función llamada DevServices que nos permite acceder a información del ambiente de desarrollo, y además generar instancias de bases de datos de forma automática utilizando Docker. Para que Quarkus pueda utilizar Docker en el PC, para el caso de Mac OSX, es necesario además ejecutar este comando:
```shell script
sudo ln -s $HOME/.docker/run/docker.sock /var/run/docker.sock
```

## Configuración de ambientes/perfiles

Quarkus nos permite manejar diferentes perfiles (dev, staging, pro, personal, etc) con sus diferentes configuraciones directamente en el archivo ***application.properties***. Para esto se debe agregar a la propiedad el sufijo "***$nombrePerfil.***", por ejemplo %local.quarkus...

Las propiedades que no comiencen con un perfil, son propiedades tranversales a todos los perfiles, y existen los perfiles reservados ***%dev.*** ***%test.*** ***%prod.***
```
mvn quarkus:dev
#tomara las propiedades que comiencen con %dev. más todas las propiedades transversales

mvn quarkus:dev -Dquarkus.profile=profileName
#tomara las propiedades que comiencen con %profileName. más todas las propiedades transversales

mvn test 
#tomara las propiedades que comiencen con %test. más todas las propiedades transversales

mvn package
#compilará con todas las propiedades que comiencen con %prod. más todas las propiedades transversales
```

**IMPORTANTE:** Este template incluye en el archivo ***application.properties*** un ambiente de desarrollo local (***%local.***) para iniciar una base de datos MySql usando docker de forma automática a través de ***DevServices***
```
%local.quarkus.hibernate-orm.log.sql=true
%local.quarkus.datasource.db-kind=mysql
%local.quarkus.datasource.devservices.image-name=amd64/mysql:5.7.42
%local.quarkus.datasource.username=bicevida
%local.quarkus.datasource.password=bicevida
%local.quarkus.datasource.devservices.db-name=PERSONAS
%local.quarkus.hibernate-orm.sql-load-script=DEMO_PAIS_TABLE.sql
%local.quarkus.hibernate-orm.database.generation=drop-and-create`
```

Para configurar las variables de entorno para los despliegues en EKS mediante Gitlab, se pueden ocupar dos enfoques. El primero consiste en utilizar los secretos del cluster, en donde cada secreto se llamará igual para todos los ambientes, estos deben referenciarse en los chart values y luego dentro de  ***application.properties***:
```
quarkus.datasource.jdbc.url=${DATASOURCE_URL}
quarkus.datasource.username=${DATASOURCE_USERNAME}
quarkus.datasource.password=${DATASOURCE_PASSWORD}
```

El otro enfoque consiste en ocupar los perfiles personalizados de Quarkus, que para el caso del pipeline de Gitlab son sólo los siguientes: 
```
%dev_eks.quarkus.datasource.db-kind=oracle
%stg_eks.quarkus.datasource.db-kind=oracle
%pro_eks.quarkus.datasource.db-kind=oracle
```

## Troubleshooting

#### Respuesta vacía al ejecutarse en modo nativo

Se debe incorporar la anotación "@RegisterForReflection" en los DTO y Wrapper si es que son implementados.

```
import io.quarkus.runtime.annotations.RegisterForReflection;
@RegisterForReflection
```

Referencia: https://quarkus.io/guides/writing-native-applications-tips#registering-for-reflection


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/poc-teamplate-base-microservicios-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI
- RESTEasy Classic JSON-B ([guide](https://quarkus.io/guides/rest-json)): JSON-B serialization support for RESTEasy Classic

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

## Devops
--------

El pipeline se encuentra modularizado en los siguientes [repositorios](https://gitlab.com/BICE_Vida/shared/pipelines/-/tree/master/pipeline-eks?ref_type=heads). Esta basado en [EDDIE-flow](https://bicevida.atlassian.net/wiki/spaces/LCDZ/pages/2879881306/Eddie+GitFlow+Semantic+Release).

Incorpora Semantic Release: leer [documentación](https://bicevida.atlassian.net/wiki/spaces/LCDZ/pages/2752774165/C+mo+utilizar+el+Semantic+Release+Autom+tico) para utilizar correctamente los Conventional Commits y aprovechar la herramienta que genera versiones automáticas.

Este template incorpora la opción de usar los perfiles de Quarkus, para versionar en el código, variables de entorno no sensibles para diferentes ambientes. Para ello se debe descomentar en el archivo .gitlab-ci.yml la variable 'USE_PROFILES: "true"'. Busque la clase ExampleProfiles.java y el archivo application.properties (usar %dev_eks, %qa_eks y %prod_eks) para entender su funcionamiento. Si se usan los perfiles, el EDDIE-flow cambia en el paso final, vea segundo [esquema](https://bicevida.atlassian.net/wiki/spaces/LCDZ/pages/2879881306/Eddie+GitFlow+Semantic+Release) ya que el despliegue a producción se produce después del job exitoso de Semantic-Release, por lo que se deben usar obligatoriamente los Conventional Commits para que se vayan creando nuevos tags que gatillen dicho job.  

Si el equipo necesita la herramienta de calidad de código para su nivel de madurez de Devops, descomentar todas las líneas del archivo .gitlab-ci.yml referentes a Sonar y escribir un mail a bv_equipodevops@bicevida.cl para solicitar la vinculación del repositorio a Sonarcloud. 
