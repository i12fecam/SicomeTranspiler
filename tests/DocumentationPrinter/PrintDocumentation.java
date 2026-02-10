package DocumentationPrinter;

import Internals.FlagEnum;
import Internals.MicroInstructionEnum;
import Internals.MicroInstructionTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrintDocumentation {
     public static void main(String[] args) {
        //System.out.println(getMicroInstructionTable());
         System.out.println(getTestTable());
    }

    public static String getMicroInstructionTable(){
         var res = new StringBuilder();
        res.append("""
                \\begin{longtable}{|p{0.4\\textwidth}|p{0.6\\textwidth}|}
                \\hline
                 Nombre & Nombre en SICOME \\\\
                 \\hline
                 """);
        res.append("""
        
        \\multicolumn{2}{|c|}{MAR} \\\\ \n
        \\hline
        """);
        res.append(getMicroInstructionType(MicroInstructionTypeEnum.mar));
        res.append("""
        
        \\multicolumn{2}{|c|}{OPR} \\\\ \n
        \\hline
        """);
        res.append(getMicroInstructionType(MicroInstructionTypeEnum.opr));
        res.append("""
        
        \\multicolumn{2}{|c|}{PC, SP y SC} \\\\ \n
        \\hline
        """);
        res.append(getMicroInstructionType(MicroInstructionTypeEnum.pc_sp_sc));
        res.append("""
        
        \\multicolumn{2}{|c|}{GPR} \\\\ \n
        \\hline
        """);
        res.append(getMicroInstructionType(MicroInstructionTypeEnum.gpr));
        res.append("""
        
        \\multicolumn{2}{|c|}{ALU} \\\\ \n
        \\hline
        """);
        res.append(getMicroInstructionType(MicroInstructionTypeEnum.alu));
        res.append("\\end{longtable}");



        return res.toString().replace("_","\\allowbreak\\_");
    }

    private static String getMicroInstructionType(MicroInstructionTypeEnum type){
        var res = new StringBuilder();
        Arrays.stream(MicroInstructionEnum.values())
                .filter(m -> m.getType().equals(type))
                .forEach(m ->{
                    res.append(m.inputName).append(" & ");
                    res.append(m.outputName).append(" \\\\");
                    res.append("\\hline\n");


        });
        return res.toString();
    }

    public static String getFlagTable(){
        var res = new StringBuilder();
        res.append("""
                 \\begin{tabular}{|l|p{0.8\\textwidth}|}
                 nombre | equivalente SICOME \
                 
                 """);
        Arrays.stream(FlagEnum.values()).forEach(flag ->{
            res
                    .append(flag.inputName)
                    .append(" & ")
                    .append(flag.outputName)
                    .append("\n").append("\\\\").append("\\hline");
        });
        return res.toString();
    }

    public static String getTestTable(){
        var res = new StringBuilder();

        res.append("\\subsection{Lógica Microprogramado}\n");

        res.append("""
                \\begin{longtable}{|p{0.4\\textwidth}|p{0.6\\textwidth}|}
                \\hline
                 Nombre & Descripción \\\\
                 \\hline
                 """);


        getTestInfo("SemanticTests.MicroLogicInstructionTest").forEach(i ->{
            res.append(i.name()).append(" & ");
            res.append(i.description()).append(" \\\\");
            res.append("\\hline\n");

        });

        res.append("\\end{longtable}");

        res.append("\\subsection{Repertorio Microprogramado}\n");

        res.append("""
                \\begin{longtable}{|p{0.4\\textwidth}|p{0.6\\textwidth}|}
                \\hline
                 Nombre & Descripción \\\\
                 \\hline
                 """);

        getTestInfo("SemanticTests.MicroInstructionsTest").forEach(i ->{
            res.append(i.name()).append(" & ");
            res.append(i.description()).append(" \\\\");
            res.append("\\hline\n");

        });

        res.append("\\end{longtable}");



        res.append("\\subsection{Repertorio Cableado}\n");

        res.append("""
                \\begin{longtable}{|p{0.4\\textwidth}|p{0.6\\textwidth}|}
                \\hline
                 Nombre & Descripción \\\\
                 \\hline
                 """);

        getTestInfo("SemanticTests.CableInstructionTest").forEach(i ->{
            res.append(i.name()).append(" & ");
            res.append(i.description()).append(" \\\\");
            res.append("\\hline\n");

        });

        res.append("\\end{longtable}");

        res.append("\\subsection{Programa}\n");

        res.append("""
                \\begin{longtable}{|p{0.4\\textwidth}|p{0.6\\textwidth}|}
                \\hline
                 Nombre & Descripción \\\\
                 \\hline
                 """);



        getTestInfo("SemanticTests.ProgramTest").forEach(i ->{
            res.append(i.name()).append(" & ");
            res.append(i.description()).append(" \\\\");
            res.append("\\hline\n");

        });

        res.append("\\end{longtable}");
        return res.toString().replace("_","\\allowbreak\\_");
    }

    private static List<testInfo> getTestInfo(String className){
        try {
            var test = Class.forName(className);
            return Arrays.stream(test.getDeclaredMethods())
                    //.peek(m -> System.out.println("Method: " + m.getName())) // Debug: Print method names
                    .filter(m -> {
                        return m.isAnnotationPresent(Test.class) ||
                                m.isAnnotationPresent(ParameterizedTest.class);
                    })
                    .sorted((m1, m2) -> {
                        return m1.getName().compareTo(m2.getName());


                    })
                    .map(m ->{
                        var description = (m.isAnnotationPresent(DisplayName.class))
                                ? m.getAnnotation(DisplayName.class).value()
                                : " ";
                        return new testInfo(m.getName(),description);
                    })

                    .toList();
        }catch (RuntimeException | ClassNotFoundException e){
            System.out.println(e);
        }
        return null;
    }
    record testInfo(String name, String description){}






}
