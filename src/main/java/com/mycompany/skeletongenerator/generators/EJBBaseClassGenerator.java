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
public class EJBBaseClassGenerator
{
    private Map<String, Object> data = new HashMap<String, Object>();  ;
    private Template ejbtemplate;
    private Template ifacetemplate;
    
    public EJBBaseClassGenerator(String pack) throws IOException
    {
        ejbtemplate = TemplateStore.getTemplate("AbstractEJBEntityFacadeBase.ftl");
        ifacetemplate = TemplateStore.getTemplate("AbstractEJBEntityFacadeInterface.ftl");
        data.put("pack", pack);
    }
    
    
    
    public void generate() throws IOException, TemplateException
    {
        File outFileBaseAbstractChangeSet = 
                new File( data.get("pack").toString().replace('.', File.separatorChar ) + 
                File.separator + "EJB" + File.separator  + 
                "AbstractEJBEntityFacadeBase.java");
        
        File outFileRelationChange = 
                new File( data.get("pack").toString().replace('.', File.separatorChar ) + 
                File.separator + "EJB" + File.separator  + 
                "AbstractEJBEntityFacadeInterface.java");

        outFileRelationChange.getParentFile().mkdirs();     
        
        ejbtemplate.process(data, new FileWriter(outFileBaseAbstractChangeSet));
        ifacetemplate.process(data, new FileWriter(outFileRelationChange));     
    }
}
