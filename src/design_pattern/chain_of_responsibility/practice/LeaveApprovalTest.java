package design_pattern.chain_of_responsibility.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-16 18:05
 * @Description:
 */
abstract class Leader {
    private Leader next;

    public Leader getNext() {
        return next;
    }

    public void setNext(Leader next) {
        this.next = next;
    }

    public abstract void handleRequest(int leaveDays);
}

class ClassAdviser extends Leader{

    @Override
    public void handleRequest(int leaveDays) {
        if (leaveDays <= 2) {
            System.out.println("班主任批准请假");
        }else {
            if (getNext() != null) {
                getNext().handleRequest(leaveDays);
            }else {
                System.out.println("请假天数太多，没有人批准该假条！");
            }
        }
    }
}

class DepartmentHead extends Leader{

    @Override
    public void handleRequest(int leaveDays) {
        if (leaveDays <= 7) {
            System.out.println("系主任批准请假");
        }else {
            if (getNext() != null) {
                getNext().handleRequest(leaveDays);
            }else {
                System.out.println("请假天数太多，没有人批准该假条！");
            }
        }
    }
}

class Dean extends Leader{

    @Override
    public void handleRequest(int leaveDays) {
        if (leaveDays <= 10) {
            System.out.println("院长批准请假");
        }else {
            System.out.println("请假天数太多，没有人批准该假条！");
        }
    }
}
public class LeaveApprovalTest {

    public static void main(String[] args) {
        Leader leader1 = new ClassAdviser();
        Leader leader2 = new DepartmentHead();
        Leader leader3 = new Dean();
        leader1.setNext(leader2);
        leader2.setNext(leader3);
        leader1.handleRequest(5);
    }
}
