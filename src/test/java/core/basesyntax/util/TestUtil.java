package core.basesyntax.util;

import core.basesyntax.model.User;

public class TestUtil {
    public User getUser() {
        User user = new User();
        user.setId(TestConstants.ID);
        user.setLogin(TestConstants.LOGIN);
        user.setPassword(TestConstants.PASSWORD);
        user.setAge(TestConstants.AGE_18);
        return user;
    }

    public User getUserDuplicate() {
        User user = new User();
        user.setId(TestConstants.ID);
        user.setLogin(TestConstants.LOGIN);
        user.setPassword(TestConstants.PASSWORD);
        user.setAge(TestConstants.AGE_18);
        return user;
    }

    public User getUserWithNotValidAge() {
        User user = new User();
        user.setId(TestConstants.ID);
        user.setLogin(TestConstants.LOGIN);
        user.setPassword(TestConstants.PASSWORD);
        user.setAge(TestConstants.AGE_17);
        return user;
    }

    public User getUserWithShortPassword() {
        User user = new User();
        user.setId(TestConstants.ID);
        user.setLogin(TestConstants.LOGIN);
        user.setPassword(TestConstants.SHORT_PASSWORD);
        user.setAge(TestConstants.AGE_17);
        return user;
    }

    public User getUserWithPasswordWithoutDigit() {
        User user = new User();
        user.setId(TestConstants.ID);
        user.setLogin(TestConstants.LOGIN);
        user.setPassword(TestConstants.PASSWORD_WITHOUT_DIGIT);
        user.setAge(TestConstants.AGE_17);
        return user;
    }
}
