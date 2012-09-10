/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.skeletongenerator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public final class TemplateStore
{

    private static Configuration tmplSource;

    static
    {
        tmplSource = new Configuration();
        tmplSource.setClassForTemplateLoading(TemplateStore.class, "/templates");

    }

    public static Template getTemplate(String name) throws IOException
    {
        return tmplSource.getTemplate(name);
    }

    private TemplateStore()
    {
    }
}
