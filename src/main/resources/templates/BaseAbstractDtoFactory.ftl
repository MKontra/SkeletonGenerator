/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ${pack}.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public abstract class BaseAbstractDtoFactory<T, Dto extends AbstractDto<T>>
{
    public abstract Dto getDtoForEntity(T obj);
    
    public List<Dto> projectIntoDtos(List<T> col)
    {
        List<Dto> retVal = new ArrayList<>();
        for ( T e : col )
        {
            retVal.add( getDtoForEntity(e) );
        }
        return retVal;
    }
    
}
