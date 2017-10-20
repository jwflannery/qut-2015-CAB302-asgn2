/*
*This file forms part of the CargoSystem Project
* Assignment Two�CAB302 2015
*
*/
package asgn2Exceptions;


/**
 * Exception thrown when an attempt is made to construct an
 * invalid ship's manifest or perform an incorrect operation
 * on a manifest.
 * 
 * @author Kayla Degraaf 08599874 | James Flannery 08326631 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ManifestException extends CargoException {

	/**
	 * Constructs a new ManifestException object.
	 * 
	 * @param message an informative message describing the problem encountered
	 */
	public ManifestException(String message) {
		super("ManifestException: " + message);
	}

}
