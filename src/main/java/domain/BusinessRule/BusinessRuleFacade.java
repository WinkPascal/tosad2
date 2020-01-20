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
 
public class BusinessRuleFacade implements BusinessRule {
	private String id;
	private List<Attribute> attributes;
	private List<String> values;
	private Error error;
	
	private String code;
	private String operator;
	private String catagory;
	
	
	
	public BusinessRuleFacade(String id, List<Attribute> attributes, List<String> values, String code,
			String operator) {
		this.id = id;
		this.attributes = attributes;
		this.values = values;
		this.code = code;
		this.operator = operator;
	}
	
	

	public void createNewBusinessRule() {
		//DAO.getValuesFromById(id)
		BusinessRuleStrategy businessRule= getBusinessRule();
		String query = businessRule.createBusinessRule();
		System.out.println(query);
		//Dao.executeQuery(query);
	}
	
	public void removeBusinessRule() {
		
	}
	
	public void updateBusinessRule() {
		
	}

	private BusinessRuleStrategy getBusinessRule() {
		BusinessRuleStrategy rule = null;
		switch(code) {
		//attribute rules
		case "ARNG":
			rule = new AttributeRangeRule("ARNG"+id, 					
					attributes.get(0).getEntiteit(),
					values.get(0),
					values.get(1),
					attributes.get(0).getNaam());
			break;
		case "ACMP":
			rule = new AttributeCompareRule("ACMP"+id, 
					operator, 
					attributes.get(0).getNaam(),
					values.get(0),
					attributes.get(0).getEntiteit());
			break;
		case "ALIS":
			rule = new AttributeListRule("ALIS"+id, 
					attributes.get(0).getEntiteit(),
					attributes.get(0).getNaam(),
					values);
			break;
		case "AOTH":
			rule = new AttributeOtherRule();
			break;
		//tuple rules
		case "TCMP":
			rule = new TupleCompareRule("TCMP"+id,
					attributes.get(0).getEntiteit(),
					operator,
					attributes.get(0).getNaam(),
					attributes.get(1).getNaam());
			break;
		case "TOTH":
			rule = new TupleOtherRule();
			break;
		// inter entity rule
		case "ICMP":
			rule = new InterEntityCompareRule("ICMP"+id,
					operator,
					attributes.get(0).getNaam(),
					attributes.get(1).getNaam(),
					attributes.get(0).getEntiteit(),
					attributes.get(2).getNaam(),
					attributes.get(3).getNaam());
			break;
		//entity rule
		case "EOTH":
			rule = new EntityOtherRule();
			break;
		//mofiy rule
		case "MODI":
			rule = new ChangeEventRule();
			break;		
		}
		return rule;
	}
}
