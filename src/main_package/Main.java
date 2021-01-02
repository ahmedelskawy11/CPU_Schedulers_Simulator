package main_package;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Utilities Utils = new Utilities() ;
        ArrayList<Process> processes = new ArrayList<>();

        System.out.println("Welcome to our CPU Scheduling Algorithms:"
                + "\n\t[1] Preemptive Shortest Job First Scheduling With Context Switching"
                + "\n\t[2] Round Robin With Context Switching"
                + "\n\t[3] Preemptive Priority Scheduling"
                + "\n\t[4] Multi level Scheduling");

        System.out.print("Choice: ");
        int choice = Utils.take_number("Choice: ");

        switch (choice) {
            case 1: {
                Utils.Initiating_Processes(processes , 1);
                int context_switching = Utils.take_positive_number("Context Switching : ");

                SRTF srtf =  new SRTF(processes, context_switching );
                srtf.calculate_average_waiting_time();
                srtf.calculate_average_turn_around_time();

                break;
            }

            case 2: {
                Utils.Initiating_Processes(processes , 2);
                int context_switching = Utils.take_positive_number("Context Switching : ");
                int time_quantum = Utils.take_positive_number("Round robin Time Quantum: ");

                RR round_robin = new RR(processes , time_quantum , context_switching );
                round_robin.calculate_average_waiting_time();
                round_robin.calculate_average_turn_around_time();

                break;
            }

            case 3:{
                Utils.Initiating_Processes(processes , 3);

                Priority_Scheduling priority_scheduling = new Priority_Scheduling(processes );
                priority_scheduling.calculate_average_waiting_time();
                priority_scheduling.calculate_average_turn_around_time();

                break;
            }

            case 4: {
                // Multi level Scheduling
                Utils.Initiating_Processes(processes,4);
                int time_quantum = Utils.take_positive_number("Round robin Time Quantum: ");

                MLQ multi_level_scheduling = new MLQ(processes, time_quantum);
                multi_level_scheduling.calculate_average_waiting_time();
                multi_level_scheduling.calculate_average_turn_around_time();

                break;
            }

            default:{
                System.out.println("Invalid Input ");
                break;
            }
        }

    }

}