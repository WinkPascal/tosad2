package domain.businessRuleGenerator.AttributeRule;

import domain.businessRuleGenerator.BusinessRuleStrategy;

import java.util.List;

public class AttributeOtherRule implements BusinessRuleStrategy {

	private String ruleId;
	private String entiteit;
	private String attribute;


	public AttributeOtherRule(String ruleId, String entiteit, String attribute) {
		this.ruleId = ruleId;
		this.entiteit = entiteit;
		this.attribute = attribute;
	}


	@Override
	public String createBusinessRule() {
		String trigger=
				"CREATE OR REPLACE TRIGGER "+ruleId+ " \n"+
				"	BEFORE insert or update \n"+
				"	ON "+entiteit+
				"   FOR EACH ROW \n "+
				"BEGIN \n" +
				"	IF "+ attribute + "THEN \n" +
				"		Raise_Application_Error (-20343, '"+ attribute + " '); \n" +
				"		ROLLBACK; \n"+
				"	END IF; \n" +
				"END;";
		return trigger;
	}

}
