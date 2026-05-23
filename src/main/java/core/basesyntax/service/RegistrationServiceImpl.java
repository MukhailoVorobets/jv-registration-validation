package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exseption.RegistrationException;
import core.basesyntax.model.User;
import core.basesyntax.validator.UserValidator;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();
    private final UserValidator userValidator = new UserValidator();

    @Override
    public User register(User user) {
        userValidator.validate(user);
        checkLoginUniqueness(user.getLogin());
        return storageDao.add(user);
    }

    private void checkLoginUniqueness(String login) {
        if (storageDao.get(login) != null) {
            throw new RegistrationException("User already exists");
        }
    }
}
