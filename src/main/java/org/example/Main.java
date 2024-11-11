package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Q4 - Write a program (c++ or java) that computes turnaround time and average wait time
 * for the processes listed in Q2 for FCFS and SJF scheduling.
 */


public class Main {
    public static void main(String[] args) {
        List<Process> processList = new ArrayList<>(Arrays.asList(
                new Process(4,4,2),
                new Process(1,2,2),
                new Process(2,1,1),
                new Process(3,8,4),
                new Process(5,5,3)
        ));

        FCFS(processList);
        SJF(processList);

    }

    /**
     * computes turnaround time and wait time for the processes listed in FCFS
     * and print all info in screen
     * @param processList the processes listed need to deal with
     */
    public static void FCFS(List<Process> processList){
        // in order by process id, First-Come, First-Served
        List<Process> sortedProcessList = processList.stream()
                .sorted(Comparator.comparingInt(Process::getProcessID))
                .toList();

        int waitingTime = 0;
        for(Process process: sortedProcessList){
            // set waiting time
            process.setWaitingTime(waitingTime);
            waitingTime += process.getBurstTime();

            //set turnaround time = finish time - response time
            // finish time = waiting time + burst time
            // response time = first waiting time
            int finishTime = process.getWaitingTime() +process.getBurstTime();
            int responseTime = process.getWaitingTime();
            process.setTurnaroundTime(finishTime-responseTime);
        }

        //print process info in FCFS
        System.out.println("---------------------FCFS-------------------");
        System.out.println("Process ID | Waiting Time | Turnaround Time");
        sortedProcessList.forEach(System.out::println);

        //get average Wait Time
        double averageWaitTime = (sortedProcessList.stream()
                .mapToDouble(Process::getWaitingTime)
                .sum())/sortedProcessList.size();

        System.out.println("Average wait time:" + averageWaitTime);
    }

    /**
     * computes turnaround time and wait time for the processes listed in SJF
     * and print all info in screen
     * @param processList the processes listed need to deal with
     */
    public static void SJF(List<Process> processList){
        // in order by Burst Time, shorter job first
        List<Process> sortedProcessList = processList.stream()
                .sorted(Comparator.comparingInt(Process::getBurstTime))
                .toList();

        int waitingTime = 0;
        for(Process process: sortedProcessList){
            // set waiting time
            process.setWaitingTime(waitingTime);
            waitingTime += process.getBurstTime();

            //set turnaround time = finish time - response time
            // finish time = waiting time + burst time
            // response time = first waiting time
            int finishTime = process.getWaitingTime() +process.getBurstTime();
            int responseTime = process.getWaitingTime();
            process.setTurnaroundTime(finishTime-responseTime);
        }
        //order the list by process id
        List<Process> orderProcessList = sortedProcessList.stream()
                .sorted(Comparator.comparingInt(Process::getProcessID))
                .toList();
        //print process info in SJF
        System.out.println("---------------------SJF-------------------");
        System.out.println("Process ID | Waiting Time | Turnaround Time");
        orderProcessList.forEach(System.out::println);

        //get average Wait Time
        double averageWaitTime = (orderProcessList.stream()
                .mapToDouble(Process::getWaitingTime)
                .sum())/orderProcessList.size();

        System.out.println("Average wait time:" + averageWaitTime);
    }


}