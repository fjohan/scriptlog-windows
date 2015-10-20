/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.lu.scriptlogwindows;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author johanf
 */
class ScriptLogStarter implements PropertyChangeListener {

    public ScriptLogStarter() {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ScriptLogInfo sli = (ScriptLogInfo) evt.getNewValue();
        Maf.println("Starting..." + sli.getfText() + ", " + sli.getfText0() + ", " + sli.getfText1() + ", " + sli.isfSelected() + ", " + sli.isfSelected0());
    }
    
}
