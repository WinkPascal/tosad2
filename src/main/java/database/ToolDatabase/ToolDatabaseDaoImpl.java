package database.ToolDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.BusinessRule.Attribute;
import domain.BusinessRule.BusinessRule;
public class ToolDatabaseDaoImpl extends DatabaseDAO implements ToolDatabaseDao {

	@Override
	public BusinessRule getRuleById(int id) {
		try {
			Connection con = getConnection();
			PreparedStatement stm = con.prepareStatement(
					"select code, operator from RULE where id = 22");
			System.out.println(stm);
			ResultSet dbResultSet = stm.executeQuery();
			while(dbResultSet.next()) {
				String code = dbResultSet.getString("code");
				String operator = dbResultSet.getString("operator");
				List<Attribute> attributes = getAttributesByRule(id);
				BusinessRule rule = new BusinessRule(Integer.toString(id), attributes, code, operator);
				return rule;
			}
		}catch(Exception exc){
			exc.printStackTrace();
		}

		return null;
	}

	public ArrayList<Attribute> getAttributesByRule(int ruleId) {
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		try {
			Connection myConn = super.getConnection();
			Statement stm = myConn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM ATTRIBUTE WHERE ruleId = "+ruleId);
			while(rs.next()) {
				ArrayList<String> values = new ArrayList<String>();

				int attributeId = rs.getInt("id");
				values = getValuesByAttribute(attributeId);

				String name = rs.getString("name");
				String entiteit = rs.getString("entity");

				attributes.add(new Attribute(attributeId, name, entiteit, values));
			}
		}catch(Exception exc){
			exc.printStackTrace();
		}
		return attributes;
	}

	public ArrayList<String> getValuesByAttribute(int attributeId) {
		ArrayList<String> values = new ArrayList<String>();
		try {
			Connection myConn = super.getConnection();
			Statement stm = myConn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT value FROM VALUE WHERE attributeId = "+attributeId);
			while(rs.next()) {
				values.add(rs.getString(1));
			}
		}catch(Exception exc){
			exc.printStackTrace();
		}
		return values;
	}

	public void setGenerateSqlQuery(int id, String query){
		try {
			Connection con = getConnection();
			PreparedStatement stm = con.prepareStatement("update RULE set SQLCODE = ?" +
					" WHERE id = " + id);
			stm.setString(1, query);

			stm.executeUpdate();
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}

	public String getQueryById(int id){
		try {
			Connection con = getConnection();
			PreparedStatement stm = con.prepareStatement("SELECT SQLCODE FROM RULE " +
					"WHERE id = "+ id);
			ResultSet dbResultSet = stm.executeQuery();
			while(dbResultSet.next()) {
				return dbResultSet.getString("SQLCODE");
			}

		}catch(Exception exc){
			exc.printStackTrace();
		}
		return null;

	}

	@Override
	public void updateStatus(int id, String status) {
		try {
			Connection con = getConnection();
			Statement stm = con.createStatement();
			
			stm.executeQuery("UPDATE rule SET =  '" + status + "' WHERE id = " + id);

		}catch(Exception exc){
			exc.printStackTrace();	
		}
		
	}
}
