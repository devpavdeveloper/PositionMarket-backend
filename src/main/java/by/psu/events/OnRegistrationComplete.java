package by.psu.events;

import by.psu.security.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter @Setter
public class OnRegistrationComplete extends ApplicationEvent {

    private String appUrl;

    private Locale locale;

    private User user;

    public OnRegistrationComplete(
            User user, String appUrl) {
        super(user);

        this.user = user;
        this.appUrl = appUrl;
    }

}
