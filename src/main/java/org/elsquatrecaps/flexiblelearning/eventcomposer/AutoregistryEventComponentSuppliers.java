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
package org.elsquatrecaps.flexiblelearning.eventcomposer;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.factory.EventProcessorComponentFactory;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.EventComponentConfiguration;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.ActivityEventProcessorComposerConfigurator;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.EventProcessorComposerConfigurator;

/**
 *
 * @author josep
 */
public class AutoregistryEventComponentSuppliers {
    
    public AutoregistryEventComponentSuppliers() {
    }

    protected void registerSuppliersFromConfiguration(EventComponentConfiguration conf) {
        if(!EventProcessorComponentFactory.isCollectionRegistered(conf.getCollection())){
            ActivityEventProcessorComposerConfigurator aannotation = conf.getClass().getAnnotation(ActivityEventProcessorComposerConfigurator.class);
            if(aannotation!=null){
                if (aannotation.registrarSuppliearsCalss() != null) {
                    this.register(aannotation.registrarSuppliearsCalss());
                }
            }else{
                EventProcessorComposerConfigurator eannotation = conf.getClass().getAnnotation(EventProcessorComposerConfigurator.class);
                if (eannotation!= null && eannotation.registrarSuppliearsCalss() != null) {
                    this.register(eannotation.registrarSuppliearsCalss());
                }
            }
        }
    }    
    
    private void register(Class registrar){        
        try {
            registrar.getMethod("registerSuppliers").invoke(null);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(BaseActivityEventProcessorComposer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(BaseActivityEventProcessorComposer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BaseActivityEventProcessorComposer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(BaseActivityEventProcessorComposer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(BaseActivityEventProcessorComposer.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
