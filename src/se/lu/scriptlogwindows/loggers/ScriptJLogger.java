/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.lu.scriptlogwindows.loggers;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.AbstractAction;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import se.lu.scriptlogwindows.ScriptLogModel;
import se.lu.scriptlogwindows.recordable.CaretUpdateRecordable;
import se.lu.scriptlogwindows.recordable.InsertStringRecordable;
import se.lu.scriptlogwindows.recordable.KeyPressedRecordable;
import se.lu.scriptlogwindows.recordable.KeyReleasedRecordable;
import se.lu.scriptlogwindows.recordable.MouseClickedRecordable;
import se.lu.scriptlogwindows.recordable.MouseSampleRecordable;
import se.lu.scriptlogwindows.recordable.Recordable;
import se.lu.scriptlogwindows.recordable.RedoRecordable;
import se.lu.scriptlogwindows.recordable.RemoveRecordable;
import se.lu.scriptlogwindows.recordable.ReplaceRecordable;
import se.lu.scriptlogwindows.recordable.StateChangedRecordable;
import se.lu.scriptlogwindows.recordable.UndoRecordable;

/**
 *
 * @author johanf
 */
public class ScriptJLogger {

    //protected BlockingQueue<Recordable> fBQueue = null;
    BlockingQueue<Recordable> bQueue;
    ScheduledExecutorService producerExec;
    ExecutorService consumerExec;

    RecordableXMLWriter recordableConsumer;

    final TreeSet keyCodeSet = new TreeSet();

    private KeyListener keyL;
    private MouseListener mouseL;
    private CaretListener caretL;
    private UndoableEditListener unedL;
    private DocumentFilter docF;
    private ChangeListener changeL;
    protected UndoManager undo = new UndoManager();
    private AbstractAction undoAction;
    private AbstractAction redoAction;
    private boolean LOGGING_ON = false;

    private Point p;
    private final JScrollPane fJsp;
    private final JTextPane fJtp;
    private Runnable mouseSampler;

    public ScriptJLogger(JScrollPane aJsp, JTextPane aJtp) {
        fJsp = aJsp;
        fJtp = aJtp;

        initListeners();
        initMaps();
    }

    private long getCurrentTime() {
        return System.nanoTime();
    }

    private void recordRecordable(Recordable rec) {
        bQueue.add(rec);
    }

    private void initListeners() {
        keyL = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                long now = getCurrentTime();
                if (keyCodeSet.add(evt.getKeyCode())) {
                    int keyCode = evt.getKeyCode();
                    recordRecordable(new KeyPressedRecordable(207, now, keyCode));
                }
            }

            @Override
            public void keyReleased(KeyEvent evt) {
                long now = getCurrentTime();
                if (keyCodeSet.remove(evt.getKeyCode())) {
                    int keyCode = evt.getKeyCode();
                    recordRecordable(new KeyReleasedRecordable(208, now, keyCode));
                }
            }
        };
        mouseL = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                long now = getCurrentTime();
                int mouseX = evt.getX();
                int mouseY = evt.getY();
                recordRecordable(new MouseClickedRecordable(309, now, mouseX, mouseY));
            }
        };

        caretL = new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent evt) {
                long now = getCurrentTime();
                int dot = evt.getDot();
                int mark = evt.getMark();
                recordRecordable(new CaretUpdateRecordable(104, now, dot, mark));
            }
        };
        unedL = new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent uee) {
                undo.addEdit(uee.getEdit());
            }
        };
