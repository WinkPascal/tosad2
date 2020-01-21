package domain.BusinessRule;

import java.util.List;

import domain.businessRuleGenerator.BusinessRuleStrategy;
import domain.businessRuleGenerator.AttributeRule.AttributeCompareRule;
import domain.businessRuleGenerator.AttributeRule.AttributeListRule;
import domain.businessRuleGenerator.AttributeRule.AttributeOtherRule;
import domain.businessRuleGenerator.AttributeRule.AttributeRangeRule;
import domain.businessRuleGenerator.EntityRule.EntityOtherRule;
import domain.businessRuleGenerator.InterEntityRule.InterEntityCompareRule;
import domain.businessRuleGenerator.TupleRule.TupleCompareRule;
import domain.businessRuleGenerator.TupleRule.TupleOtherRule;
import domain.businessRuleGeneratorChangeEventRule.ChangeEventRule;
 
public class BusinessRuleFacade implements BusinessRuleFacadeInterface {
	private int id;
	
	public String createNewBusinessRule() {
		System.out.println("=-=================================================");
		BusinessRule businessrule = DAO.getValuesFromById(id);
		return businessrule.generate();
	}
	
	public void removeBusinessRule() {
		BusinessRule businessrule = DAO.getValuesFromById(id);
		String query = "DROP TRIGGER "+ code+id;
		
	}
	
	public void updateBusinessRule() {
		// is eigenlijk gwn hetzelde als set
	}
	
	public void setBusinessRule() {
		BusinessRule businessrule = DAO.getValuesFromById(id);
		businessrule.setBusinessRule();
	}

	public BusinessRuleFacade(int id) {
		this.id = id;
	}
}
