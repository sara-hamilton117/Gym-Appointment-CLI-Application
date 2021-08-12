--Queries

--Adding a booking
INSERT INTO booking VALUES
(DEFAULT, 'ow001', '001ot', '2019-12-29', '12:30:00', 60, 'sp01');

--Updating a booking
SELECT * FROM booking;

UPDATE booking
SET booking_time = '13:30:00'
WHERE booking_id = 9;

--Deleting a booking
SELECT * FROM Booking;

DELETE FROM booking
WHERE Booking_id = 9;

--Listing bookings:

--For a given PT
SELECT * FROM personal_trainer;

SELECT * FROM booking
WHERE pt_id = '001jw';

--For a given client
SELECT * FROM client;

SELECT * FROM booking
WHERE client_id = 'es001';

--For a specific date
SELECT * FROM booking
WHERE booking_date LIKE '2019-12-%';
