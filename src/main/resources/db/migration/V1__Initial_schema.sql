create table if not exists public.event(
    id UUID not null,
    theme VARCHAR(50) not null,
    status VARCHAR(50) not null,
    location VARCHAR(50) not null,
    date_of_event TIMESTAMP not null,
    primary key (id)
);

create table if not exists public.guest(
    id UUID not null,
    name VARCHAR(50) not null,
    email VARCHAR(50) not null,
    phone_number VARCHAR(50) not null,
    primary key (id)
);

create table if not exists public.event_guest(
    id UUID not null,
    event_id UUID not null,
    guest_id UUID not null,
    attending BOOLEAN not null,
    attended BOOLEAN not null,
    primary key (id),
    UNIQUE (event_id, guest_id),
    foreign key (event_id) references public.event (id),
    foreign key (guest_id) references public.guest (id)
);