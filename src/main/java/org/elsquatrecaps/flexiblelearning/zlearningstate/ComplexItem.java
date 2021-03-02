/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.elsquatrecaps.flexiblelearning.zlearningstate;

import org.elsquatrecaps.flexiblelearning.zsyncdata.SyncList;

/**
 *
 * @author professor
 */
public class ComplexItem <Information, Data> extends MultilevelItem{
    private SyncList <MultilevelItem <Information, Data>> members; 

    public SyncList<MultilevelItem <Information, Data>> getMembers() {
        return members;
    }

    public void setMembers(SyncList<MultilevelItem<Information, Data>> members) {
        this.members = members;
    }
    
}
