package database.TargetDatabase;

import database.ToolDatabase.DatabaseDAO;

import java.sql.Connection;
import java.sql.Statement;


public class TargetDatabaseDaoImpl extends TargetDatabase implements TargetDatabaseDao {

	@Override
	public void execute(String query) {
		try {
			Connection con = getConnection();
			Statement stm = con.createStatement();
			System.out.print(query);
			stm.executeQuery(query);

		}catch(Exception exc){
			exc.printStackTrace();	
		}
		
	}
}
