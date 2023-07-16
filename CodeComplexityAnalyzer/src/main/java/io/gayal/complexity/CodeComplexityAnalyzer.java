package io.gayal.complexity;

import io.ballerina.compiler.syntax.tree.SyntaxKind;
import io.ballerina.projects.plugins.CodeAnalysisContext;
import io.ballerina.projects.plugins.CodeAnalyzer;

public class CodeComplexityAnalyzer extends CodeAnalyzer {
    @Override
    public void init(CodeAnalysisContext codeAnalysisContext) {
        // TODO:
        //  1. Cyclomatic Complexity
        //  2. code duplication
        //  3. LOC
        //  4. Code maintainability

        System.out.println("** Code Complexity Analyzer **");
        System.out.println("1. Cyclomatic Complexity");
        codeAnalysisContext.addSyntaxNodeAnalysisTask(new CodeComplexityAnalysisTask(), SyntaxKind.FUNCTION_DEFINITION);
        codeAnalysisContext.addCompilationAnalysisTask(new LinesOfCodeAnalysisTask());
    }
}
