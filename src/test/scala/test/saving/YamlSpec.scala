package test.saving

import blockudoku.saving.serializerYAMLImpl.deserialize.YamlParser
import blockudoku.saving.serializerYAMLImpl.serialize.{ArrayValue, KeyValuePair, ObjectValue, StringValue}
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
          |      key12:
          |        key17: -value17
          |        key18: value18
          |      key13:
          |        - value13
          |        - value14
          |        - value15
          |  - value11
          |""".stripMargin

      val yaml = YamlParser.parse(str)
      print(yaml)
    }
  }
  "A YAML serializer" should {
    "serialize a given object" in {
      val keyValuePair = KeyValuePair("key1", StringValue("value1"))
      val keyValuePair2 = KeyValuePair("key2", StringValue("value2"))

      val obj2 = ObjectValue(List(KeyValuePair("key4", StringValue("value4")), KeyValuePair("key5", StringValue("value5"))))

      val array = ArrayValue(List(StringValue("ArrayContent1"), StringValue("ArrayContent2"), KeyValuePair("object", obj2)))

      val keyValuePair3 = KeyValuePair("key3", array)

      val obj = ObjectValue(List(keyValuePair, keyValuePair2, keyValuePair3))

      val lastKV = KeyValuePair("key6", obj)

      print(lastKV.serialize())
    }
  }
}

