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
 */

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asgn2Codes.ContainerCode;
import asgn2Containers.DangerousGoodsContainer;
import asgn2Containers.GeneralGoodsContainer;
import asgn2Containers.RefrigeratedContainer;
import asgn2Exceptions.InvalidCodeException;
import asgn2Exceptions.InvalidContainerException;


public class ContainerTests {
	//Implementation Here - includes tests for ContainerCode and for the actual container classes. 

	private DangerousGoodsContainer dangerousCtnr;
	private GeneralGoodsContainer generalCtnr;
	private RefrigeratedContainer refrigeratedCtnr;
	private ContainerCode ctnrCode;
	private Integer grossWeight;
	private Integer explosivesCategory;
	private Integer oneDegree;
	private Integer tooHeavy;
	private Integer tooLight;
	private Integer negativeWeight;
	private ContainerCode code;
	private String strCode;

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidCodeException 
	 * @throws InvalidContainerException 
	 */
	@Before
	public void setUp() throws InvalidCodeException, InvalidContainerException {
		ctnrCode = new ContainerCode("MSCU6639871");
		grossWeight = 11;
		explosivesCategory = 1;
		oneDegree = 1;
		tooHeavy = 31;
		tooLight = 3;
		negativeWeight = -3;
		dangerousCtnr = new DangerousGoodsContainer(ctnrCode, grossWeight, explosivesCategory);
		generalCtnr = new GeneralGoodsContainer(ctnrCode, grossWeight);
		refrigeratedCtnr = new RefrigeratedContainer(ctnrCode, grossWeight, oneDegree);
		
	}

	// DangerousGoodsContainer Tests

