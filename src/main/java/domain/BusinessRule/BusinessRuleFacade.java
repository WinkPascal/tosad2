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
	
	public String updateBusinessRule() {
		ToolDatabaseDao dao = new ToolDatabaseDaoImpl();
		BusinessRule businessrule = dao.getRuleById(id);
		return businessrule.update();
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
