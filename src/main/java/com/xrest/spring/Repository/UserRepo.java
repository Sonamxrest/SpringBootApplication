package com.xrest.spring.Repository;

import com.xrest.spring.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends BaseRepository<User,Long> {

    User findByUsername(String username);
}
