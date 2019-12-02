package main.java.algorithm.recursion;

/**
 * @Author: M˚Haonan
 * @Date: 2019-07-25 11:15
 * @Description: 全排列算法
 */
public class FullSort {

    public void search(int[] arr) {
        getSearch(0, arr.length - 1, arr);
    }

    /**
     *
     * @param start 要确定的当前位置，即序列的起始位置
     * @param end 序列的终止位置
     * @param arr 数组本身
     */
    private void getSearch(int start, int end, int[] arr) {
        //递归终止条件，即剩下的序列只有一个元素, 排列完成
        if (start == end) {
            for (int i : arr) {
                System.out.print(i);
            }
            System.out.println();
            return;
        }

        //就相当于依次把每个位置的元素当做排列的第一个元素(start)，为了便于序列的处理，因此将其与第一个位置(start)进行交换
        for (int i = start; i <= end; i++) {
            //对重复元素进行判断
            if (!swapAccepted(arr, start, i)) {
                continue;
            }
            //start就代表要确定的当前位置
            //可以这样去理解，比如start为0, 代表确定第一个位置的元素，那么可以依次把每个位置的元素都放到第一个位置，例如
            //1 2 3 ,可以依次放`1` 2 3, `2` 1 3, `3` 2 1,把每个位置的元素和第一个位置的元素进行交换，这样依次确定第一个位置的元素
            swap(arr, i ,start);
            //然后只需要递归对除了第一个位置元素的后面序列进行重复的判断
            getSearch(start + 1, end, arr);
            //后面序列判断完成后，需要复原序列，保证不对下一次的交换产生影响
            swap(arr, i, start);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static boolean swapAccepted(int[] array, int start, int end) {
        //如果当前元素前面的序列中出现了相同的元素，那么该元素就可以不用判断了，直接跳过即可
        for (int i = start; i < end; i++) {
            if (array[i] == array[end]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        FullSort fullSort = new FullSort();
        int [] arr = new int[] {1, 2, 2};
        fullSort.search(arr);
    }
}
