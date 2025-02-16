import java.util.Scanner;

public class ChatBot{
    //each bot tracks it's own state to see if it's tired
    boolean tired = false;

    //initializes the conversation by asking for a name and creating a scanner
    public static void main(String[] args){
        ChatBot BeeMO = new ChatBot();
        int discussionCount = 0;
        Scanner scan = new Scanner(System.in);
        System.out.print("BeeMO: Hello! I am BeeMO - your friendly chatbot! Could you tell me your name?\nYou: ");
        String name = scan.nextLine();

        System.out.print("BeeMO: Nice to meet you, " + name + "! ");
        BeeMO.discussion(0, scan, discussionCount, name);

        scan.close();
    }



    public void discussion(int firstMessageType, Scanner scan, int discussionCount,String name){
        if (firstMessageType == 0){
            System.out.println("What would you like to talk about? Here are some suggestions:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Burnout   ||");
        }
        if (firstMessageType == 1){
            if(tired){
                System.out.println("BeeMO: Again? Damn, well here are your options:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Burnout   ||");
            }
            else{
                System.out.println("BeeMO: Alright, lets keep talking! Go ahead and choose our next topic:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Burnout   ||");
            }
        }
        if (firstMessageType == 2){
            if(tired){
                System.out.println("BeeMO: I've got no clue what you're talking about. Try again and make sense this time");
            }
            else{
                System.out.println("BeeMO: Sorry, I didn't quite catch that, could you repeat what you wanted to talk about more clearly?");
            }
        }
        System.out.print(name + ": ");
        String input = scan.nextLine();


        //the honor discussion path
        if (input.toLowerCase().contains("honor")){
            if (tired){
                System.out.print("BeeMO: Honor's cool I guess. Do you agree that \"life is short, but honor lives forever\"?\n" + name + ": ");
            }
            else{
                System.out.print("BeeMO: Ahh, the core value of countless great men throughout history! It is a common saying that \"life is short, but honor is immortal\". Would you agree with this statement?\n" + name + ": ");
            }

            input = scan.nextLine();

            if (input.toLowerCase().contains("ye")){
                if (tired){
                    System.out.print("BeeMO: Wow, pretty old-school. So you're very honorable, huh?\n" + name + ": ");
                }
                else{
                    System.out.print("BeeMO: You would have done great in ancient Rome! Would you say that honor ranks very highly among your values?\n" + name + ": ");
                }

                input = scan.nextLine();

                if (input.toLowerCase().contains("ye")){
                    if (tired){
                        System.out.print("BeeMO: Well hoop-dee-doo we have a hero over here. You wanna talk again?\n" + name + ": ");
                    }
                    else{
                        System.out.print("BeeMO: It's nice to hear that honor is a core value of yours! People most likely don't hesitate to designate you as a leading figure when the situation calls for it. Would you like to have another conversation?\n" + name + ": ");
                    }
                    
                    repeatDiscussion(scan, discussionCount, name);
                }

                if (input.toLowerCase().contains("no")){
                    if (tired){
                        System.out.print("BeeMO: Well hoop-dee-doo we have a hero over here. You wanna talk again?\n" + name + ": ");
                    }
                    else{
                        System.out.print("BeeMO: It's nice to hear that honor is a core value of yours! People most likely don't hesitate to designate you as a leading figure when the situation calls for it. Would you like to have another conversation?\n" + name + ": ");
                    }
                    
                    repeatDiscussion(scan, discussionCount, name);
                }
            }

            else if (input.toLowerCase().contains("no")){
                if (tired){
                    System.out.print("BeeMO: Could've guessed huh?\n" + name + ": ");
                }
                else{
                    System.out.print("BeeMO: You would have done great in ancient Rome! Would you say that honor ranks very highly among your values?\n" + name + ": ");
                }

                input = scan.nextLine();

                if (input.toLowerCase().contains("ye")){
                    if (tired){
                        System.out.print("BeeMO: Well hoop-dee-doo we have a hero over here. You wanna talk again?\n" + name + ": ");
                    }
                    else{
                        System.out.print("BeeMO: It's nice to hear that honor is a core value of yours! People most likely don't hesitate to designate you as a leading figure when the situation calls for it. Would you llike to have another conversation?\n" + name + ": ");
                    }

                    repeatDiscussion(scan, discussionCount, name);
                }

                if (input.toLowerCase().contains("no")){
                    if (tired){
                        System.out.print("BeeMO: Well hoop-dee-doo we have a hero over here. You wanna talk again?\n" + name + ": ");
                    }
                    else{
                        System.out.print("BeeMO: It's nice to hear that honor is a core value of yours! People most likely don't hesitate to designate you as a leading figure when the situation calls for it. Would you llike to have another conversation?\n" + name + ": ");
                    }
                    
                    repeatDiscussion(scan, discussionCount, name);
                }
            }

            else {
                discussion(2, scan, discussionCount, name);
            }
        }



        else if (input.toLowerCase().contains("rodents")){
            
        }



        else if (input.toLowerCase().contains("life") || input.toLowerCase().contains("meaning")){

        }



        else if (input.toLowerCase().contains("burnout")){

        }

        else if (input.toLowerCase().contains("stop") || input.toLowerCase().contains("bye")){
            if (tired){
                System.out.println("BeeMO: Thank god.");
            }
            else{
                System.out.println("BeeMO: Goodbye! See you next time.");
            }
        }

        else {
            discussion(2, scan, discussionCount, name);
        }
    }

    public void repeatDiscussion(Scanner scan, int discussionCount, String name){
        discussionCount += 1;
        if (discussionCount >= 3){
            tired = true;
        }

        String input = scan.nextLine();
        if (input.toLowerCase().contains("ye")){
            discussion(1, scan, discussionCount, name);
        }
        else {
            if (tired){
                System.out.println("BeeMO: Thank god.");
            }
            else{
                System.out.println("BeeMO: Goodbye! See you next time.");
            }
        }
    }

}