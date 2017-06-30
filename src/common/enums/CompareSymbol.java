package common.enums;

public enum CompareSymbol {
	gt(">"), lt("<"), eq("="), desc("desc"), asc("asc"),gteq(">="),lteq("<=");

	private String symbol;

	private CompareSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
}
