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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.ui.ModelMap;

/**
 *
 * @author JRamon
 */
public class TestAttemptStatus {
    

    @Test
    public void firstTest() {
    
        StatusScheme sc=new StatusScheme();
        
        sc.addItem("Coneixements$$Sap Sumar", "Suma be",     Integer.class);
        sc.addItem("Coneixements$$Sap Restar", "Resta be",    Integer.class);
        sc.addItem("Actitud", "Actitud adequada", Character.class);

        sc.addItem("Coneixements",  "Quan en sap", Character.class);
        sc.addItem("Coneixements$$Sap Multiplicar", "Multiplica be", Integer.class);
        sc.addItem("Faltes$$Injustificades",  "Faltes no justificades", Integer.class);
        sc.addItem("Puntualitat", "Dies que ha estat puntual",    Integer.class );
        sc.addItem("Coordinacio", "Coordina bé",  String.class );
        sc.addItem("Faltes", "Nombre faltes",  Integer.class );
        sc.addItem("Faltes$$Justificades",   "Faltes justificades", Integer.class);
        
        ModelMap mp=new ModelMap();
        
        mp.put("Justificants", 8);
        mp.put("Absencies",  10);
        
        sc.setProcedure("$context[\"Faltes\"]=$form[\"Absencies\"];\n$context[\"Faltes$$Justificades\"]=$form[\"Justificants\"];\n$context[\"Faltes$$Injustificades\"]=$context[\"Faltes\"] - $context[\"Faltes$$Justificades\"];");
        
        AttemptStatus as=new AttemptStatus();
        
        as.updateAttemptStatus(sc, mp);
        
        int x = sc.get("Faltes").getNumber();
        int y= (Integer)as.getAttemptStatus().get(x);
        assertEquals(10,y);

        x = sc.get("Faltes$$Justificades").getNumber();
        y= (Integer)as.getAttemptStatus().get(x);
        assertEquals(8,y);

        x = sc.get("Faltes$$Injustificades").getNumber();
        y= (Integer)as.getAttemptStatus().get(x);
        assertEquals(2,y);



        
        assertTrue(as.getStudentInput("Justificants").equals(8));
        assertTrue(as.getStudentInput("Absencies").equals(10));
    
    
    }
}
