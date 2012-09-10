/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.skeletongenerator.generators;

import com.mycompany.skeletongenerator.TemplateStore;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class BaseChangeSetGenerator
{
    
    private Map<String, Object> data = new HashMap<String, Object>();  ;
    private Template chsettemplate;
    private Template relchtemplate;
    
    public BaseChangeSetGenerator(String pack) throws IOException
    {
        chsettemplate = TemplateStore.getTemplate("BaseAbstractChangeSet.ftl");
        relchtemplate = TemplateStore.getTemplate("RelationChange.ftl");
        data.put("pack", pack);
    }
    
    
    
    public void generate() throws IOException, TemplateException
    {
        File outFileBaseAbstractChangeSet = 
                new File( data.get("pack").toString().replace('.', File.separatorChar ) + 
                File.separator + "ChangeSet" + File.separator  + 
                "BaseAbstractChangeSet.java");
        
        File outFileRelationChange = 
                new File( data.get("pack").toString().replace('.', File.separatorChar ) + 
                File.separator + "ChangeSet" + File.separator  + 
                "RelationChange.java");
        
        outFileRelationChange.getParentFile().mkdirs();
        
        chsettemplate.process(data, new FileWriter(outFileBaseAbstractChangeSet));
        relchtemplate.process(data, new FileWriter(outFileRelationChange));     
    }
}
