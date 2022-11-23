package Sort;

public class quickSort {
    /*快速排序*/
    public static void quickSort(int[] array,int leftIndex,int rightIndex) {
        if (leftIndex >= rightIndex)
            return;
        int left = leftIndex;
        int right = rightIndex;
        int key = array[left];
        while (left < right) {
            while (right > left && array[right] >= key)//注意等号，等于key的话换不换位置都可以，所以就没必要做多余操作交换位置了
                right--;
            //循环完成后，即找到了右边的大于key位置的数组元素，将array[right]放入array[left]中
            array[left] = array[right];

            while (left < right && array[left] <= key)//注意等号，等于key的话换不换位置都可以，所以就没必要做多余操作交换位置了
                left++;//从左往右扫描，找到第一个比基准值大的元素

            //找到后将array[left]放入array[right]中
            array[right] = array[left];
        }

        //基准值归位
        array[left] = key;
        //递归对基准值左边和右边的元素进行排序
        quickSort(array,leftIndex,left - 1);
        quickSort(array,right + 1,rightIndex);
    }
    /*测试自己写的*/
    public static void quicksort(int[] array,int leftIndex,int rightIndex) {
        if(leftIndex >= rightIndex)
            return;

        int left = leftIndex,right = rightIndex;
        int key = array[left];

        while(left < right) {
            while(left < right && array[right] > key)
                right--;

            array[left] = array[right];

            while(left < right && array[left] < key)//<= key ????
                left++;

            array[right] = array[left];
        }
        array[left] = key;
        quicksort(array,leftIndex,left - 1);
        quicksort(array,left + 1,rightIndex);
    }

}