	// THIS ONE ISN'T IN THE JAVADOC ********************************
	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidContainerException 
	 */ /*
	@Test(expected=InvalidCodeException.class)
	public void dgcConstructorNullCode() throws InvalidContainerException{
		DangerousGoodsContainer ctnr = new DangerousGoodsContainer(null, grossWeight, explosivesCategory);
	}*/

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidContainerException 
	 */
	@Test(expected=InvalidContainerException.class)
	public void dgcConstructorWeightTooHigh() throws InvalidContainerException{
		DangerousGoodsContainer ctnr = new DangerousGoodsContainer(ctnrCode, tooHeavy, explosivesCategory);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidContainerException 
	 */
	@Test(expected=InvalidContainerException.class)
	public void dgcConstructorWeightTooLow() throws InvalidContainerException{
		DangerousGoodsContainer ctnr = new DangerousGoodsContainer(ctnrCode, tooLight, explosivesCategory);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidContainerException 
	 */
	@Test(expected=InvalidContainerException.class)
	public void dgcConstructorNegativeWeight() throws InvalidContainerException{
		DangerousGoodsContainer ctnr = new DangerousGoodsContainer(ctnrCode, negativeWeight, explosivesCategory);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidContainerException 
	 */
	@Test(expected=InvalidContainerException.class)
	public void dgcConstructorCategoryTooHigh() throws InvalidContainerException{
		Integer invalidCategory = 10;
		DangerousGoodsContainer ctnr = new DangerousGoodsContainer(ctnrCode, grossWeight, invalidCategory);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidContainerException 
	 */
	@Test(expected=InvalidContainerException.class)
	public void dgcConstructorCategoryTooLow() throws InvalidContainerException{
		Integer invalidCategory = 0;
		DangerousGoodsContainer ctnr = new DangerousGoodsContainer(ctnrCode, grossWeight, invalidCategory);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidContainerException 
	 */
	@Test(expected=InvalidContainerException.class)
	public void dgcConstructorNegativeCategory() throws InvalidContainerException{
		Integer invalidCategory = -3;
		DangerousGoodsContainer ctnr = new DangerousGoodsContainer(ctnrCode, grossWeight, invalidCategory);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 */
	@Test
	public void dgcGetCode(){
		assertTrue("Returned code is incorrect", dangerousCtnr.getCode()==ctnrCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 */
	@Test
	public void dgcGetGrossWeight(){
		assertTrue("Returned gross weight is incorrect", dangerousCtnr.getGrossWeight()==grossWeight);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 */
	@Test
	public void dgcGetCategory(){
		assertTrue("Returned category is incorrect", dangerousCtnr.getCategory()==explosivesCategory);
	}

	// GeneralGoodsContainer Tests

	// THIS ONE ISN'T IN THE JAVADOC ********************************
	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidContainerException 
	 */ /*
	@Test(expected=InvalidCodeException.class)
	public void ggcConstructorNullCode() throws InvalidContainerException{
		GeneralGoodsContainer ctnr = new GeneralGoodsContainer(null, grossWeight);
	}*/

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidContainerException 
	 */
	@Test(expected=InvalidContainerException.class)
	public void ggcConstructorWeightTooHigh() throws InvalidContainerException{
		GeneralGoodsContainer ctnr = new GeneralGoodsContainer(ctnrCode, tooHeavy);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidContainerException 
	 */
	@Test(expected=InvalidContainerException.class)
	public void ggcConstructorWeightTooLow() throws InvalidContainerException{
		GeneralGoodsContainer ctnr = new GeneralGoodsContainer(ctnrCode, tooLight);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidContainerException 
	 */
	@Test(expected=InvalidContainerException.class)
	public void ggcConstructorNegativeWeight() throws InvalidContainerException{
		GeneralGoodsContainer ctnr = new GeneralGoodsContainer(ctnrCode, negativeWeight);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 */
	@Test
	public void ggcGetCode(){
		assertTrue("Returned code is incorrect", generalCtnr.getCode()==ctnrCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 */
	@Test
	public void ggcGetGrossWeight(){
		assertTrue("Returned gross weight is incorrect", generalCtnr.getGrossWeight()==grossWeight);
	}

	// RefrigeratedContainer Tests

	// THIS ONE ISN'T IN THE JAVADOC ********************************
	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidContainerException 
	 */ /*
	@Test(expected=InvalidCodeException.class)
	public void rcConstructorNullCode() throws InvalidContainerException{
		RefrigeratedContainer ctnr = new RefrigeratedContainer(null, grossWeight, oneDegree);
	}*/

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidContainerException 
	 */
	@Test(expected=InvalidContainerException.class)
	public void rcConstructorWeightTooHigh() throws InvalidContainerException{
		RefrigeratedContainer ctnr = new RefrigeratedContainer(ctnrCode, tooHeavy, oneDegree);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidContainerException 
	 */
	@Test(expected=InvalidContainerException.class)
	public void rcConstructorWeightTooLow() throws InvalidContainerException{
		RefrigeratedContainer ctnr = new RefrigeratedContainer(ctnrCode, tooLight, oneDegree);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidContainerException 
	 */
	@Test(expected=InvalidContainerException.class)
	public void rcConstructorNegativeWeight() throws InvalidContainerException{
		RefrigeratedContainer ctnr = new RefrigeratedContainer(ctnrCode, negativeWeight, oneDegree);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 */
	@Test
	public void rcGetCode(){
		assertTrue("Returned code is incorrect", refrigeratedCtnr.getCode()==ctnrCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 */
	@Test
	public void rcGetGrossWeight(){
		assertTrue("Returned gross weight is incorrect", refrigeratedCtnr.getGrossWeight()==grossWeight);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 */
	@Test
	public void rcGetTemperature(){
		assertTrue("Returned temperature is incorrect", refrigeratedCtnr.getTemperature()==oneDegree);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 */
	@Test
	public void rcSetTemperature(){
		Integer newTemp = 2;
		refrigeratedCtnr.setTemperature(newTemp);
		assertTrue("Temperature was not set properly", refrigeratedCtnr.getTemperature()==newTemp);
	}

	// ContainerCode Tests
	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidCodeException 
	 */
	@Test
	public void containerCodeValid() throws InvalidCodeException{
		strCode = "CSQU3054389";
		code = new ContainerCode(strCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidCodeException 
	 */
	@Test(expected=InvalidCodeException.class)
	public void codeTooShort() throws InvalidCodeException{
		strCode = "CSQU3059";
		code = new ContainerCode(strCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidCodeException 
	 */
	@Test(expected=InvalidCodeException.class)
	public void codeTooLong() throws InvalidCodeException{
		strCode = "CSQU37298273629059";
		code = new ContainerCode(strCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidCodeException 
	 */
	@Test(expected=InvalidCodeException.class)
	public void ownerCodeNotCapitalised() throws InvalidCodeException{
		strCode = "csqU3054389";
		code = new ContainerCode(strCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidCodeException 
	 */
	@Test(expected=InvalidCodeException.class)
	public void ownerCodeAllNumbers() throws InvalidCodeException{
		strCode = "123U3054389";
		code = new ContainerCode(strCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidCodeException 
	 */
	@Test(expected=InvalidCodeException.class)
	public void ownerCode1Number() throws InvalidCodeException{
		strCode = "CS1WU3054389";
		code = new ContainerCode(strCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidCodeException 
	 */
	@Test(expected=InvalidCodeException.class)
	public void ownerCode2Numbers() throws InvalidCodeException{
		strCode = "C12WU3054389";
		code = new ContainerCode(strCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidCodeException 
	 */
	@Test(expected=InvalidCodeException.class)
	public void categoryIdentifierANumber() throws InvalidCodeException{
		strCode = "CSQ33054389";
		code = new ContainerCode(strCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidCodeException 
	 */
	@Test(expected=InvalidCodeException.class)
	public void categoryIdentifierNotLetterU() throws InvalidCodeException{
		strCode = "CSQX3054389";
		code = new ContainerCode(strCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidCodeException 
	 */
	@Test(expected=InvalidCodeException.class)
	public void serialNumberAllLetters() throws InvalidCodeException{
		strCode = "CSQUDJUENF9";
		code = new ContainerCode(strCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidCodeException 
	 */
	@Test(expected=InvalidCodeException.class)
	public void serialNumberSomeLetters() throws InvalidCodeException{
		strCode = "CSQU30DC389";
		code = new ContainerCode(strCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidCodeException 
	 */
	@Test(expected=InvalidCodeException.class)
	public void checkDigitNotNumber() throws InvalidCodeException{
		strCode = "CSQU305438A";
		code = new ContainerCode(strCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidCodeException 
	 */
	@Test(expected=InvalidCodeException.class)
	public void checkDigitIncorrect() throws InvalidCodeException{
		strCode = "CSQU3054381";
		code = new ContainerCode(strCode);
	}

	/**
	 * @author Kayla Degraaf 08599874
	 */
	@Test
	public void containerCodeToString(){
		assertTrue("Code does not match", ctnrCode.toString() == "MSCU6639871");
	}

	/**
	 * @author Kayla Degraaf 08599874
	 */
	@Test
	public void containerCodeEqualsSameObject(){
		assertTrue("Codes do not match", ctnrCode.equals(ctnrCode));
	}
	
	/**
	 * @author Kayla Degraaf 08599874
	 * @throws InvalidCodeException 
	 */
	@Test
	public void containerCodeEqualsDifferentObjects() throws InvalidCodeException{
		ContainerCode ctnrCode2 = new ContainerCode("MSCU6639871");
		assertTrue("Codes do not match", ctnrCode.equals(ctnrCode2));
	}

}

