package staticproxy;

public class BuyProxy implements IBuyService {

    private IBuyService target;

    public IBuyService getTarget() {
        return target;
    }

    public void setTarget(IBuyService target) {
        this.target = target;
    }

    @Override
    public void bugItem(int userId) {
        if (target != null) {
            System.out.println("do something before buy action");
            target.bugItem(userId);
            System.out.println("do something after buy action");
        }
    }
}
