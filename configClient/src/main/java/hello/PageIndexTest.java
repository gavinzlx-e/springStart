package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by @author linxin on 05/09/2017.  <br>
 */
@Service
public class PageIndexTest {
    Logger logger= LoggerFactory.getLogger(PageIndexTest.class);



    ExecutorService  executorServics= Executors.newFixedThreadPool(10);

    public void testThreads(){
        AtomicInteger pageIndex=new AtomicInteger(0);

        for(int i=0;i<10;i++){
            executorServics.submit(new MyTask(pageIndex));
        }
    }

    class MyTask implements  Runnable{
        AtomicInteger pageIndex;
        public MyTask(AtomicInteger pageIndex){
            this.pageIndex=pageIndex;
        }
        @Override
        public void run() {
            while(true){
                int index=pageIndex.getAndIncrement();
                logger.info(Thread.currentThread().getName()+"  : "+index);
            }
        }
    }


    public static  void main(String args[]){
        PageIndexTest x=new PageIndexTest();
        x.testThreads();
    }

}
