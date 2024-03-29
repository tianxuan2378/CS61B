package capers;

import java.io.File;
import java.io.IOException;

import static capers.Dog.DOG_FOLDER;
import static capers.Dog.fromFile;
import static capers.Utils.*;

/** A repository for Capers
 * @author Carl Guo
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = join(CWD, ".capers"); // DONE TODO Hint: look at the `join`

    //      function in Utils
    static final File story = join(CAPERS_FOLDER, "story.txt");


    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        // Done TODO
        if (!CAPERS_FOLDER.exists()) {
            CAPERS_FOLDER.mkdir();
            try {
                story.createNewFile();
                DOG_FOLDER.mkdir(); //create a folder called "dogs"
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        // Done TODO
        String existedContent = readContentsAsString(story);
        writeContents(story, existedContent + "\n" + text);
        String Content = readContentsAsString(story);
        System.out.println(Content);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        // Done TODO
        Dog dog = new Dog(name, breed, age);
        dog.saveDog();
        System.out.println(dog.toString());
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        // Done TODO
        Dog d = fromFile(name);
        d.haveBirthday();
        d.saveDog();
    }
}