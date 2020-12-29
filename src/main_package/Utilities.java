package main_package;

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

    int take_number(String msg)
    {
        System.out.print(msg);
        int temp = input.nextInt();
        return  temp ;
    }
}
