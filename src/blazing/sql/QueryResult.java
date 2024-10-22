package blazing.sql;

/**
 *
 * @author ERC
 */
import blazing.BlazingLog;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueryResult {

	private PreparedStatement stmt;
	private Connection conn;

	public QueryResult(Connection conn, String stmt) {
		this.conn = conn;
		try {
			if (conn == null) {
				throw new SQLException("No db connected");
			}
			this.stmt = this.conn.prepareStatement(stmt);
		} catch (SQLException ex) {
			BlazingLog.panic("SQL: " + ex.getMessage());
		}
	}

	public QueryResult setInt(int index, int value) {
		try {
			this.stmt.setInt(index, value);
		} catch (SQLException ex) {
			BlazingLog.panic("SQL: " + ex.getMessage());
		}
		return this;
	}

	public QueryResult setFloat(int index, float value) {
		try {
			this.stmt.setFloat(index, value);
		} catch (SQLException ex) {
			BlazingLog.panic("SQL: " + ex.getMessage());
		}
		return this;
	}

	public QueryResult setDouble(int index, double value) {
		try {
			this.stmt.setDouble(index, value);
		} catch (SQLException ex) {
			BlazingLog.panic("SQL: " + ex.getMessage());
		}
		return this;
	}

	public QueryResult setString(int index, String value) {
		try {
			this.stmt.setString(index, value);
		} catch (SQLException ex) {
			BlazingLog.panic("SQL: " + ex.getMessage());
		}
		return this;
	}

	public QueryResult setShort(int index, short value) {
		try {
			this.stmt.setShort(index, value);
		} catch (SQLException ex) {
			BlazingLog.panic("SQL: " + ex.getMessage());
		}
		return this;
	}

	public QueryResult setLong(int index, long value) {
		try {
			this.stmt.setLong(index, value);
		} catch (SQLException ex) {
			BlazingLog.panic("SQL: " + ex.getMessage());
		}
		return this;
	}

	public QueryResult setByte(int index, byte value) {
		try {
			this.stmt.setByte(index, value);
		} catch (SQLException ex) {
			BlazingLog.panic("SQL: " + ex.getMessage());
		}
		return this;
	}

	public QueryResult setBytes(int index, byte[] value) {
		try {
			this.stmt.setBytes(index, value);
		} catch (SQLException ex) {
			BlazingLog.panic("SQL: " + ex.getMessage());
		}
		return this;
	}

	public ResultSet toResultSet() {
		try {
			ResultSet rs = this.stmt.executeQuery();
			return rs;
		} catch (SQLException ex) {
			BlazingLog.panic("SQL: " + ex.getMessage());
		}

		return null;
	}

	public boolean execute() {
		try {
			return this.stmt.execute();
		} catch (SQLException ex) {
			BlazingLog.panic("SQL: " + ex.getMessage());
		}
		return false;
	}

	public <T> List<T> mapExec(Class<T> cls) {
		var rs = toResultSet();
		List<T> objs = new ArrayList<>();
		try {
			var fields = cls.getDeclaredFields();
			while (rs.next()) {
				ArrayList<Object> args = new ArrayList<>();
				for (var field : fields) {
					String name = field.getName();
					if (field.isAnnotationPresent(Column.class)) {
						name = field.getAnnotation(Column.class).name();
					}

					int index = rs.findColumn(name);
					Object arg = rs.getObject(index);
					args.add(arg);
				}

				Object[] argArray = args.toArray();
				var ctors = cls.getDeclaredConstructors();
				Constructor<T> matchedCtor = null;

				for (var ctor : ctors) {
					Class<?>[] paramTypes = ctor.getParameterTypes();
					if (paramTypes.length == args.size()) {
						boolean match = true;
						for (int i = 0; i < paramTypes.length; i++) {
							if (!paramTypes[i].isAssignableFrom(argArray[i].getClass())) {
								match = false;
								break;
							}
						}

						if (match) {
							matchedCtor = (Constructor<T>) ctor;
							break;
						}
					}
				}

				if (matchedCtor != null) {
					T instance;
					if (argArray.length == 0) {
						instance = matchedCtor.newInstance(); 
					} else {
						instance = matchedCtor.newInstance(argArray);
					}
					objs.add(instance);
				} else {
					BlazingLog.panic("No matching constructor found for class: " + cls.getName());
				}
			}
			return objs;
		} catch (SQLException ex) {
			BlazingLog.panic("SQL: " + ex.getMessage());
		} catch (InstantiationException ex) {
			BlazingLog.panic("Reflection: " + ex.getMessage());
		} catch (IllegalAccessException ex) {
			BlazingLog.panic("Reflection: " + ex.getMessage());
		} catch (IllegalArgumentException ex) {
			BlazingLog.panic("Reflection: " + ex.getMessage());
		} catch (InvocationTargetException ex) {
			BlazingLog.panic("Reflection: " + ex.getMessage());
		}

		return objs;
	}
}
