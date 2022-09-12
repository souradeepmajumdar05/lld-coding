package DistributedQueueLikeKafka.application;

import DistributedQueueLikeKafka.Utility.Constants;
import DistributedQueueLikeKafka.Utility.ThreadUtil;
import DistributedQueueLikeKafka.repository.DataQueue;
import DistributedQueueLikeKafka.service.Consumer;
import DistributedQueueLikeKafka.service.Producer;

import java.util.ArrayList;
import java.util.List;
import DistributedQueueLikeKafka.Utility.ThreadUtil.*;

public class Application {
    public static void main(String[] args)
    {
        DataQueue dataQueue = DataQueue.getInstance();

        String[] ttc=Constants.TOPIC_TO_CONSUMER.split(" ");
        List<Integer> listOfTopics =new ArrayList<>();
        listOfTopics.add(0);
        listOfTopics.add(1);

        List<Thread> threads = new ArrayList<>();
        Producer producer = null;
        for(int i = 0; i < Constants.TOTAL_PRODUCERS; i++) {
            producer = new Producer(dataQueue,listOfTopics,"Producer "+(i+1));
            Thread producerThread = new Thread(producer);
            //producerThread.start();
            threads.add(producerThread);
        }
        Consumer consumer =null;

        List<Consumer> consumers=new ArrayList<>();
        for(int i = 0; i < Constants.TOTAL_CONSUMERS; i++) {
            consumer = new Consumer(dataQueue,getConsumerTopics(i,ttc),"Consumer"+(i+1));
            Thread consumerThread = new Thread(consumer);
            //consumerThread.start();
            threads.add(consumerThread);
            consumers.add(consumer);
        }
        // let threads run for two seconds
        for (Thread t:threads)
        {
            t.start();
        }
        ThreadUtil.sleep(50000);

        for (Consumer c:consumers)
        {
            c.stop();
        }

        ThreadUtil.waitForAllThreadsToComplete(threads);
    }

    private static List<Integer> getConsumerTopics(int consumerCount, String[] ttc) {
        ArrayList<Integer> listOfTopics = new ArrayList<>();
        for (int i=0;i<ttc.length;i++)
        {
            String[] consumer=ttc[i].split(":");
            if(consumer[1].contains(String.valueOf(consumerCount+1))){
                listOfTopics.add(i);
            }
        }
        return listOfTopics;
    }
}
