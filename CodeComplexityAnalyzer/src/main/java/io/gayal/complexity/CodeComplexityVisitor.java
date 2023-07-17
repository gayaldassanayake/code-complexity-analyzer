package io.gayal.complexity;

import io.ballerina.compiler.syntax.tree.BinaryExpressionNode;
import io.ballerina.compiler.syntax.tree.ConditionalExpressionNode;
import io.ballerina.compiler.syntax.tree.ForEachStatementNode;
import io.ballerina.compiler.syntax.tree.FunctionDefinitionNode;
import io.ballerina.compiler.syntax.tree.MatchClauseNode;
import io.ballerina.compiler.syntax.tree.Node;
import io.ballerina.compiler.syntax.tree.NodeVisitor;
import io.ballerina.compiler.syntax.tree.WhileStatementNode;
import org.ballerinalang.model.tree.statements.IfNode;

import java.util.Iterator;

public class CodeComplexityVisitor extends NodeVisitor {
    private ComplexityData complexityData;

    public CodeComplexityVisitor() {
        this.complexityData = new ComplexityData();
    }

    @Override
    public void visit(FunctionDefinitionNode node) {
        complexityData.linesOfCode = node.toSourceCode().strip().lines().count();
        Iterator<Node> iterator = node.children().iterator();
        visitChildren(iterator);
    }

    public void visit(IfNode node) {
        complexityData.decisionPointCount++;
    }

    @Override
    public void visit(ConditionalExpressionNode node) {
        complexityData.decisionPointCount++;
        Iterator<Node> iterator = node.children().iterator();
        visitChildren(iterator);
    }

    @Override
    public void visit(MatchClauseNode node) {
        complexityData.decisionPointCount++;
        Iterator<Node> iterator = node.children().iterator();
        visitChildren(iterator);
    }

    @Override
    public void visit(BinaryExpressionNode node) {
        if (node.operator().text().equals("&&") || node.operator().text().equals("||")) {
            complexityData.decisionPointCount++;
        }
        Iterator<Node> iterator = node.children().iterator();
        visitChildren(iterator);
    }

    @Override
    public void visit(ForEachStatementNode node) {
        complexityData.decisionPointCount++;
        Iterator<Node> iterator = node.children().iterator();
        visitChildren(iterator);
    }

    @Override
    public void visit(WhileStatementNode node) {
        complexityData.decisionPointCount++;
        Iterator<Node> iterator = node.children().iterator();
        visitChildren(iterator);
    }

    private void visitChildren(Iterator<Node> iterator) {
        while (iterator.hasNext()) {
            iterator.next().accept(this);
        }
    }

    public ComplexityData getComplexityData() {
        return complexityData;
    }

    static class ComplexityData {
        private int decisionPointCount;
        private long linesOfCode;

        ComplexityData() {
            // decisionPointCount set to 1 because by default flow of the function
            decisionPointCount = 1;
            linesOfCode = 0;
        }

        public int getDecisionPointCount() {
            return decisionPointCount;
        }

        public long getLinesOfCode() {
            return linesOfCode;
        }
    }
}
