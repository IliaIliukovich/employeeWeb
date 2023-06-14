insert into employee (name, surname, age, id, job_role)
values ('Bob', 'Marley', 45, '351df916-a056-468a-8d23-43cd4ef242b6', 'MANAGER'),
       ('Bill', 'Gates', 33, 'c9a2a5db-b599-4ab8-ae8b-cc2e62db0df8', 'MANAGER'),
       ('Ivan', 'Ivanov', 23, '0a10496e-242d-4156-a849-64c76d99f23b', 'DEVELOPER'),
       ('Kate', 'Fox', 42, 'ab812e21-3fbe-4bb7-bc10-126460a2f397', 'DEVELOPER'),
       ('Mary', 'Werner', 51, '14472d05-23de-4285-8e93-d03e92aafc41', 'DEVELOPER'),
       ('Jane', 'Fox', 19, 'cf5a2182-e7e2-4947-955c-3a8608860c22', 'DEVELOPER'),
       ('Bob', 'Smiths', 59, 'c4d35240-6f87-4512-82d7-5acb9bcd9a72', 'DEVELOPER'),
       ('Peter', 'Fox', 22, 'e011c1bc-a971-4430-b6a4-88cac578ef30', 'GENERAL_STAFF'),
       ('Tommy', 'Arams', 42, '4bb23513-73bb-4285-abf4-914034515050', 'GENERAL_STAFF'),
       ('Peter', 'Smiths', 45, 'aa169339-fb76-40ca-8d44-22a43422e17c', 'GENERAL_STAFF');

-- In case of @Inheritance(strategy = InheritanceType.SINGLE_TABLE):
-- insert into people_in_company (name, surname, age,id, job_role, dtype)
-- values ('Bob', 'Marley', 45, '351df916-a056-468a-8d23-43cd4ef242b6', 'MANAGER', 'employee'),
--        ('Bill', 'Gates', 33, 'c9a2a5db-b599-4ab8-ae8b-cc2e62db0df8', 'DEVELOPER', 'contractor'),
--        ('Ivan', 'Ivanov', 23, '0a10496e-242d-4156-a849-64c76d99f23b', 'GENERAL_STAFF', 'employee');

insert into office (office_name, address)
values ('Headquarter', 'New York');

insert into project (name, description)
values ('Project A', 'First project to do');

insert into employeewebuser (username, password, role)
values ('admin', '$2a$10$1VrjKoROlEorStivD59L/uEML1U5ocM2KO0hFvEsANjtsaJZCVDjq', 'ROLE_ADMIN'),
       ('tom', '$2a$10$1VrjKoROlEorStivD59L/uEML1U5ocM2KO0hFvEsANjtsaJZCVDjq', 'ROLE_USER'),
       ('bob', '$2a$10$1VrjKoROlEorStivD59L/uEML1U5ocM2KO0hFvEsANjtsaJZCVDjq', 'ROLE_USER');