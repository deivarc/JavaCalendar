package javaExer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pang Zhi Ming
 */
import java.util.*;

public class QueueSim {
    static int MsgQueued=0;
    static int MinsRan=10; //Simulation of how long the email processor has been running
    static Queue<Integer> q;
    static int msgSent=0;
    static int preMsgSent=0;
    static int MinQ=0; // Total amount of times requeued
    static int[] MsgSent;
    static int i;
    public static void main(String [] args) {
        q = new LinkedList<>();
        MsgSent = new int[MinsRan];
        for (i=0;i<MinsRan;i++) {//No. of minutes the program ran
        msgArr();// Msg Arrival per min
    }
        System.out.println("No. of Msgs Processed is "+msgSent);
        System.out.println("Average no. of msgs arrived and queued is "+MsgQueued/MinsRan);
        System.out.println("Average no. of Messages sent per minute is "+msgSent/MinsRan);
        System.out.println("Average no. of messages in the queue per minute is "+((MinQ+MsgQueued)/(MinsRan*2))); 
        for (int i=0;i<MinsRan;i++){
            System.out.println("No.of messages sent on attempt "+i+" is "+MsgSent[i]);
        }
        System.out.println("Average no. of messages requeued per minute is "+MinQ/MinsRan);
    }
    
    public static void msgArr() {
        Random rand = new Random();
        //Avg 30 Msgs arrive and queued.. 60/2=30 avg msgs
        for (int i=0; i<60; i++){
        if (rand.nextBoolean()==true) {
            q.add(1);
            MsgQueued+=1;
        }
        }
        //Msgs processed by mail processor per min
        msgProcess();
    }
    
    public static void msgProcess() {
        Random rand2 = new Random();
        //40 msgs are either dequeued and sent OR enqueued
        ForLoop:for (int i=0; i<40;i++) {
        //if Queue is empty, nothing is SENT or enqueued
            if(q.peek()==null) {
                break ForLoop;
        }
        //75% of msgs are dequeued and SENT
        if (rand2.nextInt(100)<75) {
                q.remove();
                msgSent++;
        } else {
            //25% are enqueued to the rear of the queue for another round
                int tempInt=q.peek();
                q.remove();
                q.add(tempInt);
                MinQ++;
        }
    }
        MsgSent[i]=msgSent-preMsgSent;
        preMsgSent+=MsgSent[i];

    }
}
