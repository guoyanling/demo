package dynamicproxy.jdk;

import dynamicproxy.Hello;
import dynamicproxy.IHello;

public class JdkProxyMain {

    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy(new Hello());
        IHello iHello = jdkProxy.getProxy();
        iHello.say("jdk-proxy");
    }
}
