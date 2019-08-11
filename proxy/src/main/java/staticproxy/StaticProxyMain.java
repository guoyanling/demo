package staticproxy;

public class StaticProxyMain {

    public static void main(String[] args) {
        BuyProxy buyProxy = new BuyProxy();
        buyProxy.setTarget(new BuyService());
        buyProxy.bugItem(1);
    }
}
