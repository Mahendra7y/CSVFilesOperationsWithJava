import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class CSVFilesWriter {

	public void writeMergedListToCsvFile(LinkedHashSet<String> finalCsvMergedList, String separator, String fileName) {

		try (FileWriter writer = new FileWriter(fileName)) {
			Iterator<String> itr = finalCsvMergedList.iterator();
			while (itr.hasNext()) {
				String[] strings = itr.next().split(";");
				for (int i = 0; i < strings.length; i++) {
					writer.append(strings[i]);
					if (i < (strings.length - 1))
						writer.append(separator);
				}
				writer.append(System.lineSeparator());

			}
			writer.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeAverageCalculationsToCsvFile(List<String[]> thingsToWrite, String separator, String fileName) {
		try (FileWriter writer = new FileWriter(fileName)) {
			for (String[] strings : thingsToWrite) {
				for (int i = 0; i < strings.length; i++) {
					writer.append(strings[i]);
					if (i < (strings.length - 1))
						writer.append(separator);
				}
				writer.append(System.lineSeparator());
			}
			writer.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
