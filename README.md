# Transpire

*CAN'T SPEAK ENGLISH? ATLEAST YOU CAN CODE!*

## Project Description

This project sets up an open-source-contribution environemnt where users anywhere around the globe can contribute translations of tokens in various different languages. (we implemented java, c, python in the span of 1 hour as an example to show how general and powerful our program is)

Our project will automatically keep track of new translation contributions and integrate it into our "transpiler" which will allow you to code in any human-spoken-language and generate source code for any programming langauge (given that someone committed translations of it to the repository).

## How does this all work?

There are two components to this project:

1. The parser
2. The translations

This project is the parser. It's main role is to fetch the up-to-date translations from our servers and carry out the translation process of the program.

The translations part of our project is a completely different repository: 

https://github.com/OcEaNvS/trnpkgs


The trnpkgs will be open sourced to allow pull requests from any user around the globe to make translations from their own language to some programming language.

Everytime someone builds our program, gradle will fetch a tar ball of the most stable translation mapping and store it in the local machine. And  


## Directory Structure

```
.
├── LICENSE
├── README.md
├── bin
│   *OMITTED*
├── build
│   *OMITTED*
├── build.gradle # BUILD INSTRUCTION
├── fetchtranslation.sh #FETCHES BASIC TRANSLATION TEMPLATE
├── gradle
│   *OMMITTED*
├── gradlew # FOR MACHINES WITHOUT GRADLE
├── gradlew.bat # FOR WINDOWS
├── samples # SAMPLES TO EXECUTE WITH
├── settings.gradle # GRADLE SETTINGS
├── src # ACTUAL SOURCE CODE
│   ├── main
│   │   └── java
│   │       └── Transpire
│   │           ├── App.java # MAIN PROGRAM ENTRYPOINT
│   │           ├── CommentSeparator.java # SEPARATE COMMENTS LOGIC
│   │           ├── Mapper.java # STORES TOKEN-TRANSLATION MAPPING
│   │           ├── NotSupportedLanguage.java # CUSTOM EXCEPTION 
│   │           ├── Parser.java # PARSER ENGINE 
│   │           ├── Prompt.java # PROMPTS DISPLAYED TO USER
│   │           ├── SpaceSeparator.java # TOKENIZER ENGINE
│   │           └── Translations.java # TRANSLATION MANAGEMENT ENGINE
│   └── test
│			*OMMITTED*
├── translations # DIRECTORY PER SPOKEN LANGUAGE
│   ├── fr # TRANSLATION MAPPING JSON PER PROGRAMMING LANGUAGE
│   │   *OMMITTED
│   └── jp
│       *OMITTED*
│   ...
├── transpire  # MAIN BINARY
└── transpireOut # FOLDER FOR OUTPUTTING TRANSLATIONS
    ├── sampleFile.java
	└── sampleFile.py
```




## How to Build

1) Install gradle for your respective operating system. (Guide at [A])

2) Once you have cloned the repository, execute the following command:

```bash
gradle build
```

3) If everything goes according to plan, you should see an output akin to the following:

```bash
> Task :compileJava
Note: /Users/tamimazmain/Projects/LangTranslate/src/main/java/Transpire/App.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.

> Task :curlTranslations
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   356  100   356    0     0    726      0 --:--:-- --:--:-- --:--:--   725

Deprecated Gradle features were used in this build, making it incompatible with Gradle 7.0.
Use '--warning-mode all' to show the individual deprecation warnings.
See https://docs.gradle.org/6.8.2/userguide/command_line_interface.html#sec:command_line_warnings

BUILD SUCCESSFUL in 3s
8 actionable tasks: 8 executed
``` 

4) It is important to note that a folder called "translations" should be generated under the project root. If it doesn't, please create it yourself with `mkdir`

## How to Run

1) Running it in gradle is the most stable way to execute our program.However, we do have a binary that is in the works @Unikmask. Go to 3) for instructions on using binary

2) Run gradle with the following command:


Our program's argument has the following strucutre:

```bash
usage: transpire [-h] --lang {python,c,java} --source {en,fr,jp}
                 [--target {en}] [--update] [--verbose] FILE [FILE ...]

Translates program written in native language to compilable code.

positional arguments:
  FILE                   A file to translate to other language

named arguments:
  -h, --help             show this help message and exit
  --lang {python,c,java}, -l {python,c,java}
                         The programming language used here.
  --source {en,fr,jp}, -s {en,fr,jp}
                         The source file natural language.
  --target {en}, -t {en}
                         The spoken language to translate the file to.
  --update, -u           Whether to update the JSON if already downloaded.
  --verbose, -v          Whether the program should be verbose.
```

The following is an example of how to run in gradle

```bash
gradle run --args="sampleFile.java -u --lang java --source jp --target en"
```

Gradle automatically runs the transpire app with the arguments stated inside --args. The above example will take the the java file (`--lang java`) called sampleFile.java, read it as japanese (`--source jp`), and map it to the standard java syntax (`--target en`) using the respective json. The `-u` argument always fetches the most up-to-date translation and caches it into local machine. If you do not have internet connection, drop the `-u`.




[A] https://gradle.org/install/