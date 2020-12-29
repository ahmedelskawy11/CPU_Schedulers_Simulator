package main_package;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SJF {
    int current_time ;
    int finished_processes;
    int processes_length ;
    ArrayList<Process> processes ;
    ArrayList<Process> ready_processes  ;

    public SJF(ArrayList<Process>given_processes)
    {
        finished_processes = 0;
        processes = given_processes ;
        ready_processes = new ArrayList<>();
        processes_length = given_processes.size();
        current_time = processes.get(0).get_arrival_time() ;

    }

    public void is_work()
    {
        while(finished_processes < processes_length )
        {
            ready_processes_initiator();
            current_time += ready_processes.get(0).get_Burst_time() ;
            System.out.println("Current Time = " + current_time);
            System.out.println(ready_processes.get(0));
            ready_processes.remove(0);
            finished_processes++;
        }

    }

    public void ready_processes_initiator()
    {
        int i = processes.size() ;
        while (i > 0 )
        {
            if(processes.get(0).get_arrival_time() > current_time)
                break;
            ready_processes.add(processes.get(0));
            processes.remove(0);
            i-- ;
        }

        Comparator<Process> compare_by_burst = new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return Integer.compare(o1.get_Burst_time(), o2.get_Burst_time());
            }
        };


        Collections.sort(ready_processes,compare_by_burst);

    }
}
