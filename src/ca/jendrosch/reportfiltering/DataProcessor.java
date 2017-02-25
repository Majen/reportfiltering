package ca.jendrosch.reportfiltering;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import ca.jendrosch.reportfiltering.data.Report;

/**
 * Data processor class for filtering and sorting report retrieved from CSV, XML
 * and JSON files.
 * 
 * @author Manfred Jendrosch
 *
 */
public class DataProcessor {

	/**
	 * Main method that executes the business logic.
	 * 
	 * @param args
	 *            arguments of the application - the first argument should
	 *            include the file directory if specified
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static void main(String[] args) throws JsonProcessingException, IOException {

		// take the directory from the arguments if specified, otherwise use the
		// current folder
		String fileDir = ((args.length > 0) ? args[0] : ".");

		List<Report> reports = new ArrayList<>();

		// read CSV file
		File csvFile = new File(fileDir + "/reports.csv");
		readFromFile(csvFile, new CsvMapper(), Optional.of(CsvSchema.emptySchema().withHeader()), reports);

		// read XML file
		File xmlFile = new File(fileDir + "/reports.xml");
		readFromFile(xmlFile, new XmlMapper(), Optional.empty(), reports);

		// read JSON file
		File jsonFile = new File(fileDir + "/reports.json");
		readFromFile(jsonFile, new ObjectMapper(), Optional.empty(), reports);

		// filter out zero serviced packets and order by request time ASC
		List<Report> results = reports.stream().filter(r -> r.getPacketsServiced() != 0)
				.sorted((r1, r2) -> r1.getRequestTime().compareTo(r2.getRequestTime())).collect(Collectors.toList());

		// write result to CSV file
		File outputFile = new File(fileDir + "/output.csv");
		writeToCsvFile(outputFile, results);
		System.out.println("Combined CSV file written to '" + outputFile.getAbsolutePath() + "'\n");

		// calculate and print out the number of records per service-guid
		System.out.println("### Service-guid summary ###\nservice-guid, number of records");
		results.stream().collect(Collectors.groupingBy(Report::getServiceGuid, Collectors.counting()))
				.forEach((k, v) -> System.out.println(k + ", " + v));

	}

	/**
	 * Read report data from the specified file using the provided mapper and schema.
	 * 
	 * @param file the file pointer to read from
	 * @param mapper the object mapper to use
	 * @param schema the optional schema to apply to the reader
	 * @param reports a list of reports to be appended to
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	private static void readFromFile(File file, ObjectMapper mapper, Optional<FormatSchema> schema,
			List<Report> reports) throws IOException, JsonProcessingException {
		
		// get the reader with an optional schema
		ObjectReader reader = mapper.readerFor(Report.class);
		if (schema.isPresent()) {
			reader = reader.with(schema.get());
		}

		// read the values and add them to the report list
		MappingIterator<Object> values = reader.readValues(file);
		while (values.hasNext()) {
			Report report = (Report) values.next();
			reports.add(report);
		}
		
	}

	/**
	 * Writes the given reports into the specified output file.
	 * 
	 * @param outputFile pointer to the CSV file to write
	 * @param reports the list of reports to write
	 * @throws IOException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 */
	private static void writeToCsvFile(File outputFile, List<Report> reports)
			throws IOException, JsonGenerationException, JsonMappingException {

		CsvMapper outputMapper = new CsvMapper();

		// only use quotation marks when necessary in the output file
		outputMapper.configure(CsvGenerator.Feature.STRICT_CHECK_FOR_QUOTING, true);

		// write all reports out to the CSV file
		outputMapper.writer(outputMapper.schemaFor(Report.class).withHeader()).writeValue(outputFile, reports);
		
	}

}
