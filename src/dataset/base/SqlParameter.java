package dataset.base;

import common.enums.FieldTypeEnum;

public class SqlParameter {
	
	/**
	 * �R���X�g���N�^
	 */
	public SqlParameter(FieldTypeEnum type, String stringValue) {
		this.type = type;
		this.stringValue = stringValue;
	}

	/**
	 * �R���X�g���N�^
	 */
	public SqlParameter(FieldTypeEnum type, int intValue) {
		this.type = type;
		this.intValue = intValue;
	}

	/**
	 * �R���X�g���N�^
	 */
	public SqlParameter(FieldTypeEnum type, double doubleValue) {
		this.type = type;
		this.doubleValue = doubleValue;
	}


	/**
     * type
     */
    private FieldTypeEnum type;

    /**
     * stringValue
     */
    private String stringValue;

    /**
     * intValue
     */
    private int intValue;

    /**
     * doubleValue
     */
    private double doubleValue;


    /**
     * type�̎擾
     *
     * @return FieldTypeEnum type
     */
    public FieldTypeEnum getType() {
        return type;
    }

    /**
     * stringValue�̎擾
     *
     * @return String stringValue
     */
    public String getStringValue() {
        return stringValue;
    }

    /**
     * intValue�̎擾
     *
     * @return int intValue
     */
    public int getIntValue() {
        return intValue;
    }

    /**
     * doubleValue�̎擾
     *
     * @return double doubleValue
     */
    public double getDoubleValue() {
        return doubleValue;
    }

    /**
     * type�̐ݒ�
     *
     * @param newType type
     */
    public void setType(FieldTypeEnum newType) {
        this.type = newType;
    }

    /**
     * stringValue�̐ݒ�
     *
     * @param newStringValue stringValue
     */
    public void setStringValue(String newStringValue) {
        this.stringValue = newStringValue;
    }

    /**
     * intValue�̐ݒ�
     *
     * @param newIntValue intValue
     */
    public void setIntValue(int newIntValue) {
        this.intValue = newIntValue;
    }

    /**
     * doubleValue�̐ݒ�
     *
     * @param newDoubleValue doubleValue
     */
    public void setDoubleValue(double newDoubleValue) {
        this.doubleValue = newDoubleValue;
    }
}
