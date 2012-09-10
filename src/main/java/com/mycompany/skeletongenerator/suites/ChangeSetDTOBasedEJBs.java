/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.skeletongenerator.suites;

import com.mycompany.skeletongenerator.extractors.JarExtractor;
import com.mycompany.skeletongenerator.generators.*;
import com.mycompany.skeletongenerator.misc.FieldRepr;
import com.mycompany.skeletongenerator.processors.DTOBeanFieldsGenerator;
import com.mycompany.skeletongenerator.processors.EntityFieldsExtractor;
import com.mycompany.skeletongenerator.processors.EntityFilter;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.collection.LambdaCollections.*;
/**
 *
 * @author Administrator
 */
public class ChangeSetDTOBasedEJBs
{

    private String pack;

    public ChangeSetDTOBasedEJBs(String pack)
    {
        this.pack = pack;
    }

    public void runSuite() throws IOException, TemplateException, Exception
    {
        List<Class> clsses = new JarExtractor("RemoteDiaryModel-1.0-SNAPSHOT.jar").getClasses();
        Collection<Class> entits = EntityFilter.filterByMe(clsses);



        for (Class c : entits)
        {
            Collection<FieldRepr> flds = EntityFieldsExtractor.convertByMe(c);
            Collection<FieldRepr> flds2  = with(flds).convert(new DTOBeanFieldsGenerator());

            new DTOGenerator(pack, c, entits, flds2).generateByClassName();
            new ChangeSetGenerator(pack, c, entits, flds2).generateByClassName();
        }

        new BaseDTOandFactorygenerator(pack).generate();
        new BaseChangeSetGenerator(pack).generate();
        new EJBBaseClassGenerator(pack).generate();
    }
}
