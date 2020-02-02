package com.aayyad.uniticker.action;

import com.aayyad.uniticker.Constants;
import com.aayyad.uniticker.context.Context;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class PrintResultsAction implements Action {
    @Override
    public void execute(Context context) {
        List<String> sortedOperation = context.getParam(Constants.ContextParamsConstants.SORTED_OPERATIONS);
        Map<String, Integer> operationCount = context.getParam(Constants.ContextParamsConstants.OPERATIONS_COUNTS);
        List<String> allUsers = context.getParam(Constants.ContextParamsConstants.ALL_USERS);

        StringBuilder result = new StringBuilder();

        IntStream.range(0, sortedOperation.size()).forEach(idx -> {
            result.append("operation \"");
            result.append(sortedOperation.get(idx));
            result.append("\" number ");
            result.append(idx + 1);
            result.append(" and it is used by ");
            result.append(operationCount.get(sortedOperation.get(idx)));
            result.append(" users of ");
            result.append(allUsers.size());
            result.append(" percentage ");
            result.append((operationCount.get(sortedOperation.get(idx)) / (double) allUsers.size()) * 100);
            result.append(" %\n");
        });

        System.out.println(result);
    }
}
