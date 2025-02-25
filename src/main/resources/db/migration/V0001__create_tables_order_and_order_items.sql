CREATE TABLE orders (
    id UUID PRIMARY KEY,
    external_id VARCHAR(255) UNIQUE NOT NULL,
    total_value DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'RECEIVED',
    created_at TIMESTAMP DEFAULT now()
);

CREATE INDEX idx_orders_external_id ON orders (external_id);

CREATE TABLE order_item (
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    product_code VARCHAR(100) NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    created_at TIMESTAMP DEFAULT now()
);


