import com.kms.katalon.core.util.KeywordUtil

/*
 * Test Cases/evaluateResult
 */
assert expected != null
assert (expected instanceof Boolean)
assert result != null
assert (result instanceof Map)

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