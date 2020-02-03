class MergeSortHelper {
    public static void main(String[] args) {
        int[] array = {0,8,1,2,7,9,3,4};
        sort(array);
        for (int i : array) {
            System.out.println(i);
        }
    }

    public static void sort(int[] array) {
        mergeSort(array, 0, array.length - 1, new int[array.length]);
    }

    public static void mergeSort(int[] array, int start, int end, int[] temp) {
        //一直分割到两边都只有一个元素
        if (start < end) {
            int mid = start + (end - start) / 2;

            mergeSort(array, start, mid, temp);
            mergeSort(array, mid + 1, end, temp);
            merge(array, start, mid, end, temp);
        }
    }

    private static void merge(int[] array, int start, int mid, int end, int[] temp) {
        int l = start, r = mid + 1;
        int p = 0;
        //先对两边进行比较merge
        while (l <= mid && r <= end) {
            if (array[l] < array[r]) {
                temp[p++] = array[l++];
            } else {
                temp[p++] = array[r++];
            }
        }

        //如果有一遍还剩元素，那么就直接把剩下的元素append到temp中
        while (l <= mid) {
            temp[p++] = array[l++];
        }

        while (r <= end) {
            temp[p++] = array[r++];
        }

        p = 0;

        //将temp拷贝回原来数组
        while (start <= end) {
            array[start++] = temp[p++];
        }
    }
}