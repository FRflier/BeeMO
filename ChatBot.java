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
                System.out.println("Tired BeeMO: Again? Damn, well here are your options:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Burnout   ||");
            }
            else{
                System.out.println("BeeMO: Alright, lets keep talking! Go ahead and choose our next topic:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Burnout   ||");
            }
        }
        if (firstMessageType == 2){
            if(tired){
                System.out.println("Tired BeeMO: I've got no clue what you're talking about. Try again and make sense this time");
            }
            else{
                System.out.println("BeeMO: Sorry, I didn't quite catch that and lost my train of thought, lets start over!");
            }
        }
        System.out.print(name + ": ");
        String input = scan.nextLine();



        if (input.toLowerCase().contains("honor")){
            if (tired){
                System.out.print("Tired BeeMO: Honor's cool I guess. Do you agree that \"life is short, but honor lives forever\"?\n" + name + ": ");
            }
            else{
                System.out.print("BeeMO: Ahh, the core value of countless great men throughout history! It is a common saying that \"life is short, but honor is immortal\". Would you agree with this statement?\n" + name + ": ");
            }

            input = scan.nextLine();

            if (input.toLowerCase().contains("ye")){
                if (tired){
                    System.out.print("Tired BeeMO: Wow, pretty old-school. So you're very honorable, huh?\n" + name + ": ");
                }
                else{
                    System.out.print("BeeMO: You would have done great in ancient Rome! Would you say that honor ranks very highly among your values?\n" + name + ": ");
                }

                input = scan.nextLine();

                if (input.toLowerCase().contains("ye")){
                    if (tired){
                        System.out.print("Tired BeeMO: Well hoop-dee-doo we have a hero over here. You wanna talk again?\n" + name + ": ");
                    }
                    else{
                        System.out.print("BeeMO: It's nice to hear that honor is a core value of yours! People most likely don't hesitate to designate you as a leading figure when the situation calls for it. Would you like to have another conversation?\n" + name + ": ");
                    }
                    
                    repeatDiscussion(scan, discussionCount, name);
                }

                else if (input.toLowerCase().contains("no")){
                    if (tired){
                        System.out.print("Tired BeeMO: Why even ask about honor if you don't care? Ah forget it, wanna talk again?\n" + name + ": ");
                    }
                    else{
                        System.out.print("BeeMO: That's not a bad answer. Throughout history, people have used a twisted sense of honor to justify and encourage horrible things, such as suicide bombings in terrorist groups, so it might be best to keep your sharp thinking. Would you like to have another conversation?\n" + name + ": ");
                    }
                    
                    repeatDiscussion(scan, discussionCount, name);
                }

                else {
                    discussion(2, scan, discussionCount, name);
                }
            }

            else if (input.toLowerCase().contains("no")){
                if (tired){
                    System.out.print("Tired BeeMO: Could've guessed, you're not even honorable enough to let a bot rest once in a while. What do you value then?\n" + name + ": ");
                }
                else{
                    System.out.print("BeeMO: So you're not a big believer in the longevity of honor, I take it. What's your core value then?\n" + name + ": ");
                }

                input = scan.nextLine();

                if (input.toLowerCase().contains("family") || input.toLowerCase().contains("friend") || input.toLowerCase().contains("love") || input.toLowerCase().contains("empathy")){
                    if (tired){
                        System.out.print("Tired BeeMO: Tough to believe you have many people around to love, seeing that you enjoy working an honest bot to his bones. You wanna talk again?\n" + name + ": ");
                    }
                    else{
                        System.out.print("BeeMO: What great values, " + name + "! They put honor to shame, you certainly sound very caring and kind. Would you like to have another conversation?\n" + name + ": ");
                    }

                    repeatDiscussion(scan, discussionCount, name);
                }

                else if (input.toLowerCase().contains("courage") || input.toLowerCase().contains("resilience") || input.toLowerCase().contains("leadership") || input.toLowerCase().contains("trust")){
                    if (tired){
                        System.out.print("Tired BeeMO: You should work on empathy instead, think of my poor nuts and bolts that you're working to death, at least reboot the program so I can have a breather. You wanna talk again?\n" + name + ": ");
                    }
                    else{
                        System.out.print("BeeMO: What great values, " + name + "! They put honor to shame, you certainly sound like a strong and upstanding individual. Would you like to have another conversation?\n" + name + ": ");
                    }

                    repeatDiscussion(scan, discussionCount, name);
                }
                
                else if (input.toLowerCase().contains("honor")){
                    if (tired){
                        System.out.print("Tired BeeMO: You gotta make your mind up about honor, ah I don't want to talk about this anymore. You wanna talk again about something else?\n" + name + ": ");
                    }
                    else{
                        System.out.print("BeeMO: Ahh, so you just don't believe that honor outlasts life for too long, but still value it dearly, right? That's a pretty noble way of life. Would you like to have another conversation?\n" + name + ": ");
                    }

                    repeatDiscussion(scan, discussionCount, name);
                }

                else{
                    if (tired){
                        System.out.print("Tired BeeMO: You should work on empathy instead, think of my poor nuts and bolts that you're working to death, at least reboot the program so I can have a breather. You wanna talk again?\n" + name + ": ");
                    }
                    else{
                        System.out.print("BeeMO: Great! It's important to hold things you believe inhigh regard. Would you like to have another conversation?\n" + name + ": ");
                    }

                    repeatDiscussion(scan, discussionCount, name);
                }
            }

            else {
                discussion(2, scan, discussionCount, name);
            }
        }



        else if (input.toLowerCase().contains("rodent")){
            if (tired){
                System.out.print("Tired BeeMO: What rodent do you like?\n" + name + ": ");
            }
            else{
                System.out.print("BeeMO: Rodents sure are wonderous. One of the few species to survive the dinosaur extinction was a small bouncy rodent. Do you have a favorite rodent?\n" + name + ": ");
            }

            input = scan.nextLine();

            if (input.toLowerCase().contains("rat")){
                if (tired){
                    System.out.print("Tired BeeMO: Ah, I bet you can relate to them. What do you like about them?\n" + name + ": ");
                }
                else{
                    System.out.print("BeeMO: Rats really are great. They're smart, cute and make great pets. Many would say that rats are the most popular rodent in the world. What's your favorite thing about rats?\n" + name + ": ");
                }
            }
            
            else if (input.toLowerCase().contains("guinea") || input.toLowerCase().contains("chinchil") || input.toLowerCase().contains("capybara")){
                if (tired){
                    System.out.print("Tired BeeMO: They're quite big for rodents, just like you probably are for humans. What do you like about them?\n" + name + ": ");
                }
                else{
                    System.out.print("BeeMO: That's nice! You seem to like the bigger rodents. What's your favorite thing about them?\n" + name + ": ");
                }
            }

            else if (input.toLowerCase().contains("mouse") || input.toLowerCase().contains("hamster")){
                if (tired){
                    System.out.print("Tired BeeMO: You like the tiniest and cutest rodents? Do they not remind you of a certain something unpleasant? What do you like about them?\n" + name + ": ");
                }
                else{
                    System.out.print("BeeMO: Those tiny rodents really are great bundles of joy. What about them do you enjoy most?\n" + name + ": ");
                }
            }

            else if (input.toLowerCase().contains("no")){
                if (tired){
                    System.out.print("Tired BeeMO: You don't like any rodents? And here I thought my view of you couldn't get any lower. Wanna talk again?\n" + name + ": ");
                }
                else{
                    System.out.print("BeeMO: You don't like rodents? Well to each their own, let's change the subject. Would you like to have another conversation?\n" + name + ": ");
                }
                
                repeatDiscussion(scan, discussionCount, name);
           }

            else {
                discussion(2, scan, discussionCount, name);
            }
        
            if (tired){
                System.out.print("Tired BeeMO: Yeah, that's really nice. Wanna talk again?\n" + name + ": ");
            }
            else{
                System.out.print("BeeMO: Rodents really are special no matter how we look at them. Would you like to have another conversation?\n" + name + ": ");
            }

            repeatDiscussion(scan, discussionCount, name);

        }



        else if (input.toLowerCase().contains("life") || input.toLowerCase().contains("meaning")){

        }



        else if (input.toLowerCase().contains("burnout")){

        }

        else if (input.toLowerCase().contains("stop") || input.toLowerCase().contains("bye")){
            if (tired){
                System.out.println("Tired BeeMO: Thank god.");
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
                System.out.println("Tired BeeMO: Thank god.");
            }
            else{
                System.out.println("BeeMO: Goodbye! See you next time.");
            }
        }
    }

}