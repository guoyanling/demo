package staticproxy;

public class BuyService implements IBuyService {
    @Override
    public void bugItem(int userId) {
        System.out.println("buy something, i'm "+userId);
    }
}
