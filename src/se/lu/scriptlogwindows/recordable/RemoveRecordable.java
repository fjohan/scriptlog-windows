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
public class RemoveRecordable extends Recordable {

    private static final long serialVersionUID = 20120608L;

    private int offset;
    private int length;

    public RemoveRecordable(int eventID, long when, int offset, int length) {
        super(eventID, when);
        setOffset(offset);
        setLength(length);
    }

//    @Override
//    public String dumpMe() {
//        StringBuilder sb = new StringBuilder("");
//        sb.append("<remove>").append(KeyLogConstants.DUMP_SEPARATOR);
//        sb.append(getWhen()).append(KeyLogConstants.DUMP_SEPARATOR);
//        sb.append(getOffset()).append(KeyLogConstants.DUMP_SEPARATOR).append(getLength()).append(KeyLogConstants.NEW_LINE);
//        return sb.toString();
//    }

    /**
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void xmlMe(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeAttribute("type", "remove");
        writer.writeAttribute("time", getWhen() + "");
        writer.writeAttribute("offset", getOffset() + "");
        writer.writeAttribute("length", getLength() + "");
    }

}
