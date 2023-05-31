package ONLINE_EXAMINATION;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

class candidate{

    String name;
    int roll_number;
    String password;
    int obtained_marks = 0;
    int correct =0;
    int incorrect = 0;
    static int test_id = 1111;
    int tst_id = test_id;

    void create_user(){
        Scanner in =new Scanner(System.in);
        System.out.println("WELCOME TO STUDENT REGISTRATION PORTAL\n");
        System.out.println("\nENTER NAME OF THE USER?");
        name = in.nextLine();
        System.out.println("\nENTER THE ROLL NUMBER OF THE USER?");
        roll_number=in.nextInt();
        System.out.println("\nSET THE PASSWORD FOR THE USER?");
        password=in.next();
        System.out.println("\n\nUSER REGISTRATION SUCCESFUL, PLEASE NOTE THE USER test-id- " +test_id++);
        try {
            Thread.sleep(5000);
        } catch (Exception e){
            System.out.println("Caught");
        }
    }

    void update_profile(candidate ob){
        Scanner in =new Scanner(System.in);
        System.out.println("WELCOME TO UPDATE PROFILE INTERFACE\n");
        System.out.println("THE FOLLOWING THINGS CAN BE UPDATED\nName---1\nRoll Number---2\nPassword---3");
        int ch =in.nextInt();
        switch (ch){
            case 1 {
                System.out.println("ENTER THE NEW NAME? ");
                ob.name=in.nextLine();
                break;
            }

            case 2{
                System.out.println("ENTER THE NEW ROLL NUMBER? ");
                ob.roll_number = in.nextInt();
                break;
            }
            case 3{
                System.out.println("ENTER THE NEW PASSWORD?");
                ob.password=in.next();
                break;
            }
        }
    }

    class portal{

        void login(candidate[] usr,int number_students){
            Scanner in =new Scanner(System.in);
            System.out.println("WELCOME TO TEST PORTAL\n");
            System.out.println("ENTER THE USER Test ID");
            int test_id=in.nextInt();
            System.out.println("ENTER ROLL NUMBER?");
            int roll = in.nextInt();
            System.out.println("ENTER PASSWORD?");
            String pwd=in.next();
            for (int i=0;i<number_students;i++){
                if ((usr[i].tst_id == test_id) && (usr[i].roll_number == roll) && usr[i].password.equals((pwd))){
                    System.out.println("LOGGED IN SUCCESFULY");
                    System.out.println("PRESS ANY KEY TO BEGIN TEST!");
                    in.nextLine();
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    run_test(usr[i]);
                }
                else {
                    System.out.println("CREDENTIAL MISMATCH OR CANDIDATE NOT YET REGISTERED");
                }
            }
        }

        void run_test(candidate user){
            String fileName = "question.txt";
            try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
                String line;
                Date startTime = new Date();
                Date endTime = new Date(startTime.getTime() + 60 *1000);
                System.out.println("TEST STARTED AT "+startTime+"\nTHE TEST WILL END AT "+endTime);

                Scanner sc = new Scanner(System.in);
                while (((line =br.readLine()) =! null)){
                    if (new Date().before(endTime)){
                        System.out.println(line);
                        System.out.println("ANS: ");
                        String input =sc.nextLine();
                        String ans_fileName = "answer_user_" + user.tst_id + ".txt";
                        try(BufferedWriter bw = new BufferedWriter(new FileWriter(ans_fileName,true))){
                            bw.write(input);
                            bw.newLine();
                        } catch (IOException e){
                            System.err.format("IOException: %$%n",e);
                        }
                    }
                    else {
                        System.out.println("TIME OUT");
                        break;
                    }
                }
                System.out.println("TEST COMPLETED FOR THE CANDIDATE WITH DETAILS - \nName- "+user.name+"\nRoll - "+user.roll_number+"\nTest ID- "+user.tst_id);
                System.out.println("PRESS ANY KEY TO CONTINUE!");
                sc.nextLine();
            } catch (IOException e){
                System.err.format("IOException: %$%n",e);
            }
            System.out.println("\033[H\033[2J");
            System.out.flush();
            evaluate_marks(user);
        }
        void evaluate_marks(candidate user){
            String fileName="answer_user"+ user.tst_id +".txt";
            try (BufferedReader br = new BufferedReader(new FileReader(fileName));
            BufferedReader br2 = new BufferedReader(new FileReader("answer.txr"))){
                String answer_line,user_answer;
                while ((answer_line=br2.readLine())!= null && (user_answer = br.readLine())!=null){
                    if (user_answer.equalsIgnoreCase(answer_line)){
                        user.obtained_marks +=1;
                        user.correct++;
                    } else {
                        user.incorrect++;
                    }
                }
                catch (IOException e){
                    System.err.format("IOException: %$%n",e);
                }
                System.out.println("\n\nTHE OBTAINED MARKS OF THE USER IS - "+user.obtained_marks);
                System.out.println("THE NUMBER OF CORRECT RESPONSE IS - "+user.correct);
                System.out.println("THE NUMBER OF INCORRECT RESPONSE IS - "+user.incorrect);
                System.out.println("PRESS ANY KEY TO CONTINUE");
                System.out.println("LOGGIING OUT");
                Scanner in =new Scanner(System.in);
                in.nextLine();
            }
        }
        public class ONLINE_EXAMINATION{
            public static void main(String[] args) {
                Scanner in = new Scanner(System.in);
                System.out.println("ENTER NUMBER OF CANDIDATES YOU WANT TO REGISTER: ");
                int n=in.nextInt();
                candidate[] ob =new candidate[n];
                for (int i =0;i<n;i++){
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    ob[i]=new candidate();
                    ob[i].create_user();
                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("DO YOU WANT TO UPDATE DETAILS OF ANY USER Y/N");
                char c=in.next().charAt(0);
                if ((c=='Y') || (c=='y')){
                    System.out.println("ENTER TEST ID FOR THE CANDIDATE WHOSE INFORMATION IS TO BE UPATED - ");
                    int test_id =in.nextInt();
                    for (int i=0;i<n;i++){
                        if ((ob[i].tst_id==test_id)){
                            ob[i].update_profile(ob[i]);
                        } else {
                            System.out.println("CREDENTIAL MISMATCH OR CANDIADATE NOT YET REGISTERED.");
                        }
                    }
                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
                portal ob2 = new portal();
                for (;;){
                    ob2.login(ob,n);
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("DO YOU WANT TO CONTINUE TEST PORTAL Y/N?");
                    char ch =in.next().charAt(0);
                    if (!((ch=='y') || (ch=='Y'))){
                        break;
                    }
                }
                System.out.println("THE MARKS OBTAINED BY ALL CANDIDATES IS - ");
                for (int i=0;i<n;i++){
                    System.out.println("Candidate roll number - "+ob[i].roll_number+"Candidate Name - "+ob[i].name+"obtained marks "+ob[i].obtained_marks);
                }
            }
        }
    }
}
public class  {
}