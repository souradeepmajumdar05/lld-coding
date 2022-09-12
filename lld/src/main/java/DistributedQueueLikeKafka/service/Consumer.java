package DistributedQueueLikeKafka.service;

import DistributedQueueLikeKafka.Utility.ThreadUtil;
import DistributedQueueLikeKafka.models.Message;
import DistributedQueueLikeKafka.repository.DataQueue;

import java.util.List;
import java.util.stream.Collectors;

public class Consumer implements Runnable {
    private final DataQueue dataQueue;
    private volatile boolean runFlag;
    private final List<Integer> topicNumber;
    public final String name;

    public Consumer(DataQueue dataQueue, List<Integer> topicNumber,String name) {
        this.dataQueue = dataQueue;
        runFlag = true;
        this.topicNumber=topicNumber;
        this.name=name;
        System.out.printf("Created  %s  will consume messages from [Topic %s],\n", this.name, topicNumber.stream().map(i->String.valueOf(i+1)).collect(Collectors.joining(",")));

    }

    @Override
    public void run() {
        consume();
    }

    public void consume() {
        while (runFlag) {
            for(Integer i:topicNumber)
            {
                Message message;
                if (dataQueue.isEmpty(i)) {
                    try {
                        dataQueue.waitOnEmpty();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
                if (!runFlag) {
                    break;
                }

                message = dataQueue.remove(i);
                dataQueue.notifyAllForFull();
                useMessage(message,i);
            }
        }
        System.out.println("Consumer Stopped");
    }

    private void useMessage(Message message,Integer topicNumber) {
        if (message != null) {
            System.out.printf("[ %s ] Consuming Message from [Topic %d], Data: %s\n", this.name, topicNumber+1, message.getData());
        }
        try {
            dataQueue.waitOnEmpty();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        runFlag = false;
        dataQueue.notifyAllForEmpty();
    }
}
