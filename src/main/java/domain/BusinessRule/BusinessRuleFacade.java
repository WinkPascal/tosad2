package domain.BusinessRule;

import database.ToolDatabase.ToolDatabaseDao;
import database.ToolDatabase.ToolDatabaseDaoImpl;
 
public class BusinessRuleFacade implements BusinessRuleFacadeInterface {
	private int id;
	
	public String createNewBusinessRule() {
		System.out.println("=-=================================================");
		ToolDatabaseDao dao = new ToolDatabaseDaoImpl();
		BusinessRule businessrule = dao.getValuesFromById(id);
		return businessrule.generate();
	}
	
	public void removeBusinessRule() {
		ToolDatabaseDao dao = new ToolDatabaseDaoImpl();
		BusinessRule businessrule = dao.getValuesFromById(id);
		businessrule.remove();		
	}
	
	public void updateBusinessRule() {
		// is eigenlijk gwn hetzelde als set
	}
	
	public void setBusinessRule() {
		ToolDatabaseDao dao = new ToolDatabaseDaoImpl();
		BusinessRule businessrule = dao.getValuesFromById(id);
		businessrule.setBusinessRule();
	}

	public BusinessRuleFacade(int id) {
		this.id = id;
	}
}
