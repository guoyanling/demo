package dynamicproxy;

public class Hello implements IHello {
    @Override
    public void say(String name) {
        System.out.println("Hello,"+name+"!");
    }
}
