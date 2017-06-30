package dataset.base;

/**
 * SQLコマンドクラスです。
 */
public class SqlCommand {

	/**
	 * SQLテキスト
	 */
	private String text;

	/**
	 * パラメータ配列
	 */
	private SqlParameter[] parameters;

	/**
	 * コンストラクタ
	 */
	public SqlCommand() {

	}

	/**
	 * コンストラクタ
	 * 
	 * @param text
	 *            SQLテキスト
	 * @param parameters
	 *            パラメータ配列
	 */
	public SqlCommand(String text, SqlParameter[] parameters) {
		this.text = text;
		this.parameters = parameters;
	}

	/**
	 * SQLテキストを取得する。
	 * 
	 * @return SQLテキスト
	 */
	public String getText() {
		return text;
	}

	/**
	 * SQLテキストを設定する。
	 * 
	 * @param text
	 *            SQLテキスト
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * パラメータ配列を取得する。
	 * 
	 * @return パラメータ配列
	 */
	public SqlParameter[] getParameters() {
		return parameters;
	}

	/**
	 * パラメータ配列を設定する。
	 * 
	 * @param parameters
	 *            パラメータ配列
	 */
	public void setParameters(SqlParameter[] parameters) {
		this.parameters = parameters;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append(text);
		if (parameters != null && parameters.length > 0) {
			buf.append(" [");
			int index = 0;
			for (Object param : parameters) {
				if (index > 0) {
					buf.append(", ");
				}
				buf.append(param);
				index++;
			}
			buf.append("]");
		}
		return buf.toString();
	}
}
