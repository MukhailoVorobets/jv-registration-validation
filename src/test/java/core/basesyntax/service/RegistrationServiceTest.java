package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.exseption.RegistrationException;
import core.basesyntax.model.User;
import core.basesyntax.util.TestConstants;
import core.basesyntax.util.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegistrationServiceTest {
    private RegistrationService registrationService;
    private StorageDao storageDao;
    private TestUtil testUtil;

    private User user;
    private User userShortPassword;
    private User userWithNotValidAge;
    private User userDuplicate;
    private User userAgeAbove18;

    @BeforeEach
    void setUp() {
        registrationService = new RegistrationServiceImpl();
        storageDao = new StorageDaoImpl();
        testUtil = new TestUtil();
        user = testUtil.getUser();
        userShortPassword = testUtil.getUserWithShortPassword();
        userWithNotValidAge = testUtil.getUserWithNotValidAge();
        userDuplicate = testUtil.getUserDuplicate();
        userAgeAbove18 = testUtil.getUserOk();
    }

    @AfterEach
    void cleanUp() {
        Storage.people.clear();
    }

    @Test
    @DisplayName("Valid user should be registered ok")
    void register_validUser_ok() {
        User result = registrationService.register(user);
        assertEquals(user, result);
        assertEquals(storageDao.get(user.getLogin()), user);
    }

    @Test
    @DisplayName("Valid user should be registered age above 18 Ok")
    void register_validUserAgeAbove18_ok() {
        User result = registrationService.register(userAgeAbove18);
        assertEquals(userAgeAbove18, result);
    }

    @Test
    @DisplayName("Null user should throw RegistrationException")
    void register_nullUser_notOk() {
        assertThrows(RegistrationException.class,
                () -> registrationService.register(null)
        );
    }

    @Test
    @DisplayName("Null login should throw RegistrationException")
    void register_nullLogin_notOk() {
        user.setLogin(null);
        assertThrows(RegistrationException.class,
                () -> registrationService.register(user)
        );
    }

    @Test
    @DisplayName("Short login should throw RegistrationException")
    void register_shortLogin_notOk() {
        user.setLogin("abasd");
        assertThrows(RegistrationException.class,
                () -> registrationService.register(user)
        );
    }

    @Test
    @DisplayName("Null password should throw RegistrationException")
    void register_nullPassword_notOk() {
        user.setPassword(null);
        assertThrows(RegistrationException.class,
                () -> registrationService.register(user)
        );
    }

    @Test
    @DisplayName("Short password should throw RegistrationException")
    void register_shortPassword_notOk() {
        assertThrows(RegistrationException.class,
                () -> registrationService.register(userShortPassword)
        );
    }

    @Test
    @DisplayName("Age below minimum should throw RegistrationException")
    void register_ageBelowMinimum_notOk() {
        assertThrows(RegistrationException.class,
                () -> registrationService.register(userWithNotValidAge)
        );
    }

    @Test
    @DisplayName("Duplicate login should throw RegistrationException")
    void register_duplicateLogin_notOk() {
        Storage.people.add(user);
        assertThrows(RegistrationException.class,
                () -> registrationService.register(userDuplicate)
        );
    }

    @Test
    @DisplayName("Password 4 chars throw RegistrationException")
    void register_password5Chars_notOk() {
        user.setPassword(TestConstants.SHORT_PASSWORD);
        assertThrows(RegistrationException.class,
                () -> registrationService.register(user));
    }

    @Test
    @DisplayName("Negative age throw RegistrationException")
    void register_negativeAge_notOk() {
        user.setAge(TestConstants.NEGATIVE_AGE);
        assertThrows(RegistrationException.class,
                () -> registrationService.register(user));
    }

    @Test
    @DisplayName("Password 5 chars throw RegistrationException")
    void register_passwordFiveChars_notOk() {
        user.setPassword(TestConstants.SHORT_PASSWORD_5_CHARS);
        assertThrows(RegistrationException.class,
                () -> registrationService.register(user));
    }
}
