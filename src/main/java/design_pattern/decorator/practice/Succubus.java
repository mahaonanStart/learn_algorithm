package design_pattern.decorator.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 14:50
 * @Description:
 */
public class Succubus extends Changer{

    public Succubus(Morrigan m) {
        super(m);
    }

    @Override
    public void display() {
        setChanger();
        super.display();
    }

    public void setChanger() {
        ((Original) super.m).setImage("Morrigan1.jpg");
    }
}
