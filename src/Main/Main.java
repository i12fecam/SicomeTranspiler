package Main;

import Runner.ObjetiveConfig;

import Runner.Runner;
import picocli.CommandLine;
import picocli.CommandLine.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class Main {


    private static Runner runner = new Runner();


@Command(name = "AsiTranspiler",
        mixinStandardHelpOptions = true,
        version = "AsiTranspiler 1.0",
        description = "Convierte archivos .asi a archivos .lcb, .rep, .prog para ser utilizados con el programa SICOME.",
        subcommands = {
                Analizar.class,
                Convertir.class
        }
        //,resourceBundle = "Translations"
)
static class AsiTranspiler implements Callable<Integer> {



    public Integer call() throws Exception {

        return 1;
    }


}



@Command(name="analizar",
        mixinStandardHelpOptions = true
        //resourceBundle = "Translations"
)
static class Analizar implements Callable<Integer>{
    @Parameters(index = "0",description = "Que objetivo tendrá a analizar (logica repertorio todo)")
    ObjetiveConfig objetive;
    @Parameters(index = "1", description = "El archivo a analizar")
    private File file;
    @Option(names = {"-i", "--incluir"}, description = "Archivo que utilizar para repertorio y lógica")
    private File includedFile;
    @Override
    public Integer call() throws Exception {

//        System.out.println(
//                ResourceBundle.getBundle("Translations").getString("picocli.Usage")
//        );
        String fileContent1 = readFileContent(file);
        String fileContent2 = null;
        if (includedFile != null)  fileContent2 = readFileContent(includedFile);
        try{
            runner.run(fileContent1,objetive,fileContent2);
        } catch (RuntimeException e) {
            //runner.printErrorsToConsole(true);
        }
        runner.printErrorsToConsole(true);
        return 1;
    }
}
@Command(name="convertir",
        mixinStandardHelpOptions = true
        //,resourceBundle = "Translations"
)
static class Convertir implements Callable<Integer>{
    @Parameters(index = "0",description = "Que objetivo tendrá a convertir  (logica repertorio todo)")
    ObjetiveConfig objetive;
    @Parameters(index = "1", description = "El archivo a convertir")
    private File file;

    @Parameters(index="2",description = "El directorio donde se creará los nuevos archivos")
    private Path outputPath;

    @Option(names = {"-n", "--nombre"}, description = "El nombre de los archivos a crear")
    private String name;
    @Option(names = {"-i", "--incluir"}, description = "Archivo que utilizar para repertorio y lógica")
    private File includedFile;
    @Override
    public Integer call() throws Exception {
        String fileContent1 = readFileContent(file);
        String fileContent2 = null;
        if (includedFile != null)  fileContent2 = readFileContent(includedFile);
        if(name == null) name = file.getName().replaceFirst("[.][^.]+$", "");

        try{
            runner.run(fileContent1,objetive,fileContent2);
        } catch (RuntimeException e) {
            //System.out.println(e);
            runner.printErrorsToErr(true);
        }
        runner.printErrorsToErr(true);




        if(!runner.canCompile()){
            System.out.println("No se puede convertir los archivos debido a los errores encontrados.");
            return 1;
        }

        Files.createDirectories(outputPath);
        switch (objetive){
            case logica -> {
                createFile(outputPath,name + ".lcb", runner.getLogicText());
            }
            case repertorio -> {
                createFile(outputPath,name + ".lcb", runner.getLogicText());
                createFile(outputPath,name +".rep",runner.getRepositoryText());
            }
            case todo ->{
                createFile(outputPath,name + ".lcb", runner.getLogicText());
                createFile(outputPath,name +".rep",runner.getRepositoryText());
                createFile(outputPath,name + ".prog",runner.getProgramText());
            }
        }


        return 1;
    }
}


    private static void createFile(Path parentFolder, String fileName, String fileContent) {
        File file = new File(parentFolder.toFile(), fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("Archivo creado en: " + file.getAbsolutePath());
                FileWriter writer = new FileWriter(file);
                writer.write(fileContent);
                writer.close();
            } else {
                System.err.println("Hubo un problema creando el archivo en: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Hubo un problema creando el archivo: " + e.getMessage());
        }
    }


    public static String readFileContent(File file) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

        return content.toString();
    }

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        var cmd = new CommandLine(new AsiTranspiler());
        var resourceBundle = ResourceBundle.getBundle("Translations", new Locale("es", "ES"));

        //System.out.println( resourceBundle.getString("picocli.Usage"));
        // apply bundle to subcommands
        cmd.setResourceBundle(resourceBundle);
//        for (CommandLine sub : cmd.getSubcommands().values()) {
//            sub.getCommandSpec().resourceBundle(resourceBundle);
//        }
        int exitCode = cmd.execute(args);
        System.exit(exitCode);

    }

}