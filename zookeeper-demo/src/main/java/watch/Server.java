package watch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class Server {

    private static ZooKeeper zk;
    private static final String CONNECT_STRING = "localhost:2181";
    private static final int SESSION_TIMEOUT = 5000*10;
    private static final String PARENT = "/name";

    public static void main(String[] args) throws Exception {
        zk = new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, event -> {
            String path = event.getPath();
            Watcher.Event.EventType type = event.getType();
            Watcher.Event.KeeperState state = event.getState();
            System.out.println(path + "\t" + type + "\t" + state);

            // 循环监听
            try {
                zk.getChildren(PARENT, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        zk.create(PARENT , "aa".getBytes(), ZooDefs.Ids
                .OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        // 添加监听
        zk.getChildren(PARENT, true);

        // 模拟服务器一直运行
        Thread.sleep(Long.MAX_VALUE);
    }

}
