package javacodegeeks;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Created by Mateusz on 16.05.2017.
 */
public class AuthenticatorApplicationTest {

    private static AuthenticatorInterface authenticatorMock;
    private static AuthenticatorApplication authenticator;

    @BeforeClass
    public static void setUpClass(){
        authenticatorMock = Mockito.mock(AuthenticatorInterface.class);
        authenticator = new AuthenticatorApplication(authenticatorMock);

    }

    @Test
    public void testAuthenticate(){
        String username = "JavaCodeGeeks";
        String password = "unsafePassword";

        when(authenticatorMock.authenticateUser(username,password)).thenReturn(false);

        boolean actual = authenticator.authenticate(username,password);

        assertFalse(actual);
    }

    @Test
    public void verifyMethodInvocation(){
        String username = "JavaCodeGeeks";
        String password = "unsafePassword";

        when(authenticatorMock.authenticateUser(username,password)).thenReturn(false);

        boolean actual = authenticator.authenticate(username,password);

        verify(authenticatorMock, atLeastOnce()).authenticateUser(username,password);
    }

    @Test
    public void verifyMethodNoInvocation(){
        String username = "JavaCodeGeeks";
        String password = "unsafePassword";

        verify(authenticatorMock, never()).authenticateUser(username,password);
    }


    @Test
    public void fooShouldBeCallAfterAuthenticate(){
        String username = "JavaCodeGeeks";
        String password = "unsafePassword";

        verify(authenticatorMock, never()).authenticateUser(username,password);

        authenticator.authenticate(username,password);

        InOrder inOrder = inOrder(authenticatorMock);

        inOrder.verify(authenticatorMock).foo();
        inOrder.verify(authenticatorMock).authenticateUser(username,password);
    }
}
