# Documentação do Jogo de Xadrez

Este documento apresenta um **guia passo a passo** para a construção do jogo de **Xadrez** utilizando o framework de jogos de tabuleiro baseado em padrões de projeto, já aplicado no desenvolvimento do jogo **Selva (Jungle)**. O objetivo é garantir uma arquitetura modular, extensível e compatível com os componentes já existentes.

---

## Etapa 1: Entender os Desafios Específicos do Xadrez
Antes de iniciar a implementação, é importante compreender os principais aspectos que diferenciam o xadrez de jogos como o Jungle:

### Regras de movimentação são mais sofisticadas
- Cavalo movimenta-se em "L".
- Peão possui regras específicas de captura, primeiro movimento e promoção.
- Roque, xeque e xeque-mate exigem verificação de múltiplas condições.

### As peças são sensíveis ao lado (branco/preto)
- Direção do movimento do peão depende do jogador.
- Roque é permitido apenas se o rei e a torre ainda não se moveram.

### O posicionamento inicial é fixo e completo
- Cada lado começa com 16 peças em posições bem definidas.

**Solução geral:** seguir o mesmo arcabouço do jogo Jungle, mas com ajustes no Builder, nas fábricas de peças e nos movimentos.

---

## Etapa 2: Criar os Tipos Básicos

### `ChessPieceType`
- **Tipo**: Enum
- **Implementa**: `PieceType`
- **Uso**: Identificar cada tipo de peça (REI, RAINHA, TORRE, BISPO, CAVALO, PEÃO)
- **Padrão aplicado**: Flyweight (usado como chave para propriedades da peça)

```java
public enum ChessPieceType implements PieceType {
    KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN;

    @Override
    public String getName() {
        return name();
    }
}
```

### `ChessCellType`
- **Tipo**: Enum
- **Implementa**: `CellType`
- **Uso**: Definir o tipo de célula no tabuleiro (normal, se desejar marcar casas especiais)

```java
public enum ChessCellType implements CellType {
    NORMAL;

    @Override
    public String getName() {
        return name();
    }
}
```

---

## Etapa 3: Criar a Fábrica Principal do Jogo

### `ChessAbstractFactory`
- **Herda de**: `GameAbstractFactory`
- **Responsabilidades**:
  1. Criar peças com `ChessGamePieceFactory`
  2. Criar tabuleiro com `ChessBoardBuilder`
  3. Criar os jogadores "White" e "Black"
  4. Registrar o jogo no `GameRegistry`
- **Padrão aplicado**: Abstract Factory

```java
@GameId("Chess")
public class ChessAbstractFactory implements GameAbstractFactory {
    static {
        GameRegistry.register("Chess", new ChessAbstractFactory());
    }

    @Override
    public List<GamePiece> createGamePieces() {
        return new ChessGamePieceFactory().createAllInitialPieces();
    }

    @Override
    public GameBoard createGameBoard() {
        return new GameBoardDirector(new ChessBoardBuilder()).construct(8, 8);
    }

    @Override
    public List<Player> createPlayers() {
        return List.of(new Player("White"), new Player("Black"));
    }
}
```

---

## Etapa 4: Construir o Tabuleiro do Jogo

### `ChessBoardBuilder`
- **Implementa**: `BoardBuilder`
- **Responsável por**:
  1. Criar tabuleiro 8x8
  2. Configurar células (opcional)
  3. Posicionar peças inicializadas nas posições fixas do xadrez
- **Padrão aplicado**: Builder

```java
@Override
public void populatePieces() {
    List<GamePiece> pieces = board.getPieces().getAll();

    // Exemplo: posicionando torres
    board.placePiece(pieces.get(0), new Position(0, 0)); // torre branca
    board.placePiece(pieces.get(1), new Position(7, 0)); // torre branca
    board.placePiece(pieces.get(2), new Position(0, 7)); // torre preta
    board.placePiece(pieces.get(3), new Position(7, 7)); // torre preta
}
```

