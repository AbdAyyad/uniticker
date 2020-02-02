package com.aayyad.uniticker.action;

import com.aayyad.uniticker.Constants;
import com.aayyad.uniticker.context.Context;

import java.util.*;
import java.util.stream.Collectors;

public class SortOperationsByNumberOfUsersAction implements Action {
    @Override
    public void execute(Context context) {
        List<String> operations = context.getParam(Constants.ContextParamsConstants.ALL_OPERATIONS);

        Map<String, Integer> operationsCounts = context.getParam(Constants.ContextParamsConstants.OPERATIONS_COUNTS);

        List<String> sortedOperations = operations.stream()
                .sorted((op1, op2) -> operationsCounts.get(op2).compareTo(operationsCounts.get(op1)))
                .collect(Collectors.toList());
        context.addParam(Constants.ContextParamsConstants.SORTED_OPERATIONS, sortedOperations);
    }
}
