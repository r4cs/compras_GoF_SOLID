// src/main/java/com/br/ecommerce/domain/OrderStatus.java
// package com.br.ecommerce.domain;

// public enum OrderStatus {
//     NEW,
//     PROCESSING,
//     SHIPPED,
//     DELIVERED,
//     CANCELLED
// }
// src/main/java/com/br/ecommerce/domain/OrderStatus.java
package com.br.ecommerce.domain;

public enum OrderStatus {
    NEW {
        @Override
        public OrderStatus next() {
            return PROCESSING;
        }
    },
    PROCESSING {
        @Override
        public OrderStatus next() {
            return SHIPPED;
        }
    },
    SHIPPED {
        @Override
        public OrderStatus next() {
            return DELIVERED;
        }
    },
    DELIVERED {
        @Override
        public OrderStatus next() {
            return null;
        }
    };

    public abstract OrderStatus next();
}