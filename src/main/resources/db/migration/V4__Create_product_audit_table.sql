CREATE TABLE product_audit (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product VARCHAR(255) ,
    operation_type VARCHAR(50),
    modified_by VARCHAR(255) ,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);