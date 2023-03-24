# Guidance
This is a suggested template for a project. You can modify it as you please, but
but remember to keep:

* a timelog, updated regularly in the `timelog.md` format;
* all source under version control;
* data well organised and with appropriate ethical approval (for human subject data);

Here's an overview of the structure as it stands:

* `timelog.md` The time log for your project.
* `plan.md` A skeleton week-by-week plan for the project. 
* `data/` data you acquire during the project
* `src/` source code for your project
* `status_report/` the status report submitted in December
* `meetings/` Records of the meetings you have during the project.
* `dissertation/` source and for your project dissertation
* `presentation/` your presentation

* Make sure you add a `.gitignore` or similar for your VCS for the tools you are using!
* Add any appropriate continuous integration (e.g. Travis CI) in this directory.

* Remove this `readme.md` file from any repository and replace it with something more appropriate!

## Important
* It should be easy to rebuild and run your project and your dissertation
        * Include clear instructions in the relevant directories to make this possible

This project has involved the development of a command line tool for generating natural language descriptions of JSON files, for my Level 4 honours Computer Science Project. 
The documentation for the code is available to view at : https://holly-hewitt.github.io/JSONTalk/index.html

We have packaged JSONTalk as jar file that should run on any system that has Java installed.
JSONTalk is a command line tool that takes an input JSON file and generate various different natural language descriptions of the file.
Usage: java -jar jsontalk.jar [-fhlrV] [-oa] [-tl] [-d=] [-o=] filename
Description level options (By default, no description levels are turned on. These explicitly need to be requested through the following options):
-f : Generates a full description of the file.
-oa : Generates a structural description of the file.
-tl : Generates a top level description of the file
Additional description options (By default, the depth option is set to 100, and the nesting option is not enabled):
-d=<x> : The description is only generated for JSON elements up to depth <x>
-n : A "Depth <y>" indicator will be added to the start of each line of the description
Output modes (The default is printing to command line):
-r : Use the tools integrated text-to-speech engine to read the description aloud
-o=<output file> : Specify a file path for the description to be saved to


Usage Example:
Suppose I had extracted the folder to my downloads folder, and I want to generate a full description and top level description of the a.json file.
1. cd into the "files for evaluation" directory
2. run the following command
java -jar jsontalk.jar -f -tl "C:\Users\User\Downloads\lFiles for evaluation\a.json"

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


  
