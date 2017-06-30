package common.enums;

 
public enum FieldTypeEnum {

	Integer, Double, String;

	public static FieldTypeEnum getType(String value) {
		if (value == null) {
			return String;
		}
		String valueL = value.trim().toLowerCase();

		if (valueL.startsWith("int") || valueL.startsWith("integer")) {
			return Integer;
		} else if (valueL.startsWith("real") || valueL.startsWith("float") || valueL.startsWith("double")) {
			return Double;
		}
		return String;
	}
}