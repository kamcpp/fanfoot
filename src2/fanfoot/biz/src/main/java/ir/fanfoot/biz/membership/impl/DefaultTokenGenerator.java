package ir.fanfoot.biz.membership.impl;

import ir.fanfoot.biz.membership.*;

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
