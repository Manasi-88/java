import java.io.*;
class Producer extends Thread
{
private Product Chocolate=new Product();
Producer(Product cadberry)
{
Chocolate=cadberry;
}
public void run()
{
for (int pid = 1; pid <= 10; pid++)
{
Chocolate.put(pid);
try {
sleep((int)(Math.random() * 15000));
}
catch (InterruptedException e) { }
}
}
}
class Consumer extends Thread
{
private Product Chocolate=new Product();
private int prod_id;
Consumer(Product cadberry)
{
Chocolate=cadberry;
}
public void run()
{
for (int i = 1; i <= 10; i++)
{
prod_id = Chocolate.get();
System.out.println("Consumer consumes product:" + prod_id);
System.out.println("-- ");
}
}
}
class Product
{
private int prod_id=0;
private boolean available = false;
public synchronized int get()
{
while (available == false)
{
try
{
wait();
}
catch (InterruptedException e){ }
}
available = false;
return prod_id;
}
public synchronized void put(int value)
{
prod_id = value;
System.out.println("Producer produces product" + prod_id);
available = true;
notify();
}
}
class ProducerConsumer
{
public static void main(String args[])
{
Product store= new Product();
Producer p1 = new Producer(store);
Consumer c1 = new Consumer(store);
p1.start();
c1.start();
}
}