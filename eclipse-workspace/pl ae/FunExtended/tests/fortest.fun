# Test assignment commands.
func int fac (int n): # returns n! 
	int i = 0 	
	int f = 1 
	for i = 2 to n: 
		f = f*i . 
	return f.
proc main ():
	int g = 7
	int x = fac(g)
	write(x).

