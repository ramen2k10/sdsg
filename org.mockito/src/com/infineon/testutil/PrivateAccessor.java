package com.infineon.testutil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Assert;

public class PrivateAccessor {
    public static Object getPrivateField(Object o, String fieldname) {
        Assert.assertNotNull(o);
        Assert.assertNotNull(fieldname);

        try {
            final Field field = o.getClass().getDeclaredField(fieldname);
            field.setAccessible(true);
            return field.get(o);
        } catch (NoSuchFieldException e) {
            Assert.fail("Field " + fieldname + " not found in class " + o.getClass().toString());
            e.printStackTrace();
        } catch (SecurityException e) {
            Assert.fail("Access error for field " + fieldname + " in class " + o.getClass().toString());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            Assert.fail("Illegal argument for field " + fieldname + " in class " + o.getClass().toString());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Assert.fail("Illegal access to field " + fieldname + " in class " + o.getClass().toString());
            e.printStackTrace();
        }
        return null;
    }

    public static Object invokePrivateMethode(Object o, String methodeName, Object[] params, Class<?>[] paramtypes) {
        Assert.assertNotNull(o);
        Assert.assertNotNull(methodeName);

        try {
            Method method = o.getClass().getDeclaredMethod(methodeName, paramtypes);
            method.setAccessible(true);
            return method.invoke(o, params);
            // return method;
        } catch (NoSuchMethodException e) {
            Assert.fail("Method " + methodeName + " not found in class " + o.getClass().toString());
            e.printStackTrace();
        } catch (SecurityException e) {
            Assert.fail("Access error for method " + methodeName + " in class " + o.getClass().toString());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Assert.fail("Illegal access to method " + methodeName + " in class " + o.getClass().toString());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            Assert.fail("Illegal argument for method " + methodeName + " in class " + o.getClass().toString());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Assert.fail("Wrong target for method " + methodeName + " with class " + o.getClass().toString());
            e.printStackTrace();
        }
        return null;
    }

    public static void setPrivateField(Object o, String fieldname, Object newField) {
        Assert.assertNotNull(o);
        Assert.assertNotNull(fieldname);

        try {
            final Field field = o.getClass().getDeclaredField(fieldname);
            field.setAccessible(true);
            field.set(o, newField);
        } catch (NoSuchFieldException e) {
            Assert.fail("Field " + fieldname + " not found in class " + o.getClass().toString());
            e.printStackTrace();
        } catch (SecurityException e) {
            Assert.fail("Access error for field " + fieldname + " in class " + o.getClass().toString());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            Assert.fail("Illegal argument for field " + fieldname + " in class " + o.getClass().toString());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Assert.fail("Illegal access to field " + fieldname + " in class " + o.getClass().toString());
            e.printStackTrace();
        }
        return;
    }

    public static void setPrivateFieldRec(Object o, String fieldname, Object newField) {
        Assert.assertNotNull(o);
        Assert.assertNotNull(fieldname);
        Class<?> tmpClass = o.getClass();
        boolean fieldFound = false;
        while (tmpClass != null) {
            try {
                final Field field = tmpClass.getDeclaredField(fieldname);
                field.setAccessible(true);
                field.set(o, newField);
                fieldFound = true;
                break;
            } catch (NoSuchFieldException e) {
                // Goto the super class.
            } catch (SecurityException e) {
                Assert.fail("Access error for field " + fieldname + " in class " + o.getClass().toString());
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                Assert.fail("Illegal argument for field " + fieldname + " in class " + o.getClass().toString());
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                Assert.fail("Illegal access to field " + fieldname + " in class " + o.getClass().toString());
                e.printStackTrace();
            }
            tmpClass = tmpClass.getSuperclass();
        }
        if (!fieldFound)
            Assert.fail("Field " + fieldname + " not found in class and in superclasses of " + o.getClass().toString());
        return;
    }

    public static void setSuperPrivateField(Object o, String fieldname, Object newField) {
        Assert.assertNotNull(o);
        Assert.assertNotNull(fieldname);

        try {
            final Field field = o.getClass().getSuperclass().getDeclaredField(fieldname);
            field.setAccessible(true);
            field.set(o, newField);
        } catch (NoSuchFieldException e) {
            Assert.fail("Field " + fieldname + " not found in class " + o.getClass().toString());
            e.printStackTrace();
        } catch (SecurityException e) {
            Assert.fail("Access error for field " + fieldname + " in class " + o.getClass().toString());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            Assert.fail("Illegal argument for field " + fieldname + " in class " + o.getClass().toString());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Assert.fail("Illegal access to field " + fieldname + " in class " + o.getClass().toString());
            e.printStackTrace();
        }
        return;
    }
}
