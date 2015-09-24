GoEuro Test
===================

The purpose of this project is to provide a solution to the test proposed by GoEuro. Please refer to following repository for a full description of the program specifications: [GoEuro Specification page](https://github.com/goeuro/dev-test/)

The source code rely on the following frameworks:

1. Jersey  --> https://jersey.java.net/
2. Opencsv --> http://opencsv.sourceforge.net/ 

To start working on it, just import it on Eclipse as a Maven project, and then execute the following Maven goal: _"test assembly:single"_

Such command will create a uber jar file by the name specified in pom.xml (see "<finalName></finalName>"), executable from the command line as follows (CITY_NAME is an input parameter to specify the name of a desired city):

java -jar GoEuro.jar "CITY_NAME" 

The project contains also a set of test (see package "com.goEuroTest.test").

The URL, name of the csv file and additional output strings can be found under: _src/main/resources/configuration/configuration.properties_

## License

GoEuroTest is released under the *GNU LESSER GENERAL PUBLIC LICENSE version 3*.  See `LICENSE.md`.


## Executable

A generated uber jar (_GoEuro.jar_) can be found attached to the first release (0.1)




