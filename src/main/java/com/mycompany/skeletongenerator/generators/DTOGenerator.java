/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.skeletongenerator.generators;

import com.mycompany.skeletongenerator.TemplateStore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.collection.LambdaCollections.*;
import ch.lambdaj.function.convert.Converter;
import java.io.*;
import java.util.*;
import com.mycompany.skeletongenerator.misc.FieldRepr;
/**
 *
 * @author Administrator
 */
public class DTOGenerator
{

    private Map<String, Object> data;
    private Template template;
    
    public DTOGenerator(String pck, Class entity, Collection<Class> entities, Collection<FieldRepr> flds) throws IOException, TemplateException
    {
        template = TemplateStore.getTemplate("DTO.ftl");
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
                File.separator + "DTO" + File.separator  + 
                ( (Class)data.get("entity")).getSimpleName() + "Dto.java");
        
        outFile.getParentFile().mkdirs();
        
        template.process(data, new FileWriter(outFile));
    }
}
