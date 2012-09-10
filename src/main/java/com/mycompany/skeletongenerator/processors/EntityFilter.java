/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.skeletongenerator.processors;

import java.util.Collection;
import java.util.List;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import javax.persistence.Entity;
import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.collection.LambdaCollections.*;

/**
 *
 * @author Administrator
 */
public class EntityFilter extends BaseMatcher<Class>
{

    public static Collection<Class> filterByMe( Collection<Class> in )
    {
        return with(in).retain( new EntityFilter() );
    }
    
    @Override
    public boolean matches(Object item)
    {
        return ((Class)item).isAnnotationPresent(javax.persistence.Entity.class);
    }

    public void describeTo(Description description)
    {
    }
    
}
