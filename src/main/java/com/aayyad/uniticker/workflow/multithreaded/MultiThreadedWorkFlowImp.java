package com.aayyad.uniticker.workflow.multithreaded;

import com.aayyad.uniticker.context.Context;
import com.aayyad.uniticker.workflow.WorkFlow;
import com.aayyad.uniticker.action.Action;
import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedWorkFlowImp implements WorkFlow {
    private List<List<Action>> actions;

    MultiThreadedWorkFlowImp(List<List<Action>> actions) {
        this.actions = actions;
    }

    @Override
    public void start(@NotNull Context context) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        this.actions.forEach(actionList -> {
            CountDownLatch latch = new CountDownLatch(actionList.size());
            actionList.forEach(action -> {
                executorService.submit(() -> {
                    action.execute(context);
                    latch.countDown();
                });
            });
            awaitLatch(latch);
        });

        executorService.shutdown();
    }

    private void awaitLatch(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
