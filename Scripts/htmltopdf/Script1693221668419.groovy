import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.SimpleFileVisitor

import com.kms.katalon.core.configuration.RunConfiguration

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path reports = projectDir.resolve("Reports")

List<Path> reportFolders = new ArrayList<Path>()

Files.walkFileTree(reports,
	new SimpleFileVisitor<Path>() {
		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			if ("Reports" == dir.getParent().getParent().getParent().getFileName().toString()) {
				reportFolders.add(dir)
			}
		}
	})

reportFolders.each { dir ->
	println dir
}