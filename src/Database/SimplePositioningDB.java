package Database;

import java.sql.*;

public class SimplePositioningDB {
	public static SimplePositioningDB db;
	
	public static SimplePositioningDB getInstance() {
		if(db == null)
			db = new SimplePositioningDB();
		
		return db;
	}
	
	

}