//        caretL = (CaretEvent evt) -> {
//            long now = getCurrentTime();
//            int dot = evt.getDot();
//            int mark = evt.getMark();
//            recordRecordable(new CaretUpdateRecordable(104, now, dot, mark));
//        };
//        unedL = (UndoableEditEvent uee) -> {
//            undo.addEdit(uee.getEdit());
//        };
        docF = new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String str, AttributeSet attr) throws BadLocationException {
                long now = getCurrentTime();
                recordRecordable(new InsertStringRecordable(101, now, offset, str));
                fb.insertString(offset, str, attr);
            }

            @Override
            public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
                long now = getCurrentTime();
                recordRecordable(new RemoveRecordable(102, now, offset, length));
                fb.remove(offset, length);
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String str, AttributeSet attr) throws BadLocationException {
                long now = getCurrentTime();
                recordRecordable(new ReplaceRecordable(103, now, offset, length, str));
                fb.replace(offset, length, str, attr);
            }
        };

        undoAction = new AbstractAction("Undo") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                long now = System.nanoTime();
                try {
                    if (undo.canUndo()) {
                        recordRecordable(new UndoRecordable(105, now));
                        undo.undo();
                    }
                } catch (CannotUndoException e) {
                }
            }
        };
        redoAction = new AbstractAction("Redo") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                long now = System.nanoTime();
                try {
                    if (undo.canRedo()) {
                        recordRecordable(new RedoRecordable(106, now));
                        undo.redo();
                    }
                } catch (CannotRedoException e) {
                }
            }
        };
        changeL = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                long now = getCurrentTime();
                p = ((JViewport) e.getSource()).getViewPosition();
                recordRecordable(new StateChangedRecordable(410, now, p.x, p.y));
            }

        };
//        changeL = (ChangeEvent e) -> {
//            long now = getCurrentTime();
//            p = ((JViewport) e.getSource()).getViewPosition();
//            recordRecordable(new StateChangedRecordable(410, now, p.x, p.y));
//        };

        mouseSampler = new Runnable() {

            @Override
            public void run() {
                long now = getCurrentTime();
                p = MouseInfo.getPointerInfo().getLocation();
                recordRecordable(new MouseSampleRecordable(311, now, p.x, p.y));
            }
        };

    }

    private void initMaps() {
        fJtp.getActionMap().put("Undo", undoAction);
        fJtp.getActionMap().put("Redo", redoAction);

        fJtp.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Undo");
        fJtp.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Redo");

    }

    public void record(ScriptLogModel scriptLogModel) {
        if (isLOGGING_ON()) return;
        setLOGGING_ON(true);
        fJtp.setEditable(true);
        ((JTextComponent) fJtp).enableInputMethods(false);

        ((AbstractDocument) fJtp.getDocument()).setDocumentFilter(docF);
        fJtp.addKeyListener(keyL);
        fJtp.addCaretListener(caretL);
        fJtp.addMouseListener(mouseL);
        fJtp.getDocument().addUndoableEditListener(unedL);
        undo.discardAllEdits();
        fJsp.getViewport().addChangeListener(changeL);

        bQueue = new LinkedBlockingQueue<Recordable>();
        recordableConsumer = new RecordableXMLWriter(bQueue, scriptLogModel);
        producerExec = Executors.newScheduledThreadPool(1);
        consumerExec = Executors.newCachedThreadPool();

        consumerExec.execute(recordableConsumer);
        if (scriptLogModel.isEmulateEyesUsingMouse()) {
            producerExec.scheduleAtFixedRate(mouseSampler, 0, 4, TimeUnit.MILLISECONDS);
        }
    }

    public void stop() {
        if (!isLOGGING_ON()) return;
        
        producerExec.shutdownNow();
        consumerExec.shutdownNow();

        fJtp.setEditable(false);
        fJtp.removeKeyListener(keyL);
        fJtp.removeCaretListener(caretL);
        fJtp.removeMouseListener(mouseL);
        ((AbstractDocument) fJtp.getDocument()).setDocumentFilter(null);
        fJtp.getDocument().removeUndoableEditListener(unedL);
        undo.discardAllEdits();
        fJsp.getViewport().removeChangeListener(changeL);
        fJtp.setEditable(false);
        ((JTextComponent) fJtp).enableInputMethods(false);
        setLOGGING_ON(false);
    }

    /**
     * @return the LOGGING_ON
     */
    public boolean isLOGGING_ON() {
        return LOGGING_ON;
    }

    /**
     * @param LOGGING_ON the LOGGING_ON to set
     */
    public void setLOGGING_ON(boolean LOGGING_ON) {
        this.LOGGING_ON = LOGGING_ON;
    }

}
