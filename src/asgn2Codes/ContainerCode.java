package asgn2Codes;

import asgn2Exceptions.InvalidCodeException;

/* Note to self:
 * Use option "-noqualifier java.lang" when generating Javadoc from this
 * file so that, for instance, we get "String" instead of
 * "java.lang.String".
 */

/**
 * This class provides cargo container codes in a format similar to that
 * prescribed by international standard ISO 6346.  (The only difference
 * is that we use a simpler algorithm for calculating the check digit.)
 * <p>
 * A container code is an 11-character string consisting of the following
 * fields:
 * <ul>
 * <li>
 * An <em>Owner Code</em> which uniquely identifies the company that owns
 * the container.  The owner code consists of three upper-case letters.
 * (To ensure uniqueness in practice, owner codes must be registered at
 * the <em>Bureau International des Containers</em> in Paris.)
 * </li>
 * <li>
 * A <em>Category Identifier</em> which identifies the type of container.
 * The identifier is one of three letters, 'U', 'J' or 'Z'.  For our
 * purposes the category identifier is <em>always</em> 'U', which denotes a
 * freight container.
 * </li>
 * <li>
 * A <em>Serial Number</em> used by the owner to uniquely identify the
 * container.  The number consists of six digits.
 * </li>
 * <li>
 * A <em>Check Digit</em> used to ensure that the number has not been
 * transcribed incorrectly.  It consists of a single digit derived from the
 * other 10 characters according to a specific algorithm, described below.
 * </li>
 * </ul>
 * <p>
 * <strong>Calculating the check digit:</strong> ISO 6346 specifies a
 * particular algorithm for deriving the check digit from the
 * other 10 characters in the code.  However, because this algorithm is
 * rather complicated, we use a simpler version for this assignment.
 * Our algorithm works as follows:
 * <ol>
 * <li>
 * Each of the 10 characters in the container code (excluding the check
 * digit) is treated as a number.  The digits '0' to '9' each have
 * their numerical value.  The upper case alphabetic letters 'A' to
 * 'Z' are treated as the numbers '0' to '25', respectively.
 * </li>
 * <li>
 * These 10 numbers are added up.
 * </li>
 * <li>
 * The check digit is the least-significant digit in the sum produced
 * by the previous step.
 * </li>
 * </ol>
 * For example, consider container code <code>MSCU6639871</code> which
 * has owner code <code>MSC</code>, category identifier <code>U</code>,
 * serial number <code>663987</code> and check digit <code>1</code>.  We can 
 * confirm that this code is valid by treating the letters as numbers (e.g.,
 * '<code>M</code>' is 12, '<code>S</code>' is 18, etc) and calculating the
 * following sum.
 * <p>
 * <center>
 * 12 + 18 + 2 + 20 + 6 + 6 + 3 + 9 + 8 + 7 = 91
 * </center>
 * <p>
 * The check digit is then the least-sigificant digit in the number 91,
 * i.e., '<code>1</code>', thus confirming that container code 
 * <code>MSCU6639871</code> is valid.
 * 
 * @author CAB302 
 * @version 1.0
 */ 
public class ContainerCode {

	private String ctnrCode;
	/**
	 * Constructs a new container code.
	 * 
	 * @param code the container code as a string
	 * @throws InvalidCodeException if the container code is not eleven characters long; if the
	 * Owner Code does not consist of three upper-case letters; if the
	 * Category Identifier is not 'U'; if the Serial Number does not consist
	 * of six digits; or if the Check Digit is incorrect.
	 */
	public ContainerCode(String code) throws InvalidCodeException {
		
		if(!validCodeLength(code)){
			throw new InvalidCodeException("Container code is not 11 characters long");
		} else if(!validOwnerCode(code)){
			throw new InvalidCodeException("The owner code is not valid");
		} else if(!validCategoryIdentifer(code)){
			throw new InvalidCodeException("Category identifier must equal 'U'");
		} else if(!validSerialNumber(code)) {
			throw new InvalidCodeException("The serial number is not valid");
		} else if(!checkDigitCorrect(code)){
			throw new InvalidCodeException("The check digit is incorrect");
		} else {
			ctnrCode = code;
		}
	}

	private boolean validCodeLength(String code){
		if(code.length()==11){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validOwnerCode(String code){
		String ownerCode = code.substring(0, 3);
		String onlyThreeCapitalLettersRegex = "[A-Z]{3}";
		if(!ownerCode.matches(onlyThreeCapitalLettersRegex)){
			return false;
		} else {
			return true;
		}
	}
	
	private boolean validCategoryIdentifer(String code){
		String categoryIdentifier = code.substring(3,4);
		if(!categoryIdentifier.contains("U")){
			return false;
		} else {
			return true;
		}
	}
	
	private boolean validSerialNumber(String code){
		String serialNumber = code.substring(4,10);
		String sixDigitsRegex = "[0-9]{6}";
		if(!serialNumber.matches(sixDigitsRegex)){
			return false;
		} else {
			return true;
		}
	}
	
	private boolean checkDigitCorrect(String code) {
		// QUESTION: The split() method is implemented differently between versions of eclipse
		String[] codeStrArray = code.split("");
		int[] codeNumericValueArray;
		
		if(codeStrArray[0]==""){
			codeNumericValueArray = new int[codeStrArray.length-2];
			String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			int numericValue;

			for (int i = 1; i < codeStrArray.length-1; i++) {
				if(!codeStrArray[i].matches("[0-9]")){
					numericValue = alphabet.indexOf(codeStrArray[i]);
				} else {
					numericValue = Integer.parseInt(codeStrArray[i]);
				}
				codeNumericValueArray[i-1] = numericValue;
			}	
		}else{
			codeNumericValueArray = new int[codeStrArray.length-1];
			String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			int numericValue;
			
			for (int i = 0; i < codeStrArray.length-1; i++) {
				if(!codeStrArray[i].matches("[0-9]")){
					numericValue = alphabet.indexOf(codeStrArray[i]);
				} else {
					numericValue = Integer.parseInt(codeStrArray[i]);
				}
				codeNumericValueArray[i] = numericValue;
			}
		}
		
		
		Integer sumOfCode = 0;
		for (int i : codeNumericValueArray) {
			sumOfCode += i;
		}
		
		String strSumOfCode = sumOfCode.toString();
		
		String leastSignificantDigit = strSumOfCode.substring(strSumOfCode.length() - 1);
		if(leastSignificantDigit.contains(codeStrArray[codeStrArray.length-1])){
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ctnrCode;
	}

	
	/**
	 * Returns true if the given object is a container code and has an
	 * identical value to this code.
	 * 
	 * @author 08326631 James Flannery
	 * @param obj an object
	 * @return true if the given object is a container code with the
	 * same string value as this code, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ContainerCode)){
			return false;
		} else if(obj.toString().equals(this.toString())){
			return true;
		} else {
			return false;
		}
	}
}

