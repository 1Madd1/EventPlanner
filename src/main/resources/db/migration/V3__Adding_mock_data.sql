-- Insert Guests
INSERT INTO guest (id, name, email, phone_number) VALUES
('11111111-1111-1111-1111-111111111111', 'Alice', 'alice@example.com', '123456789'),
('22222222-2222-2222-2222-222222222222', 'Bob', 'bob@example.com', '234567891'),
('33333333-3333-3333-3333-333333333333', 'Charlie', 'charlie@example.com', '345678912'),
('44444444-4444-4444-4444-444444444444', 'Diana', 'diana@example.com', '456789123'),
('55555555-5555-5555-5555-555555555555', 'Eve', 'eve@example.com', '567891234'),
('66666666-6666-6666-6666-666666666666', 'Frank', 'frank@example.com', '678912345'),
('77777777-7777-7777-7777-777777777777', 'Grace', 'grace@example.com', '789123456');


INSERT INTO event (id, title, theme, status, location, date_of_event) VALUES
('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'Retro Vibes', '_8RN', 'COMPLETED', 'Ballroom A', NOW() - INTERVAL '40 days'),
('aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'Black Elegance', 'BTG', 'COMPLETED', 'Ballroom B', NOW() - INTERVAL '30 days'),
('aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3', 'Masquerade Madness', 'MB', 'COMPLETED', 'Ballroom C', NOW() - INTERVAL '20 days'),
('aaaaaaa4-aaaa-aaaa-aaaa-aaaaaaaaaaa4', 'Future Bash', 'FB', 'COMPLETED', 'Ballroom D', NOW() - INTERVAL '10 days'),
('bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 'Neo Gala', 'FB', 'UPCOMING', 'Ballroom A', NOW() + INTERVAL '5 days'),
('bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 'Black Night', 'BTG', 'UPCOMING', 'Ballroom B', NOW() + INTERVAL '7 days'),
('bbbbbbb3-bbbb-bbbb-bbbb-bbbbbbbbbbb3', 'Mystery Fest', 'MB', 'UPCOMING', 'Ballroom C', NOW() + INTERVAL '9 days');


INSERT INTO event_guest (id, event_id, guest_id, attending, attended) VALUES
(gen_random_uuid(), 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', '11111111-1111-1111-1111-111111111111', true, true),
(gen_random_uuid(), 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', '11111111-1111-1111-1111-111111111111', true, true),
(gen_random_uuid(), 'aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3', '11111111-1111-1111-1111-111111111111', true, true),
(gen_random_uuid(), 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', '22222222-2222-2222-2222-222222222222', true, true),
(gen_random_uuid(), 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', '22222222-2222-2222-2222-222222222222', true, true),
(gen_random_uuid(), 'aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3', '22222222-2222-2222-2222-222222222222', true, true),
(gen_random_uuid(), 'aaaaaaa4-aaaa-aaaa-aaaa-aaaaaaaaaaa4', '22222222-2222-2222-2222-222222222222', true, true),
(gen_random_uuid(), 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', '33333333-3333-3333-3333-333333333333', true, true),
(gen_random_uuid(), 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', '33333333-3333-3333-3333-333333333333', true, true),
(gen_random_uuid(), 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', '44444444-4444-4444-4444-444444444444', true, false),
(gen_random_uuid(), 'aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3', '44444444-4444-4444-4444-444444444444', true, false),
(gen_random_uuid(), 'aaaaaaa4-aaaa-aaaa-aaaa-aaaaaaaaaaa4', '44444444-4444-4444-4444-444444444444', true, true),
(gen_random_uuid(), 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', '55555555-5555-5555-5555-555555555555', true, false),
(gen_random_uuid(), 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', '55555555-5555-5555-5555-555555555555', true, false),
(gen_random_uuid(), 'aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa3', '55555555-5555-5555-5555-555555555555', true, true),
(gen_random_uuid(), 'bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1', '11111111-1111-1111-1111-111111111111', true, false),
(gen_random_uuid(), 'bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2', '22222222-2222-2222-2222-222222222222', true, false),
(gen_random_uuid(), 'bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2', '33333333-3333-3333-3333-333333333333', true, false),
(gen_random_uuid(), 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', '77777777-7777-7777-7777-777777777777', true, true),
(gen_random_uuid(), 'bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2', '77777777-7777-7777-7777-777777777777', true, true);