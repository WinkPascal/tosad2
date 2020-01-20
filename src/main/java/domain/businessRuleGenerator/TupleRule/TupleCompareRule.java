package domain.businessRuleGenerator.TupleRule;

import domain.businessRuleGenerator.BusinessRuleStrategy;

public class TupleCompareRule implements BusinessRuleStrategy {
	
	private String ruleId;
	private String entiteit;
	private String operator;
	private String attribuut1;
	private String attribuut2;
	
	public TupleCompareRule(String ruleId, String entiteit, String operator, String attribuut1, String attribuut2) {
		this.ruleId = ruleId;
		this.entiteit = entiteit;
		this.operator = operator;
		this.attribuut1 = attribuut1;
		this.attribuut2 = attribuut2;
	}
	
	@Override
	public String createBusinessRule() {
		String trigger =
				"CREATE OR REPLACE trigger" + ruleId + "\n"
					+"AFTER insert or update \n"
					+"ON '"+entiteit+"' \n"
				+"DECLARE \n"
					+"attribuut1 "+entiteit+"."+attribuut1+"%type := :NEW."+attribuut1+"; \n"
					+"attribuut2 "+entiteit+"."+attribuut2+"%type := :NEW."+attribuut2+"; \n"
				+"BEGIN \n"
					+"IF attribuut1 "+operator+" attribuut2 THEN \n"
				       +"Raise_Application_Error (-20343, Attribuut1 || ' mag niet groter zijn dan' || attribuut2); /n"
					   +"ROLLBACK;"
			        +"END IF;"
				+"END" +ruleId;
		return trigger;
	}
}
