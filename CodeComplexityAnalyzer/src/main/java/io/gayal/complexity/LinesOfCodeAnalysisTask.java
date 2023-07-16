package io.gayal.complexity;

import io.ballerina.projects.plugins.AnalysisTask;
import io.ballerina.projects.plugins.CompilationAnalysisContext;

public class LinesOfCodeAnalysisTask implements AnalysisTask<CompilationAnalysisContext> {

    @Override
    public void perform(CompilationAnalysisContext context) {
        context.currentPackage().modules().forEach(
                module -> module.documentIds().forEach(
                        documentId -> {
                            module.document(documentId).textDocument().textLines().size();
                        }
                )
        );
    }
}
