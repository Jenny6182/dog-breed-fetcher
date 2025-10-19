package dogapi;

import java.util.*;

/**
 * This BreedFetcher caches fetch request results to improve performance and
 * lessen the load on the underlying data source. An implementation of BreedFetcher
 * must be provided. The number of calls to the underlying fetcher are recorded.
 *
 * If a call to getSubBreeds produces a BreedNotFoundException, then it is NOT cached
 * in this implementation. The provided tests check for this behaviour.
 *
 * The cache maps the name of a breed to its list of sub breed names.
 */
public class CachingBreedFetcher implements BreedFetcher {
    // TODO Task 2: Complete this class

    // Store breeds fetched in a map of string key (breed) to list of strings (sub-breeds) (which is ur cache)

    private final BreedFetcher fetcher; // the fetcher we use
    private final Map<String, List<String>> cache = new HashMap<>();
    // this stores breeds fetched in a map of string key (breed) to list of strings (sub-breeds) (which is ur cache)
    private int callsMade = 0; // record calls made to API

    public CachingBreedFetcher(BreedFetcher fetcher) {
        this.fetcher = fetcher;
        // means when we try to make a cachingbreedfetcher, we give it a fetcher we defined already
        // and that defined fetcher will be used as the underlying fetcher
    }

    @Override
    public List<String> getSubBreeds(String breed) throws BreedNotFoundException {
        // check if we already had this breed in our cache map
        if (cache.containsKey(breed)) {
            return cache.get(breed); // if we do, return the corresponding list, using breed as the key
        }

        callsMade++; // add to API call count

        try {
            List<String> subBreeds = fetcher.getSubBreeds(breed);
            cache.put(breed, subBreeds); // store it in cache
            return subBreeds;
        } catch (BreedNotFoundException e) {
            throw e;
        }

        // whenever this method is called, if already called before,
        // cacher should return smth, if not we can ask the website then
        // If the underlying fetcher throws BreedNotFoundException → don’t cache that.
    }

    public int getCallsMade() {
        return callsMade;
    }
}