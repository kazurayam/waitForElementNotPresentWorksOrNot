package com.kazurayam.ks.waitforelementnotpresentworksornot

import com.kms.katalon.core.util.KeywordUtil

public class ResultEvaluator {
	
	void evaluate(Boolean expected, Map result) {
		if (expected == result.kwReturn) {
			KeywordUtil.logInfo("expected WebUI.waitForElement() to return ${expected} and it acutually returned ${result.kwReturn}")
			if (result.timeTaken <= result.timeout) {
				KeywordUtil.logInfo("timeTaken(${result.timeTaken}) is less or equal to timeout(${result.timeout})")
			} else {
				KeywordUtil.markFailed("timeTaken(${result.timeTaken}) is NOT less or equal to timeout(${result.timeout})")
			}
		} else {
			KeywordUtil.markFailed("expected WebUI.waitForElement() to return ${expected} but it returned ${result.kwReturn}")
		}
	}
}
