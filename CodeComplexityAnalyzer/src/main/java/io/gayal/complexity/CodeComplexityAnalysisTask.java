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
        printReportLine(fileName, functionName, visitor.getComplexityData());
    }

    public void printReportLine(
            String fileName, String functionName, CodeComplexityVisitor.ComplexityData complexityData) {
        System.out.println(String.format("%-20s|%-20s|%15s|%15s|%15s", fileName, functionName,
                complexityData.getCyclomaticComplexity(), complexityData.getLinesOfCode(),
                complexityData.getMaxNestingDepth()));
    }
}
