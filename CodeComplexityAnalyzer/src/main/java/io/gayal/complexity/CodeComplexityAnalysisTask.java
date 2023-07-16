package io.gayal.complexity;

import io.ballerina.compiler.syntax.tree.FunctionDefinitionNode;
import io.ballerina.projects.plugins.AnalysisTask;
import io.ballerina.projects.plugins.SyntaxNodeAnalysisContext;

public class CodeComplexityAnalysisTask implements AnalysisTask<SyntaxNodeAnalysisContext> {

    @Override
    public void perform(SyntaxNodeAnalysisContext context) {
        CodeComplexityVisitor visitor = new CodeComplexityVisitor();
        context.node().accept(visitor);
        String fileName = context.syntaxTree().filePath();
        String functionName = ((FunctionDefinitionNode)context.node()).functionName().text();
        System.out.println("File: " + fileName
                + " Function: " + functionName
                + " Decision Point Count: " + visitor.getComplexityData().getDecisionPointCount()
                + " Lines of Code: " + visitor.getComplexityData().getLinesOfCode());
    }
}
