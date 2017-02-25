# Running the application

## Prerequisites
To run the data sorting and filtering application the Java 8 Runtime Environment needs to be installed on your machine.

## Execute the application
The application is bundled into one JAR file to ease the execution. When running in default mode, the application reads and writes the input and output files from the current folder:

```
java -jar reportfiltering-1.0.0-jar-with-dependencies.jar
```

You can also provide another input/output folder via command line argument:

```
java -jar reportfiltering-1.0.0-jar-with-dependencies.jar inputFolder
```

# Tools and Libraries

## Jackson

Originally created to provide fast and reliable JSON parsing for Java, Jackson has become an universal data-binding library that allows you to serialize and deserialize POJOs from/to different data formats. It is especially suited for this task as it supports all the three required input formats: CSV, XML and JSON.

The following libraries have been used:
- jackson-dataformat-csv-2.8.7.jar
- jackson-dataformat-xml-2.8.7.jar
- jackson-core-2.8.7.jar
- jackson-databind-2.8.7.jar (implicit)
- jackson-annotations-2.8.0.jar (implicit)
- jackson-module-jaxb-annotations-2.8.7.jar (implicit)
- stax2-api-3.1.4.jar (implicit)
- woodstox-core-5.0.3.jar (implicit)

## Maven

Maven has been used for dependency management and building the assembled final JAR file for this task. Besides the easy integration into the Eclipse IDE, the single file assembly plugin was utilized to reduce the classpath length at execution time.

# Task description - Data sorting and filtering

Read the 3 input files reports.json, reports.csv, reports.xml and output a combined CSV file with the following characteristics:

- The same column order and formatting as reports.csv
- All report records with packets-serviced equal to zero should be excluded
- records should be sorted by request-time in ascending order

Additionally, the application should print a summary showing the number of records in the output file associated with each service-guid.

Please provide source, documentation on how to run the program and an explanation on why you chose the tools/libraries used.
