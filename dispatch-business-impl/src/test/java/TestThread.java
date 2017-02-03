/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/9/23
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
public class TestThread {


    public static void main(String[] args) throws InterruptedException {



//        System.out.println(Math.max(10, 2));
//        Integer flag = 1 >= 2 ? 1 : 2;
//        new ArrayList<Integer>().add(1);

//        Long time = System.currentTimeMillis();
//        OwnerThread t = new OwnerThread();
//        Thread thread = new Thread(t);
//        thread.start();
//        System.out.println(System.currentTimeMillis()-time);
//        Thread.sleep(5000);
    }

    public static  class OwnerThread implements Runnable{

        public  OwnerThread(){

        }
        @Override
        public void run() {
            try {
                System.out.println("thread");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
