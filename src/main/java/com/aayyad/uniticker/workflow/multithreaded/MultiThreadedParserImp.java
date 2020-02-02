package com.aayyad.uniticker.workflow.multithreaded;

import com.aayyad.uniticker.Constants;
import com.aayyad.uniticker.Util;
import com.aayyad.uniticker.workflow.WorkFlow;
import com.aayyad.uniticker.workflow.WorkFlowParser;
import com.aayyad.uniticker.action.Action;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MultiThreadedParserImp implements WorkFlowParser {
    private static MultiThreadedParserImp instance;

    private Util util;

    public static MultiThreadedParserImp getInstance() {
        if (instance == null) {
            synchronized (MultiThreadedParserImp.class) {
                if (instance == null) {
                    instance = new MultiThreadedParserImp();
                }
            }
        }
        return instance;
    }

    private MultiThreadedParserImp() {
        util = Util.getInstance();
    }

    @Override
    public WorkFlow parseWorkFlow(String name) {
        Document workFlowXml = util.parseFile(name);
        NodeList nodes = workFlowXml.getDocumentElement().getElementsByTagName(Constants.WorkFlowConstants.ACTION);
        Map<String, List<Action>> actions = new LinkedHashMap<>();

        for (int i = 0; i < nodes.getLength(); ++i) {
            NamedNodeMap namedNodeMap = nodes.item(i).getAttributes();
            String className = namedNodeMap.getNamedItem(Constants.WorkFlowConstants.CLASS).getNodeValue();
            String idxString = namedNodeMap.getNamedItem(Constants.WorkFlowConstants.ID).getNodeValue();
            util.getActionFromName(className).ifPresent(action -> {
                actions.compute(idxString, (idx, list) -> {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(action);
                    return list;
                });
            });
        }
        List<List<Action>> listOfActions = new ArrayList<>();
        actions.forEach((idx, list) -> listOfActions.add(list));
        return new MultiThreadedWorkFlowImp(listOfActions);
    }

}
