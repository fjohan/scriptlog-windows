package se.lu.scriptlogwindows;

/**
 *
 * @author ling-jfr
 */
class TestType {

    static TestType parseTestType(String testTypeName) {
        return new TestType(testTypeName);
    }
    private String fTestTypeName;

    private TestType(String aTestTypeName) {
        fTestTypeName=aTestTypeName;
    }

    /**
     * @return the fTestTypeName
     */
    public String getfTestTypeName() {
        return fTestTypeName;
    }

    /**
     * @param fTestTypeName the fTestTypeName to set
     */
    public void setfTestTypeName(String fTestTypeName) {
        this.fTestTypeName = fTestTypeName;
    }
    
}
