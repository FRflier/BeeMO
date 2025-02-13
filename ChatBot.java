import java.util.Scanner;

public class ChatBot{

    boolean tired = false;

    public static void main(String[] args){
        ChatBot BeeMO = new ChatBot();
        int discussionCount = 0;
        Scanner scan = new Scanner(System.in);
        System.out.print("BeeMO: Hello! I am BeeMO - your friendly chatbot! Could you tell me your name?\nYou: ");
        String name = scan.nextLine();

        System.out.print("BeeMO: Nice to meet you, " + name + "!");
        BeeMO.discussion(true, scan, discussionCount);

        scan.close();
    }

    public void discussion(boolean firstTime, Scanner scan, int discussionCount){
        if (firstTime){
            System.out.println("What would you like to talk about? Here are some suggestions:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Burnout   ||");
        }
        else{
            if(tired){
                System.out.println("BeeMO: Again? Damn, well here are your options:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Burnout   ||");
            }
            else{
                System.out.println("BeeMO: Alright, lets keep talking! Go ahead and choose our next topic:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Burnout   ||");
            }
        }
        System.out.print("You: ");
        String input = scan.nextLine();



        if (input.toLowerCase().contains("honor")){
            if (tired){
                System.out.println("BeeMO: Honor's cool I guess. Do you agree that \"life is short, but honor lives forever\"\nYou: ");
            }
            else{
                System.out.println("BeeMO: Ahh, the core value of countless great men throughout history! It is a common saying that \"life is short, but honor is immortal\". Would you agree with this statement?\nYou: ");
            }

            input = scan.nextLine();

            if (input.toLowerCase().contains("ye")){
                if (tired){
                    System.out.println("BeeMO: You would have done great in Rome?\nYou: ");
                }
                else{
                    System.out.println("BeeMO: Honor's cool I guess. Do you agree that \"life is short, but honor lives forever\"?\nYou: ");
                }

                input = scan.nextLine();


            }

        }



        if (input.toLowerCase().contains("rodents")){
            
        }



        if (input.toLowerCase().contains("life") || input.toLowerCase().contains("meaning")){

        }



        if (input.toLowerCase().contains("burnout")){

        }

    }
}