package ru.sug4chy.demo5.service;

import ru.sug4chy.demo5.model.UserProfile;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static final Map<String, UserProfile> loginToProfile = new HashMap<>() {{
        put("12", new UserProfile("12", "1234", "123"));
    }};

    public void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }
}
