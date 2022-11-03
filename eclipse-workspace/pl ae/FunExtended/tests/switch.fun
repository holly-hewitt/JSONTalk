# Test assignment commands.
func bool invert (bool b): 
	bool x = false 
	switch b: 
		case true: 
			x = false 
		default: 
			x = true .
	return x .
proc main ():
	bool b = true
	bool x = invert(b) .

