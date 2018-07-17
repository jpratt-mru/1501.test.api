import org.restlet.resource.ClientResource;
import org.restlet.data.Form;
import org.restlet.Client;
import org.restlet.data.Protocol;
import org.restlet.data.MediaType;
import org.restlet.resource.Resource;
import org.restlet.Response;
import org.restlet.representation.Representation;
import org.restlet.representation.InputRepresentation;
import java.io.BufferedInputStream;
import org.restlet.representation.CharacterRepresentation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Resource which has only one representation.
 */
public class MemeGeneratorApiTool  {
    private static final String DARK_SKY_KEY = "9a049f2369941cc869a7eeb671eb6ad3";
    private static final String FACE_KEY = "qMOd8zA13JD1M7vS9JHZ8ci-h6_tJwbx";
    private static final String MEME_KEY = "demo"; //"81692d7c-a84d-4dc7-aae1-6a85e72481a8";
    private static final String MUSIX_KEY = "16697c4e08d161ec0a4a023fd011e2f2";
    private static final String SPOTIFY_KEY = "9293f287683141569f36b5f262348671";

    public static void main(String[] args) throws java.io.IOException, ParseException {


      Form form = new Form("deck_count=1");
      String weatherUri = String.format("https://api.darksky.net/forecast/%s/51.044270,-114.062019", DARK_SKY_KEY);
      System.out.println("uri: " + weatherUri);
      String uri = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";
      String memeUri = String.format("http://version1.api.memegenerator.net/Instances_Select_ByPopular?apiKey=%s&days=100&urlName=Y-U-No", MEME_KEY);
      String topMemeGens = "http://version1.api.memegenerator.net//Generators_Select_ByPopular?pageIndex=&pageSize=25&days=&apiKey=demo";
      System.out.println("memeUri: " + memeUri);
      String musixUri = String.format("http://api.musixmatch.com/ws/1.1/artist.get?artist_id=%d&apikey=%s", 18, MUSIX_KEY);
      ClientResource resource = new ClientResource(topMemeGens);

      Representation response = resource.get(MediaType.APPLICATION_JSON);
      String text = response.getText();
      System.out.println("resp: " + text);
      JSONObject json = (JSONObject) JSONValue.parse(text);
      //System.out.println(json.get("currently"));

    }

  public String popularGenerators() {
    String topMemeGens = "http://version1.api.memegenerator.net//Generators_Select_ByPopular?pageIndex=&pageSize=25&days=&apiKey=demo";
    ClientResource resource = new ClientResource(topMemeGens);

      Representation response = resource.get(MediaType.APPLICATION_JSON);
      try {
        String text = response.getText();
        JSONObject json = (JSONObject) JSONValue.parse(text);
        JSONObject results = (JSONObject) JSONValue.parse(json.get("result").toString());
        
        return results.toString();
      } catch (Exception ex) {
        return "";
      }
  }

}