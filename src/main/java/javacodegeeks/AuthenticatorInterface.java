package javacodegeeks;

/**
 * Created by Mateusz on 16.05.2017.
 */
public interface AuthenticatorInterface {

    /**
     * User authentication method definition.
     *
     * @param username The user name to authenticate.
     * @param password The password to authenticate the user.
     * @return True if the user has been authenticated; false if it has not.
     * @throws EmptyCredentialsException If the received credentials (user name, password)  -
    are
     * empty.
     */
    boolean authenticateUser(String name, String surname);
}
