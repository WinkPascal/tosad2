package domain.businessRuleGenerator.AttributeRule;

import domain.businessRuleGenerator.BusinessRuleStrategy;
 
public class AttributeCompareRule implements BusinessRuleStrategy{
	
	private String ruleId;
	private String operator;
	private String attribuut;
	private String value;
	private String entiteit;
	
	public AttributeCompareRule(String ruleId, String operator, String attribuut, String value, String entiteit) {
		this.ruleId = ruleId;
		this.operator = operator;
		this.attribuut = attribuut;
		this.value = value;
		this.entiteit = entiteit;
	}
	
	@Override
	public String createBusinessRule() {
		String trigger = 
				"CREATE OR REPLACE TRIGGER ACMP"+ruleId+ " \n"+
					"BEFORE insert, update \n"+
					"ON "+entiteit+" \n "+
				"DECLARE \n" +
					"value varchar2(255) := "+value+"; \n" +
					"attribute"+ entiteit + "."+attribuut+"%type := :NEW."+attribuut+"; \n" +
				"BEGIN \n" +
					"IF attribute "+operator+" value THEN " +
						"Raise_Application_Error (-20343, 'AttributeCompareRule');" +
						"ROLLBACK; "+
					"END IF;" + 
				"END "+ruleId;	
		return trigger;
	}
}
