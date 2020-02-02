package domain.BusinessRule;

import java.util.List;

import database.TargetDatabase.TargetDatabaseDao;
import database.TargetDatabase.TargetDatabaseDaoImpl;
import database.ToolDatabase.ToolDatabaseDao;
import database.ToolDatabase.ToolDatabaseDaoImpl;
import domain.businessRuleGenerator.BusinessRuleStrategy;
import domain.businessRuleGenerator.AttributeRule.AttributeCompareRule;
import domain.businessRuleGenerator.AttributeRule.AttributeListRule;
import domain.businessRuleGenerator.AttributeRule.AttributeOtherRule;
import domain.businessRuleGenerator.AttributeRule.AttributeRangeRule;
import domain.businessRuleGenerator.EntityRule.EntityOtherRule;
import domain.businessRuleGenerator.InterEntityRule.InterEntityCompareRule;
import domain.businessRuleGenerator.TupleRule.TupleCompareRule;
import domain.businessRuleGenerator.TupleRule.TupleOtherRule;
import domain.businessRuleGenerator.ChangeEventRule.ChangeEventRule;

public class BusinessRule implements BusinessRuleInterface{
	private String id;
	private List<Attribute> attributes;

	private String code;
	private String operator;
	
	public BusinessRule(String id, List<Attribute> attributes, String code,
			String operator) {
		this.id = id;
		this.attributes = attributes;
		this.code = code;
		this.operator = operator;
	}
	
	public String generate() {
		BusinessRuleStrategy businessRule = getBusinessRule();
		String query = businessRule.createBusinessRule();

		ToolDatabaseDao toolDatabase = new ToolDatabaseDaoImpl();
		toolDatabase.setGenerateSqlQuery(Integer.parseInt(id), query);
		toolDatabase.updateStatus(Integer.parseInt(id), "generated");

		return query;
	}
	
	public void setBusinessRule() {
		ToolDatabaseDao toolDatabase = new ToolDatabaseDaoImpl();
		String query = toolDatabase.getQueryById(Integer.parseInt(id));

		TargetDatabaseDao targetDatabase = new TargetDatabaseDaoImpl();
		targetDatabase.execute(query);

		toolDatabase.updateStatus(Integer.parseInt(id), "executed");
	}
	
	public String remove() {
		String query = "DROP TRIGGER " + getTriggerId();
		TargetDatabaseDao targetDatabase = new TargetDatabaseDaoImpl();
		if(targetDatabase.execute(query)) {
			ToolDatabaseDao toolDatabase = new ToolDatabaseDaoImpl();
			toolDatabase.updateStatus(Integer.parseInt(id), "removed");
			return "Rule met id: " + id + " verwijderd";
		}
		else{
			return "Kon rule met id: " + id + " niet verwijderen";
		}
	}

	public  String update(){
		BusinessRuleStrategy businessRule = getBusinessRule();
		String query = businessRule.createBusinessRule();

		ToolDatabaseDao toolDatabase = new ToolDatabaseDaoImpl();
		toolDatabase.setGenerateSqlQuery(Integer.parseInt(id), query);

		remove();

		TargetDatabaseDao targetDatabase = new TargetDatabaseDaoImpl();
		targetDatabase.execute(query);


		toolDatabase.updateStatus(Integer.parseInt(id), "executed");
		return query;
	}

	private String getTriggerId(){
		return code+id;
	}

	private BusinessRuleStrategy getBusinessRule() {
		BusinessRuleStrategy rule = null;
		System.out.println(attributes.get(0).getNaam());
		System.out.println(attributes.get(0).getEntiteit());
		System.out.println(id);
		switch(code) {
		//attribute rules
		case "ARNG":
			rule = new AttributeRangeRule(getTriggerId(),
					attributes.get(0).getEntiteit(),
					attributes.get(0).getValues().get(0),
					attributes.get(0).getValues().get(1),
					attributes.get(0).getNaam());
			break;
		case "ACMP":
			rule = new AttributeCompareRule(getTriggerId(),
					operator, 
					attributes.get(0).getNaam(),
					attributes.get(0).getValues().get(0),
					attributes.get(0).getEntiteit());
			break;
		case "ALIS":
			rule = new AttributeListRule(getTriggerId(),
					attributes.get(0).getEntiteit(),
					attributes.get(0).getNaam(),
					attributes.get(0).getValues());
			break;
		case "AOTH":
			rule = new AttributeOtherRule(getTriggerId(),
					attributes.get(0).getEntiteit(),
					attributes.get(0).getNaam());
			break;
		//tuple rules
		case "TCMP":
			rule = new TupleCompareRule(getTriggerId(),
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
			rule = new InterEntityCompareRule(getTriggerId(),
					operator,
					attributes.get(0).getNaam(),
					attributes.get(1).getNaam(),
					attributes.get(0).getEntiteit(),
					attributes.get(1).getNaam(),
					attributes.get(1).getNaam());
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
