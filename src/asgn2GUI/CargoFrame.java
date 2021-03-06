/*
*This file forms part of the CargoSystem Project
* Assignment Two�CAB302 2015
*
*/
package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import asgn2Codes.ContainerCode;
import asgn2Containers.FreightContainer;
import asgn2Exceptions.ManifestException;
import asgn2Manifests.CargoManifest;

/**
 * The main window for the Cargo Manifest graphics application.
 *
 * @author CAB302
 */
public class CargoFrame extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private JButton btnLoad;
    private JButton btnUnload;
    private JButton btnFind;
    private JButton btnNewManifest;

    private CargoCanvas canvas;

    private JPanel pnlControls;
    private JPanel pnlDisplay;

    private CargoManifest cargo;

    /**
     * Constructs the GUI.
     *
     * @param title The frame title to use.
     * @throws HeadlessException from JFrame.
     */
    public CargoFrame(String title) throws HeadlessException {
        super(title);

        constructorHelper();
        disableButtons();
        redraw();
        setVisible(true);
    }

    /**
     * Initialises the container display area.
     *
     * @param cargo The <code>CargoManifest</code> instance containing necessary state for display.
     */
    private void setCanvas(CargoManifest cargo) {
        if (canvas != null) {
            pnlDisplay.remove(canvas);
        }
        if (cargo == null) {
            disableButtons();
        } else {
            canvas = new CargoCanvas(cargo);
          
            //implementation here    
            enableButtons();

            pnlDisplay.setLayout(new BorderLayout());
            pnlDisplay.add(canvas, BorderLayout.CENTER);
        }
        redraw();
    }

    /**
     * Enables buttons for user interaction.
     */
    private void enableButtons() {
    	//implementation here    
    	btnLoad.setEnabled(true);
    	btnFind.setEnabled(true);
    	btnUnload.setEnabled(true);
    }

    /**
     * Disables buttons from user interaction.
     */
    private void disableButtons() {
    	//implementation here    
    	btnLoad.setEnabled(false);
    	btnFind.setEnabled(false);
    	btnUnload.setEnabled(false);
    }

    /**
     * Initialises and lays out GUI components.
     */
    private void constructorHelper() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnLoad = createButton("Load", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runnable doRun = new Runnable() {
                    @Override
                    public void run() {
                        CargoFrame.this.resetCanvas();
                        CargoFrame.this.doLoad();
                    }
                };
                SwingUtilities.invokeLater(doRun);
            }
        });
        btnUnload = createButton("Unload", new ActionListener() {
        	//implementation here    
        	public void actionPerformed(ActionEvent e) {
        		Runnable doRun = new Runnable() {
                    @Override
                    public void run() {
                        CargoFrame.this.resetCanvas();
                        CargoFrame.this.doUnload();
                    }
                };
                SwingUtilities.invokeLater(doRun);
            }
        });
        btnFind = createButton("Find", new ActionListener() {
        	//implementation here    
        	public void actionPerformed(ActionEvent e) {
        		Runnable doRun = new Runnable() {
                    @Override
                    public void run() {
                        CargoFrame.this.doFind();
                    }
                };
                SwingUtilities.invokeLater(doRun);
            }
        });
        btnNewManifest = createButton("New Manifest", new ActionListener() {
        	//implementation here    
        	public void actionPerformed(ActionEvent e) {

        		CargoFrame.this.setNewManifest();

        		redraw();
        		CargoFrame.this.resetCanvas();
        		
        		
            }
        });

        //implementation here

        
        pnlDisplay = new JPanel();

        pnlControls = createControlPanel();
        add(pnlControls,BorderLayout.SOUTH);
        add(pnlDisplay,BorderLayout.CENTER);
                
        repaint();
    }

    /**
     * Creates a JPanel containing user controls (buttons).
     *
     * @return User control panel.
     */
    private JPanel createControlPanel() {
    	//implementation here    
    	JPanel controlPanel = new JPanel();
    	controlPanel.setBackground(Color.BLUE);
    	controlPanel.add(btnLoad);
    	controlPanel.add(btnUnload);
    	controlPanel.add(btnFind);
    	controlPanel.add(btnNewManifest);
    	return controlPanel;
    }

    /**
     * Factory method to create a JButton and add its ActionListener.
     *
     * @param name The text to display and use as the component's name.
     * @param btnListener The ActionListener to add.
     * @return A named JButton with ActionListener added.
     */
    private JButton createButton(String name, ActionListener btnListener) {
        JButton btn = new JButton(name);
        btn.setName(name);
        btn.addActionListener(btnListener);
        return btn;
    }

    /**
     * Initiate the New Manifest dialog which sets the instance of CargoManifest to work with.
     */
    private void setNewManifest() {
    	//implementation here
    	cargo = ManifestDialog.showDialog(this);
    	if (cargo != null){
    		setCanvas(cargo);
    	}
    }

    /**
     * Turns off container highlighting when an action other than Find is initiated.
     */
    private void resetCanvas() {
    	//implementation here    
    	canvas.setToFind(null);
    	//redraw();
    }

    /**
     * Initiates the Load Container dialog.
     */
    private void doLoad() {
    	//implementation here 
    	//don't forget to redraw
    	FreightContainer container;
    	container = LoadContainerDialog.showDialog(this);
    	try {
    		if (container != null)
    		{
    			cargo.loadContainer(container);
    			canvas.repaint();
    		}
		} catch (ManifestException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
    	redraw();
    }

    /**
     * Initiates the unload container dialog.
     */
    
    private void doUnload() {
    	//implementation here 
    	//don't forget to redraw
    	try {
    		ContainerCode code = ContainerCodeDialog.showDialog(this);
			if (code != null){
				cargo.unloadContainer(code);
			}
			
		} catch (ManifestException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		redraw();
    }
    
    /**
     * Initiates the find container dialog.
     */
    private void doFind() {
    	//implementation here 
    	canvas.setToFind(ContainerCodeDialog.showDialog(this));
    	repaint();
    }

    /**
     * Updates the display area.
     */
    
    private void redraw() {
        invalidate();
        validate();
        repaint();
    }
}
