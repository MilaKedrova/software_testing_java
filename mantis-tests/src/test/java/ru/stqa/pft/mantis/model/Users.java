package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import ru.stqa.pft.mantis.model.UserData;

public class Users extends ForwardingSet<UserData> {
    private Set<UserData> delegete;

    public Users(Users users) {
        this.delegete = new HashSet<UserData>(users.delegete);
    }

    public Users(Collection<UserData> users) {
        this.delegete = new HashSet<UserData>(users);
    }

    @Override
    protected Set delegate() {
        return delegete;
    }

    public Users withAdded(UserData user) {
        Users users = new Users(this);
        users.add(user);
        return users;
    }

    public Users without(UserData user) {
        Users users = new Users(this);
        users.remove(user);
        return users;
    }

    public boolean isPresented(UserData user) {
        for (UserData thisUser : delegete) {
            if (thisUser.equals(user)) {
                return true;
            }
        }
        return false;
    }
}