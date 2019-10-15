package tools;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-09 11:07
 * @Description:
 */

class Son implements Cloneable{

    private Son son;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Son res = (Son) super.clone();
        if (son != null) {
            res.son = (Son) son.clone();
        }
        return res;
    }
}
public class Test {

}
