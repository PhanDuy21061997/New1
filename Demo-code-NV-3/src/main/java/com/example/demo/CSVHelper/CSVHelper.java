package com.example.demo.CSVHelper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Personnel;
import com.example.demo.model.User;
import com.example.demo.model.User_Role;

public class CSVHelper {

	public static String TYPE = "test/csv";
	static String[] HEADERs = { "id_p", "address", "date_of_birth", "id_manage_p" };

	// kiem tra di5nh da5ng file csv
	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	// load tu file csv vao
	public static List<Personnel> csvToPersonnels(InputStream is) throws ParseException {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<Personnel> personnels = new ArrayList<Personnel>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {

				Personnel personnel = new Personnel(Integer.parseInt(csvRecord.get("id_p")), csvRecord.get("name"),
						csvRecord.get("address"), csvRecord.get("email"),
						(Date) new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord.get("date_of_birth")),
						Integer.parseInt(csvRecord.get("id_manage_p")));
				User user = new User(Integer.parseInt(csvRecord.get("id_u")), csvRecord.get("username"),
						csvRecord.get("passwork"), Integer.parseInt(csvRecord.get(" id_personnel")));
				personnel.setUser(user);
				personnels.add(personnel);
			}

			return personnels;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	// chuyen sang file .csv
	public static ByteArrayInputStream personnelsToCSV(List<Personnel> personnels) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {

			for (Personnel personnel : personnels) {
				// User_Role user_Role=personnel.getUser().getUsersRoleses();
				Set<User_Role> user_Roles = personnel.getUser().getUsersRoleses();

				String pattern = "dd/MM/yyyy";
				DateFormat df = new SimpleDateFormat(pattern);
				String todayAsString = df.format(personnel.getDate_of_birth());
				List<String> data = Arrays.asList(String.valueOf(personnel.getId_p()), personnel.getName(),
						personnel.getAddress(), personnel.getEmail(), String.valueOf(personnel.getId_manage_p()),
						String.valueOf(String.valueOf(personnel.getUser().getId_u())),
						String.valueOf(personnel.getUser().getUsername()),
						String.valueOf(personnel.getUser().getPasswork()), loadUser(user_Roles).toString(),
						String.valueOf(personnel.getManage().getId_m()),
						String.valueOf(personnel.getManage().getId_personnel()));

				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}

	private static List<String> loadUser(Set<User_Role> user) {
		List<String> list = new ArrayList<String>();
		for (User_Role user_Role : user) {
			List<String> data = Arrays.asList(String.valueOf(user_Role.getId()), String.valueOf(user_Role.getRole_id()),
					String.valueOf(user_Role.getUser_id()), String.valueOf(user_Role.getRole().getId_r()),
					String.valueOf(user_Role.getRole().getName()));
			list.addAll(data);
		}
		// return list.toString();
		return list;
	}

	/*
	 * private static List<User_Role> loadUser(Set<User_Role>user) {
	 * List<User_Role>list=new ArrayList<User_Role>(); for (User_Role user_Role :
	 * user) { Collection<User_Role>
	 * data=Arrays.asList(String.valueOf(user_Role.getId()),String.valueOf(user_Role
	 * .getRole_id())
	 * ,String.valueOf(user_Role.getUser_id()),String.valueOf(user_Role.getRole().
	 * getId_r()),String.valueOf(user_Role.getRole().getName())); list.add(data); }
	 * // return list.toString(); return list; }
	 */

}
