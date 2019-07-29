package com.dyhospital.cloudhis.common.web.threadpool;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Description: 简约型线程池设计工具
 * <p>
 * 1.获得单例,线程池大小容量为5的线程池, 等待销毁时间0l
 * 2.一些常用的线程操作
 * </p>
 * User: zhouzhou
 * Date: 2018-08-23
 * Time: 11:22
 */
public class ThreadPoolUtils {

    private static ExecutorService threadPool;

    /**
     * 单例获取线程池
     *
     * @return 大小5的线程池
     */
    public static ExecutorService getThreadPool() {
        if (threadPool == null) {
            // 全局锁
            synchronized (ThreadPoolUtils.class) {
                threadPool = Executors.newFixedThreadPool(5);
                return threadPool;
            }
        } else {
            return threadPool;
        }
    }

    /**
     * 执行线程操作
     *
     * @param callable 线程对象
     * @param <T>
     * @return 对象
     * @throws Exception
     */
    public static <T> Future<T> submit(Callable<T> callable) throws Exception {
        if (threadPool == null) {
            getThreadPool();
        }
        return threadPool.submit(callable);
    }


}
