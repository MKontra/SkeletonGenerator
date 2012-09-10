/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ${pack}.ChangeSet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import ${entity.getPackage().getName()}.*;

/**
 *
 * @author Administrator
 */
@XmlRootElement
public class ${entity.getSimpleName()}ChangeSet extends BaseAbstractChangeSet<${entity.getSimpleName()}>
{
<#list fields as field>
<#if field.isCollection()>
    protected Collection<RelationChange<${field.getGenericCollectionType()}>> ${field.getName()} = new ArrayList<>();

    //accessors
    public Collection<RelationChange<${field.getGenericCollectionType()}>> get${field.getNameFirstCapitalized()}()
    {
        return ${field.getName()}; 
    }
    public void set${field.getNameFirstCapitalized()}(Collection<RelationChange<${field.getGenericCollectionType()}>> ${field.getName()})
    {
        this.${field.getName()} = ${field.getName()};
    }   
</#if>  
</#list>  

    public ${entity.getSimpleName()}ChangeSet()
    {
    }
    
    @Override
    public ${entity.getSimpleName()} getNewEntityFor(EntityManager em)
    {
        ${entity.getSimpleName()} newEnt = new ${entity.getSimpleName()}();
       
    <#list fields as field>
    <#if field.isCollection() >
        for ( RelationChange rc : ${field.getName()} )
        {
            if ( rc.getOp() == RelationChange.Operation.REMOVE ) continue;
                ${field.getOriginalGenericCollectionType()} find = em.find(${field.getOriginalGenericCollectionType()}.class, rc.getToRef());
                newEnt.addTo${field.getNameFirstCapitalized()}( find );
        }

    <#elseif field.isOriginalRefenceTypeTo(entities)>
        {
            ${field.getOriginalTypeName()} find = em.find(${field.getOriginalTypeName()}.class, (${field.getTypeName()}) properties.get("${field.getName()}"));
            newEnt.set${field.getNameFirstCapitalized()}( find );
        }
    <#else>
        newEnt.set${field.getNameFirstCapitalized()}( (${field.getTypeName()}) properties.get("${field.getName()}"));
    </#if>  
    </#list> 
 
        return newEnt;
    }

    @Override
    public void projectChangesOnto(${entity.getSimpleName()} entity, EntityManager em)
    {   
    <#list fields as field>
    <#if field.isCollection() >
        for ( RelationChange rc : ${field.getName()} )
        {
            if ( rc.getOp() == RelationChange.Operation.ADD )
            {
                ${field.getOriginalGenericCollectionType()} find = em.find(${field.getOriginalGenericCollectionType()}.class, rc.getToRef());
                entity.addTo${field.getNameFirstCapitalized()}( find );
            } else if ( rc.getOp() == RelationChange.Operation.REMOVE )
            {
                ${field.getOriginalGenericCollectionType()} find = em.find(${field.getOriginalGenericCollectionType()}.class, rc.getToRef());
                entity.removeFrom${field.getNameFirstCapitalized()}( find );
            }   
        }

    <#elseif field.isOriginalRefenceTypeTo(entities)>
        if ( properties.containsKey("${field.getName()}") )
        {
            ${field.getOriginalTypeName()} find = em.find(${field.getOriginalTypeName()}.class, (${field.getTypeName()}) properties.get("${field.getName()}"));
            entity.set${field.getNameFirstCapitalized()}( find );
        }
    <#else>
        if ( properties.containsKey("${field.getName()}") )
            entity.set${field.getNameFirstCapitalized()}((${field.getTypeName()})properties.get("${field.getName()}"));
    </#if>  
    </#list> 
    }
    
}
