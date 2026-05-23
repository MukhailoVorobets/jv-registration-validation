package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.exseption.RegistrationException;
import core.basesyntax.model.User;
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
    @DisplayName("Valid user should be registered successfully")
    void register_validUser_success() {
        User result = registrationService.register(user);
        assertEquals(user, result);
    }

    @Test
    @DisplayName("Valid user should be registered successfully age above 18")
    void register_validUser_success_age_above_18() {
        User result = registrationService.register(userAgeAbove18);
        assertEquals(userAgeAbove18, result);
    }

    @Test
    @DisplayName("Null user should throw RegistrationException")
    void register_nullUser_throwsException() {
        assertThrows(RegistrationException.class,
                () -> registrationService.register(null)
        );
    }

    @Test
    @DisplayName("Null login should throw RegistrationException")
    void register_nullLogin_throwsException() {
        user.setLogin(null);
        assertThrows(RegistrationException.class,
                () -> registrationService.register(user)
        );
    }

    @Test
    @DisplayName("Short login should throw RegistrationException")
    void register_shortLogin_throwsException() {
        user.setLogin("abasd");
        assertThrows(RegistrationException.class,
                () -> registrationService.register(user)
        );
    }

    @Test
    @DisplayName("Null password should throw RegistrationException")
    void register_nullPassword_throwsException() {
        user.setPassword(null);
        assertThrows(RegistrationException.class,
                () -> registrationService.register(user)
        );
    }

    @Test
    @DisplayName("Short password should throw RegistrationException")
    void register_shortPassword_throwsException() {
        assertThrows(RegistrationException.class,
                () -> registrationService.register(userShortPassword)
        );
    }

    @Test
    @DisplayName("Age below minimum should throw RegistrationException")
    void register_ageBelowMinimum_throwsException() {
        assertThrows(RegistrationException.class,
                () -> registrationService.register(userWithNotValidAge)
        );
    }

    @Test
    @DisplayName("Duplicate login should throw RegistrationException")
    void register_duplicateLogin_throwsException() {
        Storage.people.add(user);
        assertThrows(RegistrationException.class,
                () -> registrationService.register(userDuplicate)
        );
    }
}
