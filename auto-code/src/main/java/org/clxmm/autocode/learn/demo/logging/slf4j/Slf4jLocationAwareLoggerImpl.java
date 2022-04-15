package org.clxmm.autocode.learn.demo.logging.slf4j;


import org.clxmm.autocode.learn.demo.logging.Log;
import org.clxmm.autocode.learn.demo.logging.LogFactory;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.spi.LocationAwareLogger;

public class Slf4jLocationAwareLoggerImpl implements Log {


    private static final Marker MARKER = MarkerFactory.getMarker(LogFactory.MARKER);

    private static final String FQCN =  Slf4jImpl.class.getName();

    private final LocationAwareLogger logger;

    public Slf4jLocationAwareLoggerImpl(LocationAwareLogger logger) {
        this.logger = logger;
    }


    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void error(String s, Throwable e) {

    }

    @Override
    public void error(String s) {

    }

    @Override
    public void debug(String s) {
        logger.log(MARKER, FQCN, LocationAwareLogger.DEBUG_INT, s, null, null);
    }

    @Override
    public void trace(String s) {

    }

    @Override
    public void warn(String s) {

    }
}
