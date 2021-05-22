import java.util.Scanner;
import java.util.Date;


class Customer{
    String Customer_Name;
    long Account_Number;
    long Card_Number;
    int balance;
    int pin;
    Date date=new Date();
    void Details(String customer_name, int account_number, int card_number, int Balance, int Pin){
        Customer_Name=customer_name;
        balance=Balance;
        Account_Number=account_number;
        Card_Number=card_number;
        pin=Pin;
    }
    void Receipt(){
        System.out.println("       RECEIPT      ");
        System.out.println("Date of activity: "+date.toString());
        System.out.println("Name of the customer: "+Customer_Name);
        System.out.println("Remaining Balance in the Account: "+Account_Number);
        System.out.println("Remaining Balance: "+balance);
    }
    long getCardNumber(){
        return Card_Number;
    }
    int getPin(){
        return pin;
    }
    long getAccountNumber(){
        return  Account_Number;
    }
}
class Tries extends Exception{
    Tries(String s){
        super(s);
    }
}

public class ATM {
    public static void main(String[] args) throws Tries {
        System.out.println("Welcome to MazeBank");
        Scanner sc= new Scanner(System.in);
        Customer customer= new Customer();
        //long Withdraw,Deposit,Transfer;
        int people=100;
        int flag=1;
        int flag1=0;
        int flag2=0;
        int select;
        Customer[] person= new Customer[people];
        for(int i=0;i<people;i++){
            person[i]= new Customer();
        }
        person[0].Details("Ankan",24009768,34003488,278009,9346);
        person[1].Details("Aditya",24009769,34003489,670912,3889);
        do{
            System.out.println("Enter your Card Number: ");
            long Card_Num= sc.nextLong();
            System.out.println("Enter your 4 digit pin: ");
            int pin= sc.nextInt();
            for(int i=0;i<people;i++){
                if (person[i].getCardNumber()==Card_Num && person[i].getPin()==pin){
                    System.out.println("Enter 1 for Withdraw, 2 for Deposit, 3 for Transfer or 4 for Showing Balance: ");
                    int choice= sc.nextInt();
                    switch (choice) {
                        case 1 -> {
                            System.out.println("Enter the amount of money that you want to withdraw: ");
                            int withdraw = sc.nextInt();
                            if (withdraw <= person[i].balance) {
                                person[i].balance = person[i].balance - withdraw;
                                System.out.println("Withdraw Successful......");
                                person[i].Receipt();
                                System.out.println("Amount withdrawn: " + withdraw);
                            }
                            else {
                                System.out.println("Withdrawl Failed, Not Enough Balance.......");
                            }
                        }
                        case 2 -> {
                            System.out.println("Enter the amount of money that you want to deposit: ");
                            int deposit = sc.nextInt();
                            person[i].balance = person[i].balance + deposit;
                            System.out.println("Deposit Successful.....");
                            person[i].Receipt();
                            System.out.println("Amount Deposited: " + deposit);
                        }
                        case 3 -> {
                            System.out.println("Enter the account number to whose account you want to transfer: ");
                            long account = sc.nextLong();
                            for (int j=0; j<people; j++) {
                                if (person[j].getAccountNumber() == account) {
                                    System.out.println("Enter the amount of money that you want to transfer: ");
                                    int transfer = sc.nextInt();
                                    if (transfer <= person[i].balance) {
                                        person[j].balance = person[j].balance + transfer;
                                        person[i].balance = person[i].balance - transfer;
                                        System.out.println("Transfer Successful.....");
                                        person[i].Receipt();
                                        System.out.println("Amount Transfered: " + transfer);
                                    } else {
                                        System.out.println("Transfer Failed, Not Enough Balance.....");
                                    }
                                    flag1++;
                                    break;
                                }
                            }
                            if(flag1==0){
                                System.out.println("Transfer Failed, A customer with the entered Account Number does not exist.....");
                            }
                        }
                        case 4 -> person[i].Receipt();
                        default -> System.out.println("Choose a valid option.....");
                    }
                    flag2++ ;
                    break;
                }

            }
            if(flag2==0){
                System.out.println("Wrong Card Number or Pin");
                flag++;
                if(flag>5){
                    throw new Tries("Out of tries, Calling Police.......");
                }
            }
            System.out.println("To perform another function or get back to initial state press 1 else press 0 to exit: ");
            select= sc.nextInt();
        }while(select==1);
    }
}
