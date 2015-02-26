package struts.stage.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import struts.stage.actions.AllTimesPositionsAction;
import struts.stage.actions.InsertWorkoutAction;
import struts.stage.actions.LinkEditProfileAction;
import struts.stage.actions.LinkMyTargetsAction;
import struts.stage.actions.MonthlyPositionsAction;
import struts.stage.objects.Competition;
import struts.stage.objects.CompetitionsData;
import struts.stage.objects.Role;
import struts.stage.utils.BarChart;
import struts.stage.utils.LineChart;
import struts.stage.utils.MD5;
import struts.stage.utils.PieChart;

public class UserRepository {

	static {
		try {
			Class.forName("org.h2.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test;",
				"sa", "");
	}

	public static String updateWorkout(int ID, float km, String time,
			String place, String date, String pace, String kmPerHour,
			String actulaDate, int wo_id, int timeInSeconds)
			throws SQLException {
		Connection connection = getConnection();
		try {
			String sql = "UPDATE WORKOUTS SET KM = ?, TIME = ?, PLACE = ? , DATE = ?, PACE = ?, KM_HOUR = ?, INSERT_DATE = ? , TIME_IN_SECONDS = ? WHERE ID = ? AND WORKOUT_ID = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setFloat(1, km);
				pst.setString(2, time);
				pst.setString(3, place);
				pst.setString(4, date);
				pst.setString(5, pace);
				pst.setString(6, kmPerHour);
				pst.setString(7, actulaDate);
				pst.setInt(8, timeInSeconds);
				pst.setInt(9, ID);
				pst.setInt(10, wo_id);
				int updateCount = pst.executeUpdate();
				if (updateCount == 1) {
					return "success";
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return "error";
	}

	public static boolean checkWOID(int woid) throws SQLException {
		Connection connection = getConnection();
		try {
			String sql = "SELECT * FROM WORKOUTS WHERE WORKOUT_ID = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, woid);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					return true;
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return false;
	}

	public static String insertWorkout(int id, Float km, String time,
			String place, String date, String pace, String kmHour,
			String timeStamp, int timeInSeconds) throws SQLException {
		Connection connection = getConnection();
		try {
			String sql = "INSERT INTO WORKOUTS ( ID, KM, TIME , PLACE , DATE, PACE, KM_HOUR, INSERT_DATE, TIME_IN_SECONDS) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, id);
				pst.setFloat(2, km);
				pst.setString(3, time);
				pst.setString(4, place);
				pst.setString(5, date);
				pst.setString(6, pace);
				pst.setString(7, kmHour);
				pst.setString(8, timeStamp);
				pst.setInt(9, timeInSeconds);
				pst.executeUpdate();
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					return "success";
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return "error";
	}

	public static Role checkUserAndPass(String user, String pass)
			throws SQLException, IOException {
		Connection connection = getConnection();
		try {
			String sql = "select r.role_name, a.user_name , a.ID, a.first_name, a.last_name, a.picture, a.picture_Url, a.picture from roles r join accounts_info a "
					+ "on r.role_id = a.role_id where user_name = ? and password  = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setString(1, user);
				pst.setString(2, pass);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					String role = rs.getString("role_name");
					String userName = rs.getString("user_name");
					String ID = rs.getString("ID");
					int id = Integer.parseInt(ID);
					String f_name = rs.getString("first_name");
					String l_name = rs.getString("last_name");
					String profilePicUrl = rs.getString("picture_Url");

					//InputStream is = rs.getBinaryStream("picture");
	                //readPic(is, id);
					
	                
//					File image = new File("C:/Users/Boycho/Desktop/FMI/workspace/RunAsistantStruts2/WebContent/images/storage/readPic2.jpg");
//	                FileOutputStream fos = new FileOutputStream(image);	 
//	                byte[] buffer = new byte[1024];
//	                InputStream is = rs.getBinaryStream("picture");
//	                while (is.read(buffer) > 0) {
//	                    fos.write(buffer);
//	                }
//	                fos.close();
					
					if (userName != null && ID != null && role != null
							&& f_name != null && l_name != null
							&& profilePicUrl != null) {
						return new Role(role, userName, id, f_name, l_name,
								profilePicUrl);
					}
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return null;
	}

	public static boolean checkPassAndID(String pass, int ID)
			throws SQLException {
		Connection connection = getConnection();
		try {
			String sql = "select user_name from accounts_info "
					+ "where password = ? and id = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setString(1, pass);
				pst.setInt(2, ID);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					String userName = rs.getString("user_name");
					if (userName != null) {
						return true;
					}
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return false;
	}

	public static void savePicToFile(File pic, int id, String directory)
			throws SQLException {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		String md5Url = generateUrl(id);

		try {
			// read this file into InputStream
			inputStream = new FileInputStream(pic);

			// write the inputStream to a FileOutputStream
			outputStream = new FileOutputStream(new File(
					"C:/Users/Boycho/Desktop/FMI/workspace/RunAsistantStruts2/WebContent/images/"
							+ directory + md5Url + ".jpg"));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public static boolean checkUser(String user) throws SQLException {
		Connection connection = getConnection();
		try {
			String sql = "select user_name from accounts_info "
					+ " where user_name = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setString(1, user);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					String userName = rs.getString("user_name");
					if (userName != null) {
						return true;
					}
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return false;
	}

	public static boolean insertUserRole(int id) throws SQLException {

		Connection connection = getConnection();
		String userRole = "user";
		try {
			String sql = "insert into ROLES( id , role) values(?, ?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, id);
				pst.setString(2, userRole);
				pst.executeUpdate();
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					return true;
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return false;
	}

	public static String insertRegisterInfo(String userName, String password,
			String firstName, String lastName, int role_id, String city,
			String email, int age, String gender, File pic)
			throws SQLException, IOException {

		// insert into Registration table
		Connection connection = getConnection();
		try {
			String sql = "insert into accounts_info ( user_name , password , first_name , last_name,"
					+ " city, age, gender , role_id, email, picture_url, picture) "
					+ "values (?, ?, ?, ? ,? ,? , ?, ?, ?, ?, ?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setString(1, userName);
				pst.setString(2, password);
				pst.setString(3, firstName);
				pst.setString(4, lastName);
				pst.setString(5, city);
				pst.setInt(6, age);
				pst.setString(7, gender);
				pst.setInt(8, role_id);
				pst.setString(9, email);
				pst.setString(10, "");
				InputStream file = new FileInputStream(pic);
				pst.setBlob(11, file);
				pst.executeUpdate();
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					int id = checkUserAndPass(userName, password).getId();
					insertFirstTarget(id);
					String md5Url = generateUrl(id);
					updateDbUrl(md5Url, id);
					savePicToFile(pic, id, "storage/");
					return "success";
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return "error";
	}

	private static void updateDbUrl(String md5Url, int id) throws SQLException {
		Connection connection = getConnection();
		try {
			String sql = "UPDATE accounts_info SET PICTURE_URL = ? WHERE ID = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setString(1, md5Url);
				pst.setInt(2, id);
				pst.executeUpdate();

			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
	}

	private static String generateUrl(int id) {
		
		String url = null;
		try {
			MD5 hashedID = new MD5(String.valueOf(id));
			url = hashedID.hashing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

	public static String editProfileInfo(String firstName, String lastName,
			String city, int age, String gender, File myFile, int id)
			throws SQLException, FileNotFoundException {

		Connection connection = getConnection();
		try {
			String sql = "update accounts_info set first_name = ?, last_name = ?, city = ?, age = ?,"
					+ "gender = ?, picture = ? where id = ?;";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setString(1, firstName);
				pst.setString(2, lastName);
				pst.setString(3, city);
				pst.setInt(4, age);
				pst.setString(5, gender);
				InputStream file = new FileInputStream(myFile);
				pst.setBlob(6, file);
				pst.setInt(7, id);
				int update = pst.executeUpdate();
				if (update == 1) {
					savePicToFile(myFile, id, "storage/");
					return "success";
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return "error";
	}

	public static String editProfileInfoNoPic(String firstName,
			String lastName, String city, int age, String gender, int id)
			throws SQLException {
		Connection connection = getConnection();
		try {
			String sql = "update accounts_info set first_name = ?, last_name = ?, city = ?, age = ?,"
					+ "gender = ? where id = ?;";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setString(1, firstName);
				pst.setString(2, lastName);
				pst.setString(3, city);
				pst.setInt(4, age);
				pst.setString(5, gender);
				pst.setInt(6, id);
				int update = pst.executeUpdate();
				if (update == 1) {
					return "success";
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return "error";
	}

	public static LinkEditProfileAction getProfileInfo(int id)
			throws SQLException {

		Connection connection = getConnection();
		try {
			String sql = "select first_name, last_name , city, age, gender, picture_Url from accounts_info where ID = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");
					String city = rs.getString("city");
					String age = rs.getString("age");
					String gender = rs.getString("gender");
					String url = rs.getString("picture_Url");
					if (firstName != null && lastName != null && city != null
							&& age != null && gender != null && url != null) {
						return new LinkEditProfileAction(firstName, lastName,
								city, age, gender, url);
					}
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return null;
	}

	public static LinkEditProfileAction getProfileInfoAndPic(int id)
			throws SQLException, IOException {

		Connection connection = getConnection();
		try {
			String sql = "select first_name, last_name , city, age, gender, picture, pictureUrl from accounts_info where ID = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");
					String city = rs.getString("city");
					String age = rs.getString("age");
					String gender = rs.getString("gender");
					String url = rs.getString("pictureUrl");

					InputStream inputStream = rs.getBinaryStream("picture");
					System.out.println(inputStream);
					// File picture = readPic(is, id);
					readPic(inputStream, id);

					// if (firstName != null && lastName != null && city != null
					// && age != null && gender != null && picture != null) {
					// return new LinkEditProfileAction(firstName, lastName,
					// city, age, gender, picture);
					// }
					if (firstName != null && lastName != null && city != null
							&& age != null && gender != null && url != null) {
						return new LinkEditProfileAction(firstName, lastName,
								city, age, gender, url);
					}
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return null;
	}

	public static void readPic(InputStream inputStream, int id)
			throws IOException {
		File file = new File(
				"C:/Users/Boycho/Desktop/FMI/workspace/RunAsistantStruts2/WebContent/images/storage/"
						+ id + ".jpg");
		FileOutputStream outputStream = new FileOutputStream(file);

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
		if (outputStream != null) {
			try {
				// outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	
	public static boolean[] checkUserMail(String user, String email)
			throws SQLException {
		Connection connection = getConnection();
		try {
			String sql = "select id from accounts_info  where user_name = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			boolean[] results = { false, false };
			try {
				pst.setString(1, user);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					String ID = rs.getString("ID");
					if (ID != null && ID.length() != 0) {
						results[0] = true;
					}
				}
			} finally {
				pst.close();
			}
			try {
				sql = "select id from accounts_info  where email = ?";
				pst = connection.prepareStatement(sql);
				pst.setString(1, email);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					String ID = rs.getString("ID");
					if (ID != null && ID.length() != 0) {
						results[1] = true;
					}
				}
			} finally {
				pst.close();
			}
			return results;
		} finally {
			connection.close();
		}
	}

	
	
	public static ArrayList<InsertWorkoutAction> getMyWorkouts(int id)
			throws SQLException {

		Connection connection = getConnection();
		ArrayList<InsertWorkoutAction> arr = new ArrayList<InsertWorkoutAction>();

		try {
			String sql = "SELECT KM, TIME, PLACE, DATE, PACE, KM_HOUR, WORKOUT_ID, TIME_IN_SECONDS FROM WORKOUTS where id = ? order by date desc";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					float km = rs.getFloat("KM");
					String time = rs.getString("TIME");
					String place = rs.getString("PLACE");
					String date = rs.getString("DATE");
					String pace = rs.getString("PACE");
					String kmHour = rs.getString("KM_HOUR");
					int wo_id = rs.getInt("WORKOUT_ID");
					int timeInSeconds = rs.getInt("TIME_IN_SECONDS");
					InsertWorkoutAction roll = new InsertWorkoutAction(date,
							time, km, place, pace, kmHour, wo_id, timeInSeconds);
					arr.add(roll);
				}
				return arr;
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
	}

	
	public static boolean findWorkout(int id) throws SQLException {
		Connection connection = getConnection();
		try {
			String sql = "select id from workouts  where workout_id = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
	}

	
	public static boolean deleteWorkout(int wo_id) throws SQLException {
		Connection connection = getConnection();
		try {
			String sql = "delete from workouts where workout_id = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, wo_id);
				int updateCount = pst.executeUpdate();
				return updateCount == 1;
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
	}

	
	public static String best5KM(int ID) throws SQLException {
		Connection connection = getConnection();
		String result = "";
		try {
			String sql = "select MIN(time) from  workouts where km = 5 and ID = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, ID);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					result = rs.getString("MIN(TIME)");
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return result;
	}

	
	public static String best10KM(int ID) throws SQLException {
		Connection connection = getConnection();
		String result = "";
		try {
			String sql = "select MIN(time) from  workouts where km = 10 and ID = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, ID);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					result = rs.getString("MIN(TIME)");
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return result;
	}

	
	public static boolean updateTargets(int iD, int totalSecondsFive,
			int totalSecondsTen, int km) throws SQLException {

		Connection connection = getConnection();
		try {
			String sql = "update targets set time_5km  = ?, time_10km  = ?, km = ? where id = ?;";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(4, iD);
				pst.setInt(1, totalSecondsFive);
				pst.setInt(2, totalSecondsTen);
				pst.setInt(3, km);
				int updateCount = pst.executeUpdate();
				if (updateCount == 1) {
					return true;
				} else {
					checkIsTarget(iD);
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return false;
	}

	
	public static boolean insertFirstTarget(int iD) throws SQLException {

		Connection connection = getConnection();
		try {
			String sql = "INSERT INTO TARGETS (ID, TIME_5KM, TIME_10KM, KM) VALUES( ?, 1800 , 3600 , 50);";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, iD);
				int updateCount = pst.executeUpdate();
				if (updateCount != 1) {
					return true;
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return false;
	}

	
	
	public static boolean checkIsTarget(int id) throws SQLException {
		Connection connection = getConnection();
		try {
			String sql = "select * from targets  where id = ?;";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					return true;
				} else if (insertFirstTarget(id)) {
					return true;
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return false;
	}

	
	public static LinkMyTargetsAction getBestResults(String iD)
			throws SQLException {

		int bestFive = best5KMinSeconds(iD);
		int bestTen = best10KMinSeconds(iD);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		String dateMinusOneMonth = dateFormat.format(cal.getTime());

		Connection connection = getConnection();
		try {
			String sql = "select sum(KM) from workouts where date <= ? and date > ? and id = ?;";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setString(1, curDate);
				pst.setString(2, dateMinusOneMonth);
				pst.setString(3, iD);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					float km = rs.getFloat("sum(KM)");
					// System.out.println(km);
					LinkMyTargetsAction result = new LinkMyTargetsAction(
							bestFive, bestTen, km);
					return result;
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return null;
	}

	
	public static int best5KMinSeconds(String ID) throws SQLException {
		Connection connection = getConnection();
		int result = 0;
		try {
			String sql = "select MIN(TIME_IN_SECONDS) from  workouts where km = 5 and ID = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setString(1, ID);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					result = rs.getInt("MIN(TIME_IN_SECONDS)");
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return result;
	}

	
	public static int best10KMinSeconds(String ID) throws SQLException {
		Connection connection = getConnection();
		int result = 0;
		try {
			String sql = "select MIN(TIME_IN_SECONDS) from  workouts where km = 10 and ID = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setString(1, ID);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					result = rs.getInt("MIN(TIME_IN_SECONDS)");
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return result;
	}

	
	public static LinkMyTargetsAction getTargets(String iD) throws SQLException {
		Connection connection = getConnection();
		try {
			String sql = "select time_5km, time_10km , km from targets where id = ?;";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setString(1, iD);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					LinkMyTargetsAction result = new LinkMyTargetsAction(
							rs.getInt("time_5km"), rs.getInt("time_10km"),
							rs.getInt("KM"));
					return result;
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return null;
	}

	
	public static ArrayList<MonthlyPositionsAction> getLastMonthRanking()
			throws SQLException {
		Connection connection = getConnection();
		ArrayList<MonthlyPositionsAction> result = new ArrayList<MonthlyPositionsAction>(
				10);

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String today = dateFormat.format(cal.getTime());
			cal.add(Calendar.MONTH, -1);
			String minusMonth = dateFormat.format(cal.getTime());
			String sql = "select TOP 10 a.id, a.first_name, a.last_name, sum(km) from accounts_info a join workouts w on a.id = w.id where date <= ? and date >= ? group by a.id order by sum(km) desc;";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setString(1, today);
				pst.setString(2, minusMonth);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("first_name") + " "
							+ rs.getString("last_name");
					float km = rs.getFloat("sum(km)");
					result.add(new MonthlyPositionsAction(id, name, km));
				}
				return result;
			} finally {
				pst.close();
			}

		} finally {
			connection.close();
		}
	}

	
	public static ArrayList<AllTimesPositionsAction> getAllTimesRanking()
			throws SQLException {
		Connection connection = getConnection();
		ArrayList<AllTimesPositionsAction> result = new ArrayList<AllTimesPositionsAction>(
				10);

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String today = dateFormat.format(cal.getTime());
			String sql = "select TOP 10 a.id, a.first_name, a.last_name, sum(km) from accounts_info a join workouts w on a.id = w.id where date <= ? group by a.id order by sum(km) desc;";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setString(1, today);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("first_name") + " "
							+ rs.getString("last_name");
					float km = rs.getFloat("sum(km)");
					result.add(new AllTimesPositionsAction(id, name, km));
				}
				return result;
			} finally {
				pst.close();
			}

		} finally {
			connection.close();
		}
	}

	public static ArrayList<Competition> getCompetitionsList() throws SQLException {
		Connection connection = getConnection();
		ArrayList<Competition> competitions = new ArrayList<Competition>();
		try {
			String sql = "select competition_id, name from competitions";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					int competition_id = rs.getInt("competition_id");
					String competition_name = rs.getString("name");
					Competition competition = new Competition(competition_id, competition_name);
					competitions.add(competition);
				}
				return competitions;
			} catch (SQLException e) {
				e.printStackTrace();
				return competitions;
			}
			finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
	}

	public static ArrayList<CompetitionsData> getCompetitionsData(
			ArrayList<Competition> competitions) throws SQLException {
		Connection connection = getConnection();
		ArrayList<CompetitionsData> competitionsData = new ArrayList<CompetitionsData>();
		try {
			String sql = "select TOP 10 first_name, last_name, sum(km) as KM from accounts_info a join workouts w on a.id = w.id "
					+ " join COMPETITIONS_PARTICIPANTS cp on cp.id = w.id "
					+ " join competitions comp on comp.competition_id = cp.competition_id "
					+ " where date <= (select end_date from competitions where competition_id = ?) and "
					+ " date >= (select start_date from competitions where competition_id = ?) "
					+ " group by a.id order by sum(km) desc;";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, competitions.get(0).getCompetition_id());
				pst.setInt(2, competitions.get(0).getCompetition_id());
				//pst.setString(3, competitions.get(0).getCompetition_name());
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");
					int km = rs.getInt("KM");
					CompetitionsData contestant = new CompetitionsData(firstName, lastName, km);
					competitionsData.add(contestant);
				}
				return competitionsData;
			} catch (SQLException e) {
				e.printStackTrace();
				return competitionsData;
			}
			finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
	}

	public static String joinCompetition(int id, int competition_id) throws SQLException {

		Connection connection = getConnection();
		try {
			String sql = "INSERT INTO COMPETITIONS_PARTICIPANTS ( COMPETITION_ID, ID) VALUES(?, ?);";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, competition_id);
				pst.setInt(2, id);
				int updateCount = pst.executeUpdate();
				if (updateCount != 1) {
					return "error";
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return "success";
	}

	public static boolean isParticipating(int competition_id, int id) throws SQLException {
		Connection connection = getConnection();
		try {
			String sql = "select * from COMPETITIONS_PARTICIPANTS where competition_id = ? and ID = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, competition_id);
				pst.setInt(2, id);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					return true;
				}
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
	}

	public static String leaveCompetition(int id, int competition_id) throws SQLException{
		Connection connection = getConnection();
		try {
			String sql = "DELETE FROM COMPETITIONS_PARTICIPANTS WHERE competition_id = ? AND ID = ? ;";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				pst.setInt(1, competition_id);
				pst.setInt(2, id);
				int updateCount = pst.executeUpdate();
				if (updateCount != 1) {
					return "error";
				}
			} finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
		return "success";
	}

	public static String updateFactsCharts()throws SQLException {
		Connection connection = getConnection();
		try {
			String sql = "select (select sum(km) from WORKOUTS w join ACCOUNTS_INFO a on w.id = a.id where gender = 'men') as MEN,  "
					+ "(select sum(km) from WORKOUTS w join ACCOUNTS_INFO a on w.id = a.id where gender = 'women') as WOMEN;";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					float menKm = rs.getFloat("MEN");
					float womenKm = rs.getFloat("WOMEN");
					PieChart.createPieChart(menKm, womenKm);
					BarChart.createBarChar("Men vs Women", "MEN", menKm, "WOMEN", womenKm);
					updateAgeLineChart();
					return "success";
				}
				return "error";
			} catch (SQLException e) {
				e.printStackTrace();
				return "success";
			}
			finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
	}
	public static void updateAgeLineChart()throws SQLException {
		Connection connection = getConnection();
		try {
			String sql = "select (select sum(km) from WORKOUTS w join ACCOUNTS_INFO a on w.id = a.id where age < 10) as TEN, "
					+ "(select sum(km) from WORKOUTS w join ACCOUNTS_INFO a on w.id = a.id where 9 < age AND age < 20) as TWENTY, "
					+ "(select sum(km) from WORKOUTS w join ACCOUNTS_INFO a on w.id = a.id where 19 < age AND age < 30) as THIRTY, "
					+ "(select sum(km) from WORKOUTS w join ACCOUNTS_INFO a on w.id = a.id where 29 < age AND age < 40) as FORTY, "
					+ "(select sum(km) from WORKOUTS w join ACCOUNTS_INFO a on w.id = a.id where 39 < age AND age < 50) as FIFTY, "
					+ "(select sum(km) from WORKOUTS w join ACCOUNTS_INFO a on w.id = a.id where 49 < age AND age < 60) as SIXTY, "
					+ "(select sum(km) from WORKOUTS w join ACCOUNTS_INFO a on w.id = a.id where 59 < age AND age < 70) as SEVENTY;";
			PreparedStatement pst = connection.prepareStatement(sql);
			try {
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					ArrayList<Float> dataForLineChar = new ArrayList<Float>(7);
					float ten = rs.getFloat("TEN");
					dataForLineChar.add(ten);
					float twenty = rs.getFloat("TWENTY");
					dataForLineChar.add(twenty);
					float thirty = rs.getFloat("THIRTY");
					dataForLineChar.add(thirty);
					float forty = rs.getFloat("FORTY");
					dataForLineChar.add(forty);
					float fifty = rs.getFloat("FIFTY");
					dataForLineChar.add(fifty);
					float sixty = rs.getFloat("SIXTY");
					dataForLineChar.add(sixty);
					float seventy = rs.getFloat("SEVENTY");
					dataForLineChar.add(seventy);
					LineChart.createLineChart(dataForLineChar);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				pst.close();
			}
		} finally {
			connection.close();
		}
	}
}
