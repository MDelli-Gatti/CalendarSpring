package com.theironyard.services;

import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by michaeldelli-gatti on 6/23/16.
 */
public interface UserRepositroy extends CrudRepository<User, Integer> {
    public User findByName(String name);
}
