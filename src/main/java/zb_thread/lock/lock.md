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