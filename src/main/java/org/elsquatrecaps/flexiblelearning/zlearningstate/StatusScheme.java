/*
 * Copyright 2021 Grup de millora MetFlex.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elsquatrecaps.flexiblelearning.zlearningstate;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author professor
 */
public class StatusScheme {

   
    private String id;
    private String idActivity;
    private TreeMap<String,SchemeItem> sortedItems= new TreeMap<>();
    private TreeMap<Integer,SchemeItem> itemsById=new TreeMap<>();
    private final String separator="\\";
    private int counter=0;
    private String procedure;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(String id) {
        this.id = id;
    }

    
    /**
     * Get the value of idActivity
     *
     * @return the value of idActivity
     */
    public String getIdActivity() {
        return idActivity;
    }

    /**
     * Set the value of idActivity
     *
     * @param idActivity new value of idActivity
     */
    public void setIdActivity(String idActivity) {
        this.idActivity = idActivity;
    }

    public int addItem(String pathName, String description, Class clasz){
        int result;
        
        if(!checkPathName(pathName)) return 0;
        
        if(sortedItems.containsKey(pathName)) return 0;
               
        result= ++counter;
        
        SchemeItem item=new SchemeItem(pathName, description, clasz, result);
        
        sortedItems.put(pathName,item);
        itemsById.put(result, item);
        
        return result;
    }

    public SchemeItem get(String pathName){
        if(!checkPathName(pathName)) return null;

        return sortedItems.get(pathName);

    }
    
    public SchemeItem get(int id){

        return itemsById.get(id);
    }
    
    public SchemeItem remove(String pathName){
        
        if(!checkPathName(pathName)) return null;

        SchemeItem aux=sortedItems.remove(pathName);

        if(aux==null) return null;

        return itemsById.remove(aux.getNumber());

    }
    
    public SchemeItem remove(int id){
     
        SchemeItem aux=itemsById.remove(id);
        
        if(aux==null) return null;
        
        return sortedItems.remove(aux.getPathName());

    }

    public boolean setItem(String pathName, String description, Class clasz){
         
        if(!checkPathName(pathName)) return false;
        
        SchemeItem aux=sortedItems.get(pathName);

        if(aux==null) return false;
        
        aux.setDescription(description);
        aux.setClasz(clasz);
        
        return true;
    }
    
    public boolean setItem(int id, String description, Class clasz){
         
        
        SchemeItem aux=itemsById.get(id);

        if(aux==null) return false;
        
        aux.setDescription(description);
        aux.setClasz(clasz);
        
        return true;
    }


    public boolean changePath(String oldPathName, String newPathName){
         
        if(!checkPathName(oldPathName)|| !checkPathName(newPathName)) return false;
        
        if(sortedItems.containsKey(newPathName)) return false;
        
        SchemeItem aux=sortedItems.get(oldPathName);

        if(aux==null) return false;
        
        aux.setPathName(newPathName);
        
        sortedItems.put(newPathName,aux);
        
        sortedItems.remove(oldPathName);
        
        if(procedure!=null){    
            procedure=procedure.replaceAll(Pattern.quote(oldPathName), newPathName);   
        }
        

        return true;
    }

    
    public List<SchemeItem> queryRange(String prefix){
        
        return sortedItems.subMap(prefix, prefix+"~ ").values().stream().collect(Collectors.toList());

    }
    
    
    private boolean checkPathName(String pathName) {
        return !pathName.endsWith(separator) && !pathName.startsWith(separator);
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

}
