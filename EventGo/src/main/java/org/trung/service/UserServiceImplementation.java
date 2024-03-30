package org.trung.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.trung.config.JwtProvider;
import org.trung.exception.UserException;
import org.trung.model.User;
import org.trung.repository.UserRepository;

import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User>user=userRepository.findById(userId);
        if(user.isPresent()) {
            return user.get();
        }
        throw new UserException("user not found with id: " + userId);

    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        if (user==null) {
            throw new UserException("user not found with email " + email);
        }
        return user;
    }
}
