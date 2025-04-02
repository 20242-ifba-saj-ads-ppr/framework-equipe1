package framework.patterns.composite;

import framework.core.Board;
import framework.core.Move;
import framework.core.rules.imp.GameRule;

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
        for (GameRule rule : rules) {
            if (!rule.validate(move, board)) {
                return false;
            }
        }
        return true;
    }
}
