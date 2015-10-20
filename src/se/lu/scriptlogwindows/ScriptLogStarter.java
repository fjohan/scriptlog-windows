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
        Maf.println("Starting..."+ sli.getfText()
                + ", " + sli.getfText0()
                + ", " + sli.getfText1()
                + ", " + sli.isfSelected()
                + ", " + sli.isfSelected0()
                + ", " + sli.getfText2()
        );
    }
    
}
