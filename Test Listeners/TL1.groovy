import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.configuration.RunConfiguration
import java.nio.file.Path
import java.nio.file.Paths
import com.kazurayam.subprocessj.Subprocess
import com.kazurayam.subprocessj.Subprocess.CompletedProcess


class TL1 {
	
	@BeforeTestCase
	def beforeTestCase(TestCaseContext testCaseContext) {
		//println testCaseContext.getTestCaseId()
		//println testCaseContext.getTestCaseVariables()
	}

	@AfterTestCase
	def afterTestCase(TestCaseContext testCaseContext) {
		//println testCaseContext.getTestCaseId()
		//println testCaseContext.getTestCaseStatus()
	}

	@BeforeTestSuite
	def beforeTestSuite(TestSuiteContext testSuiteContext) {
		//println testSuiteContext.getTestSuiteId()
	}

	@AfterTestSuite
	def afterTestSuite(TestSuiteContext testSuiteContext) {
		WebUI.comment("testSuiteId=${testSuiteContext.getTestSuiteId()}")
		Path reportFolder = Paths.get(RunConfiguration.getReportFolder())
		WebUI.comment("reportFolder=${reportFolder.toString()}")
		String prefix = reportFolder.getFileName().toString()
		String html = prefix + ".html"
		String pdf  = prefix + ".pdf"
		WebUI.comment("html=${html}, pdf=${pdf}")
		runWkhtmltopdf(reportFolder, html, pdf)
	}
	
	void runWkhtmltopdf(Path reportFolder, String html, String pdf) {
		Subprocess.CompletedProcess cp;
		cp = new Subprocess().cwd(reportFolder.toFile())
				.run(Arrays.asList("/usr/local/bin/wkhtmltopdf", 
					"--run-script", "\"console.log('starting')\"",
					"--run-script", "\"document.querySelector('h1').textContent='hello'\"",
					"--run-script", "\"expandAllChildren('s1')\"",
					"--run-script", "\"console.log('finished')\"",
					html, pdf ));
		assert 0 == cp.returncode()
		cp.stdout().each { line -> println line }
		cp.stderr().each { line -> println line }
	}
}