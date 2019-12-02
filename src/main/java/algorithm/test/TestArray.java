package algorithm.test;

import algorithm.util.MyArray;

public class TestArray {
    public static void main(String[] args) {
        MyArray<Integer> myArray = new MyArray<>();
        myArray.add(1);
        System.out.println(myArray.get(0));
        myArray.add(2);
        myArray.add(3);
        myArray.show();
        myArray.delete(0);
        myArray.show();
    }

}
