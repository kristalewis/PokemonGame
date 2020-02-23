START TRANSACTION;

DROP TABLE IF EXISTS pokemonstrengths;
DROP TABLE IF EXISTS pokemonweaknesses;
DROP TABLE IF EXISTS pokemonmove;
DROP TABLE IF EXISTS pokemon;
DROP TABLE IF EXISTS move;
DROP TABLE IF EXISTS movetype;
DROP TABLE IF EXISTS element;

CREATE TABLE element (
     element_id serial NOT NULL,
     element_name varchar NOT NULL,
     CONSTRAINT pk_element_id PRIMARY KEY (element_id),
     CONSTRAINT check_element_name CHECK (element_name IN ('normal', 'fire', 'water', 'grass', 'electric',
        'flying', 'bug', 'ice', 'rock', 'ground', 'fighting', 'grass/poison'))
);

CREATE TABLE movetype (
     move_type_id serial NOT NULL,
     move_type varchar NOT NULL,
     CONSTRAINT pk_move_type_id PRIMARY KEY (move_type_id),
     CONSTRAINT check_move_type CHECK (move_type IN ('damage dealing', 'attack lowering', 'defense lowering'))
);

CREATE TABLE move (
    move_id serial NOT NULL,
    move_name varchar NOT NULL,
    move_element int NOT NULL,
    move_type int NOT NULL,
    base_damage int NOT NULL,
    CONSTRAINT pk_move_id PRIMARY KEY (move_id),
    CONSTRAINT fk_move_element FOREIGN KEY (move_element) REFERENCES element (element_id),
    CONSTRAINT fk_move_type FOREIGN KEY (move_type) REFERENCES movetype (move_type_id),
    CONSTRAINT check_base_damage CHECK (base_damage >= 0)
);

CREATE TABLE pokemon (
    pokemon_id serial NOT NULL,
    pokemon_name varchar NOT NULL,
    hP int NOT NULL,
    pokemon_type int NOT NULL,
    CONSTRAINT pk_pokemon_id PRIMARY KEY (pokemon_id),
    CONSTRAINT fk_pokemon_type FOREIGN KEY (pokemon_type) REFERENCES element (element_id),
    CONSTRAINT check_hP CHECK (hP >= 10)
);

CREATE TABLE pokemonmove (
    pokemon_id int NOT NULL,
    move_id int NOT NULL,
    CONSTRAINT fk_pokemon_id FOREIGN KEY (pokemon_id) REFERENCES pokemon (pokemon_id),
    CONSTRAINT fk_move_id FOREIGN KEY (move_id) REFERENCES move (move_id)
);

CREATE TABLE pokemonweaknesses (
    pokemon_id int NOT NULL,
    element_id int NOT NULL,
    CONSTRAINT fk_pokemonweaknesses_pokemon_id FOREIGN KEY (pokemon_id) REFERENCES pokemon (pokemon_id),
    CONSTRAINT fk_pokemonweaknesses_element_id FOREIGN KEY (element_id) REFERENCES element (element_id)
);

CREATE TABLE pokemonstrengths (
    pokemon_id int NOT NULL,
    element_id int NOT NULL,
    CONSTRAINT fk_pokemonstrengths_pokemon_id FOREIGN KEY (pokemon_id) REFERENCES pokemon (pokemon_id),
    CONSTRAINT fk_pokemonstrengths_element_id FOREIGN KEY (element_id) REFERENCES element (element_id)
);


COMMIT;



-- Creating element types table

INSERT INTO element (element_name) VALUES ('normal');
INSERT INTO element (element_name) VALUES ('fire');
INSERT INTO element (element_name) VALUES ('water');
INSERT INTO element (element_name) VALUES ('grass');
INSERT INTO element (element_name) VALUES ('grass/poison');
INSERT INTO element (element_name) VALUES ('electric');
INSERT INTO element (element_name) VALUES ('flying');
INSERT INTO element (element_name) VALUES ('ice');
INSERT INTO element (element_name) VALUES ('bug');
INSERT INTO element (element_name) VALUES ('ground');
INSERT INTO element (element_name) VALUES ('rock');
INSERT INTO element (element_name) VALUES ('fighting');

SELECT * FROM element;


-- Creating movetype Table

INSERT INTO movetype (move_type) VALUES ('damage dealing');
INSERT INTO movetype (move_type) VALUES ('attack lowering');
INSERT INTO movetype (move_type) VALUES ('defense lowering');

SELECT * FROM move;


-- Creating the moves that Bulbasaur, Squirtle, Charmander, Eevee, and Pikachu use

INSERT INTO move (move_name, move_element, move_type, base_damage)
VALUES ('Tackle', (SELECT element_id FROM element WHERE element_name = 'normal'), 
        (SELECT move_type_id FROM movetype WHERE move_type = 'damage dealing'), 5);

INSERT INTO move (move_name, move_element, move_type, base_damage)
VALUES ('Growl', (SELECT element_id FROM element WHERE element_name = 'normal'), 
        (SELECT move_type_id FROM movetype WHERE move_type = 'attack lowering'), 0);

