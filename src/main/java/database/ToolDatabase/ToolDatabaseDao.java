package database.ToolDatabase;

import domain.BusinessRule.BusinessRule;

public interface ToolDatabaseDao {
	public BusinessRule getRuleById(int id);


	public void setGenerateSqlQuery(int id, String code);

	public String getQueryById(int id);

	public void updateStatus(int id, String status);
}
