// TODO(reto): archivo stubbeado; implementa los cuerpos.
package com.pragma.integration.questions;

import com.pragma.integration.abilities.DynamoDBClientAbility;
import com.pragma.integration.models.Order;
import com.pragma.integration.tasks.ReadOrderFromDynamoDB;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class OrderPersistenceQuestions {

    public static Question<Order> orderWithId(String orderId) {
      throw new UnsupportedOperationException("TODO(reto): orderWithId");
    }

    public static Question<Boolean> orderExists(String orderId) {
      throw new UnsupportedOperationException("TODO(reto): orderExists");
    }

    private static class OrderByIdQuestion implements Question<Order> {
        private final String orderId;

        public OrderByIdQuestion(String orderId) {
            this.orderId = orderId;
        }

        @Override
        public Order answeredBy(Actor actor) {
          throw new UnsupportedOperationException("TODO(reto): answeredBy");
        }
    }

    private static class OrderExistsQuestion implements Question<Boolean> {
        private final String orderId;

        public OrderExistsQuestion(String orderId) {
            this.orderId = orderId;
        }

        @Override
        public Boolean answeredBy(Actor actor) {
          throw new UnsupportedOperationException("TODO(reto): answeredBy");
        }
    }
}