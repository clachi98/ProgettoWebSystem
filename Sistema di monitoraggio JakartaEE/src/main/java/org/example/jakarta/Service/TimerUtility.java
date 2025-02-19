package org.example.jakarta.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.jakarta.Beans.myMap;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class TimerUtility {

    @Inject
    private myMap mappa;

    private static final int TIMER_DELAY = 120;
    private final ScheduledExecutorService scheduler;
    private ScheduledFuture<?> timerTask1;
    private ScheduledFuture<?> timerTask2;
    private ScheduledFuture<?> timerTask3;
    private ScheduledFuture<?> timerTask4;
    private ScheduledFuture<?> timerTask5;

    public TimerUtility() {
        scheduler = Executors.newScheduledThreadPool(5);
    }

    public void startTimer1() {
        resetTimer1();
    }

    private void resetTimer1() {
        if (timerTask1 != null && !timerTask1.isDone()) {
            timerTask1.cancel(false);
        }
        timerTask1 = scheduler.schedule(this::onTimer1Expired, TIMER_DELAY, TimeUnit.SECONDS);
    }

    public void startTimer2() {
        resetTimer2();
    }

    private void resetTimer2() {
        if (timerTask2 != null && !timerTask2.isDone()) {
            timerTask2.cancel(false);
        }
        timerTask2 = scheduler.schedule(this::onTimer2Expired, TIMER_DELAY, TimeUnit.SECONDS);
    }

    public void startTimer3() {
        resetTimer3();
    }

    private void resetTimer3() {
        if (timerTask3 != null && !timerTask3.isDone()) {
            timerTask3.cancel(false);
        }
        timerTask3 = scheduler.schedule(this::onTimer3Expired, TIMER_DELAY, TimeUnit.SECONDS);
    }

    public void startTimer4() {
        resetTimer4();
    }

    private void resetTimer4() {
        if (timerTask4 != null && !timerTask4.isDone()) {
            timerTask4.cancel(false);
        }
        timerTask4 = scheduler.schedule(this::onTimer4Expired, TIMER_DELAY, TimeUnit.SECONDS);
    }

    public void startTimer5() {
        resetTimer5();
    }

    private void resetTimer5() {
        if (timerTask5 != null && !timerTask5.isDone()) {
            timerTask5.cancel(false);
        }
        timerTask5 = scheduler.schedule(this::onTimer5Expired, TIMER_DELAY, TimeUnit.SECONDS);
    }

    private void onTimer1Expired() {
        System.out.println("Timer 1 scaduto");
        JSONObject json = mappa.getLogMap().get(1);
        json.put("stato", false);
        mappa.getLogMap().put(1, json);
    }

    private void onTimer2Expired() {
        System.out.println("Timer 2 scaduto");
        JSONObject json = mappa.getLogMap().get(2);
        json.put("stato", false);
        mappa.getLogMap().put(2, json);
    }

    private void onTimer3Expired() {
        System.out.println("Timer 3 scaduto");
        JSONObject json = mappa.getLogMap().get(3);
        json.put("stato", false);
        mappa.getLogMap().put(3, json);
    }

    private void onTimer4Expired() {
        System.out.println("Timer 4 scaduto");
        JSONObject json = mappa.getLogMap().get(4);
        json.put("stato", false);
        mappa.getLogMap().put(4, json);
    }

    private void onTimer5Expired() {
        System.out.println("Timer 5 scaduto");
        JSONObject json = mappa.getLogMap().get(5);
        json.put("stato", false);
        mappa.getLogMap().put(5, json);
    }
}
