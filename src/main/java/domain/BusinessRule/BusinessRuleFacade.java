package domain.BusinessRule;

import database.ToolDatabase.ToolDatabaseDao;
import database.ToolDatabase.ToolDatabaseDaoImpl;
 
public class BusinessRuleFacade implements BusinessRuleFacadeInterface {
	private int id;
	
	public String createNewBusinessRule() {
		ToolDatabaseDao dao = new ToolDatabaseDaoImpl();
		BusinessRule businessrule = dao.getRuleById(id);
		return businessrule.generate();
	}
	
	public String removeBusinessRule() {
		ToolDatabaseDao dao = new ToolDatabaseDaoImpl();
		BusinessRule businessrule = dao.getRuleById(id);
		return businessrule.remove();
	}
	
	public void updateBusinessRule() {
		// is eigenlijk gwn hetzelde als set
	}
	
	public void setBusinessRule() {
		ToolDatabaseDao dao = new ToolDatabaseDaoImpl();
		BusinessRule businessrule = dao.getRuleById(id);
		businessrule.setBusinessRule();
	}

	public BusinessRuleFacade(int id) {
		this.id = id;
	}
}
