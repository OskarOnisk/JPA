insert into address (id, address_line1, address_line2, city, postal_code)
            values (901, 'xx', 'yy', 'Wro', '60-400');

insert into address (id, address_line1, address_line2, city, postal_code)
values (902, 'ee', 'uu', 'Wro', '30-200');

insert into address (id, address_line1, address_line2, city, postal_code)
values (903, 'ef', 'uu', 'Wro', '30-200');

insert into patient ( first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id)
values ( 'Jan', 'Kowalski', '123456789', 'jan@kowal.pl', 'P001', '1980-01-01', 901);

insert into doctor ( first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
values ( 'Anna', 'Nowak', '987654321', 'anna@nowak.pl', 'D001', 'GP', 902);

insert into doctor ( first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
values ( 'John', 'Nowak', '987654322', 'john@nowak.pl', 'D002', 'SURGEON', 903);

insert into visit ( description, time, patient_id, doctor_id)
values ( 'Pierwsza wizyta', '2026-02-16T10:00:00', 1, 1);

insert into visit ( description, time, patient_id, doctor_id)
values ( 'Druga wizyta', '2026-03-16T10:00:00', 1, 1);

insert into medical_treatment ( description, type, visit_id)
values ( 'Usg', 'USG', 1);

insert into medical_treatment ( description, type, visit_id)
values ('Ekg', 'EKG', 2);


