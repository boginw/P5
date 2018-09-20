import os, sys, re

regex = r"^!{{(.*?)}}$"

if (len(sys.argv) != 2):
	print "Usage: python build.py file.md"
	exit()

# counts number of lines in input
def linenumber(input):
	return str(sum('\n' in s for s in input) + 1)

# given a match it replaces the match with the contents of the file
def replaceWithFile(cwd, file, contents, match):
	# only one file per include
	if (len(match.groups()) == 1):
		try:
			# get absolute path of file
			filepath = os.path.join(cwd, match.group(1))
			# update current working directory
			cwd = os.path.dirname(filepath)
			
			otherfile = open(filepath, "r")
			# scan opened file for includes
			otherfileContents = scanForReplacement(cwd, otherfile)
			
			# splice new file contents into current content
			contents = contents[:match.start()] + \
						otherfileContents + \
						contents[match.end():]
		except IOError as identifier:
			print str(identifier) + " at " + file.name + ":" + linenumber(contents[:match.start()])
	return contents

# scans for includes in a file
def scanForReplacement(cwd, file):
	# assert that we can read
	if (file.mode == "r"):
		# read the whole file as a string
		contents = file.read()
		# search file contents for includes
		matches = re.finditer(regex, contents, re.MULTILINE)

		# enumerate backwards in order to not overwrite existing content
		for _, match in enumerate(reversed(list(matches))):
			contents = replaceWithFile(cwd, file, contents, match)
		
		file.close()
		return contents
	file.close()
	return "" 

cwd = os.getcwd()
filepath = os.path.realpath(os.path.join(cwd, sys.argv[1]))
cwd = os.path.dirname(filepath)

print scanForReplacement(cwd, open(filepath, "r"))