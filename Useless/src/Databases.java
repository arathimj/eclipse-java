import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Databases {
	private static Connection connect = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	final private static String host = "localhost:3306/Student";
	final private static String user = "root";
	final private static String passwd = "destinyplays1!";
	public static final String SALT = "my-salt-text";
	
	public static void main(String[] args) {
		try {
			running();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void running() throws SQLException{
		StringBuilder hash = new StringBuilder();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://" + host + "?" + "user=" + user + "&password=" + passwd );
			preparedStatement=connect.prepareStatement("select * from StudentTable;");
			resultSet=preparedStatement.executeQuery();
			
			
			while (resultSet.next()) {
			String FirstName = resultSet.getString("FirstName");
			String LastName = resultSet.getString("LastName");
			String Email = resultSet.getString("Email");
			String Password = resultSet.getString("Password");
			String Sex = resultSet.getString("Sex");
			System.out.println("FirstName: " + FirstName);
			System.out.println("LastName: " + LastName);
			System.out.println("Email: " + Email);
			String saltedPassword = SALT + Password;
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(saltedPassword.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length;++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
			System.out.println("Password: " + hash.toString());
			System.out.println("Sex: " + Sex);
			}
		} catch (ClassNotFoundException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
 }
}
