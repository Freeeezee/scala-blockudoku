package test.saving

import blockudoku.saving.serializerYAMLImpl.deserialize.YamlParser
import test.UnitSpec

class YamlSpec extends UnitSpec {
  "A YAML parser" should {
    "parse a simple string" in {
      val str =
        """|key1: value1
          |key2: value2
          |key3:
          |  key4: value4
          |  key5: value5
          |  key7:
          |    key8: value8
          |key6: value6
          |key9:
          |  - value9
          |  - key10:
          |      key11: value11
          |      key12: value12
          |  - value11
          |""".stripMargin

      val yaml = YamlParser.parse(str)
    }
  }
}

