package by.psu.security.model;

import by.psu.model.Basic;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="verification_tokens")
@Getter
public class VerificationToken extends Basic {

    public enum VerificationTokenGenerationMode{STANDARD, EXTENDED}

    private static final Integer[] verificationTokenTimeouts = {60 * 12, 60 * 24}; //mins * hrs (12 hours) registration | 24 hours - password recovery

    @Column(name="token")
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    @Column(name = "expiration_date")
    private Date expirationDate;

    private Date calculateExpirationDate(Integer expiryMinutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryMinutes);
        return new Date(cal.getTime().getTime());
    }

    public VerificationToken(User user, String token, VerificationToken.VerificationTokenGenerationMode mode) {
        this.token = token;
        this.user = user;
        this.expirationDate = calculateExpirationDate(verificationTokenTimeouts[mode.ordinal()]);
    }
    public VerificationToken(){}

    public static Integer getExpiryTimeoutsByMode(VerificationToken.VerificationTokenGenerationMode mode){
        return verificationTokenTimeouts[mode.ordinal()];
    }
}
