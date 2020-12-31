package main_package;

import java.util.ArrayList;

public class SRTF {
    int current_time ;
    int finished_processes;
    int processes_length ;
    int context_switch ;
    ArrayList<Process> processes ;
    ArrayList<Integer> processes_burst_times = new ArrayList<>();

    public SRTF(ArrayList<Process>given_processes, int given_context_switch)
    {
        finished_processes = 0;
        current_time = 0;
        this.context_switch = given_context_switch ;
        processes = given_processes ;
        processes_length = given_processes.size();
        initializing_burst_times() ; //temp burst times to change it in our loop
        Run();

    }
    public  void initializing_burst_times()
    {
        for(Process process: this.processes)
            processes_burst_times.add(process.get_Burst_time());
    }

    public void calculate_average_waiting_time()
    {

        double total = 0 ;
        double average ;
        for(Process cur_process : processes)
            total += cur_process.get_waiting_time();

        average = total / processes_length ;

        System.out.println("Average Waiting Time = " +average );
    }
    public void calculate_average_turn_around_time()
    {
        double total = 0 ;
        double average ;
        for(Process cur_process : processes)
            total += cur_process.get_turn_around_time();

        average = total / processes_length ;

        System.out.println("Average Turn Around Time = " +average );
    }

    public void printing_the_table()
    {
        for (Process process : processes)
            System.out.println(process);
    }

    public void Run()
    {
        boolean cpu_working = false ;// if cpu is executing any process now
        int minimum_burst_time_remaining = 9999 ;
        int current_process_index  = 0 ;
        int previous_executed_index = 0  ;
        int current_process_burst_time = 0;

        System.out.print("\n| ");

        while(finished_processes != processes_length)
        {
            //looping throw the processes to see if any process is available and has less burst time than current process.
            // or there is not available processes to execute
            for(int i = 0 ; i < processes_length ; i++)
            {
                if(processes.get(i).get_arrival_time() <= current_time
                        && processes_burst_times.get(i) < minimum_burst_time_remaining
                        && processes_burst_times.get(i) > 0)
                {
                    current_process_burst_time =  processes_burst_times.get(i) ;
                    minimum_burst_time_remaining = current_process_burst_time  ; // the shortest burst time -
                    current_process_index = i ; // the process we are going to execute

                    cpu_working = true ;
                }
            }

            //We have been changed our process
            if(previous_executed_index != current_process_index)
            {
                current_time+= context_switch ;
                System.out.print(processes.get(previous_executed_index).get_name() + " | ");
            }
            // we are not executing anything
            if (!cpu_working) {
                current_time++;
                continue;
            }


            // decreasing this burst time by 1
            processes_burst_times.set( current_process_index , --current_process_burst_time);
            minimum_burst_time_remaining-- ;

            if( current_process_burst_time == 0 ){
                finished_processes++;
                minimum_burst_time_remaining = 9999;
                cpu_working = false;
                processes.get(current_process_index).set_finished_turnAround_waiting_time(current_time + 1);
            }
            previous_executed_index = current_process_index ;
            current_time++ ;
        }
        System.out.print(processes.get(current_process_index).get_name() + " | ");
        System.out.print("\n");

        System.out.println("\nProcesses  Burst time  Waiting time  Turn around time");
        printing_the_table();

        System.out.println("\n");

    }


}
