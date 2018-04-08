package com.github.comp354project.utils;

import com.github.comp354project.MyMoneyApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimingInvocationHandler implements InvocationHandler {
    private static final Logger jsonlogger = LogManager.getLogger("TimingJson");
    private static final Logger columnLogger = LogManager.getLogger("TimingColumn");

    private Object obj;
    public  TimingInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // If the annotation is not present, just redirect the method call to its origin...
        if(!method.isAnnotationPresent(Timing.class)) {
            return method.invoke(obj, args);
        }
        Long start = System.nanoTime();
        try{
            Object returnObj = method.invoke(obj, args);
            long elapsed = System.nanoTime() - start;
            logMessage(method, elapsed);
            return returnObj;
        } catch (Throwable t){
            long elapsed = System.nanoTime() - start;
            logMessage(method, elapsed);
            throw t;
        }
    }

    private void logMessage(Method method, long elapsed){
        double millis = elapsed / 1000000.0;
        double seconds = elapsed / 1000000000.0;
        String jsonMessage = String.format("{\"class\":\"%s\", \"method\":\"%s\", \"nano\":\"%d\", \"milli\":\"%f\", \"sec\":\"%f\"}",
                obj.getClass().getName(),
                method.getName(),
                elapsed,
                millis,
                seconds);
        String columnMessage = String.format("%s %s %d %f %f",
                obj.getClass().getName(),
                method.getName(),
                elapsed,
                millis,
                seconds);
        jsonlogger.trace(jsonMessage);
        columnLogger.trace(columnMessage);
    }
}
