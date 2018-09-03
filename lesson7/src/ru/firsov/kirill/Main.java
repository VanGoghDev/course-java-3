package ru.firsov.kirill;

import ru.firsov.kirill.annotations.AfterSuite;
import ru.firsov.kirill.annotations.BeforeSuite;
import ru.firsov.kirill.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    static TreeMap<Priority, Method> tree = new TreeMap<>();
    static int isAfter = 0;
    static int isBefore = 0;
    static Method before = null;
    static Method after = null;

    public static void main(String[] args) {
        start(TestApp.class);
    }

    private static void start(Class clazz) {
        Method[] methods = clazz.getMethods();
        Priority priority;
        for(Method m:methods) {
            if (m.getAnnotation(BeforeSuite.class) != null) {
                if (isBefore == 0) {
                    before = m;
                    isBefore++;
                } else {
                    throw new RuntimeException("Annotation BeforeSuite has been already used");
                }
            }
            if (m.getAnnotation(AfterSuite.class) != null) {
                if (isAfter == 0) {
                    after = m;
                    isAfter++;
                } else {
                    throw new RuntimeException("Annotation AfterSuite has been already used");
                }
            }
            if (m.getAnnotation(Test.class) != null) {
                priority = new Priority(m.getAnnotation(Test.class).priority(), m.getName());
                tree.put(priority, m);
            }
        }

        try {
            Object o = clazz.getConstructor().newInstance();
            before.invoke(o);
            for(Map.Entry<Priority, Method> entry:tree.entrySet()) {
                entry.getValue().invoke(o);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
