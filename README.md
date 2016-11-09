#Assignment 1


###Write a Java file that receives command line inputs

---------------------------------------------------
Name of java file is *Main.java*.  
This file is command line interface that receives arguments from users.  
If you want any help in using command line, you can use __-help__ option.

This is example of command arguments:
>>java Main inputFile.md -filename outputFile -style styleName

1. inputFile.md

	-This file is target markdown file that you wants to convert.
	-inputFile.md have to exist already.

2. outputFile

	-This is result of converting markdown to HTML
	-If you do not declare any other name to output file, name will be same as input file name in default.

3. styleName
	
	-There are three kinds of style: Plain, Slide, Fancy
	-If you do not declare style type, style will be 'Plain' in default vaule.

-------------------------------------------

There are several rules that you have to follow:
	
* Cannot use those characters in html output file name
>>>\\ / : * ? \" < > |

* All arguments used in command line is case sensitive

