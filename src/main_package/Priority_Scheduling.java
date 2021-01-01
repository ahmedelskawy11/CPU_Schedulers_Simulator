package main_package;

import java.util.ArrayList;

public class Priority_Scheduling {
    int current_time ;
    int finished_processes;
    int processes_length ;
    ArrayList<Process> processes ;
    ArrayList<Integer> processes_burst_times = new ArrayList<>();
    ArrayList<Integer> processes_priorities = new ArrayList<>();

    public Priority_Scheduling(ArrayList<Process>given_processes)
    {
        finished_processes = 0;
        current_time = 0;
        processes = given_processes ;
        processes_length = given_processes.size();

        initializing_burst_times() ; //temp burst times to change it in our loop
        initializing_priorities() ; //temp Priorities to change it in our loop
        Run();

    }
    public  void initializing_burst_times()
    {
        for(Process process: this.processes)
            processes_burst_times.add(process.get_Burst_time());
    }

    public  void initializing_priorities()
    {
        for(Process process: this.processes)
            processes_priorities.add(process.get_priority());
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
        int max_priority= Integer.MAX_VALUE ;
        int current_process_index  = 0 ;
        int previous_executed_index = 0  ;
        int current_process_burst_time = 0;
        boolean first_iteration = true ;

        System.out.print("\n| ");

        while(finished_processes != processes_length)
        {
            //looping throw the processes to see if any process is available and has less burst time than current process.
            // or there is not available processes to execute
            for(int i = 0 ; i < processes_length ; i++)
            {
                if(processes.get(i).get_arrival_time() <= current_time
                        && processes_priorities.get(i) < max_priority
                        && processes_burst_times.get(i) > 0)
                {
                    current_process_burst_time =  processes_burst_times.get(i) ;
                    max_priority = processes_priorities.get(i)  ; // the current is the maximum process priority
                    current_process_index = i ; // the process we are going to execute

                    cpu_working = true ;
                }
            }

            //We have been changed our process
            if(previous_executed_index != current_process_index && !first_iteration)
            {
                //decreasing by one if you changed any process to solve the starvation problem
                processes_priorities.set( previous_executed_index ,processes_priorities.get(previous_executed_index) -1 ) ;
                System.out.print(processes.get(previous_executed_index).get_name() + " | ");
            }
            // we are not executing anything
            if (!cpu_working) {
                current_time++;
                continue;
            }


            // decreasing this burst time by 1
            processes_burst_times.set( current_process_index , --current_process_burst_time);

            if( current_process_burst_time == 0 ){
                finished_processes++;
                max_priority = Integer.MAX_VALUE;
                cpu_working = false;
                processes.get(current_process_index).set_finished_turnAround_waiting_time(current_time + 1);
            }
            previous_executed_index = current_process_index ;
            current_time++ ;
            first_iteration = false ;
        }

        System.out.print(processes.get(current_process_index).get_name() + " | ");
        System.out.print("\n");

        System.out.println("\nProcesses  Burst time  Waiting time  Turn around time");
        printing_the_table();

        System.out.println("\n");

    }


}

