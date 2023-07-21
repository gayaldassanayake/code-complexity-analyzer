package io.gayal.complexity;

import io.ballerina.compiler.syntax.tree.SyntaxKind;
import io.ballerina.projects.plugins.CodeAnalysisContext;
import io.ballerina.projects.plugins.CodeAnalyzer;

public class CodeComplexityAnalyzer extends CodeAnalyzer {

    @Override
    public void init(CodeAnalysisContext codeAnalysisContext) {
        codeAnalysisContext.addSyntaxNodeAnalysisTask(new CodeComplexityAnalysisTask(), SyntaxKind.FUNCTION_DEFINITION);
        printReportHeader();
    }

    private void printReportHeader() {
        String title = "Complexity Report";
        String[] headerLine1 = {"File Name", "Function Name", "Cyclomatic", "Lines of Code", "Nesting Depth"};
        String[] headerLine2 = {"", "", "Complexity", "", ""};
        System.out.println(title);
        System.out.println("-".repeat(85));
        System.out.println(String.format("%-20s|%-20s|%-15s|%-15s|%-15s", (Object[]) headerLine1));
        System.out.println(String.format("%-20s|%-20s|%-15s|%-15s|%-15s", (Object[]) headerLine2));
        System.out.println("-".repeat(85));
    }
}
