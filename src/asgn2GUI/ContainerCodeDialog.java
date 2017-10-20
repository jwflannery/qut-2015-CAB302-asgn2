/*
*This file forms part of the CargoSystem Project
* Assignment Two–CAB302 2015
*
*/
package asgn2GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import asgn2Codes.ContainerCode;
import asgn2Exceptions.CargoException;
import asgn2Exceptions.InvalidCodeException;

/**
 * Creates a dialog box allowing the user to enter a ContainerCode.
 *
 * @author CAB302
 */
public class ContainerCodeDialog extends AbstractDialog {

    private final static int WIDTH = 250;
    private final static int HEIGHT = 120;

    private JTextField txtCode;
    private JLabel lblErrorInfo;

    private ContainerCode code;

    /**
     * Constructs a modal dialog box that requests a container code.
     *
     * @param parent the frame which created this dialog box.
     */
    private ContainerCodeDialog(JFrame parent) {
        super(parent, "Container Code", WIDTH, HEIGHT);
        setName("Container Dialog");
        setResizable(true);
    }

    /**
     * @see AbstractDialog.createContentPanel()
     */
    @Override
    protected JPanel createContentPanel() {
        JPanel toReturn = new JPanel();
        toReturn.setLayout(new GridBagLayout());

        // add components to grid
        GridBagConstraints constraints = new GridBagConstraints();

        // Defaults
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.weightx = 100;
        constraints.weighty = 100;

        txtCode = new JTextField();
        txtCode.setColumns(11);
        txtCode.setName("Container Code");
        txtCode.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validate();
            }

            /*
             * Attempts to validate the ContainerCode entered in the Container Code text field.
             */
            private void validate() {
            	//implementation here
            	lblErrorInfo.setVisible(false);
            	String code = txtCode.getText();
            	try {
					ContainerCode ctnrCode = new ContainerCode(code);
				} catch (InvalidCodeException e) {
					//lblErrorInfo.setText(e.getMessage());
					//lblErrorInfo.setVisible(true);
				}
            }
        });

        //implementation here 
        addToPanel(toReturn, new JLabel(txtCode.getName()), constraints, 0, 0, 2, 1);
        addToPanel(toReturn, txtCode, constraints,3, 0, 2, 1);
        
        constraints.anchor = GridBagConstraints.SOUTH;
        lblErrorInfo = new JLabel();
        lblErrorInfo.setName("Error Label");
        lblErrorInfo.setVisible(false);
        addToPanel(toReturn, lblErrorInfo, constraints, 0, 2, 2, 1);

        return toReturn;
    }

    @Override
    protected boolean dialogDone() {
    	try {
			code = new ContainerCode(txtCode.getText());
			return true;
		} catch (InvalidCodeException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
    	return false;
    }

    /**
     * Shows the <code>ManifestDialog</code> for user interaction.
     *
     * @param parent - The parent <code>JFrame</code> which created this dialog box.
     * @return a <code>ContainerCode</code> instance with valid values.
     */
    public static ContainerCode showDialog(JFrame parent) {
    	//implementation here
    	ContainerCodeDialog containerCodeDialog = new ContainerCodeDialog(parent);
    	containerCodeDialog.setVisible(true);
    	    	
    	return containerCodeDialog.code;
    }
}
