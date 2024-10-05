package com.web.bookstore.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.web.bookstore.service.TimerService;

@Service
@SessionScope
public class TimerServiceImpl implements TimerService {

    /**
     * The member startTime will record the start time of the timer.
     */
    public long startTime = 0;

    /**
     * The member endTime will record the end time of the timer.
     */
    public long endTime = 0;

    /**
     * The member running will record if the timer is running.
     */
    public boolean running = false;

    /**
     * The startTimer method will start the timer when the user logs in.
     */
    public void startTimer() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    /**
     * The stopTimer method will stop the timer when the user logs out.
     */
    public void stopTimer() {
        endTime = System.currentTimeMillis();
        running = false;
    }

    /**
     * The getElapsedTime method will return the elapsed time of the timer.
     *
     * @return The elapsed time of the timer.
     */
    public long getElapsedTime() {
        if (running) {
            return System.currentTimeMillis() - startTime;
        } else {
            return endTime - startTime;
        }
    }

    /**
     * The resetTimer method will reset the timer.
     */
    public void resetTimer() {
        startTime = 0;
        endTime = 0;
        running = false;
    }

    /**
     * The isTimerRunning method will return true if the timer is running and
     * false if the timer is not running.
     *
     * @return True if the timer is running, false if the timer is not running.
     */
    public boolean isTimerRunning() {
        return running;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

}
