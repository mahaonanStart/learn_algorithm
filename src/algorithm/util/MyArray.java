package algorithm.util;

public class MyArray<E> {

    private E [] elemtents;


    public MyArray(){
        elemtents = (E[]) new Object[0];
    }

    /**
     * 获取数组长度
     * @return  数组的长度
     */
    public int size(){
        return elemtents.length;
    }

    /**
     * 获取指定位置的元素
     * @param index
     * @return
     */
    public E get(int index){
        return elemtents[index];
    }

    /**
     * 向数组末尾添加元素
     * @param e 所添加的元素
     * @return  所添加的元素
     */
    public E add(E e){
        E [] newArray = (E[]) new Object[elemtents.length + 1];
        System.arraycopy(elemtents, 0, newArray, 0, elemtents.length);
        newArray[elemtents.length] = e;
        elemtents = newArray;
        return e;
    }

    /**
     * 向指定下标的位置添加元素
     * @param index
     * @param e
     * @return
     */
    public E add(int index, E e){
        E [] newArray = (E[]) new Object[elemtents.length + 1];
        for (int i = 0; i < newArray.length; i++) {
            if (i < index){
                newArray[i] = elemtents[i];
            }else if (i == index){
                newArray[i] = e;
            }else{
                newArray[i] = elemtents[i - 1];
            }
        }
        elemtents = newArray;
        return e;
    }

    /**
     * 删除指定下标位置的元素
     * @param index
     * @return
     */
    public E delete(int index){
        E del = elemtents[index];
        E [] newArray = (E[]) new Object[elemtents.length - 1];
        for (int i = 0; i < newArray.length; i++) {
            if (i < index){
                newArray[i] = elemtents[i];
            }else{
                newArray[i] = elemtents[i + 1];
            }
        }
        elemtents = newArray;
        return del;
    }

    public void show(){
        for (int i = 0; i < elemtents.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }



}
