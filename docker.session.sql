CREATE TABLE Lembrete (
    ID_Lembrete INT PRIMARY KEY,
    Descricao VARCHAR(255),
    HoraLembrete TIME,
    ID_Evento INT,
    FOREIGN KEY (ID_Evento) REFERENCES Evento(ID_Evento)
);