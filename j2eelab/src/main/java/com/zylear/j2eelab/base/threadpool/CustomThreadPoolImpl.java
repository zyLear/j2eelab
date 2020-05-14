package com.zylear.j2eelab.base.threadpool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiezongyu on 2019/1/18.
 */
public class CustomThreadPoolImpl implements CustomThreadPool {

    private LinkedBlockingQueue<Runnable> linkedBlockingQueue;

    private final ReentrantLock mainLock = new ReentrantLock();


    private Integer corePoolSize;

    private Integer workThreadCount = 0;

    private Integer threadPoolStatus = 0; //running

    private Set<Worker> workers = new HashSet<>();


    private int largestPoolSize;

    private long completedTaskCount;

    private volatile long keepAliveTime;


    @Override
    public void sumbit(Runnable runnable) {

        boolean isRunning = getStatus();

        if (workThreadCount < corePoolSize) {
            addWorker(runnable, true);
        }
        if (workThreadCount >= corePoolSize) {
            if (isRunning && linkedBlockingQueue.offer(runnable)) {
                /*if isRunning */
            } else {

                if (addWorker(runnable, false)) {

                    //reject

                }

            }
        }

    }

    private Boolean addWorker(Runnable runnable, boolean isCore) {
        if (isCore) {

        }
        return true;
    }

    public boolean getStatus() {
        return false;
    }

    private static class Worker {

        private Thread thread;
        Runnable firstTask;
        volatile long completedTasks;

        public Thread getThread() {
            return thread;
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }

        public Runnable getFirstTask() {
            return firstTask;
        }

        public void setFirstTask(Runnable firstTask) {
            this.firstTask = firstTask;
        }

        public long getCompletedTasks() {
            return completedTasks;
        }

        public void setCompletedTasks(long completedTasks) {
            this.completedTasks = completedTasks;
        }
    }

}
