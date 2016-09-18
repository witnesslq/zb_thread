# 传统线程使用

## TraditionalThread.java

**1、线程构造的两种方式**

构造方法参数实现Runnable接口的run方法

子类继承重写run方法

**2、同时使用两种方式，最终程序会执行哪一个？**

执行子类继承的run方法，因为父类的run方法有判断runnable是否为空，如果不为空则使用runnable的run方法

