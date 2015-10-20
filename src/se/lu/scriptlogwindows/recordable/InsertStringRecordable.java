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
public class InsertStringRecordable extends Recordable {

    private static final long serialVersionUID = 20120608L;

    private int offset;
    private String str;

    public InsertStringRecordable(int eventID, long when, int offset, String str) {
        super(eventID, when);
        setOffset(offset);
        setStr(str);
    }

//    @Override
//    public String dumpMe() {
//        StringBuilder sb = new StringBuilder("");
//        sb.append("<insertString>").append(KeyLogConstants.DUMP_SEPARATOR);
//        sb.append(getWhen()).append(KeyLogConstants.DUMP_SEPARATOR);
//        sb.append(getOffset()).append(KeyLogConstants.DUMP_SEPARATOR).append(getStr()).append(KeyLogConstants.NEW_LINE);
//        return sb.toString();
//    }

    @Override
    public String linMe() {
        return getStr();
    }

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
     * @return the str
     */
    public String getStr() {
        return str;
    }

    /**
     * @param str the str to set
     */
    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public void xmlMe(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeAttribute("type", "insertString");
        writer.writeAttribute("time", getWhen() + "");
        writer.writeAttribute("offset", getOffset() + "");
        writer.writeAttribute("str", getStr());
    }

}
