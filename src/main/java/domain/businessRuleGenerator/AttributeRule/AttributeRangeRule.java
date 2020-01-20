package domain.businessRuleGenerator.AttributeRule;

import domain.businessRuleGenerator.BusinessRuleStrategy;
 
public class AttributeRangeRule implements BusinessRuleStrategy{

	private String ruleId;
	private String entiteit;
	private String minLimit;
	private String maxLimit;
	private String attribuut;
	
	public AttributeRangeRule(String ruleId, String entiteit, String minLimit, String maxLimit, String attribuut) {
		this.ruleId = ruleId;
		this.entiteit = entiteit;
		this.minLimit = minLimit;
		this.maxLimit = maxLimit;
		this.attribuut = attribuut;
	}

	@Override
	public String createBusinessRule() {
		String trigger =
				"CREATE OR REPLACE trigger" + ruleId + "\n"
					+"AFTER insert or update \n"
					+"ON '"+entiteit+"' \n"
				+"DECLARE \n"
					+"minLimit int := "+minLimit+"; \n"
					+"maxLimit int := "+maxLimit+"; \n"
					+"custumAttribute"+entiteit+"."+attribuut+"%type:= :NEW."+attribuut+"; \n"
				+"BEGIN \n"
				+ "if custumAttribute > maxLimit then \n"
					+ "Raise_Application_Error (-20343, 'ARNG te groot');"
					+ "ROLLBACK; "
				+ "END IF; "
				+ "if custumAttribute < minLimit then \n "
					+ "Raise_Application_Error (-20343, 'tARNG te klein .'); \n "
					+ "ROLLBACK; \n"
				+ "END IF; \n"
				+ "END " + ruleId;
		return trigger;
	}



}
