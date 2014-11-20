package ir.telefa.membership;

import org.labcrypto.membership.Credential;
import org.labcrypto.membership.User;
import org.labcrypto.membership.UserNotFoundException;

public interface UserRepository {
    User getUserByCredential(Credential credential) throws UserNotFoundException;
}
