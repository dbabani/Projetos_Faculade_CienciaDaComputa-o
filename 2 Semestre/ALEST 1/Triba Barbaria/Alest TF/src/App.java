import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        String[] testPaths = new String[] { 
            "caso04.txt",
            "caso05.txt",
            "caso06.txt",
            "caso07.txt",
            
        };

        for (String testPath : testPaths) {
            

            TribeTree tribeTree = readTestCase(testPath);

            

            System.out.println("\nCaso de teste: ");
            System.out.println("-> " + testPath);
            
            System.out.println("Guerreiro da última geração da tribo com mais terras (após herança): ");
            System.out.println("-> " + tribeTree.getWarriorWithMoreLands());

            
        }
    }

    static TribeTree readTestCase(String testPath) {
        Path path = Paths.get("src/casos/" + testPath);

        try (
            BufferedReader reader = 
                Files.newBufferedReader(path, Charset.defaultCharset())
        ) {
            String aux[];
            String s = reader.readLine();

            Integer firstWarriorLands = 0;

            if (s != null)
                firstWarriorLands = Integer.parseInt(s);

            String line = null;
            
            TribeTreeSerializer serializer = 
                new TribeTreeSerializer(firstWarriorLands);

            while ((line = reader.readLine()) != null) {
                aux = line.split(" ");
                
                String fatherName = aux[0];
                String childName = aux[1];
                Integer childLands = Integer.parseInt(aux[2]);
                
                serializer.addLineSerializer(fatherName, childName, childLands);
            }

            return serializer.generateTribeTree();
        } catch (IOException e) {
            System.err.format("An error ocurred reading the file: ", e);
        }

        return null;
    }

}