package com.senyint.test;

import com.senyint.common.context.ExternalClassLoader;
import com.senyint.common.vfs.Foo;
import com.senyint.common.vfs.FooImpl;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Hotspot2Test {

    public static void main(String[] args) throws IOException,
            ClassNotFoundException, IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {

        Foo foo = new FooImpl();
        foo.sayHello();

        ExternalClassLoader cc = new ExternalClassLoader();

        String name = FooImpl.class.getName();
        String file = "F:\\vfs\\FooImpl.class";
        byte[] data = IOUtils.toByteArray(new FileInputStream(file));
        Class<?> cls = cc.defineClass(name, data);
        Foo foo2 = (Foo) cls.newInstance();
        foo2.sayHello();
    }

}
