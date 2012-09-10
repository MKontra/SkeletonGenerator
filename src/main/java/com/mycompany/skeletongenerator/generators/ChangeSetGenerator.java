/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.skeletongenerator.generators;

import com.mycompany.skeletongenerator.TemplateStore;
import com.mycompany.skeletongenerator.misc.FieldRepr;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class ChangeSetGenerator
{

    private Map<String, Object> data;
    private Template template;
    
    public ChangeSetGenerator(String pck, Class entity, Collection<Class> entities, Collection<FieldRepr> flds) throws IOException, TemplateException
    {
        template = TemplateStore.getTemplate("ChangeSet.ftl");
        data = new HashMap<String, Object>();       

        data.put("entities", entities);
        data.put("pack", pck);
        data.put("entity", entity);
        data.put("fields", flds);

        Writer out = new OutputStreamWriter(System.out);
        template.process(data, out);
        out.flush();
    }
    
    public void generateByClassName() throws IOException, TemplateException
    {
        File outFile = 
                new File( data.get("pack").toString().replace('.', File.separatorChar ) + 
                File.separator + "ChangeSet" + File.separator  + 
                ( (Class)data.get("entity")).getSimpleName() + "ChangeSet.java");
        
        outFile.getParentFile().mkdirs();
        
        template.process(data, new FileWriter(outFile));
    }  
}
