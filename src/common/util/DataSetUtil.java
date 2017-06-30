package common.util;

import java.io.File;

import dataset.DataTable;

public abstract class DataSetUtil {
	/**
	 * ��R���N�V�����̔�����s��
	 * 
	 * @param dataTable
	 *            dataTable
	 * @return true:��|false:���
	 */
	public static boolean isEmpty(DataTable dataTable) {
		return null == dataTable || null == dataTable.getDataRows() || 0 == dataTable.getDataRows().size();
	}

	/**
	 * �p�[�X��"/"���[����
	 *
	 * @param path
	 *            �p�[�X
	 * @return ��[�����p�[�X
	 */
	public static String patchPath(String path) {

		String patchPath = path;
		if (path != null && !patchPath.isEmpty() && !patchPath.endsWith(File.separator)) {
			patchPath += File.separator;
		}

		return patchPath;
	}

	/**
	 * �p�X�ƃt�@�C�����ɂ��A�t�@�C���Ώې�������
	 *
	 * @param path
	 *            �p�X
	 * 
	 * @return �t�@�C���Ώ�
	 */
	public static File generateFile(String path) {

		if (isEmpty(path)) {
			throw new RuntimeException("�p�X�܂��̓t�@�C��������ł��B�p�X�F" + path);
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
	 * �t�H���_�̑��݂��ǂ����̔�����s��
	 *
	 * @param path
	 *            �t�H���_�p�[�X
	 * @return true:����|false:�s����
	 */
	public static boolean isDirectory(String path) {

		if (isEmpty(path)) {
			return false;
		}

		File file = new File(path);
		return file.exists() && file.isDirectory();
	}

	/**
	 * �t�H���_�̍쐬���s��
	 *
	 * @param path
	 *            �t�H���_�p�[�X
	 */
	public static void mkdirs(String path) {

		if (isEmpty(path)) {
			throw new RuntimeException("�Ώۃt�H���_���s���ł��B�Ώۃt�H���_�F" + path);
		}
		File file = new File(path);
		if (file.isFile()) {
			RuntimeException fe = new RuntimeException("�Ώۃt�H���_���s���ł��B�Ώۃt�H���_�F" + path);
			throw fe;
		} else if (!file.exists() && !file.mkdirs()) {

			RuntimeException fe = new RuntimeException("�Ώۃt�H���_�̍쐬�����s���܂����B�Ώۃt�H���_�F" + path);

			throw fe;
		}

	}

	/**
	 * �t�@�C���̑��݂��ǂ����̔�����s��
	 *
	 * @param path
	 *            �t�@�C���p�[�X
	 * @return true:����|false:�s����
	 */
	public static boolean isFile(String path) {

		if (isEmpty(path)) {
			return false;
		}

		File file = new File(path);
		return file.exists() && file.isFile();
	}

	/**
	 * �󕶎��񂩂ǂ����`�F�b�N
	 * 
	 * @param text
	 *            �Ώە�����
	 * @return boolean �`�F�b�N����
	 */
	public static boolean isEmpty(String text) {

		return isEmpty(text, false);
	}

	/**
	 * �󕶎��񂩂ǂ����`�F�b�N
	 * 
	 * @param text
	 *            �Ώە�����
	 * @param trimFlg
	 *            true:�g����|false:�g�����Ȃ�
	 * @return boolean �`�F�b�N����
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
