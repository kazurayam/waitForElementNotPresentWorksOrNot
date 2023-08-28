package com.kazurayam.ks.reporting

import java.nio.file.Files
import java.nio.file.Path

import com.kazurayam.subprocessj.Subprocess
import com.kms.katalon.core.util.KeywordUtil


public class TestSuiteReportConverter {

	/**
	 * convert a Test Suite Execution Report in HTML to PDF
	 * using the wkhtmltopdf command
	 * 
	 * @param reportFolder
	 */
	public void convertHtmlToPDF(Path reportFolder) {
		Objects.requireNonNull(reportFolder)
		assert Files.exists(reportFolder)
		String prefix = reportFolder.getFileName().toString()
		String htmlFileName = prefix + ".html"
		Path htmlFile = reportFolder.resolve(htmlFileName)
		assert Files.exists(htmlFile)
		String pdfFileName = prefix + ".pdf"
		Path pdfFile = reportFolder.resolve(pdfFileName)
		if (Files.exists(pdfFile)) {
			Files.delete(pdfFile)
		}
		//
		Subprocess.CompletedProcess cp;
		cp = new Subprocess().cwd(reportFolder.toFile())
				.run(Arrays.asList("/usr/local/bin/wkhtmltopdf", "--debug-javascript",
				"--run-script", "\"console.log('starting')\"",
				"--run-script", "\"document.querySelector('h1').textContent='Hello, world'\"",
				"--run-script", "\"console.log('finished')\"",
				"--javascript-delay", "1000",
				htmlFileName, pdfFileName));
		assert 0 == cp.returncode()
		cp.stdout().each { line -> println line }
		cp.stderr().each { line -> println line }
	}
}
