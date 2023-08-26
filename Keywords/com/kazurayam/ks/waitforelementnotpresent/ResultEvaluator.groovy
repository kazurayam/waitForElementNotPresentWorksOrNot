package com.kazurayam.ks.waitforelementnotpresent

import com.kms.katalon.core.util.KeywordUtil

public class ResultEvaluator {

	void evaluate(Boolean expected, Map result) {
		if (expected == result.kwReturn) {
			KeywordUtil.logInfo("expected WebUI.waitForElement() to return ${expected} and it acutually returned ${result.kwReturn}")
			if (result.kwDuration <= result.timeout) {
				KeywordUtil.logInfo("kwDuration(${result.kwDuration}) is less or equal to timeout(${result.timeout})")
			} else {
				KeywordUtil.markFailed("kwDuration(${result.kwDuration}) is greater than timeout(${result.timeout})")
			}
		} else {
			KeywordUtil.markFailed("expected WebUI.waitForElement() to return ${expected} but it returned ${result.kwReturn}")
		}
	}
}
