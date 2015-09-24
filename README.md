Java Developer Test
===================

The purpose of this project is to provide a solution to the test proposed by GoEuro. The produced code rely on two frameworks:

1) Jersey  --> https://jersey.java.net/
2) Opencsv --> http://opencsv.sourceforge.net/ 


To start working on it, just import it on Eclipse as a Maven project, and then execute the following Maven goal: "test assembly:single".
The command will create a jar file as the name mentioned in pom.xml (see "<finalName></finalName>"), executable from command line as follows:

java -jar GoEuro.jar "CITY_NAME"   // CITY_NAME is an input parameter to specify the name of a desired city


The project contains also a set of test, within the package "com.goEuroTest.test"

The URL, name of the csv file and additional output strings can be found unders src/main/resources/configuration/configuration.properties







