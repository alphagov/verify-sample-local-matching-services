## Building From source

### Build and run from command-line
```mvn package exec:java```
	
### Build as a fat jar
```mvn package```

## Running the jar

Setting the ```JGS_PORT``` environment variable to 8081 will start the application at port 8081; port defaults to 8080

### Using java
```java -jar target/java-grizzly-jsonpath-1.0-SNAPSHOT-capsule.jar```

### Or from the shell
```sh target/java-grizzly-jsonpath-1.0-SNAPSHOT-capsule.x```

# TODO 
- Switch to using the jsurfer library for jsonpath - https://github.com/jsurfer/JsonSurfer
- implement a logging framework
- unit tests on rules 

#### NOTE
initial project generated with 
	```mvn archetype:generate -DarchetypeArtifactId=jersey-quickstart-grizzly2 -DarchetypeGroupId=org.glassfish.jersey.archetypes -DinteractiveMode=false -DgroupId=uk.gov.ida -DartifactId=java-grizzly-jsonpath -Dpackage=uk.gov.ida -DarchetypeVersion=2.25.1```
 