# 倒计时计数器

## CountdownLatchDemo.java

CountDownLatch countDownLatch = new CountDownLatch(count);//count计数数量

countDownLatch.countDown(); //减1

countDownLatch.await();//等待

