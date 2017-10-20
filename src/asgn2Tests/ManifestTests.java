/*
*This file forms part of the CargoSystem Project
* Assignment Two–CAB302 2015
*
*/
package asgn2Tests;

/* Some valid container codes used in the tests below:
 * INKU2633836
 * KOCU8090115
 * MSCU6639871
 * CSQU3054389
 * QUTU7200318
 * IBMU4882351
 * FNPU6952577
 * XMCU9157391
 * IEWU6647210
 * MQGU1636370
 * ZGGU7132842
 * UZLU0698458
 * MSFU8966914
 * CQNU9467827
 * KZHU7839021
 * DMWU2802559
 * AMNU3341499
 * CMKU6939269
 * BUUU8635799
 * BIIU1188915
 * TKEU3416322
 * AXUU6366622
 * FSNU6387954
 * ASHU3506054
 * ICTU2968307
 * UWKU4557373
 * FQCU2898819
 * YGVU2940286
 * WGPU3811309
 * DHYU7230130
 * FQUU8201776
 * RGNU3198849
 * HCTU7419009
 * FZMU1643118
 * MEIU2499659
 * VBUU0333023
 */



import org.junit.Before;
import org.junit.Test;

import asgn2Codes.ContainerCode;
import asgn2Containers.DangerousGoodsContainer;
import asgn2Containers.FreightContainer;
import asgn2Containers.GeneralGoodsContainer;
import asgn2Containers.RefrigeratedContainer;
import asgn2Exceptions.InvalidCodeException;
import asgn2Exceptions.InvalidContainerException;
import asgn2Exceptions.ManifestException;
import asgn2Manifests.CargoManifest;
import static org.junit.Assert.*;

public class ManifestTests {
	private FreightContainer dangerousCtnr;
	private FreightContainer generalCtnr;
	private FreightContainer refrigeratedCtnr;
	
	private ContainerCode ctnrCodeOne;
	private ContainerCode ctnrCodeTwo;
	private ContainerCode ctnrCodeThree;
	private ContainerCode ctnrCodeFour;
	private ContainerCode ctnrCodeFive;
	private ContainerCode ctnrCodeSix;
	private ContainerCode ctnrCodeSeven;
	private ContainerCode ctnrCodeEight;
	private ContainerCode ctnrCodeNine;
	
	private Integer grossWeight;
	private Integer tooHeavyWeight;

	private CargoManifest manifest;
	private CargoManifest newManifest;
	
	private Integer threeStacks;
	private Integer threeCtnrsHigh;
	private Integer maxWeight;
	private Integer negativeParameter;
	
	private Integer categoryOne;
	private Integer temperature;
    
	/**
	 * @author James Flannery 08326631
	 * @throws InvalidCodeException 
	 * @throws InvalidContainerException 
	 * @throws ManifestException
	 */
    @Before 
    public void initialize() throws ManifestException, InvalidContainerException, InvalidCodeException{
    ctnrCodeOne = new ContainerCode("KOCU8090115");
    ctnrCodeTwo = new ContainerCode("INKU2633836");
    ctnrCodeThree = new ContainerCode("MSCU6639871");
    ctnrCodeFour = new ContainerCode("CSQU3054389");
    ctnrCodeFive = new ContainerCode("QUTU7200318");
    ctnrCodeSix = new ContainerCode("IBMU4882351");
    ctnrCodeSeven = new ContainerCode("FNPU6952577");
    ctnrCodeEight = new ContainerCode("XMCU9157391");
    ctnrCodeNine = new ContainerCode("IEWU6647210");
    
    grossWeight = 5;
    tooHeavyWeight = 30;
    
    threeStacks = 3;
    threeCtnrsHigh = 3;
    maxWeight = 100;
    negativeParameter = -5;

    categoryOne = 1;
    temperature = 1;

    manifest = new CargoManifest(threeStacks, threeCtnrsHigh, maxWeight);

    dangerousCtnr = new DangerousGoodsContainer(ctnrCodeOne, grossWeight, categoryOne);
    generalCtnr = new GeneralGoodsContainer(ctnrCodeTwo, grossWeight);
    refrigeratedCtnr = new RefrigeratedContainer(ctnrCodeThree, grossWeight, temperature);

    }

