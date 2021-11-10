package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sqlite.SQLiteException;

public class SimplePositioningDB extends SQLiteManager {
	public SimplePositioningDB() {
		super();
	}
	
	public SimplePositioningDB(String url) {
		super(url);
	}
	
	public enum ResultType {
		SUCCESS(1),
		WARING(0),
		FAILURE(-1);
		
		private int code = 0;
		
		private ResultType(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
	}
	
	public List<Map<String, Object>> selectList(Map<String, Object> dataMap) {
		final String query = "";
		
		final Set<String> columnNames = new HashSet<String>();
		final List<Map<String, Object>> selected = new ArrayList<Map<String, Object>>();
		
		Connection conn = ensureConnection();
		PreparedStatement pstmt = null;
		ResultSetMetaData meta = null;
		
		
		try {
			pstmt = conn.prepareStatement(query);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}

		
		return null;
		
	}
	
	public int insertFingerPrintRecord(Map<String, Object> datas) throws SQLException {
		final String sql = "";
		
		Connection conn = ensureConnection();
		PreparedStatement pstmt = null;
		
		int inserted = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// pstmt.setObject(1, pstmt);
			
			pstmt.execute();
			
			inserted = pstmt.getUpdateCount();
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			
			if(conn != null)
				conn.rollback();
			
			inserted = -1;
		} finally {
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		
		return 0;
		
	}
	
	public ResultType excuteSQL(final String SQL) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		
		ResultType result = ResultType.FAILURE;
		
		try {
			conn = ensureConnection();
			stmt = conn.createStatement();
			
			stmt.execute(SQL);
			
			conn.commit();
			
			result = ResultType.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			
			if(conn != null)
				conn.rollback();
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
		}
		
		
		return result;
	}
	
	public boolean checkTable(String tableName) throws SQLException {
		Connection conn = ensureConnection();
		DatabaseMetaData meta = conn.getMetaData();
		
		ResultSet tables = meta.getTables(null, null, tableName, null);
		
		return (tables.next() ? tables.getRow() != 0 : false);
	}
	
	public static final String CREATE_DB_TABLE_QUERY = "";
	public ResultType createTable(String tableName) throws SQLException {
		if(checkTable(tableName))
			return ResultType.WARING;
		
		return excuteSQL(CREATE_DB_TABLE_QUERY);
	}
	
	public static final String DROP_DB_TABLE_QUERY = "DROP TABLE IF EXISTS ";
	public ResultType dropTable(String tableName) throws SQLException {
		if(!checkTable(tableName))
			return ResultType.WARING;
		
		return excuteSQL(DROP_DB_TABLE_QUERY + tableName);
	}
}
