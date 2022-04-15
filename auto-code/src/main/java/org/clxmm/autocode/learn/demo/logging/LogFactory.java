package org.clxmm.autocode.learn.demo.logging;


import java.lang.reflect.Constructor;


public class LogFactory {


    /**
     * Marker to be used by logging implementations that support markers.
     * 由支持标记的日志记录实现使用的标记。
     */
    public static final String MARKER = "MYBATIS";

    private static Constructor<? extends Log> logConstructor;

    private LogFactory() {
        // disable construction
    }


    static {
        tryImplementation(LogFactory::useSlf4jLogging);
        tryImplementation(org.apache.ibatis.logging.LogFactory::useCommonsLogging);
        tryImplementation(org.apache.ibatis.logging.LogFactory::useLog4J2Logging);
        tryImplementation(org.apache.ibatis.logging.LogFactory::useLog4JLogging);
        tryImplementation(org.apache.ibatis.logging.LogFactory::useJdkLogging);
        tryImplementation(org.apache.ibatis.logging.LogFactory::useNoLogging);
    }


    public static Log getLog(Class<?> aClass) {
        return getLog(aClass.getName());
    }

    private static Log getLog(String name) {
        try {
            return logConstructor.newInstance(name);
        } catch (Throwable t) {
            throw new LogException("", t);
        }
    }


    private static void setImplementation(Class<? extends Log> implClass) {
        try {
            Constructor<? extends Log> candidate = implClass.getConstructor(String.class);
            Log log = candidate.newInstance(LogFactory.class.getName());
            if (log.isDebugEnabled()) {
                log.debug("Logging initialized using '" + implClass + "' adapter.");
            }
            logConstructor = candidate;
        } catch (Throwable t) {
            throw new LogException("", t);
        }
    }

    private static void tryImplementation(Runnable runnable) {
        if (logConstructor == null) {
            try {
                runnable.run();
            } catch (Throwable t) {
                // ignore
            }
        }
    }

    public static synchronized void useSlf4jLogging() {
        setImplementation(org.clxmm.autocode.learn.demo.logging.slf4j.Slf4jImpl.class);
    }


}
