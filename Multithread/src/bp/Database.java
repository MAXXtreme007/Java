package bp;

public class Database implements Runnable{
	private String sqlToExecute;
	private String executeResult;
	
	public String getSqlToExecute() {
		return sqlToExecute;
	}
	
	public void setSqlToExecute(String pSqlToExecute) {
		sqlToExecute = pSqlToExecute;
	}
	
	public void executeSql() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executeResult = "Successfully updated db.";
	}

	@Override
	public void run() {
		executeSql();
	}

	public String getExecuteResult() {
		return executeResult;
	}

	public void setExecuteResult(String pExecuteResult) {
		executeResult = pExecuteResult;
	}
}
