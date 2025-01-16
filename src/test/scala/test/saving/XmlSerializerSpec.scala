package test.saving

class XmlSerializerSpec extends SavingSpec {
  override def onConfigureContainers(): Unit = {
    super.onConfigureContainers()
    
    includeXmlSerializer()
  }
  
  "XmlSerializer" should {
    "serialize and deserialize the game state correctly" in {
      testSaveLoad()
    }
  }
}
