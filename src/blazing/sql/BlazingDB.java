package blazing.sql;

import blazing.BlazingLog;
import java.sql.*;

/**
 *
 * @author ERC
 */
public class BlazingDB {

  public static BlazingConnection connect(String driver, String db_path) {
    try {
      String path = String.format("jdbc:%s:%s", driver, db_path);
      return new BlazingConnection(DriverManager.getConnection(path));
    } catch (SQLException ex) {
    	BlazingLog.panic("SQL: " + ex.getMessage() + " " + ex.getSQLState());
			return null;
		}
  }

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
