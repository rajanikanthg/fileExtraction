package fileExtraction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Logger;


public class FixedFileFormatConverter {

	static Logger logger = Logger.getLogger(FixedFileFormatConverter.class.getName());

	public static void main(String args[]) throws IOException {

		try (

				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(new FileInputStream(Constants.INPUT_DATA), Constants.CHARSET_UFT8))) {
			String line;
			Writer writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(Constants.OUTPUT_CSV), Constants.CHARSET_UFT8));
			writer = Apputil.header(writer);

			while ((line = bufferedReader.readLine()) != null) {
				String str = line;
				if (line.length() == 0) {
					logger.info("NO RECORD");
				} else if (line.length() > 0 && line.length() < 46) {
					String dateCheck= Apputil.checkDate(str.substring(0, 10), line);
					if ( dateCheck != null) {
						writer.append(dateCheck + ",");
						writer.append(Apputil.checkSpecialChar(str.substring(10, 25).trim()) + ",");
						writer.append(Apputil.checkSpecialChar(str.substring(25, 40).trim()) + ",");
					}
					if (Apputil.checkNumeric(str.substring(40, 45).trim(), line) != null) {
						writer.append(str.substring(40, 45).trim());
					}
					writer.append("\r\n");
				}
				else {
					logger.info("LENGTH DEFINED IS 45 BUT ACTUAL LENGTH IS "+line.length()+"\nDATA :" +line);
				}
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			logger.info(e.getMessage()+" FILE MISSING");
		}

	}

}
