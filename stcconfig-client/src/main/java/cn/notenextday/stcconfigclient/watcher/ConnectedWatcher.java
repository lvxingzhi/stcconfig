package cn.notenextday.stcconfigclient.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/30 16:31
 */
public class ConnectedWatcher implements Watcher {
    private CountDownLatch connectedLatch;

    public ConnectedWatcher(CountDownLatch connectedLatch) {
        this.connectedLatch = connectedLatch;
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            connectedLatch.countDown();
        }
    }
}
