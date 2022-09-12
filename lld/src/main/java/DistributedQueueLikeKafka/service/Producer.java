package DistributedQueueLikeKafka.service;

import DistributedQueueLikeKafka.Utility.ThreadUtil;
import DistributedQueueLikeKafka.models.Message;
import DistributedQueueLikeKafka.repository.DataQueue;

import java.util.List;
import java.util.stream.Collectors;

public class Producer implements Runnable {
    private final DataQueue dataQueue;
    private final List<Integer> topicNumber;
    private volatile boolean runFlag;

    private static int idSequence = 0;
    public final String name;

    public Producer(DataQueue dataQueue,List<Integer> topicNumber,String name) {
        this.dataQueue = dataQueue;
        runFlag = true;
        this.topicNumber=topicNumber;
        this.name=name;
        System.out.printf("Created  %s  will produce messages to [Topic %s],\n", this.name, topicNumber.stream().map(i->String.valueOf(i+1)).collect(Collectors.joining(",")));
    }

    @Override
    public void run() {
        produce();
    }

    public void produce() {
        while (runFlag) {

            if(DataQueue.count.get()>=10)
            {
                System.out.println(name+" count :"+DataQueue.count.get());
                stop();
            }
            for(Integer i:topicNumber)
            {
                Message message = generateMessage(i);
                while (dataQueue.containsMessage(i)) {
                    try {
                        dataQueue.waitOnFull();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
                if (!runFlag) {
                    break;
                }

                dataQueue.add(message,i);
                dataQueue.notifyAllForEmpty();
            }
        }
        System.out.println("Producer Stopped");
    }

    private Message generateMessage(Integer topicNumber) {
        Message message = new Message(++idSequence, "Message"+DataQueue.count.getAndIncrement());
        System.out.printf("[%s] Generated Message to [Topic %d], Data: %s\n",
                name, topicNumber+1, message.getData());

        //Sleeping on random time to make it realistic
        ThreadUtil.sleep((long) (10 * 100));

        return message;
    }

    public void stop() {
        runFlag = false;
        dataQueue.notifyAllForFull();
    }
}
