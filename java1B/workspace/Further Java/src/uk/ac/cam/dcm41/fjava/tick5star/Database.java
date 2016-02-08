package uk.ac.cam.dcm41.fjava.tick5star;

import java.sql.Statement;

import uk.ac.cam.cl.fjava.messages.RelayMessage;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private final Connection connection;
	private static final int RECENT_MSG_LIMIT = 10;

	public Database(final String databasePath) throws SQLException, ClassNotFoundException {
		Class.forName("org.hsqldb.jdbcDriver");
		connection = DriverManager.getConnection("jdbc:hsqldb:file:"+databasePath, "SA", "");
		connection.setAutoCommit(false);
	
		Statement delayStmt = connection.createStatement();
		try {
			delayStmt.execute("SET WRITE_DELAY FALSE");
		} // Always update data on disk
		finally {
			delayStmt.close();
		}

		Statement createMsgs = connection.createStatement();
		Statement createStats = connection.createStatement();
		Statement totalMsgs = connection.createStatement();
		Statement totalLogins = connection.createStatement();
		try {
			createMsgs.execute("CREATE TABLE messages(nick VARCHAR(255) NOT NULL, message VARCHAR(4096) NOT NULL, timeposted BIGINT NOT NULL)");
			createStats.execute("CREATE TABLE statistics(key VARCHAR(255), value INT)");
			totalMsgs.executeQuery("INSERT INTO statistics(key,value) VALUES ('Total messages', 0)");
			totalLogins.executeQuery("INSERT INTO statistics(key,value) VALUES ('Total logins', 0)");
		} catch (SQLException e) {
			System.out.println("Warning: Database table \"messages\" already exists.");
		} finally {
			createMsgs.close();
			createStats.close();
			totalMsgs.close();
			totalLogins.close();
		}
		connection.commit();
	}

	public void close() throws SQLException {
		connection.close();
	}

	public void incrementLogins() throws SQLException {
		final PreparedStatement incrLogs =
				connection.prepareStatement("UPDATE statistics SET value = value + 1 WHERE key = 'Total logins'");
		incrLogs.executeUpdate();
		connection.commit();
	}

	public void addMessage(final RelayMessage m) throws SQLException {
		final PreparedStatement insertMsg =
			connection.prepareStatement("INSERT INTO MESSAGES(nick,message,timeposted) VALUES (?,?,?)");
		final PreparedStatement updateTotalMsgs =
				connection.prepareStatement("UPDATE statistics SET value = value + 1 WHERE key = 'Total messages'");
		try {
			insertMsg.setString(1, m.getFrom());
			insertMsg.setString(2, m.getMessage());
			insertMsg.setDouble(3, m.getCreationTime().getTime());
			synchronized (this) {
				insertMsg.executeUpdate();
				updateTotalMsgs.executeUpdate();
			}
		} finally {
			insertMsg.close();
			updateTotalMsgs.close();
		}

		connection.commit();
	}

	public List<RelayMessage> getRecent() throws SQLException {
		final List<RelayMessage> result = new LinkedList<>();
		final PreparedStatement recentMsgs = connection.prepareStatement(
			"SELECT nick,message,timeposted FROM messages ORDER BY timeposted DESC LIMIT 10");
		try {
			final ResultSet rs = recentMsgs.executeQuery();
			try {
				while (rs.next()) {
					result.add(new RelayMessage(rs.getString(1), rs.getString(2), new Date(rs.getLong(3))));
				}
			} finally {
				rs.close();
			}
		} finally {
			recentMsgs.close();
		}
		
		return result;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		if (args.length != 1) {
			System.err.println("Usage: java uk.ac.cam.dcm41.fjava.tick5.Database <database name>");
			return;
		}

		Class.forName("org.hsqldb.jdbcDriver");

		Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:" + args[0], "SA", "");

		connection.setAutoCommit(false);

		Statement delayStmt = connection.createStatement();
		try {
			delayStmt.execute("SET WRITE_DELAY FALSE");
		} // Always update data on disk
		finally {
			delayStmt.close();
		}

		Statement sqlStmt = connection.createStatement();
		try {
			sqlStmt.execute("CREATE TABLE messages(nick VARCHAR(255) NOT NULL,"
					+ "message VARCHAR(4096) NOT NULL,timeposted BIGINT NOT NULL)");
		} catch (SQLException e) {
			System.out.println("Warning: Database table \"messages\" already exists.");
		} finally {
			sqlStmt.close();
		}

		String stmt = "INSERT INTO MESSAGES(nick,message,timeposted) VALUES (?,?,?)";
		PreparedStatement insertMessage = connection.prepareStatement(stmt);
		try {
			insertMessage.setString(1, "Alastair"); // set value of first "?" to
													// "Alastair"
			insertMessage.setString(2, "Hello, Andy");
			insertMessage.setLong(3, System.currentTimeMillis());
			insertMessage.executeUpdate();
		} finally { // Notice use of finally clause here to finish statement
			insertMessage.close();
		}

		connection.commit();

		stmt = "SELECT nick,message,timeposted FROM messages " + "ORDER BY timeposted DESC LIMIT " + RECENT_MSG_LIMIT;
		PreparedStatement recentMessages = connection.prepareStatement(stmt);
		try {
			ResultSet rs = recentMessages.executeQuery();
			try {
				while (rs.next())
					System.out.println(rs.getString(1) + ": " + rs.getString(2) + " [" + rs.getLong(3) + "]");
			} finally {
				rs.close();
			}
		} finally {
			recentMessages.close();
		}

		connection.close();
	}
}
