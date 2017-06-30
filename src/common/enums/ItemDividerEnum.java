/**
 * 
 */
package common.enums;

/**
 * 
 * CSV ディバイダー
 * 
 * @author magy
 *
 */
public enum ItemDividerEnum {

	Tab, Comma, Semicolon, Space;

	public static ItemDividerEnum getType(String value) {
		if (value == null) {
			return null;
		}
		String valueL = value.trim().toLowerCase();

		if ("\t".equals(value) || "tab".equals(valueL)) {
			return Tab;
		} else if (",".equals(value) || "comma".equals(valueL)) {
			return Comma;
		} else if (";".equals(value) || "semicolon".equals(valueL)) {
			return Semicolon;
		} else if (" ".equals(value) || "space".equals(valueL)) {
			return Space;
		}
		return null;
	}

	public String toString() {
		switch (this) {
		case Tab:
			return "\t";
		case Comma:
			return ",";
		case Semicolon:
			return ";";
		case Space:
			return "[ ]+";
		}
		return "\t";
	}
}