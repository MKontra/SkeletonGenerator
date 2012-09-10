/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.skeletongenerator.processors;

import ch.lambdaj.function.convert.Converter;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import com.mycompany.skeletongenerator.misc.FieldRepr;
import com.mycompany.skeletongenerator.misc.JPAAnnotationsReflectUtils;
import static ch.lambdaj.Lambda.*;
import ch.lambdaj.collection.LambdaCollection;
import static ch.lambdaj.collection.LambdaCollections.*;
import ch.lambdaj.function.convert.Converter;
import java.lang.annotation.Annotation;
/**
 *
 * @author Administrator
 */
public class DTOBeanFieldsGenerator implements Converter<FieldRepr, FieldRepr>
{

    public static FieldRepr convertByMe(FieldRepr from)
    {
        return new DTOBeanFieldsGenerator().convert(from);
    }

    public FieldRepr convert(FieldRepr from)
    {
        Collection anncls = with(from.getAnnontations()).convert( new Converter<Annotation, Class>() {
            public Class convert(Annotation from)
            {
                return from.annotationType();
            }
        });
                
        if (anncls.contains(javax.persistence.OneToOne.class))
        {
            Class refClass = (Class) from.getType();
            Collection<Class> refClassPkey = JPAAnnotationsReflectUtils.getIDTypes(refClass);
            return new FieldRepr(refClassPkey.size() == 1 ? refClassPkey.iterator().next() : Object[].class, from.getName(), from.getVisibilty(), from.getAnnontations(), from.getType());
        }
        if (    (anncls.contains(javax.persistence.OneToMany.class))
                || anncls.contains(javax.persistence.ManyToMany.class))
        {
            Class refClass = (Class) ((ParameterizedType) from.getType()).getActualTypeArguments()[0];
            Collection<Class> refClassPkey = JPAAnnotationsReflectUtils.getIDTypes(refClass);
            return new FieldRepr(refClassPkey.size() == 1 ? new ParametrizedTypeImpl(Collection.class, refClassPkey.iterator().next()) : Object[].class, from.getName(), from.getVisibilty(), from.getAnnontations(), from.getType());
        }
        return from;
    }
}
