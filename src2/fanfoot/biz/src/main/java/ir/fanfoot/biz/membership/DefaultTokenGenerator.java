package ir.fanfoot.biz.membership;

import ir.fanfoot.biz.membership.FanFootToken;
import org.labcrypto.membership.Credential;
import org.labcrypto.membership.Principal;
import org.labcrypto.membership.Token;
import org.labcrypto.membership.TokenGenerator;

import java.util.Date;

public class DefaultTokenGenerator implements TokenGenerator {

    @Override
    public Token generate(Credential credential, Principal principal) {
        FanFootToken fanFootToken = new FanFootToken();
        fanFootToken.setDisabled(false);
        fanFootToken.setDuration(24 * 60);
        fanFootToken.setExpired(false);
        fanFootToken.setIssueDate(new Date().getTime());
        return fanFootToken;
    }
}
