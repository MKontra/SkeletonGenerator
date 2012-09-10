/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lolz2;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lolz.DiaryGroup;
import lolz.DiaryUser;

/**
 *
 * @author Administrator
 */
@XmlRootElement
public class DiaryUserDto extends AbstractDto<DiaryUser>
{
    @XmlElement 
    String Name;
    @XmlElement 
    byte[] PasswordSHash;
    @XmlElement 
    Collection<Object> Groups;


    public Collection<Object> getGroups()
    {
        return Groups;
    }

    public String getName()
    {
        return Name;
    }

    public byte[] getPasswordSHash()
    {
        return PasswordSHash;
    }

    public DiaryUserDto()
    {
    }

    public DiaryUserDto(DiaryUser du)
    {
        this.Name = du.getName();
        this.PasswordSHash = du.getPasswordSHash();

        Groups = new ArrayList<>();
        for (DiaryGroup dg : du.getGroups())
        {
            Groups.add(dg.getId());
        }
    }
}
