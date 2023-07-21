package io.gayal.complexity;

import io.ballerina.compiler.syntax.tree.BinaryExpressionNode;
import io.ballerina.compiler.syntax.tree.ConditionalExpressionNode;
import io.ballerina.compiler.syntax.tree.DoStatementNode;
import io.ballerina.compiler.syntax.tree.ForEachStatementNode;
import io.ballerina.compiler.syntax.tree.FunctionDefinitionNode;
import io.ballerina.compiler.syntax.tree.IfElseStatementNode;
import io.ballerina.compiler.syntax.tree.MatchClauseNode;
import io.ballerina.compiler.syntax.tree.MatchStatementNode;
import io.ballerina.compiler.syntax.tree.Node;
import io.ballerina.compiler.syntax.tree.NodeVisitor;
import io.ballerina.compiler.syntax.tree.SyntaxKind;
import io.ballerina.compiler.syntax.tree.WhileStatementNode;

import java.util.HashMap;

public class CodeComplexityVisitor extends NodeVisitor {
    private HashMap<Node, Integer> nestingDepths;
    private ComplexityData complexityData;

    public CodeComplexityVisitor() {
        nestingDepths = new HashMap<>();
        this.complexityData = new ComplexityData();
    }

    @Override
    public void visit(FunctionDefinitionNode node) {
        nestingDepths.put(node, 0);
        complexityData.linesOfCode = node.toSourceCode().strip().lines().count();
        visitSyntaxNode(node);
    }

    @Override
    public void visit(DoStatementNode node) {
        incrementNestingDepth(node);
        visitSyntaxNode(node);
    }

    @Override
    public void visit(IfElseStatementNode node) {
        complexityData.cyclomaticComplexity++;
        if (node.parent().kind() != SyntaxKind.ELSE_BLOCK) {
            incrementNestingDepth(node);
        }
        visitSyntaxNode(node);
    }

    @Override
    public void visit(ConditionalExpressionNode node) {
        complexityData.cyclomaticComplexity++;
        visitSyntaxNode(node);
    }

    @Override
    public void visit(MatchStatementNode node) {
        incrementNestingDepth(node);
        visitSyntaxNode(node);
    }

    @Override
    public void visit(MatchClauseNode node) {
        complexityData.cyclomaticComplexity++;
        visitSyntaxNode(node);
    }

    @Override
    public void visit(BinaryExpressionNode node) {
        if (node.operator().text().equals("&&") || node.operator().text().equals("||")) {
            complexityData.cyclomaticComplexity++;
        }
        visitSyntaxNode(node);
    }

    @Override
    public void visit(ForEachStatementNode node) {
        incrementNestingDepth(node);
        complexityData.cyclomaticComplexity++;
        visitSyntaxNode(node);
    }

    @Override
    public void visit(WhileStatementNode node) {
        incrementNestingDepth(node);
        complexityData.cyclomaticComplexity++;
        visitSyntaxNode(node);
    }

    private void incrementNestingDepth(Node node) {
        Node parent = node.parent();
        while (!nestingDepths.containsKey(parent)) {
            parent = parent.parent();
        }
        nestingDepths.put(node, nestingDepths.get(parent) + 1);
        if (complexityData.maxNestingDepth < nestingDepths.get(node)) {
            complexityData.maxNestingDepth = nestingDepths.get(node);
        }
    }

    public ComplexityData getComplexityData() {
        return complexityData;
    }

    static class ComplexityData {
        private int cyclomaticComplexity;
        private long linesOfCode;
        private int maxNestingDepth;

        ComplexityData() {
            // decisionPointCount set to 1 because by default flow of the function
            cyclomaticComplexity = 1;
            linesOfCode = 0;
            maxNestingDepth = 0;
        }

        public int getCyclomaticComplexity() {
            return cyclomaticComplexity;
        }

        public long getLinesOfCode() {
            return linesOfCode;
        }

        public int getMaxNestingDepth() {
            return maxNestingDepth;
        }
    }
}
