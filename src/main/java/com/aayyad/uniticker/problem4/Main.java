package com.aayyad.uniticker.problem4;

import com.aayyad.uniticker.Constants;
import com.aayyad.uniticker.context.Context;
import com.aayyad.uniticker.context.ContextImpl;
import com.aayyad.uniticker.workflow.multithreaded.MultiThreadedParserImp;
import com.aayyad.uniticker.workflow.WorkFlow;

public class Main {
    public static void main(String[] args) {
        String workflow = "src/main/resources/multi-threaded-workflow.xml";
        String inputFile = "src/main/resources/trackFile2015-03-08 .log";

        WorkFlow workFlow = MultiThreadedParserImp.getInstance().parseWorkFlow(workflow);

        Context context = new ContextImpl();
        context.addParam(Constants.ContextParamsConstants.FILE_NAME, inputFile);

        workFlow.start(context);
    }
}
