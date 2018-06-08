# README #


### Prime Number Generation ###

* Provides API for different algorithms for prime number generation, between the lower and upper range provided by user from command line
* 1.0

### How do I get set up? ###

* Summary of set up

			It's an eclipse maven project. Import in eclipse as existing maven project. If you want to run without eclipse, follow instruction provided in Deployment instruction section.

* Configuration

			All the required configuration is already included. Check build path settings, to make sure correct java is being used.

* Dependencies

			JDK 8, Maven 3

* Frameworks

			Spring-boot is used for API's development, it brings tomcat with it to use as a container for services.

* Database

			HSQL is used as in-memory database, so the data in table is only stored in memory.

* How to run tests

			1: For Running tests Using Eclipse, Open TestPrimeNumberGenerator.java and run as Junit Test. Make sure junit 4 has been already added to classpath. To run using maven, run maven goal "clean test" in maven build configuration.
			2: To run tests without Eclipse, go to the root folder of project, and run following command:
				mvn clean test

* Deployment instructions

			1: For Deployment Using Eclipse, create maven build and run package goal to create the jar file
			2: To create jar without Eclipse, go to the root folder of project, and run following command:
				mvn package
			It will create a Jar file in target folder.

* Run the appliction

			Once Jar file is created, go to the ./target/ directory where Jar file was created and run following command:
				java -jar PrimeNumberGenerator.jar
		    Once it is deployed successfully (will take about 2,3 seconds), you can consume services on localhost:8080. For detail of Services usage, please follow this document: https://docs.google.com/document/d/10pnOuvkHB0UhLehOBRbV1PZygk2hhE7VcNI54WG4pSE


### Who do I talk to? ###

* Repo owner or admin
