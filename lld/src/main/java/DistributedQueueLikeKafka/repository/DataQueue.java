package DistributedQueueLikeKafka.repository;

import DistributedQueueLikeKafka.Utility.Constants;
import DistributedQueueLikeKafka.models.Message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class DataQueue {
    private static List<Queue<Message>> listOfQueue;
    public static AtomicInteger count=new AtomicInteger(0);
    private final Object FULL_QUEUE = new Object();
    private final Object EMPTY_QUEUE = new Object();

    private DataQueue() {
        listOfQueue = new ArrayList<>();
        for (int i = 0; i < Constants.TOTAL_TOPICS; i++) {
            listOfQueue.add(new LinkedList<>());
        }
    }
    private static DataQueue INSTANCE=null;
    public static DataQueue getInstance(){
        if(DataQueue.INSTANCE==null)
        {
            DataQueue.INSTANCE=new DataQueue();
        }
        return DataQueue.INSTANCE;
    }

    public boolean isEmpty(Integer topicNumber) {
        return listOfQueue.get(topicNumber).isEmpty();
    }

    public void waitOnFull() throws InterruptedException {
        synchronized (FULL_QUEUE) {
            FULL_QUEUE.wait();
        }
    }

    public void waitOnEmpty() throws InterruptedException {
        synchronized (EMPTY_QUEUE) {
            EMPTY_QUEUE.wait();
        }
    }

    public void notifyAllForFull() {
        synchronized (FULL_QUEUE) {
            FULL_QUEUE.notifyAll();
        }
    }

    public void notifyAllForEmpty() {
        synchronized (EMPTY_QUEUE) {
            EMPTY_QUEUE.notifyAll();
        }
    }

    public void add(Message message, int topicNumber) {
        synchronized (listOfQueue) {
            listOfQueue.get(topicNumber).add(message);
        }
    }

    public Message remove(int topicNumber) {
        synchronized (listOfQueue) {
            return listOfQueue.get(topicNumber).poll();
        }
    }

    public boolean containsMessage(Integer topicNumber) {
        return listOfQueue.get(topicNumber).size()>0;
    }
}
