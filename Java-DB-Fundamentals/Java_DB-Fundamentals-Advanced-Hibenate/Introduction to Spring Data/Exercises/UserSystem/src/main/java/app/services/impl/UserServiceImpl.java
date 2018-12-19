package app.services.impl;

import app.entities.User;
import app.repositories.UserRepository;
import app.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsersByEmailProvider(String provider) {
        return this.userRepository.findAllByEmailEndingWith(provider);
    }

    @Override
    public int removeAllUsersWhoLoggedBeforeDate(LocalDate date) {
        List<User> usersToRemove =  this.userRepository.findAllByLastLoginTimeBefore(date);
        for (int i = 0; i < usersToRemove.size(); i++){
            usersToRemove.get(i).setDeleted(true);
            this.userRepository.save(usersToRemove.get(i));
        }
        return usersToRemove.size();
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }
}
