package framework.patterns.composite;

import framework.core.Board;
import framework.core.Move;

import java.util.ArrayList;
import java.util.List;

public class CompositeRule implements GameRule {
    private List<GameRule> rules = new ArrayList<>();

    public void addRule(GameRule rule) {
        rules.add(rule);
    }

    public void removeRule(GameRule rule) {
        rules.remove(rule);
    }

    @Override
    public boolean validate(Move move, Board board) {
        // Uma regra composta é válida apenas se todas as suas sub-regras forem válidas
        for (GameRule rule : rules) {
            if (!rule.validate(move, board)) {
                return false;
            }
        }
        return true;
    }
}
