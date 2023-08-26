package com.kazurayam.ks.waitforelementnotpresent

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.Duration
import java.time.LocalDateTime

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class KeywordDriver {

	Map<String, Object> drive(int distance, int timeout) {
		WebUI.comment("Katalon Studio version: ${RunConfiguration.getAppVersion()}")
		
		TestObject tObj = makeTestObject("demo", "#demo")

		Path projectDir = Paths.get(RunConfiguration.getProjectDir())
		Path outDir = projectDir.resolve("screenshots")
		Files.createDirectories(outDir)
		Path html = projectDir.resolve("page.html")

		StringBuilder sb = new StringBuilder()
		sb.append(html.toFile().toURI().toURL().toExternalForm())
		sb.append("?ksVersion=${RunConfiguration.getAppVersion()}")
		sb.append('&')
		sb.append("distance=${distance}")
		String urlString = sb.toString()
		
		WebUI.openBrowser('')
		WebUI.setViewPortSize(400, 400)
		
		WebUI.navigateToUrl(urlString)
		WebUI.verifyElementPresent(tObj, timeout, FailureHandling.STOP_ON_FAILURE)

		LocalDateTime startAt = LocalDateTime.now()
		boolean kwReturn = WebUI.waitForElementNotPresent(tObj, timeout)
		LocalDateTime endAt = LocalDateTime.now()
		long kwDuration = Duration.between(startAt, endAt).getSeconds()

		Path outfile = outDir.resolve(RunConfiguration.getAppVersion() + "_d" + distance + "_t" + timeout + ".png")
		WebUI.takeScreenshot(outfile.toString())

		WebUI.closeBrowser()
		
		return ["distance": distance, "timeout": timeout, "kwReturn": kwReturn, "kwDuration": kwDuration]
	}

	private TestObject makeTestObject(String id, String selector) {
		TestObject tObj = new TestObject(id)
		tObj.addProperty("css", ConditionType.EQUALS, selector)
		return tObj
	}
}
