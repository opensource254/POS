package pos.app.pharmacy_app.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pos.app.pharmacy_app.users.entity.Users;
import pos.app.pharmacy_app.users.repository.UsersRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

UsersRepository usersRepository;
    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=usersRepository.findUsersByUsername(username).orElseThrow(()->new
                UsernameNotFoundException("Username does not exit : "+username));
        return UserDetailsImplementation.build(users);
    }
}
