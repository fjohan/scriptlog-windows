package se.lu.scriptlogwindows;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import se.lu.scriptlogwindows.sj.gui.ScriptJFrame;

/**
 *
 * @author johanf
 */
class ScriptLogStarter implements PropertyChangeListener {

    JDesktopPane fJdp;
    ScriptJFrame sjf;

    ScriptLogStarter(JDesktopPane jDesktopPane1) {
        fJdp = jDesktopPane1;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ScriptLogInfo sli = (ScriptLogInfo) evt.getNewValue();
        Maf.println("Starting..." + sli.getfText()
                + ", " + sli.getfText0()
                + ", " + sli.getfText1()
                + ", " + sli.isfSelected()
                + ", " + sli.isfSelected0()
                + ", " + sli.getfText2()
        );
        newLog();
    }

    private void newLog() {
        sjf = new ScriptJFrame();
        //((javax.swing.plaf.basic.BasicInternalFrameUI) sjf.getUI()).setNorthPane(null);

        //alSjf.add(sjf);
        //sjf.setTitle("ScriptJ - Frame " + alSjf.size());
        fJdp.add(sjf, JLayeredPane.DEFAULT_LAYER);
        FrameCenterer.center(sjf);
        sjf.setVisible(true);

        //sjf.addPropertyChangeListener("PROP_SJLOGGING", new JLabel1Changer(jLabel1));
    }

    void start() {
        Maf.println("Starting for real!");
        sjf.record();
    }

    void stop() {
        Maf.println("Stopping!");
        sjf.stop();
    }

}
