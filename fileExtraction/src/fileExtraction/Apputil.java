package fileExtraction;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Apputil {

	static Logger logger =Logger.getLogger(Apputil.class.getName());
	
	public static Writer header(Writer writer) {

		try {
			writer.append("Birth date");
			writer.append(',');
			writer.append("First name");
			writer.append(',');
			writer.append("Last name");
			writer.append(',');
			writer.append("Weight\r\n" + "");

		} catch (IOException e) {
			// Do nothing
		}
		return writer;

	}

	public static String checkSpecialChar(String strSpecialChar) {

		Pattern special = Pattern.compile(Constants.SPECIAL_CHAR);
		Matcher hasSpecial = special.matcher(strSpecialChar);
		if (hasSpecial.find()) {
			return "\"" + strSpecialChar + "\"";
		}

		return strSpecialChar;

	}
	
	public static String checkNumeric(String strNum,String line) {
		Float f;
		 try
		 {
			 f= Float.parseFloat(strNum);
		 }catch (Exception e) {
			 logger.info(e.getMessage()+"\nDATA: "+line);
			 return null;
		}

		return String.valueOf(f);

	}
	
	public static String checkDate(String strNum,String line) {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
		Date date1;
		 try
		 {
			 date1=new SimpleDateFormat("yyyy-mm-dd").parse(strNum);
			 
		 }catch (Exception e) {
			 logger.info(e.getMessage()+"\nDATA: "+line);
			 return null;
		}

		return sdf.format(date1);

	}
	
}
