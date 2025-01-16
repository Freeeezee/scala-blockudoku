package blockudoku.saving.serializerYAMLImpl.deserialize

import scala.collection.mutable

object Interpreter {

  def buildTree (data: List[KeyValuePair]): mutable.Map[String, Value] = {
    val tree = mutable.HashMap[String, Value]() 
    data.foreach(pair => {
      
      val key = pair.key
      val value = pair.interpretValue
      tree.put(key, value)
    })
    
    tree
  }
}
