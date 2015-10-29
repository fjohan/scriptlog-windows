package se.lu.scriptlogwindows.loggers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import se.lu.scriptlogwindows.Maf;
import se.lu.scriptlogwindows.ScriptLogModel;
import se.lu.scriptlogwindows.settingskeeper.SettingsKeeper;
import se.lu.scriptlogwindows.recordable.Recordable;
import se.lu.scriptlogwindows.util.ScriptLogConstants;

/**
 *
 * @author johanf
 */
public class RecordableXMLWriter implements Runnable {

    File tmpFile;
    XMLStreamWriter writer;
    Recordable currentR;
    String fSessionName;

    protected BlockingQueue<Recordable> fBQueue;
    private final ScriptLogModel fScriptLogModel;

    public RecordableXMLWriter(BlockingQueue aBQueue, ScriptLogModel slm) {
        fBQueue = aBQueue;
        fScriptLogModel = slm;
        fSessionName = fScriptLogModel.getFirstName() + "_" + fScriptLogModel.getFamilyName();

    }

    @Override
    public void run() {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try {
            try {

                String tmpString = mkFileName();
                if (tmpString.matches("-1")) {
                    tmpFile = File.createTempFile("ScriptLog", ".tmp",
                        new File(SettingsKeeper.INSTANCE.getPrefs().get("workingDir", null)));
                } else {
                    tmpFile = new File(tmpString);
                }
                //tmpFile.deleteOnExit();

                //Lazy
                Maf.println("Writing to file: " + tmpFile.getAbsolutePath());

                writer = factory.createXMLStreamWriter(new FileWriter(tmpFile, false));
                writer.writeStartDocument("1.0");
                writer.writeCharacters("\n");
                writer.flush();

                while (!Thread.interrupted()) {
                    try {
                        currentR = fBQueue.take();

                        writer.writeStartElement("event");
                        currentR.xmlMe(writer);
                        //fBQueue.take().xmlMe(writer);
                        writer.writeEndElement();
                        writer.writeCharacters("\n");
                        writer.flush();

//                        System.out.print(fBQueue.take().dumpMe());
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(RecordableXMLWriter.class.getName()).log(Level.SEVERE, null, ex);
                        Thread.currentThread().interrupt();
                    }
                }

                writer.writeEndDocument();
                writer.flush();
                writer.close();
                Maf.println("Finished.");

            } catch (XMLStreamException ex) {
                Logger.getLogger(RecordableXMLWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(RecordableXMLWriter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String mkFileName() {

        String workingDirectory = SettingsKeeper.INSTANCE.getPrefs().get("workingDir", "unset");
        if (workingDirectory.matches("unset")) {
            Maf.println("Working directory not set!");
            return "-1";
        }

        File testFile;
        String storingDir;

        // this is a trick - exp_dir is the default but we want to increment
        // that so we start from 1 in dirs - just of convenience
        // we increment the date following to keep one top dir - easy for deleting
        if ("exp_subj".equals(fSessionName)) {
            int versionNumber = 1;
            String s = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            storingDir = workingDirectory
                    + "exp_subj"
                    + ScriptLogConstants.FILE_SEPARATOR
                    + s + "_" + versionNumber
                    + ScriptLogConstants.FILE_SEPARATOR;
            testFile = new File(storingDir);
            while (testFile.isDirectory()) {
                versionNumber = versionNumber + 1;
                storingDir = workingDirectory
                        + "exp_subj"
                        + ScriptLogConstants.FILE_SEPARATOR
                        + s + "_" + versionNumber
                        + ScriptLogConstants.FILE_SEPARATOR;
                testFile = new File(storingDir);
            }
        } else {
            String simpleDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            storingDir = workingDirectory
                    + fSessionName
                    + ScriptLogConstants.FILE_SEPARATOR
                    + simpleDate
                    + ScriptLogConstants.FILE_SEPARATOR;
            testFile = new File(storingDir);
        }
        if (!testFile.canWrite()) {
            if (!testFile.mkdirs()) {
                Maf.println("Can not write or create directory: " + storingDir + ". Please select another.");
                return "-1";
            }
        }
        SettingsKeeper.INSTANCE.getPrefs().put(ScriptLogConstants.STORING_DIR, storingDir);
        
        
                int versionNumber = 1;
        String autosaveFilename = storingDir + fSessionName + "_sl_" + versionNumber + ".sdfx";
        testFile = new File(autosaveFilename);
        while (testFile.exists()) {
            versionNumber = versionNumber + 1;
            autosaveFilename = storingDir + fSessionName + "_sl_" + versionNumber + ".sdfx";
            testFile = new File(autosaveFilename);
        }
        return autosaveFilename;


//        filePrefix = globalExperimentName
//                + globalConditionName
//                + globalSubjectName
//                + setupShortName;
//        if (aNotifyFrames) {
//            setUNIQUE_DIR_CREATED(System.nanoTime());
//        }
    }

}
