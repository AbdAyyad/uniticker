package com.aayyad.uniticker.action;

import com.aayyad.uniticker.Constants;
import com.aayyad.uniticker.Entity;
import com.aayyad.uniticker.context.Context;

import java.util.List;
import java.util.stream.Collectors;

public class WrapDataIntoEntitiesObjectsAction implements Action {
    @Override
    public void execute(Context context) {
        List<String> rowData = context.getParam(Constants.ContextParamsConstants.ROW_DATA);
        List<Entity> entities = rowData.stream()
                .map(line -> {
                    int idx = line.indexOf('/') + 1;
                    String key = line.substring(idx);
                    idx = key.indexOf('/');
                    String user = key.substring(0, idx);
                    String operation = key.substring(idx + 1);
                    return new Entity(key, user, operation);
                }).collect(Collectors.toList());
        context.addParam(Constants.ContextParamsConstants.ENTITIES, entities);
    }
}
