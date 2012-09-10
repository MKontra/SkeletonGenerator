package ${pack}.DTO;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ${entity.getPackage().getName()}.*;

@XmlRootElement
public class ${entity.getSimpleName()}Dto
{
<#list fields as field>
//field definition
//@XmlElement
    ${field.getVisibilty()} ${field.getTypeName()} ${field.getName()} ;

//accessors
    public ${field.getTypeName()} get${field.getNameFirstCapitalized()}()
    {
        return ${field.getName()}; 
    }

    public void set${field.getNameFirstCapitalized()}(${field.getTypeName()} ${field.getName()})
    {
        this.${field.getName()} = ${field.getName()};
    }    

</#list>  

    public ${entity.getSimpleName()}Dto(${entity.getSimpleName()} _${entity.getSimpleName()})
    {
<#list fields as field>
<#if field.isCollection() >
    for (IIdAble i : _${entity.getSimpleName()}.get${field.getNameFirstCapitalized()}())
    {
        this.${field.getName()}.add( (${field.getGenericCollectionType()}) i.getId());
    }
<#elseif field.isOriginalRefenceTypeTo(entities)>
    this.${field.getName()} = _${entity.getSimpleName()}.get${field.getNameFirstCapitalized()}().getId();
<#else>
    this.${field.getName()} = _${entity.getSimpleName()}.get${field.getNameFirstCapitalized()}();
</#if>  

</#list>  
    }

}