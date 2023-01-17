import java.sql.SQLException;

public class StarterApp {
	public static void main(String[] args) {
		try {
			UIInterface.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}