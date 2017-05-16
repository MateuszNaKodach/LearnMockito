package javacodegeeks;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Created by Mateusz on 16.05.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class AuthenticatorApplicationTest {

    @Mock
    private static AuthenticatorInterface authenticatorMock;

    @InjectMocks
    private static AuthenticatorApplication authenticator;

    @Test
    public void testAuthenticate() throws Exception {
        String username = "JavaCodeGeeks";
        String password = "unsafePassword";

        when(authenticatorMock.authenticateUser(username, password)).thenReturn(false);

        boolean actual = authenticator.authenticate(username, password);

        assertFalse(actual);
    }

    @Test
    public void verifyMethodInvocation() throws Exception {
        String username = "JavaCodeGeeks";
        String password = "unsafePassword";

        when(authenticatorMock.authenticateUser(username, password)).thenReturn(false);

        boolean actual = authenticator.authenticate(username, password);

        verify(authenticatorMock, atLeastOnce()).authenticateUser(username, password);
    }

    @Test
    public void verifyMethodNoInvocation() throws Exception {
        String username = "JavaCodeGeeks";
        String password = "unsafePassword";

        verify(authenticatorMock, never()).authenticateUser(username, password);
    }


    @Test
    public void fooShouldBeCallAfterAuthenticate() throws Exception {
        String username = "JavaCodeGeeks";
        String password = "unsafePassword";

        verify(authenticatorMock, never()).authenticateUser(username, password);

        authenticator.authenticate(username, password);

        InOrder inOrder = inOrder(authenticatorMock);

        inOrder.verify(authenticatorMock).foo();
        inOrder.verify(authenticatorMock).authenticateUser(username, password);
    }

    @Test(expected = EmptyCredentialException.class)
    public void testAuthenticationEmptyException() throws EmptyCredentialException{
        when(authenticatorMock.authenticateUser("","")).thenThrow(new EmptyCredentialException());

        authenticator.authenticate("","");
    }


    
}
