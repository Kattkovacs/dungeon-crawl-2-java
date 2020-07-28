ALTER TABLE IF EXISTS ONLY public.player DROP CONSTRAINT IF EXISTS player_pk CASCADE;
ALTER TABLE IF EXISTS ONLY public.game_state DROP CONSTRAINT IF EXISTS game_state_pk CASCADE;
ALTER TABLE IF EXISTS ONLY public.game_state DROP CONSTRAINT IF EXISTS player_id_fk CASCADE;
ALTER TABLE IF EXISTS ONLY public.map DROP CONSTRAINT IF EXISTS state_id_fk CASCADE;
ALTER TABLE IF EXISTS ONLY public.cell DROP CONSTRAINT IF EXISTS state_id_fk CASCADE;
ALTER TABLE IF EXISTS ONLY public.items DROP CONSTRAINT IF EXISTS state_id_fk CASCADE;

DROP TABLE IF EXISTS public.player;
CREATE TABLE public.player (
    id serial NOT NULL
        constraint player_pk
			primary key,
    player_name text NOT NULL,
    hp integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL
);

DROP TABLE IF EXISTS public.game_state;
CREATE TABLE public.game_state (
    id serial NOT NULL
        constraint game_state_pk
			primary key,
    current_map int NOT NULL,
    saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    player_id integer NOT NULL
        constraint player_id_fk
            references player
                on delete cascade
);

DROP TABLE IF EXISTS public.map;
CREATE TABLE public.map (
    id serial NOT NULL PRIMARY KEY,
    state_id int NOT NULL
        constraint state_id_fk
            references game_state
                on delete cascade,
    width int NOT NULL,
    height int NOT NULL,
    style int NOT NULL
);

DROP TABLE IF EXISTS public.cell;
CREATE TABLE public.cell (
    id serial NOT NULL PRIMARY KEY,
    state_id int NOT NULL
        constraint state_id_fk
            references game_state
                on delete cascade,
    x int NOT NULL,
    y int NOT NULL,
    actor text,
    item text,
    cell_type text NOT NULL
);

DROP TABLE IF EXISTS public.items;
CREATE TABLE public.items (
    id serial NOT NULL PRIMARY KEY,
    state_id int NOT NULL
        constraint state_id_fk
            references game_state
                on delete cascade,
    name text NOT NULL,
    count int NOT NULL
);