ALTER TABLE
    medicos
MODIFY COLUMN
    especialidade ENUM(
        'ORTOPEDIA',
        'CARDIOLOGIA',
        'GINECOLOGIA',
        'DERMATOLOGIA',
        'PSIQUIATRIA',
        'PEDIATRIA',
        'GASTROENTEROLOGIA',
        'ENDOCRINOLOGIA'
    );
