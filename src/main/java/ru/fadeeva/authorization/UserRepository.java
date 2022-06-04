package ru.fadeeva.authorization;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    ConcurrentHashMap<String, String> users = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, List<Authorities>> usersAuthorities = new ConcurrentHashMap<>();

    {
        users.put("admin", "qwerty");
        usersAuthorities.put("admin", List.of(Authorities.READ, Authorities.DELETE, Authorities.WRITE));
        users.put("user", "123");
        usersAuthorities.put("user", List.of(Authorities.READ, Authorities.WRITE));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        if (users.containsKey(user)) {
            if (users.get(user).equals(password)) {
                return usersAuthorities.get(user);
            }
        }
        return null;
    }
}
