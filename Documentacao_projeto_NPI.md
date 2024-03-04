# Requisitos do Aplicativo de Calendário

## 1. Gerenciamento de Usuários
- O aplicativo deve permitir que os usuários se registrem e façam login.
- Os usuários devem ser capazes de atualizar suas informações de perfil.

## 2. Criação e Gerenciamento de Eventos
- Os usuários devem ser capazes de criar, visualizar, atualizar e excluir eventos.
- Os eventos devem ter um título, uma data e uma descrição.

## 3. Criação e Gerenciamento de Lembretes
- Os usuários devem ser capazes de adicionar lembretes aos eventos.
- Os lembretes devem ter uma descrição e uma hora para serem acionados.

# Arquitetura
![Alt Text](/Banco%20de%20dados.png)
# Diagrama de Banco de Dados

## Usuário
- **ID do Usuário** (PK)
- Nome
- Email
- Senha

## Evento
- **ID do Evento** (PK)
- Título do Evento
- Data do Evento
- ID do Usuário (FK)

## Lembrete
- **ID do Lembrete** (PK)
- Descrição
- Hora do Lembrete
- ID do Evento (FK)

-----

```
CREATE TABLE Usuario (
    ID_Usuario INT PRIMARY KEY,
    Nome VARCHAR(255),
    Email VARCHAR(255),
    Senha VARCHAR(255)
);

CREATE TABLE Evento (
    ID_Evento INT PRIMARY KEY,
    TituloEvento VARCHAR(255),
    DataEvento DATE,
    ID_Usuario INT,
    FOREIGN KEY (ID_Usuario) REFERENCES Usuario(ID_Usuario)
);

CREATE TABLE Lembrete (
    ID_Lembrete INT PRIMARY KEY,
    Descricao VARCHAR(255),
    HoraLembrete TIME,
    ID_Evento INT,
    FOREIGN KEY (ID_Evento) REFERENCES Evento(ID_Evento)
);
```
---
 
# Diagrama de classes

![Alt Text](/Diagrama%20de%20classes.png)