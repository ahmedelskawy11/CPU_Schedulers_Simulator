package main_package;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Utilities {
    Scanner input = new Scanner(System.in) ;
    Validation validation = new Validation() ;

    String take_string(String msg)
    {
        input.nextLine();
        System.out.print(msg);
        String temp = input.nextLine() ;
        return temp;
    }
    
    int take_positive_number(String msg)
    {
        System.out.print(msg);
        int temp = input.nextInt();
        while(validation.isNegative(temp))
        {
            System.out.println("Invalid Number, Please Try Again");
            System.out.print(msg);
            temp = input.nextInt();
        }

        return  temp ;
    }

    int take_one_or_two_values(String msg )
    {
        System.out.print(msg);
        int temp = input.nextInt();

        while(validation.isNotOneOrTwo(temp))
        {
            System.out.println("Queue Number Should Be 1 Or Two, Please Try Again");
            System.out.print(msg);
            temp = input.nextInt();
        }

        return temp ;
    }

    int take_number(String msg)
    {
        System.out.print(msg);
        int temp = input.nextInt();
        return  temp ;
    }

    public void Initiating_Processes(ArrayList<Process> processes , int scheduling_algorithm   )
    {
        int processes_number = this.take_positive_number("Number Of Processes : ");
        int burst_time = 0 , arrival_time = 0 , priority = 0 , queue_number = 0 ;
        for(int i = 0 ; i < processes_number ; i++)
        {
            System.out.println("Process "+(i+1));

            String process_name = this.take_string("Name Of The Process : ");
            burst_time = this.take_positive_number("Burst Time For This Process : ");

            if(scheduling_algorithm != 2)
                arrival_time = this.take_positive_number("Arrival Time For This Process : ");

            if(scheduling_algorithm == 3)
                priority = this.take_positive_number("Priority For This Process : ");

            if(scheduling_algorithm == 4)
                queue_number = this.take_one_or_two_values("Queue Number For This Process : ") ;

            Process temp_process = new Process(process_name ,burst_time , arrival_time , priority, queue_number);
            processes.add(temp_process) ;
        }

        Collections.sort(processes);    //sorting based on arrival time

    }
}
