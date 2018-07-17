import java.util.Scanner;
import java.net.URLEncoder;

class MemeGenMeister {

  private final Scanner kbd;
  private final MemeGeneratorApiTool apiTool;

  public MemeGenMeister() {
    kbd = new Scanner(System.in);
    apiTool = new MemeGeneratorApiTool();
  }

  public static void main(String[] args) {
    new MemeGenMeister().run();
  }

  public void run() {

    displayWelcome();
    String apiKey = userResponse("What's your API key? ");
    displayAvailableGenerators();
    String generatorName = userResponse("Which generator shall we use? ");
    if (isValidGenerator(generatorName)) {
      System.out.println(generatorName + " is a valid generator");
      String topText = encodedUserResponse("What is the text for the top? ");
      String bottomText = encodedUserResponse("What is the text for the bottom? ");
      String theUrl = String.format("http://version1.api.memegenerator.net/Instance_Create?languageCode=en&generatorID=%s&text0=%s&text1=%s&apiKey=%s", generatorName, topText, bottomText, apiKey);
      System.out.println("the url: " + theUrl);
    } else {
      System.out.println(generatorName + " is not a valid generator. Exiting....");
    }
   

  }

  private String encodedUserResponse(String prompt) {
    String response = userResponse(prompt);
    try {
      return URLEncoder.encode(response, "UTF-8");
    } catch (Exception ex) {
      return "";
    }
    
  }

  private void displayWelcome() {
    System.out.println("Welcome to the Meme Generator Meister!");
    System.out.println("Let's meme!");
  }

  private void displayAvailableGenerators() {
    System.out.println(apiTool.popularGenerators());
  }

  private String userResponse(String prompt) {
    System.out.print(prompt);
    return kbd.nextLine().trim();
  }

  private boolean isValidGenerator(String generatorName) {
    return apiTool.popularGenerators().toLowerCase().contains(generatorName.toLowerCase());
  }

  private void handleUserResponse(String response) {
    System.out.println("Handling " + response);
  }
}