package cn.notenextday.stcconfigclient.watcher;

import cn.notenextday.stcconfigclient.manage.PullConfigFileManage;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/8/4 13:48
 */
public class NodeActionWatcher implements Watcher {
    private static final Logger logger = LoggerFactory.getLogger(NodeActionWatcher.class);
    private PullConfigFileManage pullConfigFileManage;

    public NodeActionWatcher(PullConfigFileManage pullConfigFileManage) {
        this.pullConfigFileManage = pullConfigFileManage;
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType().equals(Event.EventType.NodeCreated) ||
                event.getType().equals(Event.EventType.NodeDeleted) ||
                event.getType().equals(Event.EventType.NodeDataChanged) ||
                event.getType().equals(Event.EventType.NodeChildrenChanged)) {
            String nodePath = event.getPath();
            logger.info("[stcconfig][client]节点[{}]数据发生改变,action[{}]", nodePath, event.getType());
            pullConfigFileManage.init();
        }
    }
}
