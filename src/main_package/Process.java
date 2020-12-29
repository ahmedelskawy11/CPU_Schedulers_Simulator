package main_package;

public class Process implements Comparable<Process>{
      String name ;
      int burst_time ;
      int arrival_time ;
      int waiting_time ;
      int turn_around_time ;
    public Process(String given_name , int given_burst_time , int given_arrival_time)
    {
        this.name = given_name ;
        this.burst_time = given_burst_time;
        this.arrival_time = given_arrival_time ;
    }

    void set_waiting_and_turn_around(int given_waiting_time)
    {
        this.waiting_time = given_waiting_time ;
        this.turn_around_time = burst_time + waiting_time ;
    }


    int get_arrival_time()
    {
        return arrival_time;
    }

    int get_Burst_time()
    {
        return burst_time;
    }


    @Override
    public String toString(){
        return "[ Name = " + name + ", Arrival = " + arrival_time + ", Burst = " + burst_time + "]";
    }


    @Override
    public int compareTo(Process next_process) {
        return Integer.compare(this.get_arrival_time(), next_process.get_arrival_time());
    }
}
