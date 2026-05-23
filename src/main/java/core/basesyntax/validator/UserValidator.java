package core.basesyntax.validator;

import core.basesyntax.exseption.RegistrationException;
import core.basesyntax.model.User;
import core.basesyntax.util.UtilConstants;

public class UserValidator {

    public void validate(User user) {
        validateNotNull(user);
        validateLogin(user.getLogin());
        validatePassword(user.getPassword());
        validateAge(user.getAge());
    }

    private void validateNotNull(User user) {
        if (user == null) {
            throw new RegistrationException("User can't be null");
        }
    }

    private void validateLogin(String login) {
        if (login == null) {
            throw new RegistrationException("User login can't be null");
        }
        if (login.isBlank()) {
            throw new RegistrationException("User login can't be blank");
        }
        if (login.contains(" ")) {
            throw new RegistrationException("User login can't contain spaces");
        }
        if (login.length() < UtilConstants.MIN_LOGIN_LENGTH) {
            throw new RegistrationException(
                    "User login length can't be less than " + UtilConstants.MIN_LOGIN_LENGTH
            );
        }
    }

    private void validatePassword(String password) {
        if (password == null) {
            throw new RegistrationException("User password can't be null");
        }
        if (password.isBlank()) {
            throw new RegistrationException("User password can't be blank");
        }
        if (password.length() < UtilConstants.MIN_PASSWORD_LENGTH) {
            throw new RegistrationException(
                    "User password length can't be less than " + UtilConstants.MIN_PASSWORD_LENGTH
            );
        }
        if (!password.matches(".*\\d.*")) {
            throw new RegistrationException(
                    "User password must contain at least one digit"
            );
        }
    }

    private void validateAge(Integer age) {
        if (age == null) {
            throw new RegistrationException("User age can't be null");
        }
        if (age < UtilConstants.MIN_AGE) {
            throw new RegistrationException(
                    "User age can't be less than " + UtilConstants.MIN_AGE
            );
        }
    }
}
