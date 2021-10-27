package cn.notenextday.stcconfigclient.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * 连接中等待连接建立
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/30 16:31
 */
public class ConnectedWatcher implements Watcher {
    private static final Logger logger = LoggerFactory.getLogger(ConnectedWatcher.class);
    private CountDownLatch connectedLatch;

    public ConnectedWatcher(CountDownLatch connectedLatch) {
        this.connectedLatch = connectedLatch;
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            logger.info("[stcconfig][client][zookeeper] 服务重新连接成功,激活");
            connectedLatch.countDown();
        }
    }
}
