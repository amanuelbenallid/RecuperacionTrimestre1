package org.iesalixar.amanuelbenallid.helper;

import java.util.regex.Pattern;

public class Helper {
	
	/**
	 * Explanation:
	 *
	 *	^                 # start-of-string
	 *  (?=.*[0-9])       # a digit must occur at least once
	 *  (?=.*[a-z])       # a lower case letter must occur at least once
	 *	(?=.*[A-Z])       # an upper case letter must occur at least once
	 *	(?=.*[@#$%^&+=])  # a special character must occur at least once
	 *	(?=\S+$)          # no whitespace allowed in the entire string
	 *	.{6,}             # anything, at least eight places though
	 *	$                 # end-of-string
	 */
	private static final Pattern VALID_PASSWORD_REGEX = 
		    Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$");
	
	/**
	 * Explanation:
	 * 
	 * ^             	# Start of the line
     * [aA-Zz0-9_-]	 	# Match characters and symbols in the list, a-z, 0-9, underscore, hyphen
     * {4,15}  		 	# Length at least 8 characters and maximum length of 15 
	 * $				# End of the line
	 */
	private static final Pattern VALID_USERNAME_REGEX = 
		    Pattern.compile("^[aA-zZ0-9_-]{4,15}$");
	
	/**
	 * Explanation:
	 * 
	 * ^             	# Start of the line
     * [aA-Zz0-9_ ]	 	# Match characters and symbols in the list, a-z, 0-9, underscore, space character
     * {10,50}  		 	# Length at least 10 characters and maximum length of 50 
	 * $				# End of the line
	 */
	private static final Pattern VALID_FULLNAME_REGEX = 
		    Pattern.compile("^[aA-zZ]{10,50}$");
	
	public static boolean checkPassword(String password) {
		return (password !=null && VALID_PASSWORD_REGEX.matcher(password).find());
	}
	
	public static boolean checkUsername(String username) {
		return (username !=null && VALID_USERNAME_REGEX.matcher(username).find());
	}
	
	
	public static boolean checkFullname(String fullname) {
		return (fullname!=null && VALID_FULLNAME_REGEX.matcher(fullname).find());		
	}
	
	
	
	

}
