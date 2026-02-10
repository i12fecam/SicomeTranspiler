package Runner;

import Analysis.*;
import CodeGeneration.Micro.MicrocodeLogicGenerator;
import CodeGeneration.ProgramCodeGenerator;
import CodeGeneration.Cable.CableCodeGenerator;
import CodeGeneration.Micro.MicrocodeGenerator;
import Internals.Errors.ErrorController;
import Internals.Errors.ErrorEnum;
import Parsing.CustomErrorListener;
import Parsing.SicomeLexer;
import Parsing.SicomeParser;
import Internals.SymbolTable;
import Parsing.TranslatedDefaultErrorStrategy;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.nio.file.Path;
import java.util.List;

record Config(
    ObjetiveConfig obj,
    Path inputFilePath,
    Path outputPath,
    String outputName,
    Path includeInputFilePath
){
    public Config{
        if (outputName == null){
            outputName = inputFilePath.getFileName().toString().split("\\.")[0];
        }
    }

}
public class Runner {
    String logicText;
    String repositoryText;
    String programText;

    ErrorController err = new ErrorController();

    public void run(String fileContent){
        run(fileContent,ObjetiveConfig.todo,null);
    }
    public void run(String fileContent,ObjetiveConfig obj,String includeFileContent) {

        SicomeParser.ProgramContext programTree = null;
        SicomeParser.ProgramContext includeTree = null;

        SicomeLexer lexerFile = new SicomeLexer(CharStreams.fromString(fileContent));
        lexerFile.removeErrorListeners();
        lexerFile.addErrorListener(new CustomErrorListener(err));

        var tokensFile = new CommonTokenStream(lexerFile);
        SicomeParser parserFile = new SicomeParser(tokensFile);
        parserFile.removeErrorListeners();
        parserFile.addErrorListener(new CustomErrorListener(err));
        parserFile.setErrorHandler( new TranslatedDefaultErrorStrategy());

        programTree = parserFile.program();
        includeTree = programTree;

        if(includeFileContent != null){
            SicomeLexer lexerFile2 = new SicomeLexer(CharStreams.fromString(includeFileContent));
            lexerFile2.removeErrorListeners();
            lexerFile2.addErrorListener(new CustomErrorListener(err));


            var tokensFile2 = new CommonTokenStream(lexerFile2);
            SicomeParser parserFile2 = new SicomeParser(tokensFile2);
            parserFile2.removeErrorListeners();
            parserFile2.addErrorListener(new CustomErrorListener(err));
            parserFile2.setErrorHandler( new TranslatedDefaultErrorStrategy());
            includeTree = parserFile2.program();
        }



        var symbols = new SymbolTable();
        var ids = new ParseTreeProperty<Integer>();
        ParseTreeWalker walker = new ParseTreeWalker();
        switch (includeTree){

            case SicomeParser.MicroProgramContext ctx -> {

                var analysisMicrocodeLogicPass = new MicrocodeLogicAnalysis(symbols,err);
                walker.walk(analysisMicrocodeLogicPass, ctx.statusLogicBlock());

                var microcodeLogicCodeGeneratorPass = new MicrocodeLogicGenerator(symbols,err);
                walker.walk(microcodeLogicCodeGeneratorPass, includeTree);

                logicText = microcodeLogicCodeGeneratorPass.getLogicFileString();

                if (obj == ObjetiveConfig.repertorio ||  obj == ObjetiveConfig.todo){

                    if(ctx.instructionBlockMicro() == null){
                        err.addNewError(ErrorEnum.FALTA_BLOQUE_NECESARIO,List.of("Bloque de instrucciones"),ctx.start);
                    }

                    var analysisMicroPass = new MicrocodeAnalysis(ids,symbols,err);
                    walker.walk(analysisMicroPass,ctx.instructionBlockMicro());

                    var microCodeGeneratorPass = new MicrocodeGenerator(ids, symbols,err);
                    walker.walk(microCodeGeneratorPass, includeTree);

                    repositoryText = microCodeGeneratorPass.getRepositoryFileString();

                }

                if(obj == ObjetiveConfig.todo){

                    if((programTree instanceof SicomeParser.CableProgramContext ||programTree instanceof SicomeParser.MicroProgramContext)
                            && ctx.programBlock() == null){
                        err.addNewError(ErrorEnum.FALTA_BLOQUE_NECESARIO,List.of("Bloque de variables y programa"),ctx.start);
                    }

                    var analysisProgram = new ProgramAnalysis(symbols,ids,err);
                    walker.walk(analysisProgram,programTree);

                    var codeGenerationProgramPass = new ProgramCodeGenerator(ids,symbols,err);
                    walker.walk(codeGenerationProgramPass,programTree);

                    programText = codeGenerationProgramPass.getProgramFileString();
                }



            }

            case SicomeParser.CableProgramContext ctx -> {

                if(obj == ObjetiveConfig.logica){
                    err.addNewError(ErrorEnum.FALTA_BLOQUE_NECESARIO,List.of("No se puede analizar lógica en cableado"), ctx.getStart());
                }

                var cableAnalysisPass = new CableAnalysis(ids,symbols,err);
                walker.walk(cableAnalysisPass, includeTree);

                var cableCodeGeneratorPass = new CableCodeGenerator(ids, symbols,err);
                walker.walk(cableCodeGeneratorPass, includeTree);

                logicText = cableCodeGeneratorPass.getLogicFileString();
                repositoryText = cableCodeGeneratorPass.getRepositoryFileString();

                if (obj == ObjetiveConfig.todo){
                    //No hace falta comprobar el apartado de variable porque estan definidos programa y variables o ninguno
                    if((programTree instanceof SicomeParser.CableProgramContext ||programTree instanceof SicomeParser.MicroProgramContext)
                            && ctx.programBlock() == null){
                        err.addNewError(ErrorEnum.FALTA_BLOQUE_NECESARIO,List.of("Bloque de variables y programa"),ctx.start);
                    }

                    var programAnalysisPass = new ProgramAnalysis(symbols,ids, err);
                    walker.walk(programAnalysisPass,programTree);

                    var programCodeGenerationPass = new ProgramCodeGenerator(ids,symbols,err);
                    walker.walk(programCodeGenerationPass,programTree);

                    programText = programCodeGenerationPass.getProgramFileString();
                }

            }
            default -> {
                err.addNewError(ErrorEnum.FALTA_BLOQUE_NECESARIO,List.of("No se puede identificar el tipo de programa"), includeTree.getStart());
            }
        }



    }

    public String getRepositoryText(){
        return repositoryText;
    }

    public String getLogicText(){
        return logicText;
    }

    public String getProgramText(){
        return programText;
    }

    public void printErrorsToErr(boolean b){
         err.printToError(b);
    }

    public void printErrorsToConsole(boolean b){
        err.printToConsole(b);
    }



    public boolean containsErrorEnum(ErrorEnum error){
        return err.containsErrorEnum(error);
    }

    public boolean canCompile() {
        return err.canCompile();
    }
//    public void printTokens(){
//        tokens.fill();
//        for (Token token : tokens.getTokens()) {
//            String tokenName = SicomeLexer.VOCABULARY.getSymbolicName(token.getType());
//            if (tokenName == null) {
//                tokenName = SicomeLexer.VOCABULARY.getDisplayName(token.getType());
//            }
//            System.out.println("Token: " + tokenName + " (" + token.getText() + ")");
//        }
//    }
}
