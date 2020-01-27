package database.ToolDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.TargetDatabase.DatabaseDAO;
import domain.BusinessRule.Attribute;
import domain.BusinessRule.BusinessRule;
public class ToolDatabaseDaoImpl extends DatabaseDAO implements ToolDatabaseDao {

	@Override
	public BusinessRule getRuleById(int id) {
	/*	List<String> values = new ArrayList<String>();
		values.add("1");
		values.add("6");
		List<Attribute> attributen = new ArrayList<Attribute>();
		Attribute attribute = new Attribute("attribuut1", "entiteit1");
		Attribute attribute1 = new Attribute("attribuut2", "entiteit1");
		attributen.add(attribute);
		attributen.add(attribute1);

		return new BusinessRule("1",attributen, values, "TCMP", "<");*/

		try {
			Connection con = getConnection();
			PreparedStatement stm = con.prepareStatement(
					"select r.code, r.operator from RULE r join type t on r.typeid = t.id where r.id = 22");
			System.out.println(stm);
			ResultSet dbResultSet = stm.executeQuery();
			while(dbResultSet.next()) {
				String code = dbResultSet.getString("code");
				String operator = dbResultSet.getString("operator");
				List<Attribute> attributes = getAttributesByRule(id);
				List<String> values = getValuesByRule(id);
				BusinessRule rule = new BusinessRule(Integer.toString(id), attributes, values, code, operator);
				return rule;
			}
		}catch(Exception exc){
			exc.printStackTrace();
		}

		return null;
	}
	private List<String> getValuesByRule(int id) {
		 List<String> values = new ArrayList<String>();
		try {
			Connection con = getConnection();
			PreparedStatement stm = con.prepareStatement(
					"select value "
					+ "from value "
					+ "where ruleId = "+id);
			ResultSet dbResultSet = stm.executeQuery();
			while(dbResultSet.next()) {
				values.add(dbResultSet.getString("value"));
			}
		}catch(Exception exc){
			exc.printStackTrace();
		}		return values;
	}
	
	private List<Attribute> getAttributesByRule(int id) {
		 List<Attribute> attributes = new ArrayList<Attribute>();
		try {
			Connection con = getConnection();
			PreparedStatement stm = con.prepareStatement(
					"select a.name, a.value "
					+ "from attribute a "
					+ "where a.ruleId = "+id);
			ResultSet dbResultSet = stm.executeQuery();
			while(dbResultSet.next()) {
				String name = dbResultSet.getString("name");
				String entiteit = dbResultSet.getString("entiteit");
				
				attributes.add(new Attribute(name, entiteit));
			}
		}catch(Exception exc){
			exc.printStackTrace();
		}
		 return attributes;
	}
	public void setGenerateSqlQuery(int id, String code){
		try {
			Connection con = getConnection();
			Statement stm = con.createStatement();

			stm.executeQuery("INSERT INTO type (id, code) " +
					"values(" +id + ", " +code+ ");");
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}

	public String getQueryById(int id){
		try {
			Connection con = getConnection();
			PreparedStatement stm = con.prepareStatement("SELECT code FROM type " +
					"WHERE id= "+ id);
			ResultSet dbResultSet = stm.executeQuery();
			while(dbResultSet.next()) {
				String code = dbResultSet.getString("code");
				return code;
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
