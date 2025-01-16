package test.saving

import blockudoku.saving.SaveManager
import test.{GridProvider, StateMatcherSpec}

class SavingSpec extends StateMatcherSpec {
  override def onConfigureContainers(): Unit = {
    super.onConfigureContainers()
    
    includeDefaultConfig()
    includeControllers()
    includeSaveManagerWithoutSerializer()
  }
  
  def testSaveLoad(): Unit = {
    use(GridProvider.blockedGrid)

    val saveManager = provider.get[SaveManager]

    val beforeSaving = getCurrentState

    saveManager.save()

    use(GridProvider.emptyGrid())

    val beforeLoading = getCurrentState

    saveManager.load()

    val afterLoading = getCurrentState

    ensureStatesMatch(beforeSaving, afterLoading)
    ensureStatesDontMatch(afterLoading, beforeLoading)
  }
}
