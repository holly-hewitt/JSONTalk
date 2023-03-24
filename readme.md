# Repository overview

Here's an overview of the structure as it stands:

* `timelog.md` Project timelog.
* 'JSONtalk.jar' The runnable jar file for the project. (i.e. the finished product!)
* `plan.md` Plan for project.
* `data/` Data from the product evaluations.
* `JSON-Audio-Description' The project source code.
* `status_report/` The status report submitted in December
* `dissertation/` Dissertation source files.
* `presentation/` Presentation slides'
* 'evaluation/' Copies of the evaluation that was conducted and planning for it.
* 'UML modelling files/' ALTOVA projects for UML modelling of the product.


# Project Description

This project involves the development of a command line tool for generating natural language descriptions of JSON files, as part of a Level 4 honours Computer Science project. 

The code documentation is available at: https://holly-hewitt.github.io/JSONTalk/index.html

JSONTalk has been packaged as a jar file that can run on any system with Java installed. It takes an input JSON file and generates various natural language descriptions of the file.

## Usage

To use JSONTalk, run the following command in the command line:

java -jar jsontalk.jar [-fhlrV] [-oa] [-tl] [-d=] [-o=] filename


### Description level options

By default, no description levels are turned on. These explicitly need to be requested through the following options:

- `-f` : Generates a full description of the file.
- `-oa` : Generates a structural description of the file.
- `-tl` : Generates a top level description of the file.

### Additional description options

By default, the depth option is set to 100, and the nesting option is not enabled:

- `-d=<x>` : The description is only generated for JSON elements up to depth `<x>`
- `-n` : A "Depth <y>" indicator will be added to the start of each line of the description.

### Output modes

The default is printing to the command line. Other output modes are available:

- `-r` : Use the tool's integrated text-to-speech engine to read the description aloud.
- `-o=<output file>` : Specify a file path for the description to be saved to.

## Usage Example

Suppose the JSONTalk folder has been extracted to the downloads folder, and a full and top level description of the `a.json` file is needed. The following steps can be followed:

1. cd into the "files for evaluation" directory
2. run the following command

java -jar jsontalk.jar -f -tl "C:\Users\User\Downloads\lFiles for evaluation\a.json"


  
