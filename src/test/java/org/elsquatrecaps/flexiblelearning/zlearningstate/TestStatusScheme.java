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

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;


/**
 *
 * @author JRamon
 */
public class TestStatusScheme {
    


    @Test
    // Test add and gets methods
    public void firstTest() {
         StatusScheme sc= new StatusScheme();
         int [] ids= new int[10];
         
         int i=0;

         ids[i++]=sc.addItem("Coneixements$$Sap Sumar", "Suma be",     Integer.class);
         ids[i++]=sc.addItem("Coneixements$$Sap Restar", "Resta be",    Integer.class);
         ids[i++]=sc.addItem("Actitud", "Actitud adequada", Character.class);
         ids[i++]=sc.addItem("Coneixements",  "Quan en sap", Character.class);
         ids[i++]=sc.addItem("Coneixements$$Sap Multiplicar", "Multiplica be", Integer.class);
         ids[i++]=sc.addItem("Faltes$$Injustificades",  "Faltes no justificades", Integer.class);
         ids[i++]=sc.addItem("Puntualitat", "Dies que ha estat puntual",    Integer.class );
         ids[i++]=sc.addItem("Coordinacio", "Coordina bé",  String.class );
         ids[i++]=sc.addItem("Faltes", "Nombre faltes",  Integer.class );
         ids[i++]=sc.addItem("Faltes$$Justificades",   "Faltes justificades", Integer.class);
                 
         
         for(i=0;i<ids.length;i++){
             SchemeItem scheme;
             scheme=sc.get(ids[i]);
             if(scheme.getNumber()!=(i+1)){
                 fail();
             }
             
             if(sc.get(scheme.getPathName())!=scheme){
                 fail();
             }
         }
         
     }
    @Test
    // Test add, gets and remove methods
    public void secondTest() {
         StatusScheme sc= new StatusScheme();
         int [] ids= new int[10];
         int deleted1, deleted2;
         
         int i=0;

         ids[i++]=sc.addItem("Coneixements$$Sap Sumar", "Suma be",     Integer.class);
         ids[i++]=sc.addItem("Coneixements$$Sap Restar", "Resta be",    Integer.class);
         ids[i++]=sc.addItem("Actitud", "Actitud adequada", Character.class);
         deleted1=i-1;
         ids[i++]=sc.addItem("Coneixements",  "Quan en sap", Character.class);
         sc.remove(i);
         deleted2=i-1;
         sc.remove("Actitud");
         ids[i++]=sc.addItem("Coneixements$$Sap Multiplicar", "Multiplica be", Integer.class);
         ids[i++]=sc.addItem("Faltes$$Injustificades",  "Faltes no justificades", Integer.class);
         ids[i++]=sc.addItem("Puntualitat", "Dies que ha estat puntual",    Integer.class );
         ids[i++]=sc.addItem("Coordinacio", "Coordina bé",  String.class );
         ids[i++]=sc.addItem("Faltes", "Nombre faltes",  Integer.class );
         ids[i++]=sc.addItem("Faltes$$Justificades",   "Faltes justificades", Integer.class);
                 
         
         for(i=0;i<ids.length;i++){
             SchemeItem scheme;
             scheme=sc.get(ids[i]);
             if((deleted1==i || deleted2==i)&& scheme!=null){
                 fail();
             }
             if(scheme==null) continue;
             if(scheme.getNumber()!=(i+1)){
                 fail();
             }
             
             if(sc.get(scheme.getPathName())!=scheme){
                 fail();
             }
         }
         
         if(sc.get("Actitud")!=null) fail();
         
     }    
    @Test
    // Test add, gets and remove methods with errors
    public void thirdTest() {
         StatusScheme sc= new StatusScheme();
         int [] ids= new int[10];

         
         int i=0;

         ids[i++]=sc.addItem("Coneixements$$Sap Sumar", "Suma be",     Integer.class);
         ids[i++]=sc.addItem("Coneixements$$Sap Restar", "Resta be",    Integer.class);
         ids[i++]=sc.addItem("Actitud", "Actitud adequada", Character.class);
         ids[i++]=sc.addItem("Coneixements",  "Quan en sap", Character.class);
         ids[i++]=sc.addItem("Coneixements$$Sap Multiplicar", "Multiplica be", Integer.class);
         ids[i++]=sc.addItem("Faltes$$Injustificades",  "Faltes no justificades", Integer.class);
         ids[i++]=sc.addItem("Puntualitat", "Dies que ha estat puntual",    Integer.class );
         ids[i++]=sc.addItem("Coordinacio", "Coordina bé",  String.class );
         ids[i++]=sc.addItem("Faltes", "Nombre faltes",  Integer.class );
         ids[i++]=sc.addItem("Faltes$$Justificades",   "Faltes justificades", Integer.class);
                 
         assertEquals(sc.get(i*2),null);
         assertEquals(sc.get("hola"),null);
         assertEquals(sc.addItem("Faltes", "Tan es", Object.class),0);
         
     }
    
    
    @Test
    // Test changePath
    public void fourthTest() {
         StatusScheme sc= new StatusScheme();
         int [] ids= new int[10];
         SchemeItem itemToCheck;
         int toCheck;
         
         int i=0;

         ids[i++]=sc.addItem("Coneixements$$Sap Sumar", "Suma be",     Integer.class);
         ids[i++]=sc.addItem("Coneixements$$Sap Restar", "Resta be",    Integer.class);
         ids[i++]=sc.addItem("Actitud", "Actitud adequada", Character.class);
         toCheck=ids[i-1]; itemToCheck=sc.get(toCheck);
         ids[i++]=sc.addItem("Coneixements",  "Quan en sap", Character.class);
         ids[i++]=sc.addItem("Coneixements$$Sap Multiplicar", "Multiplica be", Integer.class);
         ids[i++]=sc.addItem("Faltes$$Injustificades",  "Faltes no justificades", Integer.class);
         ids[i++]=sc.addItem("Puntualitat", "Dies que ha estat puntual",    Integer.class );
         ids[i++]=sc.addItem("Coordinacio", "Coordina bé",  String.class );
         ids[i++]=sc.addItem("Faltes", "Nombre faltes",  Integer.class );
         ids[i++]=sc.addItem("Faltes$$Justificades",   "Faltes justificades", Integer.class);

         assertFalse(sc.changePath("Actitud", "Coneixements"));
         assertTrue(sc.changePath("Actitud", "Conducta"));
         assertEquals(sc.get("Conducta"),itemToCheck);
         assertEquals(sc.get(toCheck),itemToCheck);
     }             
    
