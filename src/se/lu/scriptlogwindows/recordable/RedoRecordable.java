/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.lu.scriptlogwindows.recordable;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author johanf
 */
public class RedoRecordable extends Recordable {

    public RedoRecordable(int eventID, long when) {
        super(eventID, when);
    }

//    @Override
//    public String dumpMe() {
//        StringBuilder sb = new StringBuilder("");
//        sb.append("<redo>").append(KeyLogConstants.DUMP_SEPARATOR);
//        sb.append(getWhen()).append(KeyLogConstants.DUMP_SEPARATOR);
//        sb.append(KeyLogConstants.NEW_LINE);
//        return sb.toString();
//    }

    @Override
    public void xmlMe(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeAttribute("type", "redo");
        writer.writeAttribute("time", getWhen() + "");
    }

}
