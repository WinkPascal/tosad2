package domain.businessRuleGenerator.InterEntityRule;

import domain.businessRuleGenerator.BusinessRuleStrategy;

public class InterEntityCompareRule implements BusinessRuleStrategy {

	private String ruleId;
	private String operator;
	private String attribuut1;
	private String attribute1ForeignKey;
	private String entiteit;
	private String entiteit1;

	private String compareAttribute;
	private String compareAttributePrimaryKey;
	
	public InterEntityCompareRule(String ruleId, String operator, String attribuut1, String attribute1ForeignKey,
			String entiteit, String compareAttribute, String compareAttributePrimaryKey, String entiteit1) {
		this.ruleId = ruleId;
		this.operator = operator;
		this.attribuut1 = attribuut1;
		this.attribute1ForeignKey = attribute1ForeignKey;
		this.entiteit = entiteit;
		this.entiteit1 = entiteit1;
		this.compareAttribute = compareAttribute;
		this.compareAttributePrimaryKey = compareAttributePrimaryKey;
	}

	@Override
	public String createBusinessRule() {
		String trigger =
			"CREATE OR REPLACE trigger " + ruleId + "\n"
			+"	BEFORE insert or update \n"
			+"	ON "+entiteit+" \n"
			+"	FOR EACH ROW \n"
			+"DECLARE \n"
			+"	newAttribute "+entiteit+"."+attribuut1+"%type := :NEW."+attribuut1+"; \n"
			+"	newAttributeForeignKey "+entiteit1+"."+attribute1ForeignKey+"%type := :NEW."+attribute1ForeignKey+"; \n"
			+"	compareAttribute int; \n"
			+ "BEGIN \n"
			+ "	select "+compareAttribute+" INTO compareAttribute \n"
			+ "	from "+entiteit+" \n"
			+ "	where "+compareAttributePrimaryKey+" = newAttributeForeignKey; \n"
			+ "	IF  newAttribute "+operator+" compareAttribute THEN \n"
			+ "		Raise_Application_Error (-20343,' newAttribute can not " + operator + " then  compareAttribute' ); \n"
			+ "		ROLLBACK; \n"
			+ "	END IF; \n"
			+ "END;";
						
		return trigger;
	}
}
