package Transpire;

import java.io.File;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;


public class Prompt
{
	public Namespace launchPrompt(String[] args) {
		ArgumentParser parserEng = ArgumentParsers.newFor("transpire").build()
			.description("Translates program written in native language to "
						 + "compilable code.");
		parserEng.addArgument("files")
			.metavar("FILE")
			.type(File.class)
			.nargs("+")
			.help("A file to translate to other language");
		parserEng.addArgument("--lang", "-l")
			.required(true)
			.dest("programming language")
			.type(String.class)
			.choices("python", "c")
			.help("The programming language used here.");
		parserEng.addArgument("--source", "-s")
			.required(true)
			.dest("source language")
			.type(String.class)
			.choices("en", "fr")
			.help("The source file natural language.");
		parserEng.addArgument("--target")
			.dest("target language")
			.type(String.class)
			.choices("en", "fr")
			.help("The spoken language to translate the file to.");

		try {
			Namespace resn = parserEng.parseArgs(args);
			return resn;
		}
		catch (ArgumentParserException e) {
			parserEng.handleError(e);
			return null;
		}
	}
}
