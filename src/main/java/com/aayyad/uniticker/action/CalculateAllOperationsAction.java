package com.aayyad.uniticker.action;

import com.aayyad.uniticker.Constants;
import com.aayyad.uniticker.Entity;
import com.aayyad.uniticker.context.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalculateAllOperationsAction implements Action {
    @Override
    public void execute(Context context) {
        List<Entity> entities = context.getParam(Constants.ContextParamsConstants.FILTERED_ENTITIES);
        Set<String> operations = new HashSet<>();
        entities.forEach(entity -> operations.add(entity.getOperation()));
        context.addParam(Constants.ContextParamsConstants.ALL_OPERATIONS, new ArrayList<>(operations));

    }
}
