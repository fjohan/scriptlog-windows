/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.lu.scriptlogwindows.recordable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author johanf
 */
public class SparseRecordable {

    private int fEventID;
    private long fWhen;
    private int fDot;
    private int fMark;
    private int fOffset;
    private int fLength;
    private String fStr;
    private int fKeyCode;
    private int fMouseX;
    private int fMouseY;

    XMLOutputFactory factory = XMLOutputFactory.newInstance();
    XMLStreamWriter writer;

    public SparseRecordable() {
        try {
            try {
                writer = factory.createXMLStreamWriter(new FileWriter("/home/johanf/tmp", false));
            } catch (IOException ex) {
                Logger.getLogger(SparseRecordable.class.getName()).log(Level.SEVERE, null, ex);
            }
            writer.writeStartDocument("1.0");
            writer.writeCharacters("\n");
            writer.flush();
        } catch (XMLStreamException ex) {
            Logger.getLogger(SparseRecordable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SparseRecordable(int aEventID, long aWhen, int aDot, int aMark, int aOffset, int aLength, String aStr, int aKeyCode, int aMouseX, int aMouseY) {

        fEventID = aEventID;
        fWhen = aWhen;
        fDot = aDot;
        fMark = aMark;
        fOffset = aOffset;
        fLength = aLength;
        fStr = aStr;
        fKeyCode = aKeyCode;
        fMouseX = aMouseX;
        fMouseY = aMouseY;

    }

    public void toXml() {
        try {
            writer.writeStartElement("event");
            switch (fEventID) {
                case 101:
                    // insertString
                    writer.writeAttribute("type", "insertString");
                    writer.writeAttribute("time", fWhen + "");
                    writer.writeAttribute("offset", fOffset + "");
                    writer.writeAttribute("str", fStr);
                    break;
                case 102:
                    // remove
                    writer.writeAttribute("type", "remove");
                    writer.writeAttribute("time", fWhen + "");
                    writer.writeAttribute("offset", fOffset + "");
                    writer.writeAttribute("length", fLength + "");
                    break;
                case 103:
                    // replace
                    writer.writeAttribute("type", "replace");
                    writer.writeAttribute("time", fWhen + "");
                    writer.writeAttribute("offset", fOffset + "");
                    writer.writeAttribute("length", fLength + "");
                    writer.writeAttribute("str", fStr);
                    break;
                case 104:
                    // caretUpdate
                    writer.writeAttribute("type", "caretUpdate");
                    writer.writeAttribute("time", fWhen + "");
                    writer.writeAttribute("dot", fDot + "");
                    writer.writeAttribute("mark", fMark + "");
                    break;
                case 105:
                    // undo
                    writer.writeAttribute("type", "undo");
                    writer.writeAttribute("time", fWhen + "");
                    break;
                case 106:
                    // redo
                    writer.writeAttribute("type", "redo");
                    writer.writeAttribute("time", fWhen + "");
                    break;
                case 207:
                    // keyPressed
                    writer.writeAttribute("type", "keyPressed");
                    writer.writeAttribute("time", fWhen + "");
                    writer.writeAttribute("key", fKeyCode + "");
                    break;
                case 208:
                    // keyReleased
                    writer.writeAttribute("type", "keyReleased");
                    writer.writeAttribute("time", fWhen + "");
                    writer.writeAttribute("key", fKeyCode + "");
                    break;
                case 309:
                    // mouseClicked
                    writer.writeAttribute("type", "mouseClicked");
                    writer.writeAttribute("time", fWhen + "");
                    writer.writeAttribute("mouseX", fMouseX + "");
                    writer.writeAttribute("mouseY", fMouseY + "");
                    break;
            }
            writer.writeEndElement();
            writer.writeCharacters("\n");
            writer.flush();
        } catch (XMLStreamException ex) {
            Logger.getLogger(SparseRecordable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
