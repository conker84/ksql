/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.util;

import io.confluent.ksql.GenericRow;
import io.confluent.ksql.schema.ksql.LogicalSchema;
import io.confluent.ksql.schema.ksql.PhysicalSchema;
import io.confluent.ksql.schema.ksql.types.SqlTypes;
import io.confluent.ksql.serde.SerdeOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ItemDataProvider extends TestDataProvider {

  private static final String namePrefix =
      "ITEM";

  private static final String ksqlSchemaString =
      "(ID varchar, DESCRIPTION varchar)";

  private static final String key = "ID";

  private static final LogicalSchema schema = LogicalSchema.builder()
      .valueColumn("ID", SqlTypes.STRING)
      .valueColumn("DESCRIPTION", SqlTypes.STRING)
      .build();

  private static final Map<String, GenericRow> data = buildData();

  public ItemDataProvider() {
    super(namePrefix, ksqlSchemaString, key, PhysicalSchema.from(schema, SerdeOption.none()), data);
  }

  private static Map<String, GenericRow> buildData() {

    final Map<String, GenericRow> dataMap = new HashMap<>();
    dataMap.put("ITEM_1", new GenericRow(Arrays.asList("ITEM_1",  "home cinema")));
    dataMap.put("ITEM_2", new GenericRow(Arrays.asList("ITEM_2",  "clock radio")));
    dataMap.put("ITEM_3", new GenericRow(Arrays.asList("ITEM_3",  "road bike")));
    dataMap.put("ITEM_4", new GenericRow(Arrays.asList("ITEM_4",  "mountain bike")));
    dataMap.put("ITEM_5", new GenericRow(Arrays.asList("ITEM_5",  "snowboard")));
    dataMap.put("ITEM_6", new GenericRow(Arrays.asList("ITEM_6",  "iphone 10")));
    dataMap.put("ITEM_7", new GenericRow(Arrays.asList("ITEM_7",  "gopro")));
    dataMap.put("ITEM_8", new GenericRow(Arrays.asList("ITEM_8",  "cat")));

    return dataMap;
  }

}
