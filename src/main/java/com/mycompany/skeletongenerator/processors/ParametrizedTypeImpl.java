/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.skeletongenerator.processors;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 *
 * @author Administrator
 */
public class ParametrizedTypeImpl implements ParameterizedType
{

    private Class collectionType;

    /**
     * Get the value of collectionType
     *
     * @return the value of collectionType
     */
    public Class getCollectionType()
    {
        return collectionType;
    }

    /**
     * Set the value of collectionType
     *
     * @param collectionType new value of collectionType
     */
    public void setCollectionType(Class collectionType)
    {
        this.collectionType = collectionType;
    }
    private Class genericType;

    /**
     * Get the value of genericType
     *
     * @return the value of genericType
     */
    public Class getGenericType()
    {
        return genericType;
    }

    /**
     * Set the value of genericType
     *
     * @param genericType new value of genericType
     */
    public void setGenericType(Class genericType)
    {
        this.genericType = genericType;
    }

    public ParametrizedTypeImpl(Class collectionType, Class genericType)
    {
        this.collectionType = collectionType;
        this.genericType = genericType;
    }

    public Type[] getActualTypeArguments()
    {
        return new Type[] { this.genericType };
    }

    public Type getRawType()
    {
        return this.collectionType;
    }

    public Type getOwnerType()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
