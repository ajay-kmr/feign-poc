package com.globomart.productcatalog.conf;


import com.globomart.productcatalog.dao.entity.User;
import com.globomart.productcatalog.dao.repository.UserRepository;
import org.springframework.data.domain.AuditorAware;

/**
 * Creating implementation of AuditorAware and override its methods to provide currently logged in user
 */
public class AuditorAwareImpl implements AuditorAware<User> {

    private UserRepository userRepository;

    AuditorAwareImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getCurrentAuditor() {
        //TODO
//        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return null;
    }
}
