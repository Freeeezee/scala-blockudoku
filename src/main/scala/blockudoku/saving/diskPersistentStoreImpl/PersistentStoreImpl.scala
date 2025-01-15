package blockudoku.saving.diskPersistentStoreImpl

import blockudoku.saving.PersistentStore
import net.harawata.appdirs.AppDirsFactory

import java.io.*
import java.nio.file.{Files, Paths}
import scala.io.Source


class PersistentStoreImpl extends PersistentStore {

  private val appDirs = AppDirsFactory.getInstance()
  private val path = appDirs.getUserDataDir("Blockudoku", null, "Blockudoku") + "/save.block"

  val dirPath = Paths.get(path)
  if (!Files.exists(dirPath)) {
    Files.createDirectories(dirPath)
  }
  
  def store(string: String): Unit = {
    val pw = new PrintWriter(new File(path))
    pw.write(string)
    pw.close()
  }

  def load(): String = {
    val source = Source.fromFile(path)
    val str = source.mkString
    source.close()
    str
  }
}
