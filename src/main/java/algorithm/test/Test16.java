package algorithm.test;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-25 17:42
 * @Description:
 */
public class Test16 {

    public void search(int[] arr){
        fullSort(0, arr.length - 1, arr);
    }

    private void fullSort(int start, int end, int[] arr) {
        if (start == end) {
            for (int i : arr) {
                System.out.print(i);
            }
            System.out.println();
            return;
        }
        for (int i = start; i <= end; i++) {
            if (isRepeat(start, i, arr)) {
                continue;
            }
            swap(i, start, arr);
            fullSort(start + 1, end, arr);
            swap(i, start, arr);
        }
    }

    private void swap(int i, int j, int[] arr) {
        int tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }

    private boolean isRepeat(int start, int index, int [] arr) {
        for (int i = start; i < index; i++) {
            if (arr[i] == arr[index]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Test16 fullSort = new Test16();
        int [] arr = new int[] {1, 2, 2};
        fullSort.search(arr);
    }
}
