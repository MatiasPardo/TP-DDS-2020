package geSoc.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import geSoc.excepcion.*;
import geSoc.validadores.*;

public class PassValidatorTest {
	@Test
	public void isCommonPassReturnsTrueWhenPassIsCommon() {
		assertEquals(true, PassValidator.isCommonPassword("pasword"));
	};
	@Test
	public void isCommonPassReturnsFalseWhenPassIsNotCommon() {
		assertEquals(false, PassValidator.isCommonPassword("notCommonPass"));
	};
	@Test
	public void containsThreeOrMoreIdenticallCharactersReturnsTrueWhenPassContainsConsecutiveCharacters() {
		assertEquals(false, PassValidator.identicallCharacters("88"));
	};
	@Test
	public void containsThreeOrMoreIdenticallCharactersReturnsFalseWhenPassContainsConsecutiveCharacters() {
		assertEquals(false, PassValidator.identicallCharacters("goodpAAs"));
	};
	@Test
	public void containsConsecutiveCharactersReturnsTrue() {
		assertEquals(true, PassValidator.identicallCharacters("threeTimesThree333"));
	};
	@Test
	public void containsConsecutiveCharactersReturnsFalse() {
		assertEquals(false, PassValidator.identicallCharacters("TwoTimesTwo22"));
	};
	@Test
	public void atLeast8caractersReturnsTrue() {
		assertEquals(false, PassValidator.lessThan8characters("fourfour"));
	}
	@Test
	public void atLeast8caractersReturnsFalse() {
		assertEquals(true, PassValidator.lessThan8characters("fourtwo"));
	}
	
	@Test(expected = ExceptionSecurityLow.class)
	public void isNotAStrongPass() {
		PassValidator.isStrong("notStrong");
	}
	//@Test
	//public void isStrongPass() {
	//	PassValidator.isStrong("StronGPass123.");
	//}
	
	@Test
	public void hasCapitalsLettersReturnsTrue() {
		assertEquals(true, PassValidator.hasCapitalLetters("itHas"));
	}
	
	@Test
	public void hasCapitalsLettersReturnsFalse() {
		assertEquals(false, PassValidator.hasCapitalLetters("dont"));
	}

	@Test
	public void hasLowerCaseLettersReturnsTrue() {
		assertEquals(true, PassValidator.hasLowerCaseLetters("itHas"));
	}
	
	@Test
	public void hasLowerCaseLettersReturnsFalse() {
		assertEquals(false, PassValidator.hasLowerCaseLetters("DONT"));
	}

	@Test
	public void hasNumbersReturnsTrue() {
		assertEquals(true, PassValidator.hasNumbers("123"));
	}

	@Test
	public void hasNumbersReturnsFalse() {
		assertEquals(false, PassValidator.hasNumbers("abc"));
	}

	@Test
	public void hasSpecialCharactersReturnsTrue() {
		assertEquals(true, PassValidator.hasSpecialCharacters("contains."));
	}

	@Test
	public void hasSpecialCharactersReturnsFalse() {
		assertEquals(false, PassValidator.hasSpecialCharacters("dont"));
	}
	
	@Test
	public void containsEnoughComplexityReturnsTrue() {
		assertEquals(true, PassValidator.containsEnoughComplexity("lowerUPPER12."));
	}
	
	
	@Test
	public void containsEnoughComplexityReturnsFalse() {
		assertEquals(false, PassValidator.containsEnoughComplexity("lowernotupernotspecial"));
	}
}
