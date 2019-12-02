package design_pattern.prototype;
import java.util.*;

interface Shape extends Cloneable
{
    public Object clone();    //拷贝
    public void countArea();    //计算面积
}
class Circle implements Shape
{
    public Object clone()
    {
        Circle w=null;
        try
        {
            w=(Circle)super.clone();
        }
        catch(CloneNotSupportedException e)
        {
            System.out.println("拷贝圆失败!");
        }
        return w;
    }
    public void countArea()
    {
        int r=0;
        System.out.print("这是一个圆，请输入圆的半径：");
        Scanner input=new Scanner(System.in);
        r=input.nextInt();
        System.out.println("该圆的面积="+3.1415*r*r+"\n");
    }
}


class Square implements Shape {

    public Object clone() {

        Square b=null;
        try {
            b=(Square)super.clone();
        }
        catch(CloneNotSupportedException e)
        {
            System.out.println("拷贝正方形失败!");
        }
        return b;
    }
    public void countArea() {
        int a=0;
        System.out.print("这是一个正方形，请输入它的边长：");
        Scanner input=new Scanner(System.in);
        a=input.nextInt();
        System.out.println("该正方形的面积="+a*a+"\n");
    }
}

class ProtoTypeManager {

    private HashMap<String, Shape>ht = new HashMap<String,Shape>();

    public ProtoTypeManager() {
        ht.put("Circle",new Circle());
        ht.put("Square",new Square());
    }

    public void addshape(String key,Shape obj) {
        ht.put(key,obj);
    }

    public Shape getShape(String key) {
        Shape temp=ht.get(key);
        return (Shape) temp.clone();
    }
}

public class PrototypeShape {

    public static void main(String[] args) {
        ProtoTypeManager pm=new ProtoTypeManager();
        Shape obj1=(Circle)pm.getShape("Circle");
        obj1.countArea();
        Shape obj2=(Shape)pm.getShape("Square");
        obj2.countArea();
    }
}