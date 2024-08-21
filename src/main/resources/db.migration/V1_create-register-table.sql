CREATE TABLE usuario {
    id INT PRIMARY KEY UNIQUE NOT NULL,
    nome TEXT NOT NULL,
    sobrenome TEXT NOT NULL,
    estado TEXT NOT NULL,
    cidade TEXT NOT NULL,
    bairro TEXT NOT NULL,
    rua TEXT NOT NULL,
    cep TEXT NOT NULL,
    numero TEXT NOT NULL,
    telefone TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    senha TEXT NOT NULL,
    role TEXT NOT NULL
};


