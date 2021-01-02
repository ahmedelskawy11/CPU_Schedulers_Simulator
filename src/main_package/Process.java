package main_package;

public class Process implements Comparable<Process>{
      String name ;
      int burst_time ;
      int arrival_time ;
      int priority;
      int finished_time ;
      int waiting_time ;
      int turn_around_time ;
      int queue_number ;

    public Process(String given_name , int given_burst_time , int given_arrival_time, int given_priority , int given_queue_number)
    {
        this.name = given_name ;
        this.burst_time = given_burst_time;
        this.arrival_time = given_arrival_time ;
        this.priority = given_priority ;
        this.queue_number = given_queue_number;
    }

    void set_finished_turnAround_waiting_time(int given_finished_time)
    {
        this.finished_time = given_finished_time ;
        this.waiting_time = given_finished_time - arrival_time - burst_time  ;
        this.waiting_time = this.waiting_time < 0 ? 0 : this.waiting_time ;

        this.turn_around_time = this.waiting_time + this.burst_time ;

    }

    String get_name() { return this.name ; }

    void set_burst_time(int given_burst_time) {this.burst_time = given_burst_time ;}

    int get_queue_number(){return this.queue_number;}

    int get_priority()
    {
        return this.priority ;
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
        return " " + this.name
                + "\t\t\t\t" + this.burst_time
                + "\t\t\t" + this.waiting_time +
                "\t\t\t\t" + this.turn_around_time;
    }


    @Override
    public int compareTo(Process next_process) {
        return Integer.compare(this.get_arrival_time(), next_process.get_arrival_time());
    }
}
