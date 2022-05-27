import java.util.LinkedHashSet;
import java.util.List;

public class MainClass {

	public static void main(String[] args) {

		String inputFiles[] = { "C:\\Users\\robin\\Documents\\Assessment\\data\\factbook.csv",
				"C:\\Users\\mahendra\\Documents\\Assessment\\data\\factbook-2.csv" };

		String tempFile = "C:\\Users\\mahendra\\Documents\\Assessment\\temp\\temp.csv";
		String outputFile = "C:\\Users\\mahendra\\Documents\\Assessment\\output\\output.csv";
		String seperator = ",";

		CSVFileReaderAndOperations csvReader = new CSVFileReaderAndOperations();
		CSVFilesWriter csvWriter = new CSVFilesWriter();

		/*
		 * Reading two input CSV files & filtering duplicate records and after that
		 * merging records into temp CSV file
		 */
		LinkedHashSet<String> finalCsvMergedList = csvReader.readCSVFilesAndRemoveDuplicates(inputFiles);
		csvWriter.writeMergedListToCsvFile(finalCsvMergedList, seperator, tempFile);

		/*
		 * Reading temp CSV file & calculating averages based on starting alphabet of
		 * the country which starts with 'a' and writing into the output CSV file
		 */

		List<String[]> averageCalcuationsReponse = csvReader.ReadMergedCSVFileAndCalculateAverage(tempFile);
		csvWriter.writeAverageCalculationsToCsvFile(averageCalcuationsReponse, seperator, outputFile);

	}

}
