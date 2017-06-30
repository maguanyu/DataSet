package dataset.base;

/**
 * SQL�R�}���h�N���X�ł��B
 */
public class SqlCommand {

	/**
	 * SQL�e�L�X�g
	 */
	private String text;

	/**
	 * �p�����[�^�z��
	 */
	private SqlParameter[] parameters;

	/**
	 * �R���X�g���N�^
	 */
	public SqlCommand() {

	}

	/**
	 * �R���X�g���N�^
	 * 
	 * @param text
	 *            SQL�e�L�X�g
	 * @param parameters
	 *            �p�����[�^�z��
	 */
	public SqlCommand(String text, SqlParameter[] parameters) {
		this.text = text;
		this.parameters = parameters;
	}

	/**
	 * SQL�e�L�X�g���擾����B
	 * 
	 * @return SQL�e�L�X�g
	 */
	public String getText() {
		return text;
	}

	/**
	 * SQL�e�L�X�g��ݒ肷��B
	 * 
	 * @param text
	 *            SQL�e�L�X�g
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * �p�����[�^�z����擾����B
	 * 
	 * @return �p�����[�^�z��
	 */
	public SqlParameter[] getParameters() {
		return parameters;
	}

	/**
	 * �p�����[�^�z���ݒ肷��B
	 * 
	 * @param parameters
	 *            �p�����[�^�z��
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
