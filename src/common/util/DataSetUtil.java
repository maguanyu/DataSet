package common.util;

import java.io.File;

import dataset.DataTable;

public abstract class DataSetUtil {
	/**
	 * 空コレクションの判定を行う
	 * 
	 * @param dataTable
	 *            dataTable
	 * @return true:空|false:非空
	 */
	public static boolean isEmpty(DataTable dataTable) {
		return null == dataTable || null == dataTable.getDataRows() || 0 == dataTable.getDataRows().size();
	}

	/**
	 * パースの"/"を補充する
	 *
	 * @param path
	 *            パース
	 * @return 補充したパース
	 */
	public static String patchPath(String path) {

		String patchPath = path;
		if (path != null && !patchPath.isEmpty() && !patchPath.endsWith(File.separator)) {
			patchPath += File.separator;
		}

		return patchPath;
	}

	/**
	 * パスとファイル名により、ファイル対象生成する
	 *
	 * @param path
	 *            パス
	 * 
	 * @return ファイル対象
	 */
	public static File generateFile(String path) {

		if (isEmpty(path)) {
			throw new RuntimeException("パスまたはファイル名が空です。パス：" + path);
		}
		int pathIndex = path.lastIndexOf(File.separator);
		String newPath = path.substring(0, pathIndex);
		if (!newPath.endsWith(File.separator)) {
			newPath = newPath + File.separator;
		}
		if (!isDirectory(newPath)) {
			mkdirs(newPath);
		}

		return new File(path);

	}

	/**
	 * フォルダの存在かどうかの判定を行う
	 *
	 * @param path
	 *            フォルダパース
	 * @return true:存在|false:不存在
	 */
	public static boolean isDirectory(String path) {

		if (isEmpty(path)) {
			return false;
		}

		File file = new File(path);
		return file.exists() && file.isDirectory();
	}

	/**
	 * フォルダの作成を行う
	 *
	 * @param path
	 *            フォルダパース
	 */
	public static void mkdirs(String path) {

		if (isEmpty(path)) {
			throw new RuntimeException("対象フォルダが不正です。対象フォルダ：" + path);
		}
		File file = new File(path);
		if (file.isFile()) {
			RuntimeException fe = new RuntimeException("対象フォルダが不正です。対象フォルダ：" + path);
			throw fe;
		} else if (!file.exists() && !file.mkdirs()) {

			RuntimeException fe = new RuntimeException("対象フォルダの作成が失敗しました。対象フォルダ：" + path);

			throw fe;
		}

	}

	/**
	 * ファイルの存在かどうかの判定を行う
	 *
	 * @param path
	 *            ファイルパース
	 * @return true:存在|false:不存在
	 */
	public static boolean isFile(String path) {

		if (isEmpty(path)) {
			return false;
		}

		File file = new File(path);
		return file.exists() && file.isFile();
	}

	/**
	 * 空文字列かどうかチェック
	 * 
	 * @param text
	 *            対象文字列
	 * @return boolean チェック結果
	 */
	public static boolean isEmpty(String text) {

		return isEmpty(text, false);
	}

	/**
	 * 空文字列かどうかチェック
	 * 
	 * @param text
	 *            対象文字列
	 * @param trimFlg
	 *            true:トリム|false:トリムなし
	 * @return boolean チェック結果
	 */
	public static boolean isEmpty(String text, boolean trimFlg) {

		if (text == null) {
			return true;
		}

		String value = text;
		if (trimFlg) {
			value = text.trim();
		}

		return value.length() == 0;
	}
}