INSERT INTO move (move_name, move_element, move_type, base_damage)
VALUES ('Leech Seed', (SELECT element_id FROM element WHERE element_name = 'grass'), 
        (SELECT move_type_id FROM movetype WHERE move_type = 'damage dealing'), 2);

INSERT INTO move (move_name, move_element, move_type, base_damage)
VALUES ('Vine Whip', (SELECT element_id FROM element WHERE element_name = 'grass'), 
        (SELECT move_type_id FROM movetype WHERE move_type = 'damage dealing'), 7);

INSERT INTO move (move_name, move_element, move_type, base_damage)
VALUES ('Scratch', (SELECT element_id FROM element WHERE element_name = 'normal'), 
        (SELECT move_type_id FROM movetype WHERE move_type = 'damage dealing'), 5);

INSERT INTO move (move_name, move_element, move_type, base_damage)
VALUES ('Ember', (SELECT element_id FROM element WHERE element_name = 'fire'), 
        (SELECT move_type_id FROM movetype WHERE move_type = 'damage dealing'), 7);

INSERT INTO move (move_name, move_element, move_type, base_damage)
VALUES ('Leer', (SELECT element_id FROM element WHERE element_name = 'normal'), 
        (SELECT move_type_id FROM movetype WHERE move_type = 'defense lowering'), 0);

INSERT INTO move (move_name, move_element, move_type, base_damage)
VALUES ('Tail Whip', (SELECT element_id FROM element WHERE element_name = 'normal'),
        (SELECT move_type_id FROM movetype WHERE move_type = 'defense lowering'), 0);

INSERT INTO move (move_name, move_element, move_type, base_damage)
VALUES ('Bubble', (SELECT element_id FROM element WHERE element_name = 'water'), 
        (SELECT move_type_id FROM movetype WHERE move_type = 'damage dealing'), 7);

INSERT INTO move (move_name, move_element, move_type, base_damage)
VALUES ('Water Gun', (SELECT element_id FROM element WHERE element_name = 'water'), 
        (SELECT move_type_id FROM movetype WHERE move_type = 'damage dealing'), 8);

INSERT INTO move (move_name, move_element, move_type, base_damage)
VALUES ('Quick Attack', (SELECT element_id FROM element WHERE element_name = 'normal'),
        (SELECT move_type_id FROM movetype WHERE move_type = 'damage dealing'),  6);

INSERT INTO move (move_name, move_element, move_type, base_damage)
VALUES ('Thundershock', (SELECT element_id FROM element WHERE element_name = 'electric'), 
        (SELECT move_type_id FROM movetype WHERE move_type = 'damage dealing'), 7);

SELECT * FROM move;




-- Creating the pokemon (Bulbasaur, Squirtle, Charmander, Eevee, and Pikachu)

INSERT INTO pokemon (pokemon_name, hP, pokemon_type)
VALUES ('Bulbasaur', 24, (SELECT element_id FROM element WHERE element_name  = 'grass/poison'));

INSERT INTO pokemon (pokemon_name, hP, pokemon_type)
VALUES ('Squirtle', 24, (SELECT element_id FROM element WHERE element_name = 'water'));

INSERT INTO pokemon (pokemon_name, hP, pokemon_type)
VALUES ('Charmander', 23, (SELECT element_id FROM element WHERE element_name = 'fire'));

INSERT INTO pokemon (pokemon_name, hP, pokemon_type)
VALUES ('Eevee', 24, (SELECT element_id FROM element WHERE element_name = 'normal'));

INSERT INTO pokemon (pokemon_name, hP, pokemon_type)
VALUES ('Pikachu', 23, (SELECT element_id FROM element WHERE element_name = 'electric'));

SELECT * FROM pokemon;




-- Creating pokemon_move joiner table

-- Making Bulbasaur Moves
INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Bulbasaur'),
        (SELECT move_id FROM move WHERE move_name = 'Tackle'));

INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Bulbasaur'),
        (SELECT move_id FROM move WHERE move_name = 'Growl')); 

INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Bulbasaur'),
        (SELECT move_id FROM move WHERE move_name = 'Leech Seed'));  

INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Bulbasaur'),
        (SELECT move_id FROM move WHERE move_name = 'Vine Whip')); 


-- Making Squirtle Moves
INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Squirtle'),
        (SELECT move_id FROM move WHERE move_name = 'Tackle'));

INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Squirtle'),
        (SELECT move_id FROM move WHERE move_name = 'Tail Whip'));

INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Squirtle'),
        (SELECT move_id FROM move WHERE move_name = 'Bubble'));

INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Squirtle'),
        (SELECT move_id FROM move WHERE move_name = 'Water Gun'));  


-- Making Charmander Moves
INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Charmander'),
        (SELECT move_id FROM move WHERE move_name = 'Growl')); 

INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Charmander'),
        (SELECT move_id FROM move WHERE move_name = 'Scratch')); 

INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Charmander'),
        (SELECT move_id FROM move WHERE move_name = 'Ember')); 

INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Charmander'),
        (SELECT move_id FROM move WHERE move_name = 'Leer'));


-- Making Eevee Moves
INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Eevee'),
        (SELECT move_id FROM move WHERE move_name = 'Tackle')); 

INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Eevee'),
        (SELECT move_id FROM move WHERE move_name = 'Tail Whip')); 

INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Eevee'),
        (SELECT move_id FROM move WHERE move_name = 'Quick Attack')); 

INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Eevee'),
        (SELECT move_id FROM move WHERE move_name = 'Growl')); 

        
-- Making Pikachu Moves
INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Pikachu'),
        (SELECT move_id FROM move WHERE move_name  = 'Thundershock')); 

INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Pikachu'),
        (SELECT move_id FROM move WHERE move_name  = 'Growl')); 

INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Pikachu'),
        (SELECT move_id FROM move WHERE move_name  = 'Tail Whip')); 

INSERT INTO pokemonmove (pokemon_id, move_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Pikachu'),
        (SELECT move_id FROM move WHERE move_name  = 'Quick Attack')); 

SELECT * FROM pokemonmove;



-- Creating pokemon weaknesses

-- Creating Bulbasaur weaknesses
INSERT INTO pokemonweaknesses (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Bulbasaur'),
        (SELECT element_id FROM element WHERE element_name = 'fire'));

INSERT INTO pokemonweaknesses (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Bulbasaur'),
        (SELECT element_id FROM element WHERE element_name = 'ice'));

INSERT INTO pokemonweaknesses (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Bulbasaur'),
        (SELECT element_id FROM element WHERE element_name = 'flying'));

INSERT INTO pokemonweaknesses (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Bulbasaur'),
        (SELECT element_id FROM element WHERE element_name = 'bug'));

-- Creating Squirtle weaknesses
INSERT INTO pokemonweaknesses (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Squirtle'),
        (SELECT element_id FROM element WHERE element_name = 'electric'));

INSERT INTO pokemonweaknesses (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Squirtle'),
        (SELECT element_id FROM element WHERE element_name = 'grass'));

-- Creating Charmander weaknesses
INSERT INTO pokemonweaknesses (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Charmander'),
        (SELECT element_id FROM element WHERE element_name = 'water'));

INSERT INTO pokemonweaknesses (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Charmander'),
        (SELECT element_id FROM element WHERE element_name = 'rock'));

INSERT INTO pokemonweaknesses (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Charmander'),
        (SELECT element_id FROM element WHERE element_name = 'ground'));

-- Creating Eevee weaknesses
INSERT INTO pokemonweaknesses (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Eevee'),
        (SELECT element_id FROM element WHERE element_name = 'fighting'));

-- Creating Pikachu weaknesses
INSERT INTO pokemonweaknesses (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Pikachu'),
        (SELECT element_id FROM element WHERE element_name = 'ground'));



-- Creating pokemon strengths

-- Creating Bulbasaur strengths
INSERT INTO pokemonstrengths (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Bulbasaur'),
        (SELECT element_id FROM element WHERE element_name = 'water'));

INSERT INTO pokemonstrengths (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Bulbasaur'),
        (SELECT element_id FROM element WHERE element_name = 'grass'));

INSERT INTO pokemonstrengths (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Bulbasaur'),
        (SELECT element_id FROM element WHERE element_name = 'electric'));

INSERT INTO pokemonstrengths (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Bulbasaur'),
        (SELECT element_id FROM element WHERE element_name = 'ground'));

-- Creating Squirtle strengths
INSERT INTO pokemonstrengths (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Squirtle'),
        (SELECT element_id FROM element WHERE element_name = 'water'));

INSERT INTO pokemonstrengths (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Squirtle'),
        (SELECT element_id FROM element WHERE element_name = 'fire'));

INSERT INTO pokemonstrengths (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Squirtle'),
        (SELECT element_id FROM element WHERE element_name = 'ice'));

-- Creating Charmander strengths
INSERT INTO pokemonstrengths (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Charmander'),
        (SELECT element_id FROM element WHERE element_name = 'fire'));

INSERT INTO pokemonstrengths (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Charmander'),
        (SELECT element_id FROM element WHERE element_name = 'grass'));

INSERT INTO pokemonstrengths (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Charmander'),
        (SELECT element_id FROM element WHERE element_name = 'ice'));

INSERT INTO pokemonstrengths (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Charmander'),
        (SELECT element_id FROM element WHERE element_name = 'bug'));

-- Creating Eevee strengths (Eevee has none)

-- Creating Pikachu strengths
INSERT INTO pokemonstrengths (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Pikachu'),
        (SELECT element_id FROM element WHERE element_name = 'electric'));

INSERT INTO pokemonstrengths (pokemon_id, element_id)
VALUES ((SELECT pokemon_id FROM pokemon WHERE pokemon_name = 'Pikachu'),
        (SELECT element_id FROM element WHERE element_name = 'flying'));

SELECT * FROM pokemonstrengths;


