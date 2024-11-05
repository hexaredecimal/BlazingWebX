package blazing.sql;

/**
 *
 * @author ERC
 */
import java.sql.*;

public class BlazingConnection {
  private Connection conn;

	/**
	 * Wraps an SQL connection for convenience.
	 * @param conn 
	 */
  public BlazingConnection(Connection conn) {
    this.conn = conn;
  }
  
	/**
	 * Preforms a SQL SELECT statement
	 * @param stmt
	 * @return 
	 */
  public QueryFirstPart select(Query.QueryStmt stmt) {
    return new QueryFirstPart("select " + stmt);
  }

	public QueryFirstPart update(String table) {
		return new QueryFirstPart("update " + table); 
	}
	
  
  public class QueryFirstPart {
    private String part; 
		private QueryFirstPart() {}
    private QueryFirstPart(String part) {
      this.part = part; 
    }
    
		/**
		 * Adds a FROM statement to a SQL statement
		 * @param stmt
		 * @return 
		 */
    public QuerySecondPart from(Query.QueryStmt stmt) {
      this.part += " from " + stmt;
      return new QuerySecondPart(this.part);
    }

    public QuerySecondPart set(Query.QueryStmt stmt) {
      this.part += " set " + stmt;
      return new QuerySecondPart(this.part);
		}
  }
  
  public class QuerySecondPart {
    private String part; 
		private QuerySecondPart() {}
    private QuerySecondPart(String part) {
      this.part = part; 
    }
    
		/**
		 * Adds a WHERE to a SQL statement
		 * @param stmt
		 * @return 
		 */
    public QuerySecondPart where(Query.QueryStmt stmt) {
      this.part += " where " + stmt;
      return this;
    }
    
		/**
		 * Finalizes the SQL statement, ready for execution
		 * @return 
		 */
    public QueryResult finish() {
      return new QueryResult(conn, this.part + ";");
    }
  }  
 
}
