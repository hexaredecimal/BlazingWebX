package blazing.sql;

import blazing.BlazingLog;
import java.sql.*;

/**
 *
 * @author ERC
 */
public class BlazingDB {
	private BlazingDB() {}

	/**
	 * Creates a SQL connection from the driver and a SQL database path
	 * @param driver
	 * @param db_path
	 * @return 
	 */
  public static BlazingConnection connect(String driver, String db_path) {
    try {
      String path = String.format("jdbc:%s:%s", driver, db_path);
      return new BlazingConnection(DriverManager.getConnection(path));
    } catch (SQLException ex) {
    	BlazingLog.panic("SQL: " + ex.getMessage() + " " + ex.getSQLState());
			return null;
		}
  }

	/**
	 * Creates a SQL connection from the driver and SQL database path, protected by username and password.
	 * @param driver
	 * @param db_path
	 * @param username
	 * @param passwd
	 * @return 
	 */
  public static BlazingConnection connect(String driver, String db_path, String username, String passwd) {
    try {
      String path = String.format("jdbc:%s:%s", driver, db_path);
      return new BlazingConnection(DriverManager.getConnection(path, username, passwd));
    } catch (SQLException ex) {
    	BlazingLog.panic("SQL: " + ex.getMessage() + " " + ex.getSQLState());
      return null; // Should be unreachable
    }
  }
}
