import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ChatBot {
    // Bot tracks it's own state to see if it's tired
    boolean tired = false;
    
    // Maps to store dialogue data
    private Map<String, DialogueOption> topicOptions = new HashMap<>();
    private Map<String, Map<String, DialogueResponse>> responseTree = new HashMap<>();
    
    // Inner class to represent a dialogue option
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
    
    // Inner class to represent a dialogue response with follow-ups
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
    
    // Constructor initializes dialogue data
    public ChatBot() {
        initializeDialogueData();
    }
    
    // Initialize all dialogue options and responses
    private void initializeDialogueData() {
        // Initialize topic options
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
        
        // Honor responses
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
        
        // Add more responses for other dialogues here...
        // Rodent responses
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
        
        // Store the responses in the response tree
        responseTree.put("honor", honorResponses);
        responseTree.put("honor_yes", honorYesResponses);
        responseTree.put("honor_no", honorNoResponses);
        responseTree.put("rodent", rodentTypeResponses);
    }
    
    // Initializes the conversation by asking for a name and creating a scanner
    public static void main(String[] args) {
        ChatBot BeeMO = new ChatBot();
        int discussionCount = 0; // For later changing the state variable - tired
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
        
        // Display initial message based on type
        if (firstMessageType == 0) {
            System.out.println("What would you like to talk about? Here are some suggestions:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Exhaustion   ||");
        } else if (firstMessageType == 1) {
            if (tired) {
                System.out.println(botID + "Again? Damn, well here are your options:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Exhaustion   ||");
            } else {
                System.out.println(botID + "Alright, lets keep talking! Go ahead and choose our next topic:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Exhaustion   ||");
            }
        } else if (firstMessageType == 2) {
            if (tired) {
                System.out.println(botID + "I've got no clue what you're talking about. Try again and make sense this time:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Exhaustion   ||");
            } else {
                System.out.println(botID + "Sorry, I didn't quite catch that and lost my train of thought, lets start over:\n||   Honor   ||   Rodents   ||   Meaning of life   ||   Exhaustion   ||");
            }
        }

        System.out.print(userID);
        String input = scan.nextLine();

        // Handle exit command
        if (input.toLowerCase().contains("stop") || input.toLowerCase().contains("bye")) {
            if (tired) {
                System.out.println(botID + "Thank god.");
            } else {
                System.out.println(botID + "Goodbye! See you next time.");
            }
            return;
        }

        // Find the appropriate topic
        String topic = findTopic(input);
        if (topic != null) {
            // Get and display response for the topic
            DialogueOption option = topicOptions.get(topic);
            System.out.println(botID + option.getResponse(tired));
            
            // Get user input for the next step
            System.out.print(userID);
            input = scan.nextLine();
            
            // Process topic-specific dialogue tree
            processDialogue(topic, input, scan, discussionCount, name, botID, userID);
        } else {
            // Topic not found, restart discussion
            discussion(2, scan, discussionCount, name);
        }
    }
    
    // Helper method to find which topic the input matches
    private String findTopic(String input) {
        input = input.toLowerCase();
        if (input.contains("honor")) return "honor";
        if (input.contains("rodent")) return "rodent";
        if (input.contains("life") || input.contains("meaning")) return "meaning";
        if (input.contains("exhaustion")) return "exhaustion";
        return null;
    }
    
    // Process dialogue based on topic and input
    private void processDialogue(String topic, String input, Scanner scan, int discussionCount, 
                               String name, String botID, String userID) {
        input = input.toLowerCase();
        
        // Get responses for this topic
        Map<String, DialogueResponse> responses = responseTree.get(topic);
        if (responses == null) {
            // No defined responses, go back to topic selection
            discussion(2, scan, discussionCount, name);
            return;
        }
        
        // Find the appropriate response based on input keywords
        DialogueResponse response = null;
        String nextPrompt = null;
        
        // Check for specific keywords in the input
        for (Map.Entry<String, DialogueResponse> entry : responses.entrySet()) {
            String key = entry.getKey();
            if (input.contains(key)) {
                response = entry.getValue();
                nextPrompt = response.nextPrompt;
                break;
            }
        }
        
        // If no specific match, try to use a default response
        if (response == null && responses.containsKey("default")) {
            response = responses.get("default");
            nextPrompt = response.nextPrompt;
        }
        
        // If still no match, go back to topic selection
        if (response == null) {
            discussion(2, scan, discussionCount, name);
            return;
        }
        
        // Display the response, replacing {name} with actual name
        String responseText = response.getResponse(tired).replace("{name}", name);
        System.out.println(botID + responseText);
        
        // Process the next dialogue step based on nextPrompt
        if ("repeat".equals(nextPrompt)) {
            repeatDiscussion(scan, discussionCount, name);
        } else if (nextPrompt != null) {
            // Get next user input
            System.out.print(userID);
            input = scan.nextLine();
            
            // Process the next step in the dialogue tree
            processDialogue(nextPrompt, input, scan, discussionCount, name, botID, userID);
        }
    }

    // Tracks discussion repetition count for changing the bot's state, then restarts or ends the discussion.
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
}