package main_package;

public class Process implements Comparable<Process>{
      String name ;
      int burst_time ;
      int arrival_time ;
      int finished_time ;
      int waiting_time ;
      int turn_around_time ;

    public Process(String given_name , int given_burst_time , int given_arrival_time)
    {
        this.name = given_name ;
        this.burst_time = given_burst_time;
        this.arrival_time = given_arrival_time ;
    }

    void set_finished_turnAround_waiting_time(int given_finished_time)
    {
        this.finished_time = given_finished_time ;
        this.waiting_time = given_finished_time - arrival_time - burst_time  ;
        this.waiting_time = this.waiting_time < 0 ? 0 : this.waiting_time ;

        this.turn_around_time = this.waiting_time + this.burst_time ;

    }

    int get_waiting_time()
    {
        return this.waiting_time ;
    }

    int get_turn_around_time()
    {
        return  this.turn_around_time;
    }

    int get_arrival_time()
    {
        return this.arrival_time;
    }

    int get_Burst_time()
    {
        return this.burst_time;
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
