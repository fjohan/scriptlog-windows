package se.lu.scriptlogwindows.util;

/**
 * Collected constants of very general utility.
 *
 * <P> All constants are immutable.
 */
public final class ScriptLogConstants {

    //Common Strings
    public static final String NEW_LINE = "\n";
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    
    //Settings Strings
    public static final String SETTINGS_WORKING_DIR = "workingDir";
    public static final String SETTINGS_DEFAULT_LANGUAGE = "defaultLanguage";
    public static final String SETTINGS_SHOW_TIME_IN = "showTimeIn";
    public static final String SETTINGS_TEST_TYPE = "testType";
    public static final String SETTINGS_MOUSE_EMUL_EYES = "mouseEmulEyes";
//    //Log header Strings
//    public static final String VERSION = "Version";
//    public static final String INITIAL_TEXT = "initialText";
//    public static final String INITIAL_CARET_POSITION = "initialCaretPosition";
//    public static final String START_TIME = "startTime";
//    public static final String END_TIME = "endTime";
//    public static final String TEXT_AREA_X = "TextAreaX";
//    public static final String TEXT_AREA_Y = "TextAreaY";
//    public static final String TEXT_AREA_HEIGHT = "TextAreaHeight";
//    public static final String TEXT_AREA_WIDTH = "TextAreaWidth";
    public static final String STORING_DIR = "storingDir";
//    public static final String TOKENS_IN_FINAL_TEXT = "tokensInFinalText";
//    public static final String FILE_PREFIX = "filePrefix";
//    
//    public static final String FONT_SIZE = "fontSize";
//    public static final String FONT_FAMILY = "fontFamily";
//    public static final String LINE_SPACING = "lineSpacing";
//    public static final String OS_NAME = "osName";
//    
//    public static final String BATCH_PROCESS_INPUT = "batchProcessInput";
//    public static final String BATCH_PROCESS_FT_OUTPUT = "batchProcessFtOutput";
//    public static final String BATCH_PROCESS_LIN_OUTPUT = "batchProcessLINOutput";
//    public static final String BATCH_PROCESS_STAT_OUTPUT = "batchProcessStatOutput";
//    public static final String BATCH_PROCESS_ILXML_OUTPUT = "batchProcessIlxmlOutput";
//    //public static final String BATCH_PROCESS_WG_OUTPUT = "batchProcessWgOutput";
//
//    public static final String WG_BATCH_SOURCE = "wgBatchSource";
//    public static final String WG_BATCH_DESTINATION = "wgBatchDestination";
//
//    public static final String TRIPLE_TASK_DIALOG_NAME = "TripleTaskDialog";
//    
//    public static final String TEXT_LANGUAGE = "textLanguage";

    // PRIVATE //
    /**
     * Prevent object construction.
     */
    private ScriptLogConstants() {
        throw new AssertionError();
    }
}