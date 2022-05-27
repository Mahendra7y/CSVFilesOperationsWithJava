
public class CommonUtil {
	// Check if String with spaces is Empty or Null
	public static boolean checkIsNullOrEmpty(String str) {

		if (str != null && !str.trim().isEmpty())
			return true;
		else
			return false;
	}
}
