/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceset;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author hp
 */
public class DataSave {

    private static DataSave dataSave = new DataSave();
    private Map<String,String> draw = new HashMap<String, String>();

    private DataSave(){
    }

    public static DataSave getData(){
        return  dataSave;
    }

    public void insert(String identity, String values){
        draw.put(identity,values);
    }

    public String retrieve(String identity){
        for(Map.Entry<String,String> entries : draw.entrySet()){
            if(identity.equals(entries.getKey())){
                return  entries.getValue();
            }
        }
        return null;
    }
    public void view() {
        for (Map.Entry<String, String> entries : draw.entrySet()) {
            System.out.println("identity " + entries.getKey() + " token" + entries.getValue());
        }
    }
    
}