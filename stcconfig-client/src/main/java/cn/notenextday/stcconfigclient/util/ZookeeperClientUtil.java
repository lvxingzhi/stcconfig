package cn.notenextday.stcconfigclient.util;

import cn.notenextday.stcconfigclient.constant.NodePathContant;
import cn.notenextday.stcconfigclient.dto.NodeDTO;
import cn.notenextday.stcconfigclient.watcher.ConnectedWatcher;
import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * 1,连接到zk
 * 2,创建node
 * 3,检查node存在
 * 4,获取node数据
 * 5,设置node数据
 * 6,获取node所有子节点
 * 7,删除node及子节点
 * 8,关闭zk连接
 *
 * @Author xingzhi.lv
 * @Version 2.0
 * @Date 2021/7/27 11:19
 */
public class ZookeeperClientUtil {

    private ZookeeperClientUtil() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperClientUtil.class);
    private static ZooKeeper zookeeper = null;

    static {
        init();
    }

    /**
     * 创建节点
     */
    public static void createNode(String path, byte[] data, CreateMode createMode) {
        try {
            if (existsNode(path, null)) {
                return;
            }
            waitUntilConnected(zookeeper);
            zookeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
        } catch (Exception e) {
            logger.error("[zookeeper] 创建节点异常", e);
        }
    }

    /**
     * 修改节点
     */
    public static void modifyNode(String path, byte[] data) {
        try {
            waitUntilConnected(zookeeper);
            zookeeper.setData(path, data, zookeeper.exists(path, false).getVersion());
        } catch (Exception e) {
            logger.error("[zookeeper] 修改节点异常", e);
        }
    }

    /**
     * 获取节点
     */
    public static NodeDTO getNode(String path, Watcher watcher) {
        try {
            waitUntilConnected(zookeeper);
            byte[] dataByte = zookeeper.getData(path, watcher, null);
            if (dataByte == null) {
                return null;
            }
            String dataStr = new String(dataByte, StandardCharsets.UTF_8);
            return JSON.parseObject(dataStr, NodeDTO.class);
        } catch (Exception e) {
            logger.error("[zookeeper] 获取节点异常", e);
        }
        return null;
    }

    /**
     * 获取子节点列表
     */
    public static List<NodeDTO> getChildrenNodes(String path) {
        List<NodeDTO> resultNodeList = new ArrayList<>();
        try {
            waitUntilConnected(zookeeper);
            List<String> pathList = zookeeper.getChildren(path, false);
            for (String childPath : pathList) {
                NodeDTO childNode = getNode(path + NodePathContant.PATH_SUB + childPath, null);
                resultNodeList.add(childNode);
            }
        } catch (Exception e) {
            logger.error("[zookeeper] 获取子节点列表异常", e);
        }
        return resultNodeList;
    }

    /**
     * 删除节点
     */
    public static void deleteNode(String path) {
        try {
            waitUntilConnected(zookeeper);
            zookeeper.delete(path, zookeeper.exists(path, false).getVersion());
        } catch (Exception e) {
            logger.error("[zookeeper] 删除节点异常", e);
        }
    }

    /**
     * 检查节点
     */
    public static boolean existsNode(String path, Watcher watcher) {
        try {
            waitUntilConnected(zookeeper);
            return Objects.nonNull(zookeeper.exists(path, watcher));
        } catch (Exception e) {
            logger.error("[zookeeper] 检查节点异常", e);
        }
        return false;
    }

    /**
     * 初始化连接
     */
    private static void init() {
        try {
            if (Objects.nonNull(zookeeper) && zookeeper.getState().isAlive()) {
                return;
            }
            zookeeper = new ZooKeeper("127.0.0.1:2181", 155000, watchedEvent -> {
                // 监听节点事件, 监听连接事件
                if (Watcher.Event.KeeperState.SyncConnected.equals(watchedEvent.getState())) {
                    logger.info("[zookeeper] 服务连接成功");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("[zookeeper] 初始化异常", e);
        }
    }

    /**
     * 关闭zk连接
     */
    public static void close() throws InterruptedException {
        waitUntilConnected(zookeeper);
        zookeeper.close();
    }

    /**
     * 检查连接状态
     *
     * @param zooKeeper
     */
    public static void waitUntilConnected(ZooKeeper zooKeeper) {
        CountDownLatch connectedLatch = new CountDownLatch(1);
        Watcher watcher = new ConnectedWatcher(connectedLatch);
        // 注册监听
        zooKeeper.register(watcher);
        if (ZooKeeper.States.CONNECTING == zooKeeper.getState()) {
            try {
                // 阻塞, 等待事件触发后的激活以继续执行
                connectedLatch.await();
            } catch (InterruptedException e) {
                logger.error("[zookeeper] 等待连接中异常", e);
            }
        }
    }
}
