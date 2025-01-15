package blockudoku.saving.saveManagerImpl

import blockudoku.saving.{PersistentStore, SaveManager, Serializer}

class SaveManagerImpl(serializer: Serializer, persistentStore: PersistentStore) extends SaveManager {
  
  override def save(): Unit = {
    val saveState = serializer.serialize()
    persistentStore.store(saveState)
  }
  
  override def load(): Unit = {
    val saveState = persistentStore.load()
    serializer.deserialize(saveState)
  }
}