    /**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
    @Test (expected = ManifestException.class)
    public void constructorNegativeStacks() throws ManifestException{
    	newManifest = new CargoManifest(negativeParameter, threeCtnrsHigh, maxWeight);
    }
    
    /**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
    @Test (expected = ManifestException.class)
    public void constructorNegativeHeight() throws ManifestException{
    	newManifest = new CargoManifest(threeStacks, negativeParameter, maxWeight);
    }

    /**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
	@Test (expected = ManifestException.class)
	public void constructorNegativeWeight() throws ManifestException{
		newManifest = new CargoManifest(threeStacks, threeCtnrsHigh, negativeParameter);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
	@Test
	public void loadOneContainerInCorrectStack() throws ManifestException{
		manifest.loadContainer(generalCtnr);
		assertTrue(manifest.whichStack(ctnrCodeTwo)==0);
	}
	
	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
	@Test
	public void loadOneContainerAtCorrectHeight() throws ManifestException{
		manifest.loadContainer(generalCtnr);
		assertTrue(manifest.howHigh(ctnrCodeTwo)==0);
	}
	
	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void loadTwoSameTypeContainersInCorrectStacks() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeTwo, grossWeight));
		
		assertTrue(manifest.whichStack(ctnrCodeOne)==0);
		assertTrue(manifest.whichStack(ctnrCodeTwo)==0);
	}
	
	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void loadTwoSameTypeContainersAtCorrectHeights() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeTwo, grossWeight));
		
		assertTrue(manifest.howHigh(ctnrCodeOne)==0);
		assertTrue(manifest.howHigh(ctnrCodeTwo)==1);
	}
	
	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
	@Test
	public void loadTwoDifferentTypeContainersInCorrectStacks() throws ManifestException{
		manifest.loadContainer(dangerousCtnr);
		manifest.loadContainer(generalCtnr);
		
		assertTrue(manifest.whichStack(ctnrCodeOne)==0);
		assertTrue(manifest.whichStack(ctnrCodeTwo)==1);
	}
	
	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
	@Test
	public void loadTwoDifferentTypeContainersAtCorrectHeights() throws ManifestException{
		manifest.loadContainer(dangerousCtnr);
		manifest.loadContainer(generalCtnr);
		
		assertTrue(manifest.howHigh(ctnrCodeOne)==0);
		assertTrue(manifest.howHigh(ctnrCodeTwo)==0);
	}
	
	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void loadTwoSameOneDifferentInCorrectStacks() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeTwo, grossWeight,1));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeThree, grossWeight));

		assertTrue(manifest.whichStack(ctnrCodeOne)==0);
		assertTrue(manifest.whichStack(ctnrCodeTwo)==1);
		assertTrue(manifest.whichStack(ctnrCodeThree)==0);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void loadTwoSameOneDifferentAtCorrectHeights() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeTwo, grossWeight,1));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeThree, grossWeight));

		assertTrue(manifest.howHigh(ctnrCodeOne)==0);
		assertTrue(manifest.howHigh(ctnrCodeTwo)==0);
		assertTrue(manifest.howHigh(ctnrCodeThree)==1);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void loadTwoOfTwoTypesInCorrectStacks() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeTwo, grossWeight,categoryOne));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeThree, grossWeight));
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeFour, grossWeight,categoryOne));
		
		assertTrue(manifest.whichStack(ctnrCodeOne)==0);
		assertTrue(manifest.whichStack(ctnrCodeTwo)==1);
		assertTrue(manifest.whichStack(ctnrCodeThree)==0);
		assertTrue(manifest.whichStack(ctnrCodeFour)==1);
	}
	
	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void loadTwoOfTwoTypesAtCorrectHeights() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeTwo, grossWeight,categoryOne));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeThree, grossWeight));
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeFour, grossWeight,categoryOne));
		
		assertTrue(manifest.howHigh(ctnrCodeOne)==0);
		assertTrue(manifest.howHigh(ctnrCodeTwo)==0);
		assertTrue(manifest.howHigh(ctnrCodeThree)==1);
		assertTrue(manifest.howHigh(ctnrCodeFour)==1);
	}
	
	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
	@Test
	public void loadThreeTypesInCorrectStacks() throws ManifestException{
		manifest.loadContainer(dangerousCtnr);
		manifest.loadContainer(generalCtnr);
		manifest.loadContainer(refrigeratedCtnr);
		
		assertTrue(manifest.whichStack(ctnrCodeOne)==0);
		assertTrue(manifest.whichStack(ctnrCodeTwo)==1);
		assertTrue(manifest.whichStack(ctnrCodeThree)==2);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
	@Test
	public void loadThreeTypesAtCorrectHeights() throws ManifestException{
		manifest.loadContainer(dangerousCtnr);
		manifest.loadContainer(generalCtnr);
		manifest.loadContainer(refrigeratedCtnr);
		
		assertTrue(manifest.howHigh(ctnrCodeOne)==0);
		assertTrue(manifest.howHigh(ctnrCodeTwo)==0);
		assertTrue(manifest.howHigh(ctnrCodeThree)==0);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void fillStackThenLoadSameTypeInCorrectStack() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeTwo, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeThree, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeFour, grossWeight));
		
		assertTrue(manifest.whichStack(ctnrCodeOne)==0);
		assertTrue(manifest.whichStack(ctnrCodeTwo)==0);
		assertTrue(manifest.whichStack(ctnrCodeThree)==0);
		assertTrue(manifest.whichStack(ctnrCodeFour)==1);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void fillStackThenLoadSameTypeAtCorrectHeight() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeTwo, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeThree, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeFour, grossWeight));
		
		assertTrue(manifest.howHigh(ctnrCodeOne)==0);
		assertTrue(manifest.howHigh(ctnrCodeTwo)==1);
		assertTrue(manifest.howHigh(ctnrCodeThree)==2);
		assertTrue(manifest.howHigh(ctnrCodeFour)==0);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void fillManifestInCorrectStacks() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeTwo, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeThree, grossWeight));
		
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeFour, grossWeight, categoryOne));
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeFive, grossWeight, categoryOne));
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeSix, grossWeight, categoryOne));
		
		manifest.loadContainer(new RefrigeratedContainer(ctnrCodeSeven, grossWeight, temperature));
		manifest.loadContainer(new RefrigeratedContainer(ctnrCodeEight, grossWeight, temperature));
		manifest.loadContainer(new RefrigeratedContainer(ctnrCodeNine, grossWeight, temperature));
		
		assertTrue(manifest.whichStack(ctnrCodeOne)==0);
		assertTrue(manifest.whichStack(ctnrCodeTwo)==0);
		assertTrue(manifest.whichStack(ctnrCodeThree)==0);
				
		assertTrue(manifest.whichStack(ctnrCodeFour)==1);
		assertTrue(manifest.whichStack(ctnrCodeFive)==1);
		assertTrue(manifest.whichStack(ctnrCodeSix)==1);

		assertTrue(manifest.whichStack(ctnrCodeSeven)==2);
		assertTrue(manifest.whichStack(ctnrCodeEight)==2);
		assertTrue(manifest.whichStack(ctnrCodeNine)==2);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void fillManifestAtCorrectHeights() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeTwo, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeThree, grossWeight));
		
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeFour, grossWeight, categoryOne));
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeFive, grossWeight, categoryOne));
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeSix, grossWeight, categoryOne));
		
		manifest.loadContainer(new RefrigeratedContainer(ctnrCodeSeven, grossWeight, temperature));
		manifest.loadContainer(new RefrigeratedContainer(ctnrCodeEight, grossWeight, temperature));
		manifest.loadContainer(new RefrigeratedContainer(ctnrCodeNine, grossWeight, temperature));
		
		assertTrue(manifest.howHigh(ctnrCodeOne)==0);
		assertTrue(manifest.howHigh(ctnrCodeTwo)==1);
		assertTrue(manifest.howHigh(ctnrCodeThree)==2);
		
		assertTrue(manifest.howHigh(ctnrCodeFour)==0);
		assertTrue(manifest.howHigh(ctnrCodeFive)==1);
		assertTrue(manifest.howHigh(ctnrCodeSix)==2);

		assertTrue(manifest.howHigh(ctnrCodeSeven)==0);
		assertTrue(manifest.howHigh(ctnrCodeEight)==1);
		assertTrue(manifest.howHigh(ctnrCodeNine)==2);	
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test (expected = ManifestException.class)
	public void loadFirstContainerExceedingWeight() throws ManifestException, InvalidContainerException{
		newManifest = new CargoManifest(threeStacks, threeCtnrsHigh, 29);
		newManifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, tooHeavyWeight));
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test (expected = ManifestException.class)
	public void loadLastContainerExceedingWeight() throws ManifestException, InvalidContainerException{
		newManifest = new CargoManifest(threeStacks, threeCtnrsHigh, 35);
		newManifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		newManifest.loadContainer(new GeneralGoodsContainer(ctnrCodeTwo, grossWeight));
		newManifest.loadContainer(new GeneralGoodsContainer(ctnrCodeThree, grossWeight));
		newManifest.loadContainer(new GeneralGoodsContainer(ctnrCodeFour, tooHeavyWeight));
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
	@Test (expected = ManifestException.class)
	public void loadContainerDuplicateCode() throws ManifestException{
		manifest.loadContainer(dangerousCtnr);
		manifest.loadContainer(dangerousCtnr);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test (expected = ManifestException.class)
	public void loadContainerNoSpaceInStack() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeTwo, grossWeight));
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeThree, grossWeight, categoryOne));
		manifest.loadContainer(new RefrigeratedContainer(ctnrCodeFour, grossWeight, temperature));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeFive, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeSix, grossWeight));
		
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void unloadContainerOnTop() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeTwo, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeThree, grossWeight));
		manifest.unloadContainer(ctnrCodeThree);
		assertTrue(manifest.whichStack(ctnrCodeThree)==null);		
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test (expected = ManifestException.class)
	public void unloadContainerNotOnTop () throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeTwo, grossWeight));
		manifest.unloadContainer(ctnrCodeOne);
		assertTrue(manifest.whichStack(ctnrCodeFive)!=null);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
	@Test (expected = ManifestException.class)
	public void unloadContainerNotOnShip () throws ManifestException {
		manifest.unloadContainer(ctnrCodeOne);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
	@Test
	public void unloadCtnrThenLoadAgain() throws ManifestException{
		manifest.loadContainer(dangerousCtnr);
		manifest.unloadContainer(ctnrCodeOne);
		manifest.loadContainer(dangerousCtnr);
		
		assertTrue(manifest.whichStack(ctnrCodeOne)==0);
		assertTrue(manifest.howHigh(ctnrCodeOne)==0);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void unloadTwoFromSameStack() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeTwo, grossWeight));
		
		manifest.unloadContainer(ctnrCodeTwo);
		manifest.unloadContainer(ctnrCodeOne);
		
		assertTrue(manifest.whichStack(ctnrCodeOne)==null);
		assertTrue(manifest.whichStack(ctnrCodeTwo)==null);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
	@Test
	public void unloadTwoFromDifferentStacks() throws ManifestException{
		manifest.loadContainer(generalCtnr);
		manifest.loadContainer(dangerousCtnr);
		
		manifest.unloadContainer(ctnrCodeTwo);
		manifest.unloadContainer(ctnrCodeOne);
		
		assertTrue(manifest.whichStack(ctnrCodeOne)==null);
		assertTrue(manifest.whichStack(ctnrCodeTwo)==null);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void unloadDoesNotAffectRemainingContainersStack() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeTwo, grossWeight,categoryOne));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeThree, grossWeight));
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeFour, grossWeight,categoryOne));
		
		manifest.unloadContainer(ctnrCodeThree);
		
		assertTrue(manifest.whichStack(ctnrCodeOne)==0);
		assertTrue(manifest.whichStack(ctnrCodeTwo)==1);
		assertTrue(manifest.whichStack(ctnrCodeThree)==null);
		assertTrue(manifest.whichStack(ctnrCodeFour)==1);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void unloadDoesNotAffectRemainingContainersHeight() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeOne, grossWeight));
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeTwo, grossWeight,categoryOne));
		manifest.loadContainer(new GeneralGoodsContainer(ctnrCodeThree, grossWeight));
		manifest.loadContainer(new DangerousGoodsContainer(ctnrCodeFour, grossWeight,categoryOne));
		
		manifest.unloadContainer(ctnrCodeThree);
		
		assertTrue(manifest.howHigh(ctnrCodeOne)==0);
		assertTrue(manifest.howHigh(ctnrCodeTwo)==0);
		assertTrue(manifest.howHigh(ctnrCodeThree)==null);
		assertTrue(manifest.howHigh(ctnrCodeFour)==1);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void unloadAllFromCenterStackDoesNotMoveContainersInFurtherestStack() throws ManifestException, InvalidContainerException{
		manifest.loadContainer(dangerousCtnr);
		manifest.loadContainer(generalCtnr);
		manifest.loadContainer(refrigeratedCtnr);
		
		manifest.unloadContainer(ctnrCodeTwo);
		
		assertTrue(manifest.whichStack(ctnrCodeOne)==0);
		assertTrue(manifest.whichStack(ctnrCodeTwo)==null);
		assertTrue(manifest.whichStack(ctnrCodeThree)==2);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
	@Test
	public void unloadAllContainers() throws ManifestException{
		manifest.loadContainer(dangerousCtnr);
		manifest.loadContainer(generalCtnr);
		manifest.loadContainer(refrigeratedCtnr);
		
		manifest.unloadContainer(ctnrCodeOne);
		manifest.unloadContainer(ctnrCodeTwo);
		manifest.unloadContainer(ctnrCodeThree);
		
		CargoManifest emptyManifest = new CargoManifest(threeCtnrsHigh, threeCtnrsHigh, maxWeight);

		assertEquals(emptyManifest.toString(), manifest.toString());
	}

	/**
     * @author James Flannery 08326631
     */
	@Test
	public void whichStackNotOnBoard () {
		assertEquals(null, manifest.whichStack(ctnrCodeOne));
	}

