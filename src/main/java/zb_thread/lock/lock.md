# 锁、读写锁

## ThreadLock.java

使用Lock接口实现ReentrantLock类，使用lock()和unlock()进行上锁和解锁操作

## ReadWriteLockDemo.java

读写锁ReadWriteLock接口实现ReentrantReadWriteLock，使用：

readLock().lock()

writeLock().lock()

readLock().unlock()

writeLock().unlock()

进行对读写锁的上锁和解锁操作

读写锁规则：读能读，读不能写，写不能读，写不能写

# ConditionCommunication.java

**1、Condition 线程唤醒、等待**

使用lock.newCondition()实现Condition接口，使用：

condition.await()(如同this.wait()，线程等待)

condition.signal()(如同this.notify()，线程唤醒)

# ThreeConditionCommunication.java

condition可以改变其他线程的状态，灵活很多



