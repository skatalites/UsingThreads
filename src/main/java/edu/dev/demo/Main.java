package edu.dev.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {

        var start = System.currentTimeMillis();
        log.info("Current Thread: {}", Thread.currentThread().getName());

        var runnable = new CountAndPrintUsingRunnable();
        new Thread(runnable).start();

        new CountAndPrintUsingThread().start();

        var end = System.currentTimeMillis();
        log.info("Current Thread: {}, final time: {}", Thread.currentThread().getName(), end - start);
    }

    @Slf4j
    public static class CountAndPrintUsingRunnable implements Runnable {

        @SneakyThrows
        @Override
        public void run() {

            var start = System.currentTimeMillis();
            log.info("Current Thread: {}, initial time: {}", Thread.currentThread().getName(), start);

            for (int i = 0; i < 10; i++) {
                log.info("Value using Runnable is: {}", i); // no sleep 1 task * 10 approx. 10 millis
            }

            var end = System.currentTimeMillis();
            log.info("Current Thread: {}, final time: {}", Thread.currentThread().getName(), end - start);
        }

    }

    @Slf4j
    public static class CountAndPrintUsingThread extends Thread {

        @SneakyThrows
        @Override
        public void run() {

            var start = System.currentTimeMillis();
            log.info("Current Thread: {}, initial time: {}", Thread.currentThread().getName(), start);

            for (int i = 0; i < 10; i++) {
                Thread.sleep(10); // 10 sleep millis * 10 tasks approx. 120 millis
                log.info("Value using Thread is: {}", i);
            }

            var end = System.currentTimeMillis();
            log.info("Current Thread: {}, final time: {}", Thread.currentThread().getName(), end - start);
        }
    }

}