---

## Etapa 5: Criar as Peças com Regras de Movimento

### `ChessGamePieceFactory`
- **Herda de**: `GamePieceFactory`
- **Responsável por**:
  1. Criar todas as peças iniciais de forma padronizada (2 torres, 2 cavalos, 8 peões, etc.)
  2. Usar o tipo da peça (`ChessPieceType`) para associar um `GamePieceProps` compartilhado
  3. Integrar com a `ChessMoveFactory` para definir o comportamento da peça
- **Padrões aplicados**: Factory Method + Flyweight

```java
@Override
protected GamePiece createGamePiece(PieceType type) {
    ChessPieceType chessType = (ChessPieceType) type;
    Move moveChain = moveFactory.createMoveChain(chessType);
    GamePieceProps props = new GamePieceProps(chessType, moveChain);
    return new GamePiece(props);
}
```

---

## Etapa 6: Criar a Fábrica de Movimentos

### `ChessMoveFactory`
- **Herda de**: `AbstractChessMoveFactory`
- **Responsável por**:
  1. Criar a cadeia de `MoveHandler` apropriada para cada peça
  2. Aplicar regras como:
     - `StraightMove` para torres e rainhas
     - `DiagonalMove` para bispos e rainhas
     - `Range(1)` para reis
     - `PawnMove` com controle de direção
     - `LShapedMove` para cavalos
- **Padrões aplicados**: Chain of Responsibility + Factory Method

```java
@Override
public Move createMoveChain(ChessPieceType type) {
    return switch (type) {
        case KING -> chain(new Range(1));
        case QUEEN -> chain(new StraightMove(), new DiagonalMove());
        case ROOK -> chain(new StraightMove());
        case BISHOP -> chain(new DiagonalMove());
        case KNIGHT -> chain(new LShapedMove());
        case PAWN -> chain(new PawnMove());
    };
}
```

---

## Etapa 7: (Opcional) Estender `GameBoard`

### `ChessBoard`
- **Herda de**: `GameBoard`
- **Responsável por**:
  - Verificar se o rei está em cheque
  - Validar roque, en passant, ou repetição de jogadas
- **Padrão aplicado**: Template Method (comportamento customizável)

```java
public class ChessBoard extends GameBoard {
    public boolean isInCheck(Player player) {
        // lógica para detectar se o rei desse player está sob ameaça
    }
}
```

---

## Etapa 8: Integração com o Framework

### Passo 1: Registro do jogo
```java
GameRegistry.register("Chess", new ChessAbstractFactory());
```

### Passo 2: Inicialização da partida
```java
GameManager.getInstance().start("Chess", "White");
```

> Isso cria o tabuleiro, as peças e inicia o controle via `GameSession` (Facade).

---

## Etapa 9: Visão Geral dos Padrões Utilizados

| Padrão                | Aplicação no Jogo de Xadrez                    |
|-----------------------|----------------------------------------------|
| **Abstract Factory**  | `ChessAbstractFactory`                        |
| **Builder**           | `ChessBoardBuilder`, `GameBoardDirector`      |
| **Factory Method**    | `ChessGamePieceFactory`, `ChessMoveFactory`   |
| **Flyweight**         | `GamePieceProps` compartilhados por tipo      |
| **Chain of Responsibility** | Handlers de movimento das peças       |
| **Prototype**         | Cópia de `GamePiece` e `Position`             |
| **Iterator**          | Iteração de peças com `PieceDeck`             |
| **Command**           | `MoveCommand`, `PassTurnCommand`              |
| **Memento**           | `GameMemento`, `HistoryManager`               |
| **Singleton**         | `GameManager`                                 |
| **Facade**            | `GameSession`                                 |

---

## Considerações Finais

Com este guia detalhado e estruturado em etapas claras, a implementação do jogo de Xadrez no framework segue um caminho sustentável, reutilizável e alinhado com os princípios da engenharia de software orientada a objetos.