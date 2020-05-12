/**
 * 这道题的核心点是要找到一个点将nums1，nums2合并后的数组分开，并且满足
 * 1.len(left_part) == len(right_part)
 * 2.max(left_part) <= min(right_part)
 * 所以就在nums1和nums2中总共找到k个元素使得k=(n1+n2+1)/2 并且
 * 1.nums1[m1](1的分割点右边第一个元素 > nums2[m2-1](分割点左边的最后一个元素)
 * 2.nums1[m1-1] < nums2[m2]
 */
class LC4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 > n2) return findMedianSortedArrays(nums2, nums1);
        
        int k = (n1 + n2 + 1) / 2;
        int l = 0, r = n1;
        
        while (l < r) {
            int m1 = l + (r - l) / 2;
            int m2 = k - m1;
            
            if (nums1[m1] < nums2[m2 - 1]) {
                //最小的l使得移动r的条件成立 即最小的l使得nums1[m1] >= nums2[m2 - 1]
                l = m1 + 1;
            } else {
                r = m1;
            }
        }
        
        int m1 = l;
        int m2 = k - l;
        
        //分割线左边最大值，之所以判断m1<=0是因为存在1中的元素太大，所以一个都不能取，前k个元素只能在nums2中取
        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1], m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);
         
        if ((n1 + n2) % 2 == 1) {
            return c1;
        }
        
        //分割线右边最小值
        int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1], m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);
        
        return (c1 + c2) * 0.5;
    }
}