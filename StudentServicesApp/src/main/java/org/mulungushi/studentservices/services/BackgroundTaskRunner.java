package org.mulungushi.studentservices.services;

import javafx.concurrent.Task;

// Handles slow operations safely off the main JavaFX thread
public class BackgroundTaskRunner {
    public static void runSlowTask(Runnable operation, Runnable onComplete) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                operation.run();
                return null;
            }
        };
        task.setOnSucceeded(e -> onComplete.run());
        new Thread(task).start();
    }
}