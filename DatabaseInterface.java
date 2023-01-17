import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInterface {
	private static Connection con;
	
	public static void DbConnect() 
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", 
									"root", "");			
		
	}
	
	public static void DbDisconnect() 
			throws SQLException {
		con.close();
	}
	
	public static String getAll() throws SQLException {
		String query = "select * from product";
		
		Statement stat = con.createStatement();
		
		ResultSet rs = stat.executeQuery(query);
		
		String output = "";
		output += "\n\n--------------------------------------------------------\n";
		output += "|ID\t"
				+ "|Name\t\t\t|Price\t\t|Quantity\n";
		output += "--------------------------------------------------------\n";
		
		while(rs.next()) {
			output += "|" + rs.getInt("product_id") + 
					"\t|" + rs.getString(2) + 
					"\t|" + rs.getDouble(3)+
					"\t\t|" + rs.getInt(4)+"\n";
		}
		output += "--------------------------------------------------------\n";
		
		return output;
	}

	public static String getById(int id) throws SQLException {
		String query = "select * from product where product_id=?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			output += "\n\n--------------------------------------------------\n";
			output += "|ID\t|Name\t\t\t|Price\t|Quantity\n";
			output += "--------------------------------------------------\n";
			output += "|" + rs.getInt("product_id") + 
					"\t|" + rs.getString(2) + 
					"\t|" + rs.getDouble(3) + 
					"\t|" + rs.getInt(4) + "\t|\n";
			output += "--------------------------------------------------\n";
		}		
		else
			output += "\n\n **** No Record Found **** \n\n";
		
		return output;
	}

	public static String add(int id, String name, double price, int quantity) 
				throws SQLException {
		String query = "insert into product values(?,?,?,?)";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setDouble(3, price);
		ps.setInt(4, quantity);
		
		int i = ps.executeUpdate();
		
		if(i>0)
			output += "Insert Successful";
		else
			output += "Insertion Failed";
		
		return output;
	}

	public static String delete(int id) throws SQLException {
		String query = "delete from product where product_id = ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		int i = ps.executeUpdate();
		
		if(i>0)
			output += "Delete Successful";
		else 
			output += "Delete Failed";
		
		return output;
	}

	public static String updateName(int id, String name) throws SQLException {
		String query = "update product set product_name = ? where product_id = ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, name);
		ps.setInt(2, id);
		
		int i = ps.executeUpdate();
		
		if(i>0)
			output += "Update Successful";
		else 
			output += "Update Failed";
		
		return output;
	}

	public static String updatePrice(int id, double price) throws SQLException {
String query = "update product set product_price = ? where product_id = ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setDouble(1, price);
		ps.setInt(2, id);
		
		int i = ps.executeUpdate();
		
		if(i>0)
			output += "Update Successful";
		else 
			output += "Update Failed";
		
		return output;
	}

	public static String updateQuantity(int id, int quantity) throws SQLException {
String query = "update product set product_quantity = ? where product_id = ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, quantity);
		ps.setInt(2, id);
		
		int i = ps.executeUpdate();
		
		if(i>0)
			output += "Update Successful";
		else 
			output += "Update Failed";
		
		return output;
	}

	public static String getByName(String name) throws SQLException {
		String query = "select * from product where product_name like ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, "%" + name + "%");
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			output += "\n\n----------------------------------------------\n";
			output += "|ID\t|Name\t\t\t|Price\t|Quantity|\n";
			output += "----------------------------------------------\n";
			output += "|" + rs.getInt("product_id") + 
					"\t|" + rs.getString(2) + 
					"\t|" + rs.getDouble(3) + 
					"\t|" + rs.getInt(4) + "\t|\n";
			output += "----------------------------------------------\n";
		}		
		else
			output += "\n\n **** No Record Found **** \n\n";
		
		return output;
	}
}