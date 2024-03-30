package org.trung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.trung.exception.UserException;
import org.trung.model.User;

public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
}
