package domain.businessRuleGenerator.AttributeRule;

import domain.businessRuleGenerator.BusinessRuleStrategy;

public class AttributeOtherRule implements BusinessRuleStrategy {

	private String ruleId;
	private String invoerveld;
	private String entiteit;

	public AttributeOtherRule(String ruleId, String invoerveld, String entiteit) {
		this.ruleId = ruleId;
		this.invoerveld = invoerveld;
		this.entiteit = entiteit;
	}


	@Override
	public String createBusinessRule() {
		String trigger=
				"CREATE OR REPLACE TRIGGER "+ruleId+ " \n"+
				"	BEFORE insert or update \n"+
				"	ON "+entiteit+
				"   FOR EACH ROW \n "+
				"BEGIN \n" +
				"	IF "+ invoerveld + "THEN \n" +
				"		Raise_Application_Error (-20343, '"+ invoerveld+ " '); \n" +
				"		ROLLBACK; \n"+
				"	END IF; \n" +
				"END;";
		return trigger;
	}

}
