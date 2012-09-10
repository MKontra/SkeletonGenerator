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
public class BaseDTOandFactorygenerator
{
    private Map<String, Object> data = new HashMap<String, Object>();  ;
    private Template facttemplate;
    private Template basetemplate;
    
    public BaseDTOandFactorygenerator(String pack) throws IOException
    {
        facttemplate = TemplateStore.getTemplate("BaseAbstractDtoFactory.ftl");
        basetemplate = TemplateStore.getTemplate("AbstractDto.ftl");
        data.put("pack", pack);
    }
    
    
    
    public void generate() throws IOException, TemplateException
    {
        File outFileBaseAbstractChangeSet = 
                new File( data.get("pack").toString().replace('.', File.separatorChar ) + 
                File.separator + "DTO" + File.separator  + 
                "BaseAbstractDtoFactory.java");
        
        File outFileRelationChange = 
                new File( data.get("pack").toString().replace('.', File.separatorChar ) + 
                File.separator + "DTO" + File.separator  + 
                "AbstractDto.java");
        
        outFileRelationChange.getParentFile().mkdirs();
                
        facttemplate.process(data, new FileWriter(outFileBaseAbstractChangeSet));
        basetemplate.process(data, new FileWriter(outFileRelationChange));     
    }
}
