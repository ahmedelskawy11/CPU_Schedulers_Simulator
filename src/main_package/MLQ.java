package main_package;

import java.util.ArrayList;

public class MLQ {
    int current_time ;
    int finished_processes;
    int total_processes_length ;
    int time_quantum ;
    ArrayList<Process> first_queue = new ArrayList<>();
    ArrayList<Integer> first_queue_burst_times = new ArrayList<>();

    ArrayList<Process> second_queue =new ArrayList<>();
    ArrayList<Integer> second_queue_burst_times = new ArrayList<>();

    public MLQ(ArrayList<Process>given_processes, int given_time_quantum)
    {
        this.time_quantum = given_time_quantum ;
        this.total_processes_length = given_processes.size();
        finished_processes = 0 ;
        current_time = 0 ;
        creating_queues(given_processes) ;
        initializing_burst_times() ;

        Run() ;
    }

    public  void initializing_burst_times()
    {
        for(Process process: this.first_queue)
            first_queue_burst_times.add(process.get_Burst_time());

        for(Process process: this.second_queue)
            second_queue_burst_times.add(process.get_Burst_time());
    }

    public void creating_queues(ArrayList<Process>given_processes)
    {
        for(Process process : given_processes)
        {
            if(process.get_queue_number() == 1)
                this.first_queue.add(process);

            if(process.get_queue_number() == 2)
                this.second_queue.add(process);

        }
    }
    public  void printing_the_table()
    {
        for (Process process : first_queue)
            System.out.println(process);

        for (Process process : second_queue)
            System.out.println(process);

    }

    public void calculate_average_waiting_time()
    {
        double total = 0 ;
        double average ;

        for(Process cur_process : first_queue)
            total += cur_process.get_waiting_time();

        for(Process cur_process : second_queue)
            total += cur_process.get_waiting_time();

        average = total / total_processes_length ;

        System.out.println("Average Waiting Time = " +average );
    }
    public void calculate_average_turn_around_time()
    {
        double total = 0 ;
        double average ;

        for(Process cur_process : first_queue)
            total += cur_process.get_turn_around_time();

        for(Process cur_process : second_queue)
            total += cur_process.get_turn_around_time();

        average = total / total_processes_length ;

        System.out.println("Average Turn Around Time = " +average );
    }
    public void Run()
    {
        boolean is_cpu_working = false ;// if cpu is executing any process now
        boolean is_first_queue_working  ;
        int current_FCFS_process_index =  0;
        int previous_FCFS_process_index = -1 ;
        int current_FCFS_process_burst_time = 0  ;

        while(finished_processes != total_processes_length)
        {
            while(true)
            {
                is_first_queue_working = false ;

                for(int i = 0 ; i < first_queue.size() ; i++)
                {
                    if(first_queue.get(i).get_arrival_time() > current_time || first_queue_burst_times.get(i) == 0)
                        continue;

                    if(first_queue_burst_times.get(i)  > time_quantum )
                    {
                        System.out.print(first_queue.get(i).get_name() + " | ");
                        current_time+= time_quantum  ;
                        //burst time -= time quantum but in Array list
                        first_queue_burst_times.set(i ,first_queue_burst_times.get(i) - time_quantum) ;
                        is_first_queue_working = true ;

                    }else
                    {
                        System.out.print(first_queue.get(i).get_name() + " | ");
                        current_time+=first_queue_burst_times.get(i) ;//add the rest to the time
                        int finished_time = current_time  ;
                        first_queue.get(i).set_finished_turnAround_waiting_time(finished_time);
                        first_queue_burst_times.set(i,0); //set this burst time to 0
                        finished_processes++ ;
                        previous_FCFS_process_index = -1 ; // usefull for printing only
                        is_first_queue_working = true ;
                    }

                }

                if(!is_first_queue_working)
                    break;

            }

            for(int i = 0 ; i < second_queue.size() ; i++)
            {
                if(second_queue.get(i).get_arrival_time() <= current_time
                        && second_queue_burst_times.get(i) > 0)
                {
                    current_FCFS_process_burst_time =  second_queue_burst_times.get(i) ;
                    current_FCFS_process_index = i ; // the process we are going to execute
                    is_cpu_working = true ;
                    break;
                }

            }

            if(current_FCFS_process_index != previous_FCFS_process_index  )
            {
                System.out.print(second_queue.get(current_FCFS_process_index).get_name() + " | ");
            }
            if(!is_cpu_working)
            {
                current_time++;
                continue;
            }
            // decreasing this burst time by 1
            second_queue_burst_times.set( current_FCFS_process_index , --current_FCFS_process_burst_time);

            if( current_FCFS_process_burst_time == 0 ){
                finished_processes++;
                second_queue.get(current_FCFS_process_index).set_finished_turnAround_waiting_time(current_time + 1);
            }

            previous_FCFS_process_index = current_FCFS_process_index ;
            current_time++;
        }

        System.out.print("\n");

        System.out.println("\nProcesses  Burst time  Waiting time  Turn around time");
        printing_the_table();

        System.out.println("\n");
    }
}
