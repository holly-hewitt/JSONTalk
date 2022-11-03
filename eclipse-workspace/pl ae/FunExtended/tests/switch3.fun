# Test assignment commands.

proc main():
	int n = 3
	int r = 0 
	int s = 0 
	switch n: 
		case 1: 
			r = 1 
			s = 2 
		case 2..4: 
			s = 3 
		default:
			s = 4. 
	write(s).

