# 信号灯

## SemaphoreDemo.java

与锁类似，但是锁只能谁谁上的锁就谁可以解锁，而信号灯可以解别人的锁,差不多这个意思

声明信号灯Semaphore semaphore = new Semaphore(3);

拿灯：semaphore.acquire();

弃灯：semaphore.release();