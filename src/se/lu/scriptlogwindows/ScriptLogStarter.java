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
    private ScriptLogModel fScriptLogModel;
    ScriptJFrame sjf;
    String sessionName;

//    ScriptLogStarter(JDesktopPane jDesktopPane1) {
//        fJdp = jDesktopPane1;
//    }

    ScriptLogStarter(JDesktopPane jDesktopPane1, ScriptLogModel slm) {
        fJdp = jDesktopPane1;
        fScriptLogModel = slm;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //ScriptLogModel slm = (ScriptLogModel) evt.getNewValue();
        fScriptLogModel = (ScriptLogModel) evt.getNewValue();
        sessionName = getfScriptLogModel().getFirstName() + "_" + getfScriptLogModel().getFamilyName();
        Maf.println(getfScriptLogModel().toShortString());
//        Maf.println("Starting..." + sli.getfText()
//                + ", " + sli.getfText0()
//                + ", " + sli.getfText1()
//                + ", " + sli.isfSelected()
//                + ", " + sli.isfSelected0()
//                + ", " + sli.getfText2()
//        );
        newLog();
    }

    private void newLog() {
        sjf = new ScriptJFrame();

        //alSjf.add(sjf);
        //sjf.setTitle("ScriptJ - Frame " + alSjf.size());
        fJdp.add(sjf, JLayeredPane.DEFAULT_LAYER);
        FrameCenterer.center(sjf);
        sjf.setVisible(true);

        //sjf.addPropertyChangeListener("PROP_SJLOGGING", new JLabel1Changer(jLabel1));
    }

    public void start() {
        //Maf.println("Starting for real!");
        sjf.record(fScriptLogModel);
    }

    public void stop() {
        //Maf.println("Stopping!");
        sjf.stop();
    }

    void close() {
        sjf.dispose();
    }

    /**
     * @return the fScriptLogModel
     */
    public ScriptLogModel getfScriptLogModel() {
        return fScriptLogModel;
    }

}
