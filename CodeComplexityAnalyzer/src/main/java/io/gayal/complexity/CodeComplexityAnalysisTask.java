package io.gayal.complexity;

import io.ballerina.compiler.syntax.tree.FunctionDefinitionNode;
import io.ballerina.projects.plugins.AnalysisTask;
import io.ballerina.projects.plugins.SyntaxNodeAnalysisContext;

import java.util.HashMap;
import java.util.Map;

public class CodeComplexityAnalysisTask implements AnalysisTask<SyntaxNodeAnalysisContext> {
    // TODO: remove the report map if not needed in the future
    private Map<String, Map<String, CodeComplexityVisitor.ComplexityData>> report;

    public CodeComplexityAnalysisTask() {
        report = new HashMap<>();
    }

    @Override
    public void perform(SyntaxNodeAnalysisContext context) {
        CodeComplexityVisitor visitor = new CodeComplexityVisitor();
        context.node().accept(visitor);
        String fileName = context.syntaxTree().filePath();
        String functionName = ((FunctionDefinitionNode)context.node()).functionName().text();
        report.putIfAbsent(fileName, new HashMap<>());
        printReportLine(fileName, functionName, visitor.getComplexityData());
    }

    public void printReportLine(
            String fileName, String functionName, CodeComplexityVisitor.ComplexityData complexityData) {
        System.out.println(String.format("%-20s|%-20s|%15s|%15s|%15s|%15s", fileName, functionName,
                complexityData.getDecisionPointCount(), complexityData.getLinesOfCode(), "WIP", "WIP"));
    }
}
