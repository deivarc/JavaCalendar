/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaExer;

/**
 *
 * @author Black Beauty
 */

import java.util.StringTokenizer;

public class strtokenizer {
    public static void main (String args [] ) {
        System.out.println("This is an example of how a stringtokenizer works.");
        String teststring = "John|62378122|20|||";
        /*
        E.g.
        Name | HP | age | job | address
        */
        String DELIM ="|";
        StringTokenizer st = new StringTokenizer (teststring, DELIM, true);
        String [] tokens = new String[10]; // assuming 10 maximum fields
        int count=0;
        String prevtoken="";
        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            if ( (token.equals("|") && prevtoken.equals(""))
                    || (token.equals("|") && prevtoken.equals("|"))
                    ) {
                tokens[count]="Empty Field";
            } 
            else if (!"|".equals(token) ) {
                tokens[count] = token;
            } else {
                prevtoken = token;
                continue;
            }
            
            prevtoken = token;
            count++;
        }
        for (String tokent : tokens) {
            //fixed showing nullvalues through 
            //presentation of string 
            // maybe i  can fix it through the array instead?
            if (tokent!=null)
            System.out.println(tokent);
        } 
    }
}
