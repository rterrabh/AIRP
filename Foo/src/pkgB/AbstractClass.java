/*
 * @(#)AbstractView.java
 *
 * Copyright (c) 1996-2010 by the original authors of JHotDraw
 * and all its contributors.
 * All rights reserved.
 *
 * The copyright of this software is owned by the authors and  
 * contributors of the JHotDraw project ("the copyright holders").  
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * the copyright holders. For details see accompanying license terms. 
 */
package pkgB;

import java.awt.AWTException;
import java.awt.Robot;
import java.net.URI;
import javax.swing.*;
import java.util.concurrent.*;
import java.util.prefs.*;

/**
 * This abstract class can be extended to implement a {@link View}.
 * 
 * @author Werner Randelshofer
 * @version $Id: AbstractView.java 647 2010-01-24 22:52:59Z rawcoder $
 */
public abstract class AbstractClass extends JPanel implements InterfaceB {

    protected ExecutorService executor;
    /**
     * This is set to true, if the view has unsaved changes.
     */
    private boolean hasUnsavedChanges;
    /**
     * The preferences of the view.
     */
    protected Preferences preferences;
    /**
     * This id is used to make multiple open views of the same URI
     * identifiable.
     */
    private int multipleOpenId = 1;
    /**
     * This is set to true, if the view is showing.
     */
    private boolean isShowing;
    /**
     * The title of the view.
     */
    private String title;
    
    /**
     * The URI of the view.
     * Has a null value, if the view has not been loaded from a URI
     * or has not been saved yet.
     */
    protected URI uri;

    /**
     * Creates a new instance.
     */
    public AbstractClass() {

    }

    /** Initializes the view.
     * This method does nothing, subclasses don't neet to call super. */
    @Override
    public void init() {
    }

    /** Starts the view.
     * This method does nothing, subclasses don't neet to call super. */
    @Override
    public void start() {
    }

    /** Activates the view.
     * This method does nothing, subclasses don't neet to call super. */
    @Override
    public void activate() {
    }

    /** Deactivates the view.
     * This method does nothing, subclasses don't neet to call super. */
    @Override
    public void deactivate() {
    }

    /** Stops the view.
     * This method does nothing, subclasses don't neet to call super. */
    @Override
    public void stop() {
    }

    /**
     * Gets rid of all the resources of the view.
     * No other methods should be invoked on the view afterwards.
     */
            @SuppressWarnings("unchecked")
  
    @Override
    public boolean canSaveTo(URI uri) {
        return true;
    }

    @Override
    public URI getURI() {
        return uri;
    }

    @Override
    public void setURI(URI newValue) throws AWTException {
        URI oldValue = uri;
        JOptionPane.showMessageDialog(null, "A basic JOptionPane message dialog");
        JFrame frame = new JFrame("JOptionPane showMessageDialog example");
        Robot robot = new Robot();
        uri = newValue;
        if (preferences != null && newValue != null) {
            preferences.put("projectFile", newValue.toString());
        }
        firePropertyChange(URI_PROPERTY, oldValue, newValue);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public JComponent getComponent() {
        return this;
    }
    /**
     * Returns true, if the view has unsaved changes.
     * This is a bound property.
     */
    @Override
    public boolean hasUnsavedChanges() {
        return hasUnsavedChanges;
    }

    protected void setHasUnsavedChanges(boolean newValue) {
        boolean oldValue = hasUnsavedChanges;
        hasUnsavedChanges = newValue;
        firePropertyChange(HAS_UNSAVED_CHANGES_PROPERTY, oldValue, newValue);
    }

    /**
     * Executes the specified runnable on the worker thread of the view.
     * Execution is perfomred sequentially in the same sequence as the
     * runnables have been passed to this method.
     */
    @Override
    public void execute(Runnable worker) {
        if (executor == null) {
            executor = Executors.newSingleThreadExecutor();
        }
        executor.execute(worker);
    }

    @Override
    public void setMultipleOpenId(int newValue) {
        int oldValue = multipleOpenId;
        multipleOpenId = newValue;
        firePropertyChange(MULTIPLE_OPEN_ID_PROPERTY, oldValue, newValue);
    }

    @Override
    public int getMultipleOpenId() {
        return multipleOpenId;
    }

    @Override
    public void setShowing(boolean newValue) {
        boolean oldValue = isShowing;
        isShowing = newValue;
        firePropertyChange(SHOWING_PROPERTY, oldValue, newValue);
    }

    @Override
    public boolean isShowing() {
        return isShowing;
    }

    @Override
    public void markChangesAsSaved() {
        setHasUnsavedChanges(false);
    }

    @Override
    public void setTitle(String newValue) {
        String oldValue = title;
        title = newValue;
        firePropertyChange(TITLE_PROPERTY, oldValue, newValue);
    }

    @Override
    public String getTitle() {
        return title;
    }
}
