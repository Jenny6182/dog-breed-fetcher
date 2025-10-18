package dogapi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;


/**
 * BreedFetcher implementation that relies on the dog.ceo API.
 * Note that all failures get reported as BreedNotFoundException
 * exceptions to align with the requirements of the BreedFetcher interface.
 */
public class DogApiBreedFetcher implements BreedFetcher {


    private final OkHttpClient client = new OkHttpClient();

    /**
     * Fetch the list of sub breeds for the given breed from the dog.ceo API.
     * @param breed the breed to fetch sub breeds for
     * @return list of sub breeds for the given breed
     * @throws BreedNotFoundException if the breed does not exist (or if the API call fails for any reason)
     */
    @Override
    public List<String> getSubBreeds(String breed) throws BreedNotFoundException{
        String url_0 = "https://dog.ceo/api/breed/" + breed + "/images"; // change to specific url for that breed
        // We don't actually need images but just tryna get the error msg by searching for invalid breed
        Request request0 = new Request.Builder()
                .url(url_0)
                .build();

        // Throw BreedNotFoundException if breed doesn't exist
        try (Response response = client.newCall(request0).execute()) {
            if (!response.isSuccessful()) {
                throw new BreedNotFoundException(breed); }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create an ArrayList to organize all the sub-breeds in
        ArrayList<String> sub_breeds = new ArrayList<>();

        // Get list of all sub-breeds of the breed
        String url_1 = "https://dog.ceo/api/breed/" + breed + "/list";
        Request request1 = new Request.Builder()
                .url(url_1)
                .build();

        // Try-catch block
        try (Response response1 = client.newCall(request1).execute()) {
            // Parsing API response
             String response1_body = response1.body().string(); // read ResponseBody as a string
             JSONObject json = new JSONObject(response1_body); // turn the string into a JSONObject
             JSONArray msg = json.getJSONArray("message"); // read the msg key-pair JSONArray of msg from the JSONObject

            // loop through the JSONArray and add each String value in the JSONArray into the sub_breeds ArrayList
            for (int i = 0; i < msg.length(); i++) {
                sub_breeds.add(msg.getString(i));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sub_breeds;


        // STEPS:
        // 1. see if breed is valid, if not throw an error
        // 2. get all sub-breeds of that breed
        // 3. organize all the sub-breeds in an arraylist
    }
}