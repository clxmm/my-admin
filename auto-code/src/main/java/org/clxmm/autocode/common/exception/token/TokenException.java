package org.clxmm.autocode.common.exception.token;

import javax.naming.AuthenticationException;




public abstract class TokenException extends AuthenticationException {

    public TokenException(String explanation) {
        super(explanation);
    }

}








