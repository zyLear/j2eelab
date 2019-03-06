package com.zylear.j2eelab.file;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

/**
 * Created by xiezongyu on 2017/10/24.
 */
public class NewTest {
    private ExecutorService service = Executors.newFixedThreadPool(1);


    public void process(){


        try {
            service.submit(new MyRun());
        } catch (Exception e) {
            System.out.println("process  catch");
        }
        System.out.println("end");

    }

    public static void main(String[] args) {
        new NewTest().process();
    }

    class MyRun implements Runnable{

        @Override
        public void run()  {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (true) {
                try {

                    int a=12/0;

                    if (false) {
                        throw new TimeoutException();
                    }

                } catch (TimeoutException e) {
                    System.out.println("li mian zhua ");
                } finally {
                    System.out.println("finally");
                }



            }
        }
    }

}


