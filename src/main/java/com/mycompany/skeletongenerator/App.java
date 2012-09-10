package com.mycompany.skeletongenerator;

import com.mycompany.skeletongenerator.suites.ChangeSetDTOBasedEJBs;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main(String[] args) throws Exception
    {
        new ChangeSetDTOBasedEJBs("com.mycompany.remotediarylogic").runSuite();
    }
}
