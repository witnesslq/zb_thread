# 传统线程使用

## TraditionalThread.java

**1、线程构造的两种方式**

构造方法参数实现Runnable接口的run方法

子类继承重写run方法

**2、同时使用两种方式，最终程序会执行哪一个？**

执行子类继承的run方法，因为父类的run方法有判断runnable是否为空，如果不为空则使用runnable的run方法

## TraditonalThreadSynchronized.java

**1、线程互斥，synchronized关键字**

在需要互斥的代码放在synchronized中

声明方法是用synchronized关键字

当方法为静态方法时，把this，改成OutPuter.class,静态方法只能和字节码对象进行关联

