INSERT INTO accounts (version, id, account_number, name, account_type, balance) VALUES (1, 1, '9159070bb5594c76aff7661b01f44e00', 'Savings Account (A)', 'SAVINGS', 100.00);
INSERT INTO accounts (version, id, account_number, name, account_type, balance) VALUES (1, 2, '9159070bb5594c76aff7661b01f44e01', 'Goals Account (B)', 'GOALS', 0.00);
--INSERT INTO accounts (version, id, account_number, name, account_type, balance) VALUES (1, 3, '000003', 'Investment Account (C)', 'INVESTMENT', 0.00);

ALTER sequence HIBERNATE_SEQUENCE RESTART WITH 3