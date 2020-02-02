package com.aayyad.uniticker.action;

import com.aayyad.uniticker.Constants;
import com.aayyad.uniticker.Entity;
import com.aayyad.uniticker.context.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculateOperationsCountAction implements Action {
    @Override
    public void execute(Context context) {
        List<Entity> entities = context.getParam(Constants.ContextParamsConstants.FILTERED_ENTITIES);

        Map<String, Integer> operationsCounts = new HashMap<>();

        entities.forEach(entity -> {
            operationsCounts.compute(entity.getOperation(), (operation, count) -> {
                if (count == null)
                    return 1;
                return count + 1;
            });
        });
        context.addParam(Constants.ContextParamsConstants.OPERATIONS_COUNTS, operationsCounts);
    }
}
