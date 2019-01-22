package watch;

import org.apache.zookeeper.*;

public class Client {
    private static final String CONNECT_STRING = "localhost:2181";
    private static final int SESSION_TIMEOUT = 5000*10;
    private static final String PARENT = "/name";
    private static final String CHILD = "tony";

    public static void main(String[] args) throws Exception {

        ZooKeeper zk = new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, null);
        Thread.sleep(1000);
        // 客户端创建了一个子节点，会触发NodeChildrenChanged事件
        String path = zk.create(PARENT + "/" + CHILD, CHILD.getBytes(), ZooDefs.Ids
                .OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.delete(PARENT + "/" + CHILD,-1);
        zk.delete(PARENT ,-1);
        zk.close();
    }
}
