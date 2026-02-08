INSERT INTO vehicles(type, brand, model, price_per_day, seats, awd)
VALUES
('CAR','Toyota','Camry', 25000.00, 5, NULL),
('SUV','Hyundai','Tucson', 32000.00, NULL, TRUE)
ON CONFLICT DO NOTHING;

INSERT INTO customers(full_name, phone)
VALUES
('Aikyn Student', '+7 777 000 00 00')
ON CONFLICT DO NOTHING;
