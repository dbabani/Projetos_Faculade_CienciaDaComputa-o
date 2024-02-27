import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.io.PrintWriter;


public class Renumber {
	private Map<Integer, String[]> basic;
	private Map<String, Integer> NovasLinhas;


public  Renumber(){
	basic = new TreeMap<>();
	NovasLinhas = new TreeMap<>();
}
	public void loadProgram(String narq) { 
	Integer nroNovo = 10;
    String currDir = Paths.get("").toAbsolutePath().toString(); 
	String nameComplete = currDir+"/"+narq;
	 Path path2 = Paths.get(nameComplete);
	try(Scanner sc = new Scanner(Files.newBufferedReader(path2, Charset.defaultCharset()))){


		while(sc.hasNextLine()){
			String line = sc.nextLine();
			String[] tokens = line.split(" ");
			//tratar aqui
		     NovasLinhas.put(tokens[0], nroNovo);
			
			basic.put(nroNovo, tokens);

			nroNovo += 10;
		}

	  
		 

	

	}catch (IOException x){ 
			System.err.format("Erro de E/S: %s%n", x);
		  }
	  
		
	
		
			
			Path newPath = Paths.get(currDir + "/" + "Renumb" + narq);
			try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(newPath, StandardCharsets.UTF_8))){
				for(Integer key : basic.keySet()){
					String[] auxNewLine = basic.get(key);
	
					writer.print(key + " ");
	
					for(int i = 1; i<auxNewLine.length; i++){
						if(auxNewLine[i].equalsIgnoreCase("goto") || auxNewLine[i].equalsIgnoreCase("gosub")){
							auxNewLine[i+1] = String.valueOf(NovasLinhas.get(auxNewLine[i+1]));
						}
						writer.print(auxNewLine[i] + " ");
					}
					writer.print("\n");
				}
	
			} catch (IOException x){
			  System.err.format("Erro de E/S: %s%n", x);
			}

		}

}
		
		
	

		

		
	

	


	
	

	