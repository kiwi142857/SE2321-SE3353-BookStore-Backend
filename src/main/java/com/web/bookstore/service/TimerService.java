package com.web.bookstore.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

/**
 * The TimerService interface is used to define the methods that will be
 * implemented by the TimerServiceImpl class. The TimerService interface is
 * annotated with @Service to indicate that it is a service class. The
 * TimerService interface is annotated with @SessionScope to indicate that the
 * service class is session scoped. The TimerSerivce will start a timer when the
 * user logs in and stop the timer when the user logs out.
 */
@Service
@SessionScope
public interface TimerService {

    public long getStartTime();

    public long getEndTime();

    /**
     * The startTimer method will start the timer when the user logs in.
     */
    public void startTimer();

    /**
     * The stopTimer method will stop the timer when the user logs out.
     */
    public void stopTimer();

    /**
     * The getElapsedTime method will return the elapsed time of the timer.
     *
     * @return The elapsed time of the timer.
     */
    public long getElapsedTime();

    /**
     * The resetTimer method will reset the timer.
     */
    public void resetTimer();

    /**
     * The isTimerRunning method will return true if the timer is running and
     * false if the timer is not running.
     *
     * @return True if the timer is running, false if the timer is not running.
     */
    public boolean isTimerRunning();

}
