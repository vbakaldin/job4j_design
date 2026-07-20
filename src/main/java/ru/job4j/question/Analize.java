package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, User> previousUsers = new HashMap<>();
        for (User user : previous) {
            previousUsers.put(user.getId(), user);
        }
        int added = 0;
        int changed = 0;
        for (User user : current) {
            User oldUser = previousUsers.remove(user.getId());
            if (oldUser == null) {
                added++;
            } else if (!Objects.equals(oldUser.getName(), user.getName())) {
                changed++;
            }
        }
        return new Info(added, changed, previousUsers.size());
    }
}