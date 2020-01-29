package database.TargetDatabase;

import java.sql.Connection;
import java.sql.Statement;


public class TargetDatabaseDaoImpl extends TargetDatabase implements TargetDatabaseDao {

	@Override
	public boolean execute(String query) {
		try {
			Connection con = getConnection();
			Statement stm = con.createStatement();
			stm.executeQuery(query);
			return true;

		}catch(Exception exc){
			exc.printStackTrace();
			return false;
		}
		
	}
}
