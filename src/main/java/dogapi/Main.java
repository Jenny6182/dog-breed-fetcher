package dogapi;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String breed = "hound";
        BreedFetcher breedFetcher = new CachingBreedFetcher(new BreedFetcherForLocalTesting());
        int result = getNumberOfSubBreeds(breed, breedFetcher);
        System.out.println(breed + " has " + result + " sub breeds");

        breed = "cat";
        result = getNumberOfSubBreeds(breed, breedFetcher);
        System.out.println(breed + " has " + result + " sub breeds");
    }

    /**
     * Return the number of sub breeds that the given dog breed has according to the
     * provided fetcher.
     * @param breed the name of the dog breed
     * @param breedFetcher the breedFetcher to use
     * @return the number of sub breeds. Zero should be returned if there are no sub breeds
     * returned by the fetcher
     */
    public static int getNumberOfSubBreeds(String breed, BreedFetcher breedFetcher) {
        // TODO Task 3 implement this code so that it is entirely consistent with its provided documentation.
        // return statement included so that the starter code can compile and run.
        BreedFetcher fetcher = breedFetcher;
        List<String> breeds_list = new ArrayList<>();

        int numberOfSubBreeds = 0;

        try {
            breeds_list = fetcher.getSubBreeds(breed);
            for (int i = 0; i < breeds_list.size(); i++) {
                numberOfSubBreeds += 1;
            }
        } catch (BreedFetcher.BreedNotFoundException e) {
            return 0;
        }

        // if no breed found exception thrown, we count that as 0 sub-breeds
        // else count the return results' list, just loop throw and see how many subbreeds there are, and return the number

        return numberOfSubBreeds;
    }
}