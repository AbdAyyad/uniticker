package com.aayyad.uniticker.action;

import com.aayyad.uniticker.Constants;
import com.aayyad.uniticker.Entity;
import com.aayyad.uniticker.context.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilterEntitiesByOnePerUserAction implements Action {
    @Override
    public void execute(Context context) {
        List<Entity> entities = context.getParam(Constants.ContextParamsConstants.ENTITIES);
        Set<Entity> filteredEntities = new HashSet<>(entities);
        context.addParam(Constants.ContextParamsConstants.FILTERED_ENTITIES, new ArrayList<>(filteredEntities));
    }
}
