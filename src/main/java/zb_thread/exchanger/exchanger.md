# 两个线程之间数据交换，两个线程到达后才进行交换数据

## ExchangerDemo.java

Exchanger exchanger = new Exchanger();

String xxx1 = (String)exchanger.exchange(xxx2);