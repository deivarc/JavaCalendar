
import java.util.Map;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Black Beauty
 */
public class TreeMapExample {
    public static void main (String []args) {
        Map <String, String> cityMap = new TreeMap <String, String>();
        cityMap.put ("John likes","\tFortnite");
        cityMap.put ("Cindy likes", "\tAnime");
        cityMap.put("Bob likes", "\tDota");
        
        for (Map.Entry <String, String> me : cityMap.entrySet())
            System.out.println(me.getKey() + me.getValue());
        //getKey()
        //getValue() are..
        //Map.Entry Object methods
    }
}
