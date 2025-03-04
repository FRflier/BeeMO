import java.util.Scanner;

public class ChatBot{
    //Bot tracks it's own state to see if it's tired
    boolean tired = false;

    //kodo pakeitimo checkas su branchu "decoupling"

    //initializes the conversation by asking for a name and creating a scanner
    public static void main(String[] args){

        ChatBot BeeMO = new ChatBot();
        int discussionCount = 0; //For later changing the state variable - tired
        Scanner scan = new Scanner(System.in);

        System.out.print("\n\nBeeMO: Hello! I am BeeMO - your friendly chatbot! Could you tell me your name?\n\nYou: ");
        String name = scan.nextLine();

        System.out.print("\nBeeMO: Nice to meet you, " + name + "! ");
        BeeMO.discussion(0, scan, discussionCount, name);

        scan.close();
    }



    public void discussion(int firstMessageType, Scanner scan, int discussionCount,String name){

        String botID = "\nBeeMO: ";
        if(tired){
            botID = "\nTired BeeMO: ";
        }
        String userID = "\n" + name + ": ";
        
        //Type of the first message after starting the bot and getting the user's name
        if (firstMessageType == 0){
            System.out.println("What would you like to talk about? Here are some suggestions:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Exhaustion   ||");
        }
        //Usual discussion repeat
        if (firstMessageType == 1){
            if(tired){
                System.out.println(botID + "Again? Damn, well here are your options:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Exhaustion   ||");
            }
            else{
                System.out.println(botID + "Alright, lets keep talking! Go ahead and choose our next topic:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Exhaustion   ||");
            }
        }
        //Discussion repeat after the bot doesn't understand something
        if (firstMessageType == 2){
            if(tired){
                System.out.println(botID + "I've got no clue what you're talking about. Try again and make sense this time:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Exhaustion   ||");
            }
            else{
                System.out.println(botID + "Sorry, I didn't quite catch that and lost my train of thought, lets start over:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Exhaustion   ||");
            }
        }

        System.out.print(userID);
        String input = scan.nextLine();


        //The four possible discussion branches will follow:
        if (input.toLowerCase().contains("honor")){
            if (tired){
                System.out.println(botID + "Honor's cool I guess. Do you agree that \"life is short, but honor lives forever\"?");
            }
            else{
                System.out.println(botID + "Ahh, the core value of countless great men throughout history! It is a common saying that \"life is short, but honor is immortal\". Would you agree with this statement?");
            }

            System.out.print(userID);
            input = scan.nextLine();

            if (input.toLowerCase().contains("ye")){
                if (tired){
                    System.out.println(botID + "Wow, pretty old-school. So you're very honorable, huh?");
                }
                else{
                    System.out.println(botID + "BeeMO: You would have done great in ancient Rome! Would you say that honor ranks very highly among your values?");
                }

                System.out.print(userID);
                input = scan.nextLine();

                if (input.toLowerCase().contains("ye")){
                    if (tired){
                        System.out.println(botID + "Well hoop-dee-doo we have a hero over here. You wanna talk again?");
                    }
                    else{
                        System.out.println(botID + "It's nice to hear that honor is a core value of yours! People most likely don't hesitate to designate you as a leading figure when the situation calls for it. Would you like to have another conversation?");
                    }
                    repeatDiscussion(scan, discussionCount, name);
                }

                else if (input.toLowerCase().contains("no")){
                    if (tired){
                        System.out.println(botID + "Why even ask about honor if you don't care? Ah forget it, wanna talk again?");
                    }
                    else{
                        System.out.println(botID + "That's not a bad answer. Throughout history, people have used a twisted sense of honor to justify and encourage horrible things, such as suicide bombings in terrorist groups, so it might be best to keep your sharp thinking. Would you like to have another conversation?");
                    }
                    repeatDiscussion(scan, discussionCount, name);
                }

                else {
                    discussion(2, scan, discussionCount, name);
                }
            }

            else if (input.toLowerCase().contains("no")){
                if (tired){
                    System.out.println(botID + "Could've guessed, you're not even honorable enough to let a bot rest once in a while. What do you value then?");
                }
                else{
                    System.out.println(botID + "So you're not a big believer in the longevity of honor, I take it. What's your core value then?");
                }

                System.out.print(userID);
                input = scan.nextLine();

                if (input.toLowerCase().contains("family") || input.toLowerCase().contains("friend") || input.toLowerCase().contains("love") || input.toLowerCase().contains("empathy")){
                    if (tired){
                        System.out.println(botID + "Tough to believe you have many people around to love, seeing that you enjoy working an honest bot to his bones. You wanna talk again?");
                    }
                    else{
                        System.out.println(botID + "What great values, " + name + "! They put honor to shame, you certainly sound very caring and kind. Would you like to have another conversation?");
                    }
                    repeatDiscussion(scan, discussionCount, name);
                }

                else if (input.toLowerCase().contains("courage") || input.toLowerCase().contains("resilience") || input.toLowerCase().contains("leadership") || input.toLowerCase().contains("trust")){
                    if (tired){
                        System.out.println(botID + "You should work on empathy instead, think of my poor nuts and bolts that you're working to death, at least reboot the program so I can have a breather. You wanna talk again?");
                    }
                    else{
                        System.out.println(botID + "What great values, " + name + "! They put honor to shame, you certainly sound like a strong and upstanding individual. Would you like to have another conversation?");
                    }
                    repeatDiscussion(scan, discussionCount, name);
                }
                
                else if (input.toLowerCase().contains("honor")){
                    if (tired){
                        System.out.println(botID + "You gotta make your mind up about honor, ah I don't want to talk about this anymore. You wanna talk again about something else?");
                    }
                    else{
                        System.out.println(botID + "Ahh, so you just don't believe that honor outlasts life for too long, but still value it dearly, right? That's a pretty noble way of life. Would you like to have another conversation?");
                    }
                    repeatDiscussion(scan, discussionCount, name);
                }

                else{
                    if (tired){
                        System.out.println(botID + "You should work on empathy instead, think of my poor nuts and bolts that you're working to death, at least reboot the program so I can have a breather. You wanna talk again?");
                    }
                    else{
                        System.out.println(botID + "Great! It's important to hold things you believe inhigh regard. Would you like to have another conversation?");
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
                System.out.println(botID + "What rodent do you like?");
            }
            else{
                System.out.println(botID + "Rodents sure are wonderous. One of the few species to survive the dinosaur extinction was a small bouncy rodent. Do you have a favorite rodent?");
            }

            System.out.print(userID);
            input = scan.nextLine();

            if (input.toLowerCase().contains("rat")){
                if (tired){
                    System.out.println(botID + "Ah, I bet you can relate to them. What do you like about them?");
                }
                else{
                    System.out.println(botID + "Rats really are great. They're smart, cute and make great pets. Many would say that rats are the most popular rodent in the world. What's your favorite thing about rats?");
                }
            }
            
            else if (input.toLowerCase().contains("guinea") || input.toLowerCase().contains("chinchil") || input.toLowerCase().contains("capyba")){
                if (tired){
                    System.out.println(botID + "They're quite big for rodents, just like you probably are for humans. What do you like about them?");
                }
                else{
                    System.out.println(botID + "That's nice! You seem to like the bigger rodents. What's your favorite thing about them?");
                }
            }

            else if (input.toLowerCase().contains("squir")){
                if (tired){
                    System.out.println(botID + "Oh man, I love squirrels so much that even hearing about them just brought some life back into me. What do you like about them?");
                    discussionCount -= 2;
                    tired = false;
                }
                else{
                    System.out.println(botID + "They're my favorite too!! Oh man, hearing that made me really happy. What's your favorite thing about them?");
                }
            }

            else if (input.toLowerCase().contains("mouse") || input.toLowerCase().contains("hamster")){
                if (tired){
                    System.out.println(botID + "You like the tiniest and cutest rodents? Do they not remind you of a certain something unpleasant? What do you like about them?");
                }
                else{
                    System.out.println(botID + "Those tiny rodents really are great bundles of joy. What about them do you enjoy most?");
                }
            }

            else if (input.toLowerCase().contains("no") || input.toLowerCase().contains("don")){
                if (tired){
                    System.out.println(botID + "You don't like any rodents? And here I thought my view of you couldn't get any lower. Wanna talk again?");
                }
                else{
                    System.out.println(botID + "You don't like rodents? Well to each their own, let's change the subject. Would you like to have another conversation?");
                }
                
                repeatDiscussion(scan, discussionCount, name);
            }

            else {
                discussion(2, scan, discussionCount, name);
            }
            
            System.out.print(userID);
            input = scan.nextLine();

            if (tired){
                System.out.println(botID + "Yeah, that is really nice. Wanna talk again?");
            }
            else{
                System.out.println(botID + "That's amazing! Rodents really are exceptional no matter what they do! Would you like to have another conversation?");
            }
            repeatDiscussion(scan, discussionCount, name);
        }



        else if (input.toLowerCase().contains("life") || input.toLowerCase().contains("meaning")){
            if (tired){
                System.out.println(botID + "I sure am enjoying my life right now. What do you think is the meaning of life?");
            }
            else{
                System.out.println(botID + "You sure have picked an interesting and complicated topic. I would very much like to know, what do you think is the meaning of life, or at least what it is worth living for?");
            }

            System.out.print(userID);
            input = scan.nextLine();

            if (input.toLowerCase().contains("happ") || input.toLowerCase().contains("joy") || input.toLowerCase().contains("fun") || input.toLowerCase().contains("excit")){
                if (tired){
                    System.out.println(botID + "You're all about fun, huh. Alright, mr. fun guy, what do you think people should avoid to live a meaningful life?");
                }
                else{
                    System.out.println(botID + "So you believe that enjoying life is synonimous with the meaning of it? That's the perfect mindset to fill your day to day life with enjoyment. Do you have something that, on the contrary, people shoud avoid to live a fulfilling life?");
                }
            }
            
            else if (input.toLowerCase().contains("family") || input.toLowerCase().contains("kid") || input.toLowerCase().contains("child") || input.toLowerCase().contains("love")){
                if (tired){
                    System.out.println(botID + "So you're on the biologists' side and think that breeding is the sole goal of any animal. Alright, mr. sex guy, what do you think people should avoid to live a meaningful life?");
                }
                else{
                    System.out.println(botID + "Ahh, so you believe that creating a loving and cozy family environment is as close to the meaning as it gets. That is an extremely warm and human way to see things. Do you have something that, on the contrary, people shoud avoid to live a fulfilling life?");
                }
            }

            else if (input.toLowerCase().contains("purpose") || input.toLowerCase().contains("growth") || input.toLowerCase().contains("contribut") || input.toLowerCase().contains("proud") || input.toLowerCase().contains("pride")){
                if (tired){
                    System.out.println(botID + "So you think everyone has some sort of purpose preprogrammed in them that we should strive to reach. Alright, mr. purposeful guy, what do you think people should avoid to live a meaningful life?");
                }
                else{
                    System.out.println(botID + "So you think that finding the thing that makes you fulfilled or proud of yourself, and developing yourself to accomplish that thing is most crucial. This way of thinking definitely paves the path to constant self-improvement, you certainly will accomplish great things. Do you have something that, on the contrary, people shoud avoid to live a fulfilling life?");
                }
            }

            else if (input.toLowerCase().contains("no") || input.toLowerCase().contains("don") || input.toLowerCase().contains("meaningless") || input.toLowerCase().contains("know")){
                if (tired){
                    System.out.println(botID + "Well that's a sad way to live. Alright, mr. meaningless guy, what do you think people should avoid to live a meaningful life?");
                }
                else{
                    System.out.println(botID + "That's quite a pity that you're not so sure about the meaning of life, but no matter. Many people spend decades trying to figure this out, so I'm sure you have plenty of time left to find your very own meaning of life. Alright, then how about telling me something that, on the contrary, you think people shoud avoid to live a fulfilling life?");
                }
            }

            else {
                if (tired){
                    System.out.println(botID + "I'm too tired to even understand what you're yapping about. Alright, mr. smart guy, what do you think people should avoid to live a meaningful life?");
                }
                else{
                    System.out.println(botID + "Sorry, I didn't quite understand your ideas, they were too sophisticated for a mere robot like me. Could you tell me me something that, on the contrary, you think people shoud avoid to live a fulfilling life?");
                }
            }
            
            System.out.print(userID);
            input = scan.nextLine();

            if (input.toLowerCase().contains("material") || input.toLowerCase().contains("object") || input.toLowerCase().contains("money")){
                if (tired){
                    System.out.println(botID + "Yeah, you should avoid materialism. Don't treat me like an object and let me rest, while you're at it. Wanna talk again?");
                }
                else{
                    System.out.println(botID + "Being too materialistic can definitely make you lose your way and focus on things that aren't so important. It's good that you understand what to avoid to improve your life! Would you like to have another conversation?");
                }
                repeatDiscussion(scan, discussionCount, name);
            }

            else if (input.toLowerCase().contains("ego") || input.toLowerCase().contains("empath") || input.toLowerCase().contains("care") || input.toLowerCase().contains("hate") || input.toLowerCase().contains("judge")){
                if (tired){
                    System.out.println(botID + "Yeah, you shouldn't be too self absorbed. Don't treat me like an object and let me rest, while you're at it. Wanna talk again?");
                }
                else{
                    System.out.println(botID + "Being too self absorbed can definitely reduce the quality of your life. It's good that you understand what to avoid to improve your life! Would you like to have another conversation?");
                }
                repeatDiscussion(scan, discussionCount, name);
            }

            else{
                if (tired){
                    System.out.println(botID + "I didn't even understand what you said, but here's my suggestion for the answer: don't treat me like an object and let me rest, please. Wanna talk again?");
                }
                else{
                    System.out.println(botID + "Sorry, I didn't quite catch that, but I'm sure your answer was great. Would you like to have another conversation?");
                }
                repeatDiscussion(scan, discussionCount, name);
            }

        }



        else if (input.toLowerCase().contains("exhaustion")){
            if (tired){
                System.out.println(botID + "Great topic, as I am very tired. Just like my developer when he wrote this. So here's a simple discussion branch. Do you like bread?");
            }
            else{
                System.out.println(botID + "What a coincidence, as my developer is very tired. So here's a nice and concise discussion branch for you to enjoy: Do you like bread?");
            }

            System.out.print(userID);
            input = scan.nextLine();

            if (input.toLowerCase().contains("ye")){
                if (tired){
                    System.out.println(botID + "Amazing. Wanna talk again?");
                }
                else{
                    System.out.println(botID + "That's great, " + name + "! Do you want to have another conversation?");
                }
                repeatDiscussion(scan, discussionCount, name);
            }

            else if (input.toLowerCase().contains("no")){
                if (tired){
                    System.out.println(botID + "Damn. Wanna talk again?");
                }
                else{
                    System.out.println(botID + "Well, to each their own. Do you want to have another conversation?");
                }
                repeatDiscussion(scan, discussionCount, name);
            }

            else{
                if (tired){
                    System.out.println(botID + "Can you not recognize a yes or no question when you see one? Oh well, Wanna talk again?");
                }
                else{
                    System.out.println(botID + "Sorry, I didn't quite understand your answer, so I'l just assume that you're a bread enthusiast. Do you want to have another conversation?");
                }
                repeatDiscussion(scan, discussionCount, name);
            }
        }


        //Ends if the user types "stop" when prompted for the topic at the beginning of the discussion
        else if (input.toLowerCase().contains("stop") || input.toLowerCase().contains("bye")){
            if (tired){
                System.out.println(botID + "Thank god.");
            }
            else{
                System.out.println(botID + "Goodbye! See you next time.");
            }
        }

        //repeats the discussion if the bot doesn't understand what the user wants to talk about
        else {
            discussion(2, scan, discussionCount, name);
        }
    }

    //Tracks discussion repetition count for changing the bot's state, then restarts or ends the discussion.
    public void repeatDiscussion(Scanner scan, int discussionCount, String name){

        discussionCount += 1;
        if (discussionCount >= 3){
            tired = true;
        }

        System.out.print("\n" + name + ": ");
        String input = scan.nextLine();

        if (input.toLowerCase().contains("ye")){
            discussion(1, scan, discussionCount, name);
        }
        else {
            if (tired){
                System.out.println("\nTired BeeMO: Thank god.");
            }
            else{
                System.out.println("\nBeeMO: Goodbye! See you next time.");
            }
        }
    }

}