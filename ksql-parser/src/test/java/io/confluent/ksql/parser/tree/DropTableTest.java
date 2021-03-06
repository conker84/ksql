/*
 * Copyright 2019 Confluent Inc.
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

package io.confluent.ksql.parser.tree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.google.common.testing.EqualsTester;
import io.confluent.ksql.execution.expression.tree.QualifiedName;
import io.confluent.ksql.parser.NodeLocation;
import java.util.Optional;
import org.junit.Test;

public class DropTableTest {

  public static final NodeLocation SOME_LOCATION = new NodeLocation(0, 0);
  public static final NodeLocation OTHER_LOCATION = new NodeLocation(1, 0);
  private static final QualifiedName SOME_NAME = QualifiedName.of("bob");

  @Test
  public void shouldImplementHashCodeAndEqualsProperty() {
    new EqualsTester()
        .addEqualityGroup(
            // Note: At the moment location does not take part in equality testing
            new DropTable(SOME_NAME, true, true),
            new DropTable(SOME_NAME, true, true),
            new DropTable(Optional.of(SOME_LOCATION), SOME_NAME, true, true),
            new DropTable(Optional.of(OTHER_LOCATION), SOME_NAME, true, true)
        )
        .addEqualityGroup(
            new DropTable(QualifiedName.of("jim"), true, true)
        )
        .addEqualityGroup(
            new DropTable(SOME_NAME, false, true)
        )
        .addEqualityGroup(
            new DropTable(SOME_NAME, true, false)
        )
        .testEquals();
  }

  @Test
  public void shouldCopyWithoutDeleteTopic() {
    // Given:
    final DropTable table = new DropTable(SOME_NAME, true, true);

    // When:
    final DropTable result = (DropTable) table.withoutDeleteClause();

    // Then:
    assertThat(result, is(new DropTable(SOME_NAME, true, false)));
  }
}