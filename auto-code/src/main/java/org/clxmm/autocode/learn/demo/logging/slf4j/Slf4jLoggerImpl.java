package org.clxmm.autocode.learn.demo.logging.slf4j;

import org.clxmm.autocode.learn.demo.logging.Log;
import org.slf4j.Logger;

public class Slf4jLoggerImpl implements Log {


    private final Logger log;

    public Slf4jLoggerImpl(Logger log) {
        this.log = log;
    }


    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    @Override
    public void error(String s, Throwable e) {

    }

    @Override
    public void error(String s) {

    }

    @Override
    public void debug(String s) {
        log.debug(s);
    }

    @Override
    public void trace(String s) {

    }

    @Override
    public void warn(String s) {

    }
}
