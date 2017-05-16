package javacodegeeks;

/**
 * Created by Mateusz on 16.05.2017.
 */
public class EmptyCredentialException extends Exception {

    public EmptyCredentialException() {
        super("Empty credentials!");
    }
}
