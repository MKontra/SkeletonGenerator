/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.skeletongenerator.misc;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author Administrator
 */
public class FieldRepr
{

    private Type type;
    private String name;
    private String visibilty;
    private Collection<Annotation> annontations;
    private Type originalType;

    public FieldRepr(Type type, String name, String visibilty)
    {
        this.type = type;
        this.name = name;
        this.visibilty = visibilty;
    }

    public FieldRepr(Type type, String name, String visibilty, Collection<Annotation> annontations)
    {
        this.type = type;
        this.name = name;
        this.visibilty = visibilty;
        this.annontations = annontations;
    }

    public FieldRepr(Type type, String name, String visibilty, Collection<Annotation> annontations, Type originalType)
    {
        this.type = type;
        this.name = name;
        this.visibilty = visibilty;
        this.annontations = annontations;
        this.originalType = originalType;
    }

    public Type getOriginalType()
    {
        return originalType;
    }

    public void setOriginalType(Type originalType)
    {
        this.originalType = originalType;
    }  
    
    public String getOriginalGenericCollectionType()
    {
        return type instanceof ParameterizedType ?
                ((Class) ((ParameterizedType) originalType).getActualTypeArguments()[0]).getSimpleName() :  "ERROR";
    }
    
    public String getName()
    {
        return name;
    }

    public String getNameFirstCapitalized()
    {
        return WordUtils.capitalize(name);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Type getType()
    {
        return type;
    }
    
    public boolean isRefenceTypeTo(Collection<Class> possibleRefs)
    {
        return type instanceof Class ? possibleRefs.contains((Class) type) : false;
    }
    
    public boolean isOriginalRefenceTypeTo(Collection<Class> possibleRefs)
    {
        return originalType instanceof Class ? possibleRefs.contains((Class) originalType) : false;
    }

    public boolean isCollection()
    {
        return type instanceof ParameterizedType
                ? Collection.class.isAssignableFrom((Class) ((ParameterizedType) type).getRawType()):
                  Collection.class.isAssignableFrom(((Class) type));
    }
    
    public String getGenericCollectionType()
    {
        return type instanceof ParameterizedType ?
                ((Class) ((ParameterizedType) type).getActualTypeArguments()[0]).getSimpleName() :  "ERROR";
    }

    public String getOriginalTypeName()
    {
        return originalType instanceof ParameterizedType ?
                ((Class) ((ParameterizedType) originalType).getRawType()).getSimpleName() + "<" + ((Class) ((ParameterizedType) originalType).getActualTypeArguments()[0]).getSimpleName() + ">" :
                ((Class) originalType).getSimpleName();
    } 
    
    public String getTypeName()
    {
        return type instanceof ParameterizedType ?
                ((Class) ((ParameterizedType) type).getRawType()).getSimpleName() + "<" + ((Class) ((ParameterizedType) type).getActualTypeArguments()[0]).getSimpleName() + ">" :
                ((Class) type).getSimpleName();
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public String getVisibilty()
    {
        return visibilty;
    }

    public void setVisibilty(String visibilty)
    {
        this.visibilty = visibilty;
    }

    public Collection<Annotation> getAnnontations()
    {
        return annontations;
    }

    public void setAnnontations(Collection<Annotation> annontations)
    {
        this.annontations = annontations;
    }
}
