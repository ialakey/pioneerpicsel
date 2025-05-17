INSERT INTO user_info (id, name, date_of_birth, password) VALUES
  (1, 'Alice', '1990-01-01', '$2a$10$ViAX.pCCwdJTIixZmEjo.ufAlLYlhKdGSBCG.1GLfyNNAk0fi3pSi'), -- password: 123456
  (2, 'Bob', '1985-05-05', '$2a$10$ViAX.pCCwdJTIixZmEjo.ufAlLYlhKdGSBCG.1GLfyNNAk0fi3pSi');

INSERT INTO email_data (email, user_info_id) VALUES
  ('alice@example.com', 1),
  ('bob@example.com', 2);

INSERT INTO phone_data (phone, user_info_id) VALUES
  ('+1234567890', 1),
  ('+0987654321', 2);

INSERT INTO account (user_info_id, balance, initial_balance) VALUES
  (1, 1000.00, 1000.00),
  (2, 500.00, 500.00);
