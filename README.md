# Jogo da Selva - Padrões de Projeto

O jogo da Selva (*Jungle*) é um jogo de tabuleiro inspirado no xadrez, onde animais com diferentes hierarquias se enfrentam. Neste projeto, utilizamos os padrões de projeto **Decorator** e **Template Method** para estruturar as classes dos animais e seus comportamentos.

---

## 1. Padrão Decorator

### Intenção do Padrão (conforme Gama)
O padrão **Decorator** permite estender o comportamento de objetos de maneira flexível e dinâmica, sem alterar as classes base, adicionando funcionalidades de forma transparente e modular.

### Motivação
No jogo da Selva, cada animal possui características específicas que podem variar conforme o tipo de animal (por exemplo, o Leão pode pular sobre a água, enquanto o Elefante não pode capturar o Rato). A implementação direta dessas características resultaria em um código rígido e de difícil manutenção. O padrão **Decorator** permite modularizar essas variações sem modificar diretamente as classes dos animais.

### Cenário sem o Padrão
Sem o uso do padrão **Decorator**, as funcionalidades específicas dos animais seriam implementadas diretamente nas classes concretas. Isso resultaria em:
- Código rígido e difícil de manter;
- Necessidade de modificar as classes base para adicionar novas funcionalidades;
- Baixa reutilização de código.

#### Classes Envolvidas
- `GamePiece` (interface)
- `JungleAnimal` (classe base para todos os animais)
- `Elephant`, `Lion`, `Tiger` (classes concretas com comportamento específico)

#### UML sem o Padrão
```mermaid
classDiagram
    class GamePiece {
        +PieceType getType()
        +Player getOwner()
        +Position getInitialPosition()
        +boolean canMoveTo(Board, Position, Position)
        +boolean canCapture(GamePiece)
    }

    class JungleAnimal {
        +JungleAnimalType type
        +Player owner
        +Position initialPosition
    }

    class Elephant {
        +boolean canCapture(GamePiece)
    }

    class Lion {
        +boolean canMoveTo(Board, Position, Position)
        +boolean canJumpOverWater(Board, Position, Position)
    }

    class Tiger {
        +Herda comportamento do Lion
    }

    GamePiece <|-- JungleAnimal
    JungleAnimal <|-- Elephant
    JungleAnimal <|-- Lion
    Lion <|-- Tiger
```

### Estrutura do Padrão
Com a aplicação do padrão **Decorator**, criamos classes específicas para modificar o comportamento dos animais sem alterar suas classes base.

#### UML com o Padrão Decorator
```mermaid
classDiagram
    class GamePiece
    class JungleAnimal
    class AnimalDecorator
    class Elephant
    class Lion
    class Tiger
    class JumpOverWater
    class StrongCapture

    GamePiece <|-- JungleAnimal
    JungleAnimal <|-- AnimalDecorator
    AnimalDecorator <|-- Elephant
    AnimalDecorator <|-- Lion
    Lion <|-- Tiger
    AnimalDecorator <|-- JumpOverWater
    AnimalDecorator <|-- StrongCapture
```

### Cenário com o Padrão
Com a introdução do **Decorator**, as habilidades especiais dos animais são separadas em classes decoradoras. Por exemplo:
- `JumpOverWater` pode ser adicionado ao `Lion` e `Tiger` sem modificar suas classes;
- `StrongCapture` pode ser usado para definir regras específicas de captura sem alterar a classe base dos animais.

### Participantes
| GOF               | Implementação no Jogo                |
|------------------|--------------------------------------|
| **Component**     | `JungleAnimal` (interface base) |
| **ConcreteComponent** | `Elephant`, `Lion`, `Tiger` (animais específicos) |
| **Decorator**     | `AnimalDecorator` (base para decoradores) |
| **ConcreteDecorator** | `JumpOverWater`, `StrongCapture` (habilidades adicionadas) |

---

## 2. Padrão Template Method

### Intenção do Padrão (conforme Gama)
O padrão **Template Method** define a estrutura de um algoritmo na superclasse, permitindo que subclasses sobrescrevam etapas específicas sem modificar a estrutura do algoritmo.

### Motivação
No jogo da Selva, muitos animais compartilham comportamentos similares, como movimentação e captura, mas com pequenas variações. O padrão **Template Method** permite que a lógica geral seja definida na classe abstrata `JungleAnimal`, enquanto os detalhes específicos são sobrescritos nas subclasses.

### Cenário sem o Padrão
Sem o uso do **Template Method**, cada animal precisaria implementar totalmente seus métodos de movimentação e captura, resultando em:
- Duplicação de código;
- Dificuldade de manutenção;
- Baixa reutilização de lógica comum.

#### Classes Envolvidas
- `JungleAnimal` (classe base com métodos genéricos)
- `Elephant`, `Lion`, `Tiger` (subclasses que implementam variações)

#### UML sem o Padrão
```mermaid
classDiagram
    class JungleAnimal {
        +boolean canMoveTo(Board, Position, Position)
        +boolean canCapture(GamePiece)
    }

    class Elephant {
        +Sobrescreve canCapture()
    }

    class Lion {
        +Sobrescreve canMoveTo()
        +Adiciona canJumpOverWater()
    }

    class Tiger {
        +Herda comportamento do Lion
    }

    JungleAnimal <|-- Elephant
    JungleAnimal <|-- Lion
    Lion <|-- Tiger
```

### Estrutura do Padrão
O padrão **Template Method** extrai a lógica comum para a classe base `JungleAnimal`, enquanto subclasses sobrescrevem apenas partes específicas.

#### UML com o Padrão Template Method
```mermaid
classDiagram
    class JungleAnimal {
        +boolean canMoveTo(Board, Position, Position)
        +boolean canCapture(GamePiece)
        #boolean specificMove(Board, Position, Position)
        #boolean specificCapture(GamePiece)
    }

    class Elephant {
        +Sobrescreve specificCapture()
    }

    class Lion {
        +Sobrescreve specificMove()
        +Adiciona canJumpOverWater()
    }

    class Tiger {
        +Herda comportamento do Lion
    }

    JungleAnimal <|-- Elephant
    JungleAnimal <|-- Lion
    Lion <|-- Tiger
```

### Cenário com o Padrão
Com o **Template Method**, os métodos `canMoveTo` e `canCapture` são implementados na classe `JungleAnimal`, enquanto detalhes específicos são delegados para métodos abstratos sobrescritos nas subclasses (`specificMove` e `specificCapture`).

### Participantes
| GOF               | Implementação no Jogo                                      |
|------------------|--------------------------------------------------------------|
| **AbstractClass** | `JungleAnimal` (define a estrutura padrão dos métodos)       |
| **ConcreteClass** | `Elephant`, `Lion`, `Tiger` (sobrescrevem comportamentos específicos) |