	/**
     * @author James Flannery 08326631
     */
	@Test
	public void howHighNotOnBoard () {
		assertEquals(null, manifest.howHigh(ctnrCodeOne));
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
	@Test
	public void toArrayEmptyStack() throws ManifestException {
		FreightContainer[] stack = new FreightContainer [threeStacks];  
			
		assertArrayEquals(manifest.toArray(0), stack);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
	@Test
	public void toArrayOneInStack() throws ManifestException {
		FreightContainer[] stack = new FreightContainer [] {generalCtnr, null, null};  
		
		manifest.loadContainer(generalCtnr);
		
		assertArrayEquals(manifest.toArray(0), stack);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void toArrayMultipleInStack() throws InvalidContainerException, ManifestException {
		GeneralGoodsContainer ctnrOne = new GeneralGoodsContainer(ctnrCodeOne, grossWeight);
		GeneralGoodsContainer ctnrTwo = new GeneralGoodsContainer(ctnrCodeTwo, grossWeight);
				
		FreightContainer[] stack = new FreightContainer [] {ctnrOne, ctnrTwo, null};  
		
		manifest.loadContainer(ctnrOne);
		manifest.loadContainer(ctnrTwo);
		
		assertArrayEquals(manifest.toArray(0), stack);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     * @throws InvalidContainerException
     */
	@Test
	public void toArrayFullStack() throws InvalidContainerException, ManifestException {
		GeneralGoodsContainer ctnrOne = new GeneralGoodsContainer(ctnrCodeOne, grossWeight);
		GeneralGoodsContainer ctnrTwo = new GeneralGoodsContainer(ctnrCodeTwo, grossWeight);
		GeneralGoodsContainer ctnrThree = new GeneralGoodsContainer(ctnrCodeThree, grossWeight);
				
		FreightContainer[] stack = new FreightContainer [] {ctnrOne, ctnrTwo, ctnrThree};  
		
		manifest.loadContainer(ctnrOne);
		manifest.loadContainer(ctnrTwo);
		manifest.loadContainer(ctnrThree);
		
		assertArrayEquals(manifest.toArray(0), stack);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
	@Test(expected = ManifestException.class)
	public void toArrayNoSuchStack() throws ManifestException{
		manifest.toArray(4);
	}

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
    @Test
    public void manifestToString() throws ManifestException{
    	manifest.loadContainer(dangerousCtnr);
    	assertEquals("|| KOCU8090115 ||\n||  ||\n||  ||\n", manifest.toString());
    }

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
    @Test
    public void emptyManifestToString() throws ManifestException{
    	assertEquals("||  ||\n||  ||\n||  ||\n", manifest.toString());
    }

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
    @Test
    public void toFindManifestToString() throws ManifestException{
    	manifest.loadContainer(dangerousCtnr);
    	assertEquals("||*KOCU8090115*||\n||  ||\n||  ||\n", manifest.toString(ctnrCodeOne));
    }

	/**
     * @author James Flannery 08326631
     * @throws ManifestException
     */
    @Test
    public void nonExistantToFindManifestToString() throws ManifestException{
    	manifest.loadContainer(dangerousCtnr);
    	assertEquals("|| KOCU8090115 ||\n||  ||\n||  ||\n", manifest.toString(ctnrCodeTwo));
    }
	
}
