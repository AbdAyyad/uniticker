package com.aayyad.uniticker;

import com.aayyad.uniticker.action.Action;
import com.sun.istack.internal.Nullable;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class Util {
    private static Util instance;

    private Util() {
    }

    public static Util getInstance() {
        if (instance == null) {
            synchronized (Util.class) {
                if (instance == null) {
                    instance = new Util();
                }
            }
        }

        return instance;
    }

    @Nullable
    public Document parseFile(String fileName) {
        Document doc = null;
        try {
            File inputFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public Optional<Action> getActionFromName(String className) {
        Action action = null;
        try {
            Class<?> klass = Class.forName(className);
            Constructor<?> constructor = klass.getConstructor();
            action = (Action) constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(action);
    }

    public int getIdx(String id) {
        String idxString = id.substring(1);
        try {
            return Integer.parseInt(idxString) - 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
