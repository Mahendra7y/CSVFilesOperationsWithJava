import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;

public class CSVFileReaderAndOperations {

	public LinkedHashSet<String> readCSVFilesAndRemoveDuplicates(String[] inputFiles) {

		BufferedReader reader = null;
		String eachRow = "";

		LinkedHashSet<String> finalCsvMergedList = new LinkedHashSet<>();

		for (int i = 0; i < inputFiles.length; i++) {
			String csv_file = inputFiles[i];

			try {
				reader = new BufferedReader(new FileReader(csv_file));
				if (i == 1) {
					if ((eachRow = reader.readLine()) != null) {
						// this condition is to ignore header in 2nd file
					}
				}

				while ((eachRow = reader.readLine()) != null) {
					if (finalCsvMergedList.add(eachRow)) {
						// if the row is added only once to list then no duplicate,,else duplicate row
						System.out.println("added once-->: " + eachRow);

						// removing the row which starts with 'String'
						if (eachRow.startsWith("String")) {
							finalCsvMergedList.remove(eachRow);
						}

						// replacing # with empty
						if (eachRow.startsWith("#")) {
							System.out.println("row contained #==>" + eachRow);
							finalCsvMergedList.remove(eachRow);
							String replacedStr = eachRow.replace("#", "");
							System.out.println("replacedRow==>" + replacedStr);
							finalCsvMergedList.add(replacedStr);
						}

						// replacing end ; with 0;
						if (eachRow.endsWith(";;")) {
							System.out.println("row end contained ;==>" + eachRow);
							finalCsvMergedList.remove(eachRow);
							String replacedStr = eachRow.replaceAll("[;]$", "0");
							System.out.println("replacedRow==>" + replacedStr);
							finalCsvMergedList.add(replacedStr);
						}

					} else if (CommonUtil.checkIsNullOrEmpty(eachRow)) {
						System.out.println("-------duplicate row--------" + eachRow);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}

//		Iterator<String> itr = finalCsvList.iterator();
//		while (itr.hasNext()) {
//			String value = itr.next();
//			if (value.startsWith("String")) {
//				itr.remove();
//			}
//		}

		// to print the finalList
//		finalCsvList.forEach(System.out::println);

		return finalCsvMergedList;

	}

	public List<String[]> ReadMergedCSVFileAndCalculateAverage(String tempCSVInputFile) {

		BufferedReader reader = null;
		String eachRow = "";

		TreeSet<String> csvRows = new TreeSet<String>();
		LinkedHashSet<String[]> allCountriesList = new LinkedHashSet<String[]>();
		String headers[] = null;

		List<String[]> finalList = new ArrayList<String[]>();

		try {
			reader = new BufferedReader(new FileReader(tempCSVInputFile));

			if ((eachRow = reader.readLine()) != null) {
				// this condition is for header
				headers = eachRow.split(",");
			}

			while ((eachRow = reader.readLine()) != null) {
				if (csvRows.add(eachRow)) {
					// if the row is added only once to list then no duplicate,,else duplicate row
					// System.out.println(Arrays.toString(eachRow.split(";")));
				} else if (CommonUtil.checkIsNullOrEmpty(eachRow)) {
					System.out.println("-------duplicate row--------" + eachRow);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// using delimiter splitting rows & adding to allCountriesList
		Iterator<String> itr = csvRows.iterator();
		while (itr.hasNext()) {
			allCountriesList.add(itr.next().split(","));
		}

		// ############### below code is averages calculation code ####################

		// filtering countries start with 'A' & adding to countryALetterlist
		LinkedHashSet<String[]> countryALetterlist = new LinkedHashSet<String[]>();
		Iterator<String[]> tt = allCountriesList.iterator();
		while (tt.hasNext()) {
			String[] strArr = tt.next();
			if (strArr[0].startsWith("A")) {
				countryALetterlist.add(strArr);
				System.out.println("--starts A letter countries-->" + Arrays.toString(strArr));
			}
		}

		// calculating averages
		int startALetterCountriesCount = countryALetterlist.size();
		System.out.println("startALetterCountriesCount =>" + startALetterCountriesCount);
		String[] averages = new String[headers.length];

		for (int i = 1; i < headers.length - 1; i++) {
			float sum = 0;
			Iterator<String[]> it = countryALetterlist.iterator();
			while (it.hasNext()) {
				String[] s = it.next();
				for (int j = i; j <= i; j++) {
					if (s[j] != null && !s[j].isEmpty())
						sum += Float.parseFloat(s[j]);
				}
			}
			float avg = sum / startALetterCountriesCount;
			averages[i] = avg + "";
		}

		// adding last column avg..(due to missing in above logic)
		float sum = 0;
		Iterator<String[]> it = countryALetterlist.iterator();
		while (it.hasNext()) {
			String[] s = it.next();
			if (s[s.length - 1] != null && !s[s.length - 1].isEmpty())
				sum += Float.parseFloat(s[s.length - 1]);
		}
		float avg = sum / startALetterCountriesCount;
		averages[headers.length - 1] = avg + "";

		averages[0] = "Countries Starts With \'A\'";

		finalList.add(0, headers);
		finalList.add(1, averages);

		return finalList;
	}

}
