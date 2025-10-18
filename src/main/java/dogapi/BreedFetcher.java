package dogapi;

import java.util.List;

/**
 * Interface for the service of getting sub breeds of a given dog breed.
 */
public interface BreedFetcher {

    /**
     * Fetch the list of sub breeds for the given breed.
     * @param breed the breed to fetch sub breeds for
     * @return list of sub breeds for the given breed
     * @throws BreedNotFoundException if the breed does not exist
     */

    List<String> getSubBreeds(String breed) throws BreedNotFoundException;
    // DONE Task 4, changed extends RunTimeException to extends Exception
    // Then I declared that the method getSubBreeds in the interface BreedFetcher will throw BreedNotFoundException (bc it's checked)
    // I declared in the DogApiBreedFetcher that the method getSubBreeds will throw BreedNotFoundException
    // In BreedFetcherForLocalTesting, I also declared the method getSubBreeds will throw BreedNotFoundException
    // END.

    // a class defined in an interface is public AND static
    class BreedNotFoundException extends Exception {
        public BreedNotFoundException(String breed) {
            super("Breed not found: " + breed);
        }
    }
}