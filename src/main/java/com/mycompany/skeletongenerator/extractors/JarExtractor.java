/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.skeletongenerator.extractors;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * @author Administrator
 */
public class JarExtractor implements ClassExtractor
{

    private List<Class> extractedClasses;

    public JarExtractor(String pathToJar) throws Exception
    {
        extractedClasses = new LinkedList<Class>();

        URL jloc = new File(pathToJar).toURI().toURL();
        URLClassLoader ucl = URLClassLoader.newInstance(new URL[]
                {
                    jloc
                }, getClass().getClassLoader());

        JarFile jar = new JarFile(pathToJar);
        Enumeration<JarEntry> enumeration = jar.entries();
        List<Class> classes = new ArrayList<Class>();

        //ITERATE ON THE LIST OF CLASSES I THE JAR FILE
        while (enumeration.hasMoreElements())
        {
            JarEntry jarEntry = (JarEntry) enumeration.nextElement();
            String className = jarEntry.getName();
            if (className.endsWith(".class"))
            {
                className = className.substring(0, className.length() - ".class".length());

                try
                {
                    //LOAD CLASS AND ADD IT TO ARRAY LIST
                    Class toAdd = ucl.loadClass(className.replace('/', '.'));
                    extractedClasses.add(toAdd);
                } catch (ClassNotFoundException e)
                {
                    throw new Exception("unable to find Class" + className, e);
                }

            }
        }

    }

    public List<Class> getClasses()
    {
        return extractedClasses;
    }
}
