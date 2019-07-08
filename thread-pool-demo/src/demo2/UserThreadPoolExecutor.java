package demo2;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UserThreadPoolExecutor {

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                4,              //coreSize
                4,              //MaxSize
                60,             //60
                TimeUnit.SECONDS,
//                new ArrayBlockingQueue<Runnable>(3)         //指定一种队列 （有界队列）
                new LinkedBlockingQueue<Runnable>()
//                , new MyRejected()
                , new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        MyTask mt1 = new MyTask(1, "任务1");
        MyTask mt2 = new MyTask(2, "任务2");
        MyTask mt3 = new MyTask(3, "任务3");
        MyTask mt4 = new MyTask(4, "任务4");
        MyTask mt5 = new MyTask(5, "任务5");
        MyTask mt6 = new MyTask(6, "任务6");

        pool.execute(mt1);
        pool.execute(mt2);
        pool.execute(mt3);
        pool.execute(mt4);
        pool.execute(mt5);
        pool.execute(mt6);

        pool.shutdown();
    }
}
