/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lolz2;

import lolz.DiaryUser;

/**
 *
 * @author Administrator
 */
public class DiaryUserDtoFactory extends BaseAbstractDtoFactory<DiaryUser, DiaryUserDto>
{

    @Override
    public DiaryUserDto getDtoForEntity(DiaryUser obj)
    {
        return new DiaryUserDto(obj);
    }
    
}
