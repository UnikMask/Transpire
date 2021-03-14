/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Transpire;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sourceforge.argparse4j.inf.Namespace;

public class App {
    public String getGreeting() {
        return "Suck ma balls";
    }



	// App arguments - stored here for now
	static Prompt promptInstance = new Prompt();
	List<File> sourceFiles;
	String sourceLanguage;
	String targetLanguage;
	boolean appFlag = true;


	// Get files to translate in the program.
	public List<File> getFilesToTranslate(Namespace resn) {
		List<File> resFiles = (List<File>) resn.get("files");
		return resFiles;
	}


	// Get source language to translate from.
	public String getSourceLanguage(Namespace resn) {
			String resFiles = (String) resn.get("source language");
			return resFiles;
	}


	// Get target language to translate to.
	public String getTargetLanguage(Namespace resn) {
		String resFiles = (String) resn.get("target language");
		return resFiles;
	}


	/**
	 * Get application wanted variables based on given arguments.
	 * @param args The app arguments.
	 * @return Whether the program is true or false.
	 */
	public boolean getArgsInApp(String[] args) {
		Namespace resn = promptInstance.launchPrompt(args);
		if (resn != null) {
			sourceFiles =
				this.getFilesToTranslate(resn);
			sourceLanguage =
				this.getSourceLanguage(resn);
			targetLanguage =
				this.getTargetLanguage(resn);
			return true;
		}
		else {
			return false;
		}
	}


	/**
	 * Constructor for app.
	 * @param args The application parameters.
	 */
	public App(String[] args) {
		appFlag = getArgsInApp(args);
	}


    public static void main(String[] args) {
        //Base: transpire Bonjour.java fr
        //Backend: transpire Bonjour.java -s fr -t en
        // --help

		App mainInstance = new App(args);
		if (mainInstance.appFlag) {
			Parser parser;
			try{
				parser = new Parser("fr", "python");
				System.out.println(parser.parseString("def multiplyAllSubsets(SuperNumSet):\n" +
						"    '''Returns a Set of SuperNumbers that was generated from multiplying all of the subsets of the given set .'''\n" +
						"    results = set()\n" +
						"\n" +
						"    si(len(SuperNumSet) == 0):\n" +
						"        return results\n" +
						"\n" +
						"    # The length of the subset that is going to be created.\n" +
						"    for subsetLen in range(1, len(SuperNumSet) + 1):\n" +
						"        # By using the built in itertools method combinations it gets all of the subsets of a specified length from the given set.\n" +
						"        subsetList = combinations(SuperNumSet, subsetLen)\n" +
						"        for subset in subsetList:\n" +
						"            # Goes through the subsets and multiples them together to see if a different number is produced.\n" +
						"            results.add(reduce((lambda a, b: a * b), subset))\n" +
						"    \n" +
						"    return results\n" +
						"\n" +
						"\n" +
						"def allPossibleNumbers(SuperNumSet):\n" +
						"    '''Returns a Set of SuperNumbers that were generated from multipliying the values inside the given set in all of the possible ways'''\n" +
						"    si(len(SuperNumSet) == 0):\n" +
						"        return set()\n" +
						"\n" +
						"    modulus = list(SuperNumSet)[0].modulus\n" +
						"\n" +
						"    # Because of how modulus works when a numbers powers are taken and reduced to that base the results will end up in a loop.\n" +
						"    # Because we know that it is a loop there is no need to search until infinity.\n" +
						"    results = {SuperNum ** power for SuperNum in SuperNumSet for power in range(1, modulus + 1)}\n" +
						"\n" +
						"    # Multiplies all the SuperNumber subsets from the loops together in the hopes of finding a new SuperNumber.\n" +
						"    results = multiplyAllSubsets(results)\n" +
						"\n" +
						"    return(results)"));
			}catch(NotSupportedLanguage e){
				System.out.println(e.getMessage());
			}catch(IOException e) {
				System.out.println("Couldn't open file.");
			}
		}
    }
}
