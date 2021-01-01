package main_package;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Utilities Utils = new Utilities() ;

        int processes_number = Utils.take_positive_number("Number Of Processes : ");

        ArrayList<Process> processes = new ArrayList<>();

        for(int i = 0 ; i < processes_number ; i++)
        {
            System.out.println("Process "+(i+1));

            String process_name = Utils.take_string("Name Of The Process : ");
            int burst_time = Utils.take_positive_number("Burst Time For This Process : ");
            int arrival_time = Utils.take_positive_number("Arrival Time For This Process : ");
            int priority = Utils.take_positive_number("Priority For This Process : ");

            Process temp_process = new Process(process_name,burst_time,arrival_time , priority);
            processes.add(temp_process) ;
        }

        //sorting based on arrival time
        Collections.sort(processes);

        //SJF SJF_Test = new SJF(processes);
        //SJF_Test.Run();

        //SRTF SRTF_Test = new SRTF(processes,1);
        //SRTF_Test.calculate_average_waiting_time();
        //SRTF_Test.calculate_average_turn_around_time();

        //RR RR_Test = new RR(processes , 4 , 0 );
        //RR_Test.calculate_average_waiting_time();
        //RR_Test.calculate_average_turn_around_time();

        Priority_Scheduling PS_Test = new Priority_Scheduling(processes);
        PS_Test.calculate_average_waiting_time();
        PS_Test.calculate_average_turn_around_time();

        
    }

}