/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.skeletongenerator.processors;

import ch.lambdaj.function.convert.Converter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.collection.LambdaCollections.*;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import com.mycompany.skeletongenerator.misc.FieldRepr;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 *
 * @author Administrator
 */
public class EntityFieldsExtractor implements ch.lambdaj.function.convert.Converter<Class, Collection<FieldRepr>>
{
    
public static Collection<FieldRepr> convertByMe(Class from) 
{
    return new EntityFieldsExtractor().convert(from);
}
    
    public Collection<FieldRepr> convert(Class from)
    {
        Collection<FieldRepr> retval = new ArrayList<FieldRepr>();
        
        for ( Field f : from.getDeclaredFields() )
        {
            Class type = f.getType();
            Type genericType = f.getGenericType();
            retval.add( new FieldRepr(  f.getGenericType(), 
                                        f.getName(), 
                                        Modifier.toString(f.getModifiers()),
                                        Arrays.asList(f.getDeclaredAnnotations())
                    ) );
        }
        
        return retval;
    }
    
}
