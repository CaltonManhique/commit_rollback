package commit_and_rollback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CommitAndRollback {

	private static final String url = "jdbc:mysql://localhost:3306/mydb";
	private static final String user = "root";
	private static final String password = "1234";
	 
	private static Connection connection;

	public static void main(String[] args) throws SQLException {

		try {
			connection = DriverManager.getConnection(url, user, password);

			// Change auto commit status
			connection.setAutoCommit(false);

			// Execute update query
			updateQuery();

			// commit
			connection.commit();
//			System.out.println("commit successful");
			
		} catch (Exception e) {
			try {
				// rollback
				connection.rollback();
				System.out.println("rolling back");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	private static void updateQuery() throws SQLException {
		String sql = "INSERT INTO Employees(employee_id,name, department_id) values (3,'Alice', 2)";

		try (Statement statement = connection.createStatement()) {
			statement.execute(sql);

		} catch (SQLException e) {
			throw e;
		}
	}

	
}
