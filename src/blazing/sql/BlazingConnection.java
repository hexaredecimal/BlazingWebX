package blazing.sql;

/**
 *
 * @author ERC
 */
import java.sql.*;

public class BlazingConnection {
  Connection conn;
  public BlazingConnection(Connection conn) {
    this.conn = conn;
  }
  
  public QueryFirstPart select(Query.QueryStmt stmt) {
    return new QueryFirstPart("select " + stmt);
  }
  
  public class QueryFirstPart {
    private String part; 
    private QueryFirstPart(String part) {
      this.part = part; 
    }
    
    public QuerySecondPart from(Query.QueryStmt stmt) {
      this.part += " from " + stmt;
      return new QuerySecondPart(this.part);
    }
  }
  
  public class QuerySecondPart {
    private String part; 
    private QuerySecondPart(String part) {
      this.part = part; 
    }
    
    public QuerySecondPart where(Query.QueryStmt stmt) {
      this.part += " where " + stmt;
      return this;
    }
    
    public QueryResult finish() {
      return new QueryResult(conn, this.part + ";");
    }
  }  
 
}
