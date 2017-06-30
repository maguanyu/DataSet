package dataset.base;

import common.enums.FieldTypeEnum;

public class SqlParameter {
	
	/**
	 * コンストラクタ
	 */
	public SqlParameter(FieldTypeEnum type, String stringValue) {
		this.type = type;
		this.stringValue = stringValue;
	}

	/**
	 * コンストラクタ
	 */
	public SqlParameter(FieldTypeEnum type, int intValue) {
		this.type = type;
		this.intValue = intValue;
	}

	/**
	 * コンストラクタ
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
     * typeの取得
     *
     * @return FieldTypeEnum type
     */
    public FieldTypeEnum getType() {
        return type;
    }

    /**
     * stringValueの取得
     *
     * @return String stringValue
     */
    public String getStringValue() {
        return stringValue;
    }

    /**
     * intValueの取得
     *
     * @return int intValue
     */
    public int getIntValue() {
        return intValue;
    }

    /**
     * doubleValueの取得
     *
     * @return double doubleValue
     */
    public double getDoubleValue() {
        return doubleValue;
    }

    /**
     * typeの設定
     *
     * @param newType type
     */
    public void setType(FieldTypeEnum newType) {
        this.type = newType;
    }

    /**
     * stringValueの設定
     *
     * @param newStringValue stringValue
     */
    public void setStringValue(String newStringValue) {
        this.stringValue = newStringValue;
    }

    /**
     * intValueの設定
     *
     * @param newIntValue intValue
     */
    public void setIntValue(int newIntValue) {
        this.intValue = newIntValue;
    }

    /**
     * doubleValueの設定
     *
     * @param newDoubleValue doubleValue
     */
    public void setDoubleValue(double newDoubleValue) {
        this.doubleValue = newDoubleValue;
    }
}
