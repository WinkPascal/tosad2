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
				"CREATE OR REPLACE trigger " + ruleId + "\n"
				+"	AFTER insert or update \n"
				+"	ON "+entiteit+" FOR EACH ROW \n"
				+"DECLARE \n"
				+"	minLimit int := "+minLimit+"; \n"
				+"	maxLimit int := "+maxLimit+"; \n"
				+"	custumAttribute "+entiteit+"."+attribuut+"%type:= :NEW."+attribuut+"; \n"
				+"BEGIN \n"
				+ "	if custumAttribute >  maxLimit  then \n"
				+ "		Raise_Application_Error (-20343,'" + attribuut+ "  has a greater value then " + maxLimit+ "' ); \n"
				+ "		ROLLBACK; \n"
				+ "	END IF; \n"
				+ "	if custumAttribute < minLimit then \n "
				+ "  	Raise_Application_Error (-20343, '"+  attribuut+ "  has a lower value then "+ minLimit+ "' ); \n "
		     	+ "		ROLLBACK; \n"
				+ "	END IF; \n"
				+ "END;";
		return trigger;
	}
}
