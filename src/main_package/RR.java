package main_package;

import java.util.ArrayList;

public class RR {
    int current_time ;
    int processes_length ;
    int time_quantum ;
    int context_switch ;
    ArrayList<Process> processes ;
    ArrayList<Integer> processes_burst_times = new ArrayList<>();

    RR(ArrayList<Process>given_processes, int given_time_quantum ,int given_context_switch)
    {
        this.processes = given_processes ;
        this.processes_length =given_processes.size();
        this.time_quantum = given_time_quantum ;
        this.context_switch = given_context_switch;
        this.current_time = 0  ;
        initializing_burst_times() ;
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

    public void Run()
    {
        while(true)
        {
            boolean all_finished = true ;

            for(int i = 0 ; i < processes_length ; i++)
            {
                if(processes_burst_times.get(i) > 0)
                {
                    all_finished = false ;
                    if(processes_burst_times.get(i) > time_quantum )
                    {
                        current_time+= time_quantum + context_switch;
                        //burst time -= time quantum but in Array list
                        processes_burst_times.set(i ,processes_burst_times.get(i) - time_quantum) ;
                    }else
                    {
                        current_time+=processes_burst_times.get(i) + context_switch;//add the rest to the time
                        int finished_time = current_time  - context_switch;
                        processes.get(i).set_finished_turnAround_waiting_time(finished_time);
                        processes_burst_times.set(i,0); //set this burst time to 0
                    }
                }
            }

            if(all_finished){ break; } //all burst times are equal zero
        }
    }
}
