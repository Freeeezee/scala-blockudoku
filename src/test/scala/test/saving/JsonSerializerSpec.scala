package test.saving

class JsonSerializerSpec extends SavingSpec {
  override def onConfigureContainers(): Unit = {
    super.onConfigureContainers()
    
    includeJsonSerializer()
  }
  
  "JsonSerializer" should {
    "serialize and deserialize the game state correctly" in {
      testSaveLoad()
    }
  }
}
