
package blazing.sql;

/**
 *
 * @author ERC
 */
public class Query {
  public static final QueryStmt All = new QueryStmt("*");
  
  public static QueryStmt List(String... items) {
    StringBuilder sb = new StringBuilder();
    int size = items.length;
    for (int i = 0; i < size; i++) {
      String top = items[i];
      sb
        .append(top)
        .append(i < size - 1 ? "," : "");
    }
    return new QueryStmt(sb.toString());
  }
  
  public static QueryStmt Cond(String cond, String ... items) {
    int size = items.length; 
    if (size != 2) {
      System.out.println("To many parameters passed to binary expression with operator: " + cond);
      return null;
    }
    String left = items[0]; 
    String right = items[1];
    return new QueryStmt(String.format("%s %s %s",left, cond, right));
  }
  
  public static class QueryStmt {
    private String result; 
    public QueryStmt(String stmt) {
      this.result = stmt;
    }
    @Override
    public String toString() {
      return this.result; 
    }
  }
}
