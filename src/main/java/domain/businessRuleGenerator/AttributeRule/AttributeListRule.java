package domain.businessRuleGenerator.AttributeRule;


import java.util.List; 

import domain.businessRuleGenerator.BusinessRuleStrategy;

public class AttributeListRule implements BusinessRuleStrategy {

	private String ruleId;
	private String entiteit;
	private String attribute;
	private List<String> values;

	
	public AttributeListRule(String ruleId, String entiteit, String attribute, List<String> values) {
		this.ruleId = ruleId;
		this.entiteit = entiteit;
		this.attribute = attribute;
		this.values = values;
	}

	@Override
	public String createBusinessRule() {
		String querie = 
				"CREATE OR REPLACE TRIGGER "+ruleId+ " \n"
					+ "AFTER INSERT, UPDATE \n"
					+ "ON "+entiteit+" \n"
				+ "DECLARE \n "
				
					+ "attribute " +entiteit+"."+attribute+"%type := :NEW."+attribute+" \n"
				+ "BEGIN \n"
					+ "IF attribute NOT IN "+getList()+ " THEN \n"
						+ "Raise_Application_Error(-20343, 'deze is fout G');"
						+ "ROLLBACK \n"
					+ "END IF;"
				+ "END "+ruleId;
		return querie;
	}


	private String getList() {
		String list = "(";
		for(String value : values) {
			list = list + "'"+value+"', ";
		}
		return list.substring(0, list.length() - 2) + ")";
	}
}
