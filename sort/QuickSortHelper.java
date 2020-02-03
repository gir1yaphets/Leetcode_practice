class QuickSortHelper {
    public static void main(String[] args) {
        int[] array = {0,8,1,2,7,9,3,4};
        sort(array);
        for (int i : array) {
            System.out.println(i);
        }
    }

    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int start, int end) {
        if (start >= end) return;
        
        int l = start, r = end;
        int pivot = array[l];

        while (l < r) {
            while (l < r && array[r] > pivot) {
                r--;
            }
            
            while (l < r && array[l] <= pivot) {
                l++;
            }

            if (l < r) {
                int temp = array[l];
                array[l] = array[r];
                array[r] = temp;
            }
        }

        array[start] = array[l];
        array[l] = pivot;

        quickSort(array, start, l - 1);
        quickSort(array, l + 1, end);
    }
}