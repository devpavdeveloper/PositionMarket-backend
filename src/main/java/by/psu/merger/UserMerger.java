package by.psu.merger;

import by.psu.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMerger implements BaseMerger<User> {

    @Autowired
    private UserProfileMerger userProfileMerger;

    @Override
    public User merge(User first, User second) {
        if ( first == null && second == null ) {
            return null;
        }
        if ( first == null ) {
            return second;
        }

        if ( second == null ) {
            return first;
        }

        first.setEnabled( second.getEnabled() );
        first.setLogin( second.getLogin() );
        first.setUserProfile( userProfileMerger.merge(first.getUserProfile(), second.getUserProfile()) );
        return first;
    }
}
