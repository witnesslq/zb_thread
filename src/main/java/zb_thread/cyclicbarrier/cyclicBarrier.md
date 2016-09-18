# 路障

## CyclicBarrierDemo.java

等待线程到达一个集合点则继续出发

CyclicBarrier cb = new CyclicBarrier(3);

cb.await();//等待线程达到指定数量，如果到达就继续往下走