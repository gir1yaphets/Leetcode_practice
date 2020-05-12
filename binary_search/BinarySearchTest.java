class BinarySearchTest {
    public static void main(String[] args) {
        // int[] test1 = {1,3,5,7,9};
        int[] test2 = {1,3,3,3,3,4};

        // int res = binarySearch(test2, 3);
        int res = searchLastEqual(test2, 3);
        System.out.println(res);
    }

    public static int binarySearch(int[] array, int target) {
        int l = 0, r = array.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            //1. >=: 返回第一个大于等于目标的值
            //2. >: 返回第一个大于目标的值
            if (array[mid] > target) {
                //只要中点值大于目标值就在中点的左区间找，因为l=mid+1,所以最终跳出循环的时候正好是第一个大于目标的值
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    public static int searchLastEqual(int[] array, int target) {
        int l = 0, r = array.length;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (array[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}