/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.skeletongenerator.misc;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.collection.LambdaCollections.*;
import ch.lambdaj.function.convert.Converter;
import java.lang.reflect.Field;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 *
 * @author Administrator
 */
public final class JPAAnnotationsReflectUtils
{

    public static Collection<Class> getIDTypes(Class entity)
    {
        return with(Arrays.asList(entity.getDeclaredFields())).retain( new BaseMatcher<Object>() {

            public boolean matches(Object item)
            {
                return ((Field) item).isAnnotationPresent(javax.persistence.Id.class);
            }

            public void describeTo(Description description)
            {
            }
        }).convert( new Converter<Field, Class>() {

            public Class convert(Field from)
            {
                return from.getType();
            }
        });
     }
    
    private JPAAnnotationsReflectUtils()
    {
    }
    
}
