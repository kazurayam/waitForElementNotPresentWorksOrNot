import static org.junit.Assert.*

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.apache.commons.io.FileUtils

import com.kazurayam.ks.reporting.TestSuiteReportConverter
import com.kms.katalon.core.configuration.RunConfiguration

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path fixtures = projectDir.resolve("Include/fixtures")
Path workDir = projectDir.resolve("build/tmp/htmltopdf/Reports")
Files.createDirectories(workDir)
FileUtils.copyDirectory(fixtures.resolve("Reports").toFile(), workDir.toFile())

String ts = "20230828_201457"
Path reportFolder = workDir.resolve("${ts}/TS2/${ts}")

TestSuiteReportConverter instance = new TestSuiteReportConverter()
instance.convertHtmlToPDF(reportFolder)

Path pdfFile = reportFolder.resolve("${ts}.pdf")
assertTrue(Files.exists(pdfFile))
