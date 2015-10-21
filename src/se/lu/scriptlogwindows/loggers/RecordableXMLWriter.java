package se.lu.scriptlogwindows.loggers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import se.lu.scriptlogwindows.Maf;
import se.lu.scriptlogwindows.directorykeeper.DirectoryKeeper;
import se.lu.scriptlogwindows.recordable.Recordable;

/**
 *
 * @author johanf
 */
public class RecordableXMLWriter implements Runnable {

    File tmpFile;
    XMLStreamWriter writer;
    Recordable currentR;

    protected BlockingQueue<Recordable> fBQueue;

    public RecordableXMLWriter(BlockingQueue aBQueue) {
        fBQueue = aBQueue;
    }

    @Override
    public void run() {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try {
            try {
                
                tmpFile = File.createTempFile("ScriptJ", ".tmp",
                        new File(DirectoryKeeper.INSTANCE.getPrefs().get("workingDir", null)));
                
//                tmpFile = File.createTempFile("ScriptJ", ".tmp");
                tmpFile.deleteOnExit();
                
                
                
                //Lazy
                                
                Maf.println("Temp file : " + tmpFile.getAbsolutePath());

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
}
