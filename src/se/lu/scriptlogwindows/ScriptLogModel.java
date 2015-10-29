package se.lu.scriptlogwindows;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johanf
 */
public class ScriptLogModel extends SettingsModel {

    private String firstName;
    private String familyName;
    private String age;
    private boolean mSelection;
    private boolean fSelection;
    private String otherInfo;

    ScriptLogModel(String text, String text0, String text1, boolean selected, boolean selected0, String text2) {
        super();
        firstName = text;
        familyName = text0;
        age = text1;
        mSelection = selected;
        fSelection = selected0;
        otherInfo = text2;
    }

    ScriptLogModel() {
        super();
        firstName = "exp";
        familyName = "subj";
        age = "50";
        mSelection = true;
        fSelection = false;
        otherInfo = "";
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the familyName
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * @return the fLength
     */
    public String getAge() {
        return age;
    }

    /**
     * @return the mSelection
     */
    public boolean ismSelection() {
        return mSelection;
    }

    /**
     * @return the fSelection
     */
    public boolean isfSelection() {
        return fSelection;
    }

    /**
     * @return the otherInfo
     */
    public String getOtherInfo() {
        return otherInfo;
    }

    @Override
    public String toShortString() {
        StringBuilder result = new StringBuilder();

        // get superclass
        result.append(getWorkingDir()).append(",");
        result.append(getDefaultLanguage()).append(",");
        result.append(getShowTimeIn()).append(",");
        result.append(getTestType()).append(",");
        result.append(isEmulateEyesUsingMouse()).append(",");
        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for (Field field : fields) {
            try {
                //requires access to private field:
                //result.append(field.get(this)).append(" ");
                result.append(field.get(this)).append(",");
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(SettingsModel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(SettingsModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        result.append(getTestType().getfTestTypeName());

        return result.toString();
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param familyName the familyName to set
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @param mSelection the mSelection to set
     */
    public void setmSelection(boolean mSelection) {
        this.mSelection = mSelection;
    }

    /**
     * @param fSelection the fSelection to set
     */
    public void setfSelection(boolean fSelection) {
        this.fSelection = fSelection;
    }

    /**
     * @param otherInfo the otherInfo to set
     */
    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

}
