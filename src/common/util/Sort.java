package dataset;

public abstract class Sort {
    public static <T extends Comparable<T>> void sort(T[] list) {

        for (int i = 0; i < list.length - 1; i++) {
            for (int j = 0; j < list.length - 1 - i; j++) {

                if (list[j].compareTo(list[j + 1]) > 0) {
                    T temp = list[j + 1];
                    list[j + 1] = list[j];
                    list[j] = temp;
                }

            }

        }

    }

    public static <T extends Comparable<T>> void quickSort(T[] list) {

        for (int i = 0; i < list.length - 1; i++) {
            for (int j = i + 1; j < list.length; j++) {

                if (list[i].compareTo(list[j]) > 0) {
                    T temp = list[j];
                    list[j] = list[i];
                    list[i] = temp;
                }

            }

        }

    }

    public static <T extends Comparable<T>> void insertSort(T[] list) {

        for (int i = 1; i < list.length; i++) {
            for (int j = i; j > 0; j--) {

                if (list[j].compareTo(list[j - 1]) < 0) {
                    T temp = list[j - 1];
                    list[j - 1] = list[j];
                    list[j] = temp;
                }

            }

        }

    }
}
