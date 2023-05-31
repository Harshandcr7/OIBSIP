package ATMMACHINE;

import javax.swing.*;
import java.util.Scanner;

class ATM{

    float Balance;

    int PIN=7777;

    public void checkpin(){
        Scanner sc=new Scanner(System.in);
        System.out.println("ENTER YOUR PIN");
        int enteredpin=sc.nextInt();
        if (enteredpin == PIN){
            menu();
        }
        else {
            System.out.println("ENTER A VALID PIN");
        }
    }


    public void menu(){

        System.out.println("ENTER YOUR CHOICE");
        System.out.println("1. CHECK A/C BALANCE");
        System.out.println("2. WITHDRAW MONEY");
        System.out.println("3. DEPOSIT MONEY");
        System.out.println("4. EXIT");

        Scanner sc = new Scanner(System.in);
        int opt=sc.nextInt();

            if (opt == 1){
                checkbalance();
            } else if (opt == 2) {
                withdrawmoney();
            } else if (opt == 3) {
                depositmoney();
            }
            else if (opt ==4){
                return;
            }
            else {
                System.out.println("ENTER A VALID CHOICE");
            menu();
            }
    }

    public void checkbalance(){
        System.out.println("BALANCE:Rs "+Balance+"\n");
        menu();
    }

    public void updatedbalance(){
        System.out.println("CURRENT BALANCE Rs: "+Balance + "\n" );
        menu();
    }

    public void withdrawmoney() {
        System.out.println("ENTER AMOUNT TO WITHDRAW:");
        Scanner sc = new Scanner(System.in);
        float amount=sc.nextFloat();
        if (amount>Balance){
            System.out.println("INSUFFICIENT BALANCE");
        }
        else {
            Balance = Balance - amount;
            System.out.println("MONEY WITHDRAWL SUCCESFUL");
                updatedbalance();
        }
        menu();
    }


    public void depositmoney(){
        System.out.println("ENTER THE AMOUNT");
        Scanner sc =new Scanner(System.in);
        float amount=sc.nextFloat();
        Balance= Balance+amount;
        System.out.println("MONEY DEPOSITED SUCCESFULLY");
        updatedbalance();
        menu();
    }


}




public class ATMMACHINE {
    public static void main(String[] args) {

        ATM obj = new ATM();
        obj.checkpin();


    }
}