    @Test
    //Test queryRange
    public void fifthTest() {
     StatusScheme sc= new StatusScheme();
     int [] ids= new int[10];
     String [] results= new String[]{   "Actitud",
                                        "Coneixements",
                                        "Coneixements$$Sap Multiplicar",
                                        "Coneixements$$Sap Restar",
                                        "Coneixements$$Sap Sumar",
                                        "Coordinacio",
                                        "Faltes",
                                        "Faltes$$Injustificades",
                                        "Faltes$$Justificades",
                                        "Puntualitat"
     };




     int i=0;

     ids[i++]=sc.addItem("Coneixements$$Sap Sumar", "Suma be",     Integer.class);
     ids[i++]=sc.addItem("Coneixements$$Sap Restar", "Resta be",    Integer.class);
     ids[i++]=sc.addItem("Actitud", "Actitud adequada", Character.class);
     ids[i++]=sc.addItem("Coneixements",  "Quan en sap", Character.class);
     ids[i++]=sc.addItem("Coneixements$$Sap Multiplicar", "Multiplica be", Integer.class);
     ids[i++]=sc.addItem("Faltes$$Injustificades",  "Faltes no justificades", Integer.class);
     ids[i++]=sc.addItem("Puntualitat", "Dies que ha estat puntual",    Integer.class );
     ids[i++]=sc.addItem("Coordinacio", "Coordina bé",  String.class );
     ids[i++]=sc.addItem("Faltes", "Nombre faltes",  Integer.class );
     ids[i++]=sc.addItem("Faltes$$Justificades",   "Faltes justificades", Integer.class);

     List<SchemeItem> result=sc.queryRange("Coneixements");
     testRange(result,results,1,4);
     result=sc.queryRange("Coneixements$$");
     testRange(result,results,2,4);
     result=sc.queryRange("");
     testRange(result,results,0,9);



 } 
    
    @Test
    //Test changePathname with procedure
    public void sixthTest() {
         StatusScheme sc= new StatusScheme();
         int [] ids= new int[10];

         
         int i=0;

         ids[i++]=sc.addItem("Coneixements$$Sap Sumar", "Suma be",     Integer.class);
         ids[i++]=sc.addItem("Coneixements$$Sap Restar", "Resta be",    Integer.class);
         ids[i++]=sc.addItem("Actitud", "Actitud adequada", Character.class);

         ids[i++]=sc.addItem("Coneixements",  "Quan en sap", Character.class);
         ids[i++]=sc.addItem("Coneixements$$Sap Multiplicar", "Multiplica be", Integer.class);
         ids[i++]=sc.addItem("Faltes$$Injustificades",  "Faltes no justificades", Integer.class);
         ids[i++]=sc.addItem("Puntualitat", "Dies que ha estat puntual",    Integer.class );
         ids[i++]=sc.addItem("Coordinacio", "Coordina bé",  String.class );
         ids[i++]=sc.addItem("Faltes", "Nombre faltes",  Integer.class );
         ids[i++]=sc.addItem("Faltes$$Justificades",   "Faltes justificades", Integer.class);
         
         sc.setProcedure("$context[\"Actitud\"]=\"Molt bona\";\n$context[\"Actitud\"]=\"Pot millorar\"");

         sc.changePath("Actitud", "Conducta");
         System.out.println(sc.getProcedure());
         assertTrue(sc.getProcedure().equals("$context[\"Conducta\"]=\"Molt bona\";\n$context[\"Conducta\"]=\"Pot millorar\""));



 }     
    

    private void testRange(List<SchemeItem> result, String[] results, int first, int last) {
        int i,j;
        
        for(i=0,j=first;j<=last;i++,j++) 
            assertTrue(result.get(i).getPathName().equals(results[j]));
    }
        
     

}
