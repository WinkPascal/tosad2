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
		try {
			Connection con = getConnection();
			PreparedStatement stm = con.prepareStatement(
					"select t.type, r.code, r.operator "
					+ "from rule r join type t on r.typeId = t.id"
					+ "where r.id = "+id);
			
			ResultSet dbResultSet = stm.executeQuery();
			while(dbResultSet.next()) {
				String type = dbResultSet.getString("type");
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

	@Override
	public void updateStatus(int id, String status) {
		try {
			Connection con = getConnection();
			Statement stm = con.createStatement();
			
			//String status = rule.getStatus();
			//int id=  rule.getId();
			
			stm.executeQuery("UPDATE rule SET =  '" + status + "' WHERE id = " + id); 
		}catch(Exception exc){
			exc.printStackTrace();	
		}
		
	}


}
