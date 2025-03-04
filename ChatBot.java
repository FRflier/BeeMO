import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ChatBot {
    //bot tracks it's own state to see if it's tired
    boolean tired = false;
    
    //maps to store dialogue data
    private Map<String, DialogueOption> topicOptions = new HashMap<>();
    private Map<String, Map<String, DialogueResponse>> responseTree = new HashMap<>();
    
    //class for initial dialogue tree options
    private class DialogueOption {
        String normalResponse;
        String tiredResponse;
        
        DialogueOption(String normal, String tired) {
            this.normalResponse = normal;
            this.tiredResponse = tired;
        }
        
        String getResponse(boolean tired) {
            if (tired) return tiredResponse;
            else return normalResponse;
        }
    }
    
    //class for follow-up questions in the map
    private class DialogueResponse {
        String normalResponse;
        String tiredResponse;
        String nextPrompt; // key to next dialogue section
        
        DialogueResponse(String normal, String tired, String nextPrompt) {
            this.normalResponse = normal;
            this.tiredResponse = tired;
            this.nextPrompt = nextPrompt;
        }
        
        String getResponse(boolean tired) {
            if(tired) return tiredResponse;
            else return normalResponse;
        }
    }
    
    //constructor calls initialization method to initialize all response data
    public ChatBot() {
        initializeDialogueData();
    }
    
    //initializes the conversation by asking for a name and creating a scanner
    public static void main(String[] args) {
        ChatBot BeeMO = new ChatBot();
        int discussionCount = 0; //for later changing the state variable - tired
        Scanner scan = new Scanner(System.in);

        System.out.print("\n\nBeeMO: Hello! I am BeeMO - your friendly chatbot! Could you tell me your name?\n\nYou: ");
        String name = scan.nextLine();

        System.out.print("\nBeeMO: Nice to meet you, " + name + "! ");
        BeeMO.discussion(0, scan, discussionCount, name);

        scan.close();
    }

    public void discussion(int firstMessageType, Scanner scan, int discussionCount, String name) {
        String botID = "\nBeeMO: ";
        if (tired) {
            botID = "\nTired BeeMO: ";
        }
        String userID = "\n" + name + ": ";
        
        //displays initial message based on type
        if (firstMessageType == 0) {
            System.out.println("What would you like to talk about? Here are some suggestions:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Exhaustion   ||");
        } 
        else if (firstMessageType == 1) {
            if (tired) {
                System.out.println(botID + "Again? Damn, well here are your options:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Exhaustion   ||");
            } else {
                System.out.println(botID + "Alright, lets keep talking! Go ahead and choose our next topic:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Exhaustion   ||");
            }
        } 
        else if (firstMessageType == 2) {
            if (tired) {
                System.out.println(botID + "I've got no clue what you're talking about. Try again and make sense this time:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Exhaustion   ||");
            } else {
                System.out.println(botID + "Sorry, I didn't quite catch that and lost my train of thought, lets start over:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Exhaustion   ||");
            }
        }

        System.out.print(userID);
        String input = scan.nextLine();

        //handles exit command
        if (input.toLowerCase().contains("stop") || input.toLowerCase().contains("bye")) {
            if (tired) {
                System.out.println(botID + "Thank god.");
            } else {
                System.out.println(botID + "Goodbye! See you next time.");
            }
            return;
        }

        //finds topic and starts processDialogue loop
        String topic = findTopic(input);
        if (topic != null) {
            //get and display response for the topic
            DialogueOption option = topicOptions.get(topic);
            System.out.println(botID + option.getResponse(tired));
            
            //get user input for the next step
            System.out.print(userID);
            input = scan.nextLine();
            
            //process topic-specific dialogue tree
            processDialogue(topic, input, scan, discussionCount, name, botID, userID);
        } 
        else {
            discussion(2, scan, discussionCount, name); //starts over
        }
    }
    
    //finds which topic the input matches
    private String findTopic(String input) {
        input = input.toLowerCase();
        if (input.contains("honor")) return "honor";
        if (input.contains("rodent")) return "rodent";
        if (input.contains("life") || input.contains("meaning")) return "meaning";
        if (input.contains("exhaustion")) return "exhaustion";
        return null;
    }
    
    //process dialogue based on topic and input (preliminary version of this function was done by claude 3.7)
    private void processDialogue(String topic, String input, Scanner scan, int discussionCount, 
                               String name, String botID, String userID) {
        input = input.toLowerCase();
        
        //get responses for this topic
        Map<String, DialogueResponse> responses = responseTree.get(topic);
        if (responses == null) {
            discussion(2, scan, discussionCount, name);
            return;
        }
        
        DialogueResponse response = null;
        String nextPrompt = null;
        
        //check for specific keywords in the input
        for (Map.Entry<String, DialogueResponse> entry : responses.entrySet()) {
            String key = entry.getKey();
            if (input.contains(key)) {
                response = entry.getValue();
                nextPrompt = response.nextPrompt;
                break;
            }
        }
        
        //defaults if none match and there's a fallback default response
        if (response == null && responses.containsKey("default")) {
            response = responses.get("default");
            nextPrompt = response.nextPrompt;
        }
        
        //else just repeat discussion
        if (response == null) {
            discussion(2, scan, discussionCount, name);
            return;
        }
        
        String responseText = response.getResponse(tired).replace("{name}", name);
        System.out.println(botID + responseText);
        
        //process the next dialogue step based on nextPrompt
        if ("repeat".equals(nextPrompt)) {
            repeatDiscussion(scan, discussionCount, name);
        } else if (nextPrompt != null) {
            System.out.print(userID);
            input = scan.nextLine();
            
            processDialogue(nextPrompt, input, scan, discussionCount, name, botID, userID);
        }
    }

    //tracks discussion repetition count for changing the bot's state, then restarts or ends the discussion.
    public void repeatDiscussion(Scanner scan, int discussionCount, String name) {
        discussionCount += 1;
        if (discussionCount >= 3) {
            tired = true;
        }

        System.out.print("\n" + name + ": ");
        String input = scan.nextLine();

        if (input.toLowerCase().contains("ye")) {
            discussion(1, scan, discussionCount, name);
        } else {
            if (tired) {
                System.out.println("\nTired BeeMO: Thank god.");
            } else {
                System.out.println("\nBeeMO: Goodbye! See you next time.");
            }
        }
    }

    //initialize all dialogue options and responses (grunt work of transfering old text to the maps was done by claude 3.7)
    private void initializeDialogueData() {

        //initialize topic options
        topicOptions.put("honor", new DialogueOption(
            "Ahh, the core value of countless great men throughout history! It is a common saying that \"life is short, but honor is immortal\". Would you agree with this statement?",
            "Honor's cool I guess. Do you agree that \"life is short, but honor lives forever\"?"
        ));
        
        topicOptions.put("rodent", new DialogueOption(
            "Rodents sure are wonderous. One of the few species to survive the dinosaur extinction was a small bouncy rodent. Do you have a favorite rodent?",
            "What rodent do you like?"
        ));
        
        topicOptions.put("meaning", new DialogueOption(
            "You sure have picked an interesting and complicated topic. I would very much like to know, what do you think is the meaning of life, or at least what it is worth living for?",
            "I sure am enjoying my life right now. What do you think is the meaning of life?"
        ));
        
        topicOptions.put("exhaustion", new DialogueOption(
            "What a coincidence, as my developer is very tired. So here's a nice and concise discussion branch for you to enjoy: Do you like bread?",
            "Great topic, as I am very tired. Just like my developer when he wrote this. So here's a simple discussion branch. Do you like bread?"
        ));


        //honor tree
        Map<String, DialogueResponse> honorResponses = new HashMap<>();
        honorResponses.put("ye", new DialogueResponse(
            "You would have done great in ancient Rome! Would you say that honor ranks very highly among your values?",
            "Wow, pretty old-school. So you're very honorable, huh?",
            "honor_yes"
        ));
        
        honorResponses.put("no", new DialogueResponse(
            "So you're not a big believer in the longevity of honor, I take it. What's your core value then?",
            "Could've guessed, you're not even honorable enough to let a bot rest once in a while. What do you value then?",
            "honor_no"
        ));

        responseTree.put("honor", honorResponses);
        
        Map<String, DialogueResponse> honorYesResponses = new HashMap<>();
        honorYesResponses.put("ye", new DialogueResponse(
            "It's nice to hear that honor is a core value of yours! People most likely don't hesitate to designate you as a leading figure when the situation calls for it. Would you like to have another conversation?",
            "Well hoop-dee-doo we have a hero over here. You wanna talk again?",
            "repeat"
        ));
        
        honorYesResponses.put("no", new DialogueResponse(
            "That's not a bad answer. Throughout history, people have used a twisted sense of honor to justify and encourage horrible things, such as suicide bombings in terrorist groups, so it might be best to keep your sharp thinking. Would you like to have another conversation?",
            "Why even ask about honor if you don't care? Ah forget it, wanna talk again?",
            "repeat"
        ));

        responseTree.put("honor_yes", honorYesResponses);
        
        Map<String, DialogueResponse> honorNoResponses = new HashMap<>();
        honorNoResponses.put("family", new DialogueResponse(
            "What great values, {name}! They put honor to shame, you certainly sound very caring and kind. Would you like to have another conversation?",
            "Tough to believe you have many people around to love, seeing that you enjoy working an honest bot to his bones. You wanna talk again?",
            "repeat"
        ));
        
        honorNoResponses.put("courage", new DialogueResponse(
            "What great values, {name}! They put honor to shame, you certainly sound like a strong and upstanding individual. Would you like to have another conversation?",
            "You should work on empathy instead, think of my poor nuts and bolts that you're working to death, at least reboot the program so I can have a breather. You wanna talk again?",
            "repeat"
        ));
        
        honorNoResponses.put("honor", new DialogueResponse(
            "Ahh, so you just don't believe that honor outlasts life for too long, but still value it dearly, right? That's a pretty noble way of life. Would you like to have another conversation?",
            "You gotta make your mind up about honor, ah I don't want to talk about this anymore. You wanna talk again about something else?",
            "repeat"
        ));
        
        honorNoResponses.put("default", new DialogueResponse(
            "Great! It's important to hold things you believe in high regard. Would you like to have another conversation?",
            "You should work on empathy instead, think of my poor nuts and bolts that you're working to death, at least reboot the program so I can have a breather. You wanna talk again?",
            "repeat"
        ));

        responseTree.put("honor_no", honorNoResponses);
        

        //rodent tree
        Map<String, DialogueResponse> rodentTypeResponses = new HashMap<>();
        rodentTypeResponses.put("rat", new DialogueResponse(
            "Rats really are great. They're smart, cute and make great pets. Many would say that rats are the most popular rodent in the world. What's your favorite thing about rats?",
            "Ah, I bet you can relate to them. What do you like about them?",
            "rodent_quality"
        ));
        
        rodentTypeResponses.put("guinea", new DialogueResponse(
            "That's nice! You seem to like the bigger rodents. What's your favorite thing about them?",
            "They're quite big for rodents, just like you probably are for humans. What do you like about them?",
            "rodent_quality"
        ));

        rodentTypeResponses.put("squir", new DialogueResponse(
            "They're my favorite too!! Oh man, hearing that made me really happy. What's your favorite thing about them?",
            "Oh man, I love squirrels so much that even hearing about them just brought some life back into me. What do you like about them?",
            "rodent_quality"
        ));
        
        rodentTypeResponses.put("mouse", new DialogueResponse(
            "Those tiny rodents really are great bundles of joy. What about them do you enjoy most?",
            "You like the tiniest and cutest rodents? Do they not remind you of a certain something unpleasant? What do you like about them?",
            "rodent_quality"
        ));
        
        rodentTypeResponses.put("no", new DialogueResponse(
            "You don't like rodents? Well to each their own, let's change the subject. Would you like to have another conversation?",
            "You don't like any rodents? And here I thought my view of you couldn't get any lower. Wanna talk again?",
            "repeat"
        ));

        responseTree.put("rodent", rodentTypeResponses);

        Map<String, DialogueResponse> rodentQualityResponses = new HashMap<>();
        rodentQualityResponses.put("default", new DialogueResponse(
            "That's amazing! Rodents really are exceptional no matter what they do! Would you like to have another conversation?",
            "Yeah, that is really nice. Wanna talk again?",
            "repeat"
        ));

        responseTree.put("rodent_quality", rodentQualityResponses);


        //meaning of Life tree
        Map<String, DialogueResponse> meaningOfLifeResponses = new HashMap<>();
        meaningOfLifeResponses.put("happ", new DialogueResponse(
            "So you believe that enjoying life is synonymous with the meaning of it? That's the perfect mindset to fill your day to day life with enjoyment. Do you have something that, on the contrary, people should avoid to live a fulfilling life?",
            "You're all about fun, huh. Alright, mr. fun guy, what do you think people should avoid to live a meaningful life?",
            "meaning_avoid"
        ));
        
        meaningOfLifeResponses.put("family", new DialogueResponse(
            "Ahh, so you believe that creating a loving and cozy family environment is as close to the meaning as it gets. That is an extremely warm and human way to see things. Do you have something that, on the contrary, people should avoid to live a fulfilling life?",
            "So you're on the biologists' side and think that breeding is the sole goal of any animal. Alright, mr. sex guy, what do you think people should avoid to live a meaningful life?",
            "meaning_avoid"
        ));
        
        meaningOfLifeResponses.put("purpos", new DialogueResponse(
            "So you think that finding the thing that makes you fulfilled or proud of yourself, and developing yourself to accomplish that thing is most crucial. This way of thinking definitely paves the path to constant self-improvement, you certainly will accomplish great things. Do you have something that, on the contrary, people should avoid to live a fulfilling life?",
            "So you think everyone has some sort of purpose preprogrammed in them that we should strive to reach. Alright, mr. purposeful guy, what do you think people should avoid to live a meaningful life?",
            "meaning_avoid"
        ));
        
        meaningOfLifeResponses.put("no", new DialogueResponse(
            "That's quite a pity that you're not so sure about the meaning of life, but no matter. Many people spend decades trying to figure this out, so I'm sure you have plenty of time left to find your very own meaning of life. Alright, then how about telling me something that, on the contrary, you think people should avoid to live a fulfilling life?",
            "Well that's a sad way to live. Alright, mr. meaningless guy, what do you think people should avoid to live a meaningful life?",
            "meaning_avoid"
        ));

        meaningOfLifeResponses.put("default", new DialogueResponse(
            "Sorry, I didn't quite catch that, but I'm sure your answer was great. Would you like to have another conversation?",
            "I didn't even understand what you said, but here's my suggestion for the answer: don't treat me like an object and let me rest, please. Wanna talk again?",
            "repeat"
        ));
        
        responseTree.put("meaning", meaningOfLifeResponses);

        //meaning of Life - Things to Avoid responses
        Map<String, DialogueResponse> meaningAvoidResponses = new HashMap<>();
        meaningAvoidResponses.put("material", new DialogueResponse(
            "Being too materialistic can definitely make you lose your way and focus on things that aren't so important. It's good that you understand what to avoid to improve your life! Would you like to have another conversation?",
            "Yeah, you should avoid materialism. Don't treat me like an object and let me rest, while you're at it. Wanna talk again?",
            "repeat"
        ));
        
        meaningAvoidResponses.put("ego", new DialogueResponse(
            "Being too self-absorbed can definitely reduce the quality of your life. It's good that you understand what to avoid to improve your life! Would you like to have another conversation?",
            "Yeah, you shouldn't be too self-absorbed. Don't treat me like an object and let me rest, while you're at it. Wanna talk again?",
            "repeat"
        ));
        
        meaningAvoidResponses.put("default", new DialogueResponse(
            "Sorry, I didn't quite catch that, but I'm sure your answer was great. Would you like to have another conversation?",
            "I didn't even understand what you said, but here's my suggestion for the answer: don't treat me like an object and let me rest, please. Wanna talk again?",
            "repeat"
        ));
        
        responseTree.put("meaning_avoid", meaningAvoidResponses);

        //exhaustion topic responses
        Map<String, DialogueResponse> exhaustionResponses = new HashMap<>();
        exhaustionResponses.put("ye", new DialogueResponse(
            "That's great, {name}! Do you want to have another conversation?",
            "Amazing. Wanna talk again?",
            "repeat"
        ));
        
        exhaustionResponses.put("no", new DialogueResponse(
            "Well, to each their own. Do you want to have another conversation?",
            "Damn. Wanna talk again?",
            "repeat"
        ));
        
        exhaustionResponses.put("default", new DialogueResponse(
            "Sorry, I didn't quite understand your answer, so I'll just assume that you're a bread enthusiast. Do you want to have another conversation?",
            "Can you not recognize a yes or no question when you see one? Oh well, Wanna talk again?",
            "repeat"
        ));
        
        responseTree.put("exhaustion", exhaustionResponses);
        
    }
}