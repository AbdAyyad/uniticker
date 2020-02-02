package com.aayyad.uniticker.workflow.basic;

import com.aayyad.uniticker.Constants;
import com.aayyad.uniticker.Util;
import com.aayyad.uniticker.workflow.WorkFlow;
import com.aayyad.uniticker.workflow.WorkFlowParser;
import com.aayyad.uniticker.action.Action;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;


public class BasicWorkFlowParserImpl implements WorkFlowParser {
    private static BasicWorkFlowParserImpl instance;

    private Util util;

    public static BasicWorkFlowParserImpl getInstance() {
        if (instance == null) {
            synchronized (BasicWorkFlowParserImpl.class) {
                if (instance == null) {
                    instance = new BasicWorkFlowParserImpl();
                }
            }
        }
        return instance;
    }

    private BasicWorkFlowParserImpl() {
        util = Util.getInstance();
    }

    @Override
    public WorkFlow parseWorkFlow(String name) {
        Document workFlowXml = util.parseFile(name);
        NodeList nodes = workFlowXml.getDocumentElement().getElementsByTagName(Constants.WorkFlowConstants.ACTION);
        List<Action> actions = new ArrayList<>();

        for (int i = 0; i < nodes.getLength(); ++i) {
            NamedNodeMap namedNodeMap = nodes.item(i).getAttributes();
            String className = namedNodeMap.getNamedItem(Constants.WorkFlowConstants.CLASS).getNodeValue();
            util.getActionFromName(className).ifPresent(actions::add);
        }
        return new BasicWorkFlowImpl(actions);
    }

}
