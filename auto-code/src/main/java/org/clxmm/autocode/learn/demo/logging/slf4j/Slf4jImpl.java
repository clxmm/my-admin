package org.clxmm.autocode.learn.demo.logging.slf4j;

import org.clxmm.autocode.learn.demo.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;

public class Slf4jImpl implements Log {

    private Log log;

    public Slf4jImpl(String clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);
        if (logger instanceof LocationAwareLogger) {
            //  check for slf4j >= 1.6 method signature
            try {
                logger.getClass().getMethod("log", Marker.class, String.class, int.class, String.class, Object[].class, Throwable.class);
                log = new Slf4jLocationAwareLoggerImpl((LocationAwareLogger) logger);
                return;
            } catch (NoSuchMethodException | SecurityException e) {
            }
        }
        // Logger is not LocationAwareLogger or slf4j version < 1.6
        log = new Slf4jLoggerImpl(logger);
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
