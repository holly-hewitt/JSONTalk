# Test assignment commands.
func bool invert (bool b): 
	bool x = false 
	switch b: 
		case true: 
			x = false 
		default: 
			x = true .
	return x .
func int test (int n): 
	int r = 0 
	int s = 0 
	switch n: 
		case 1: 
			r = 1 
			s = 2 
		case 2..4: 
			s = 3 
		default: 
			r = 4 . 
	write(s) 
	return r .
proc main ():
	bool b = true
	int n = 3
	int r = test(n)
	write(r)
	return invert(b).

