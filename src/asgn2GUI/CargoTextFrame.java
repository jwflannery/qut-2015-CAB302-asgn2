/*
*This file forms part of the CargoSystem Project
* Assignment Two–CAB302 2015
*
*/
package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import asgn2Codes.ContainerCode;
import asgn2Containers.DangerousGoodsContainer;
import asgn2Containers.FreightContainer;
import asgn2Containers.GeneralGoodsContainer;
import asgn2Exceptions.InvalidCodeException;
import asgn2Exceptions.InvalidContainerException;
import asgn2Exceptions.ManifestException;
import asgn2Manifests.CargoManifest;

/**
 * The main window for the Cargo Manifest Text application.
 *
 * @author CAB302
 */
public class CargoTextFrame extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private JButton btnLoad;
    private JButton btnUnload;
    private JButton btnFind;
    private JButton btnNewManifest;

    private CargoTextArea canvas;

    private JPanel pnlControls;
    private JPanel pnlDisplay;

    private CargoManifest cargo;

    /**
     * Constructs the GUI.
     *
     * @param title The frame title to use.
     * @throws HeadlessException from JFrame.
     */
    public CargoTextFrame(String frameTitle) throws HeadlessException {
        super(frameTitle);
        constructorHelper();
        disableButtons();
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
        	canvas = new CargoTextArea(cargo);
        	
        	//implementation here 
        	enableButtons();
        	pnlDisplay.add(canvas);
        }
        redraw();
    }

    /**
     * Enables buttons for user interaction.
     */
    private void enableButtons() {
    	btnLoad.setEnabled(true);
    	btnFind.setEnabled(true);
    	btnUnload.setEnabled(true);
    }

    /**
     * Disables buttons from user interaction.
     */
    private void disableButtons() {
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
                        CargoTextFrame.this.resetCanvas();
                        CargoTextFrame.this.doLoad();
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
                        CargoTextFrame.this.resetCanvas();
                        CargoTextFrame.this.doUnload();
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
                        CargoTextFrame.this.doFind();
                    }
                };
                SwingUtilities.invokeLater(doRun);
            }
        });
        btnNewManifest = createButton("New Manifest", new ActionListener() {
        	//implementation here 
        	public void actionPerformed(ActionEvent e) {
				try {
					CargoTextFrame.this.setNewManifest();
				} catch (ManifestException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
            }
        });

        //implementation here 
        
        pnlDisplay = new JPanel();
        
        pnlControls = createControlPanel();
        this.getContentPane().add(pnlControls,BorderLayout.SOUTH);
        this.getContentPane().add(pnlDisplay,BorderLayout.WEST);
        
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
    private void setNewManifest() throws ManifestException {
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
    	canvas.setToFind(null);
    	//redraw();
    }

    /**
     * Initiates the Load Container dialog.
     */
    private void doLoad() {
        //Don't forget to redraw
    	FreightContainer container;
    	container = LoadContainerDialog.showDialog(this);
    	try {
			if (container != null){
				cargo.loadContainer(container);
			}
		} catch (ManifestException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
    	redraw();
    }

    /**
     * Initiates the Unload Container dialog.
     */
    private void doUnload() {
    	try {
    		ContainerCode code = ContainerCodeDialog.showDialog(this);
			if (code != null){
				cargo.unloadContainer(code);
			}
			redraw();
		} catch (ManifestException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
    	
    }

    /**
     * Initiates the Find Container dialog.
     */
    private void doFind() {
    	canvas.setToFind(ContainerCodeDialog.showDialog(this));
    	repaint();
    	
    }

    /**
     * 
     * Updates the display area.
     *
     */
    private void redraw() {
    	canvas.updateDisplay();
    	repaint();
    }
}
