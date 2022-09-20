package pos.app.pharmacy_app.users.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pos.app.pharmacy_app.users.entity.Users;
import pos.app.pharmacy_app.users.repository.UsersRepository;

import java.text.MessageFormat;
import java.util.Optional;

public class UsersService implements UserDetailsService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUser=usersRepository.findUsersByUsername(username);
        if (optionalUser.isPresent()){
        return optionalUser.get();
        }

    else{
        throw new UsernameNotFoundException(MessageFormat.format(
                "User with Username{0} cannot be found",username));
    }
}
}
