/*
*This file forms part of the CargoSystem Project
* Assignment Two–CAB302 2015
*
*/
package asgn2GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import asgn2Codes.ContainerCode;
import asgn2Containers.FreightContainer;
import asgn2Exceptions.ManifestException;
import asgn2Manifests.CargoManifest;

/**
 * Creates a JPanel in which graphical components are laid out to represent the cargo manifest.
 *
 * @author CAB302.
 */
public class CargoCanvas extends JPanel {

    private static final int WIDTH = 120;
    private static final int HEIGHT = 50;
    private static final int HSPACE = 10;
    private static final int VSPACE = 20;

    private final CargoManifest cargo;

    private ContainerCode toFind;

    /**
     * Constructor
     *
     * @param cargo The <code>CargoManifest</code> on which the graphics is based so that the
     * number of stacks and height can be adhered to.
     */
    public CargoCanvas(CargoManifest cargo) 
    {
        this.cargo = cargo;
        setName("Canvas");
    }

    /**
     * Highlights a container.
     *
     * @param code ContainerCode to highlight.
     */
    public void setToFind(ContainerCode code) {
        //implementation here - don't forget to repaint
    	toFind = code;
    }

    /**
     * Draws the containers in the cargo manifest on the Graphics context of the Canvas.
     *
     * @param g The Graphics context to draw on.
     */
    @Override
    public void paint(Graphics g) {
    	//Implementation here 
    	super.paint(g);
		int i = 0;
	
		int x = 30;
		int y = 40;
		
		Color generalCtnrColor = Color.DARK_GRAY;
		Color generalHighlightCtnrColor = Color.LIGHT_GRAY;
		Color refigeratedCtnrColor = Color.BLUE;
		Color refigeratedHighlightCtnrColor = Color.CYAN;
		Color dangerousCtnrColor = Color.RED;
		Color dangerousHighlightCtnrColor = Color.MAGENTA;
		
		String containerType;
		
		while(true){
			try{

				FreightContainer[] currentStack = cargo.toArray(i);

				g.setColor(Color.LIGHT_GRAY);
				g.drawRect(15, y, 5, 50);
				g.fillRect(15, y, 5, 50);


				for (FreightContainer currentContainer : currentStack) {
					if(currentContainer!=null){
						containerType = currentContainer.getClass().getSimpleName();

						if (toFind != null && currentContainer.getCode().equals(toFind)){

							switch (containerType) {
							case "GeneralGoodsContainer":
								g.setColor(generalHighlightCtnrColor);
								break;

							case "DangerousGoodsContainer":
								g.setColor(dangerousHighlightCtnrColor);
								break;

							case "RefrigeratedContainer":
								g.setColor(refigeratedHighlightCtnrColor);
								break;

							default:
								break;
							}

						} else {

							switch (containerType) {
							case "GeneralGoodsContainer":
								g.setColor(generalCtnrColor);
			    				break;

			    			case "DangerousGoodsContainer":
			    				g.setColor(dangerousCtnrColor);
								break;
							
							case "RefrigeratedContainer":
								g.setColor(refigeratedCtnrColor);
								break;
							
							default:
								break;
							}
			    		}
						
			    		drawContainer(g, currentContainer, x, y);
			    		x += (WIDTH + HSPACE);
					}
				}	
		    	
		    	i++;
		    	y += (HEIGHT + VSPACE);
		    	x = 30;
		    	
			} catch(ManifestException e){
				break;
			}
		}
    }

    /**
     * Draws a container at the given location.
     *
     * @param g The Graphics context to draw on.
     * @param container The container to draw - the type determines the colour and ContainerCode is
     *            used to identify the drawn Rectangle.
     * @param x The x location for the Rectangle.
     * @param y The y location for the Rectangle.
     */
    private void drawContainer(Graphics g, FreightContainer container, int x, int y) {
    	//Implementation here 
    	//Feel free to use some other method structure here, but this is the basis for the demo. 
    	//Obviously you need the graphics context and container as parameters. 
    	//But you can also use images if you wish. 
    	g.drawRect(x, y, WIDTH, HEIGHT);
    	g.fillRect(x, y, WIDTH, HEIGHT);
    	g.setColor(Color.WHITE);
    	g.drawString(container.getCode().toString(), x+5,y+30);
    	
    }
}
