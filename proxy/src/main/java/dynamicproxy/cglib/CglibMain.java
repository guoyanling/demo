package dynamicproxy.cglib;

import dynamicproxy.Hello;
import dynamicproxy.IHello;

public class CglibMain {

    public static void main(String[] args) {
        IHello proxy = CglibProxy.getInstance().getProxy(Hello.class);
        proxy.say("cglib");
    }
}
