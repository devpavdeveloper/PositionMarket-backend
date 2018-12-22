package by.psu.service.merger;

import by.psu.model.postgres.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMerger implements BaseMerger<UserProfile> {


    @Override
    public UserProfile merge(UserProfile first, UserProfile second) {
        if ( first == null && second == null ) {
            return null;
        }
        if ( first == null ) {
            return second;
        }

        if ( second == null ) {
            return first;
        }

        first.setEmail( second.getEmail() );
        first.setPhone( second.getPhone() );
        first.setPriceDistance( second.getPriceDistance() );

        return first;
    }


}
