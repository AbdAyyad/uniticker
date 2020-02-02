package com.aayyad.uniticker.workflow.basic;

import com.aayyad.uniticker.context.Context;
import com.aayyad.uniticker.workflow.WorkFlow;
import com.aayyad.uniticker.action.Action;
import com.sun.istack.internal.NotNull;

import java.util.List;

public class BasicWorkFlowImpl implements WorkFlow {
    private List<Action> actions;

    BasicWorkFlowImpl(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public void start(@NotNull Context context) {
        actions.forEach(action -> action.execute(context));
    }
}
