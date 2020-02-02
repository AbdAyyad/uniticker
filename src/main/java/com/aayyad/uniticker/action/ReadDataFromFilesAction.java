package com.aayyad.uniticker.action;

import com.aayyad.uniticker.Constants;
import com.aayyad.uniticker.context.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadDataFromFilesAction implements Action {
    @Override
    public void execute(Context context) {
        String filePath = context.getParam(Constants.ContextParamsConstants.FILE_NAME);
        List<String> rowData = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                rowData.add(line);
            }
            context.addParam(Constants.ContextParamsConstants.ROW_DATA, rowData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
