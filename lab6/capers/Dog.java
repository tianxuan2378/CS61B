package capers;

import java.io.File;
import java.io.Serializable;
import static capers.Utils.*;

/** Represents a dog that can be serialized.
 * @author Carl Guo
 */
public class Dog implements Serializable{ // TODO

    /** Folder that dogs live in. */
    static final File DOG_FOLDER = join(".capers", "dogs"); // Done TODO (hint: look at the `join`
    //      function in Utils)

    /** Age of dog. */
    private int age;
    /** Breed of dog. */
    private String breed;
    /** Name of dog. */
    private String name;

    /**
     * Creates a dog object with the specified parameters.
     * @param name Name of dog
     * @param breed Breed of dog
     * @param age Age of dog
     */
    public Dog(String name, String breed, int age) {
        this.age = age;
        this.breed = breed;
        this.name = name;
    }

    /**
     * Reads in and deserializes a dog from a file with name NAME in DOG_FOLDER.
     *
     * @param name Name of dog to load
     * @return Dog read from file
     */
    public static Dog fromFile(String name) {
<<<<<<< HEAD
        // Done TODO (hint: look at the Utils file)
        File dogsFile = join(DOG_FOLDER, name + ".txt");
        Dog d = readObject(dogsFile, Dog.class);
        return d;
=======
//        File dogFile = new File(DOG_FOLDER, name);
//        Dog d = readObject(dogFile, Dog.class);
//        return d;
        Dog dog;
        File inFile = join(DOG_FOLDER.toString(), name);
        if (inFile.exists()) {
            dog = readObject(inFile, Dog.class);
            return dog;
        } else {
            System.out.println(String.format("Dog %s doesn't exist!", name));
            return null;
        }
>>>>>>> 12131de97cc982d3424333723c107058c3262192
    }

    /**
     * Increases a dog's age and celebrates!
     */
    public void haveBirthday() {
        age += 1;
        System.out.println(toString());
        System.out.println("Happy birthday! Woof! Woof!");
    }

    /**
     * Saves a dog to a file for future use.
     */
    public void saveDog() {
        // Done TODO (hint: don't forget dog names are unique)
        File dog = join(DOG_FOLDER, this.name + ".txt");
        if (!dog.exists()) {
            try {
                dog.createNewFile();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        writeObject(dog, this);
    }

    @Override
    public String toString() {
        return String.format(
                "Woof! My name is %s and I am a %s! I am %d years old! Woof!",
                name, breed, age);
    }

}