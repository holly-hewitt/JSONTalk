//////////////////////////////////////////////////////////////
//
// Specification of the Fun syntactic analyser.
//
// Based on a previous version developed by
// David Watt and Simon Gay (University of Glasgow).
// 
// Extended by Holly Hewitt (2463548H)
// 14/03/2022
//
//////////////////////////////////////////////////////////////


grammar Fun;

// This specifies the Fun grammar, defining the syntax of Fun.

@header{
    package ast;
}

//////// Programs

program
	:	var_decl* proc_decl+ EOF  # prog
	;


//////// Declarations

proc_decl
	:	PROC ID
		  LPAR formal_decl_seq? RPAR COLON
		  var_decl* seq_com DOT   # proc

	|	FUNC type ID
		  LPAR formal_decl_seq? RPAR COLON
		  var_decl* seq_com
		  RETURN expr DOT         # func
	;

formal_decl_seq
	:	formal_decl (COMMA formal_decl)* # formalseq
	;

formal_decl
	:	type ID                # formal
	;

var_decl
	:	type ID ASSN expr         # var
	;

type
	:	BOOL                      # bool
	|	INT                       # int
	;


//////// Commands

com
	:	ID ASSN expr              # assn
	|	ID LPAR actual_seq? RPAR       # proccall
							 
	|	IF expr COLON c1=seq_com
		  ( DOT              
		  | ELSE COLON c2=seq_com DOT   
		  )                       # if

	|	WHILE expr COLON          
		  seq_com DOT             # while

// Extension A (Part 1 of 2)

	|	FOR ID ASSN (e1=expr) TO (e2=expr) COLON 
		  seq_com DOT		# for

// end of Extension A (Part 1 of 2)

// Extension B (Part 1 of 4)

	|	SWITCH expr COLON
		  (CASE switch_expr COLON
		    seq_com)*
		  DEFAULT COLON
		    seq_com DOT		# switch

	;

// end of Extension B (Part 1 of 4)

seq_com
	:	com*                      # seq
	;



//////// Expressions

expr
	:	e1=sec_expr
		  ( op=(EQ | LT | GT) e2=sec_expr )?
	;

sec_expr
	:	e1=prim_expr
		  ( op=(PLUS | MINUS | TIMES | DIV) e2=sec_expr )?
	;

prim_expr
	:	FALSE                  # false        
	|	TRUE                   # true
	|	NUM                    # num
	|	ID                     # id
	|	ID LPAR actual_seq? RPAR    # funccall
	|	NOT prim_expr          # not
	|	LPAR expr RPAR         # parens
	;

actual_seq
	:  expr (COMMA expr)*  # actualseq
	;

// Extension B (Part 2 of 4)

switch_expr
	:	e1=prim_expr
	|	e1=prim_expr op=RANGE e2=prim_expr
	;

// End of Extension B (Part 2 of 4)


//////// Lexicon

BOOL	:	'bool' ;
ELSE	:	'else' ;
FALSE	:	'false' ;
FUNC	:	'func' ;
IF      :	'if' ;
INT     :	'int' ;
PROC	:	'proc' ;
RETURN  :	'return' ;
TRUE	:	'true' ;
WHILE	:	'while' ;

// Extension A (Part 2 of 2)

FOR	:	'for';
TO	:	'to';

// End of Extension A (Part 2 of 2)

// Extension B (Part 3 of 4)

SWITCH	:	'switch';
CASE	:	'case';
DEFAULT	:	'default';

// End of Extension B (Part 3 of 4)

EQ      :	'==' ;
LT      :	'<' ;
GT      :	'>' ;
PLUS	:	'+' ;
MINUS	:	'-' ;
TIMES	:	'*' ;
DIV     :	'/' ;
NOT     :	'not' ;

// Extension B (Part 4 of 4)

RANGE	:	'..';

// End of Extension B (Part 4 of 4)

ASSN	:	'=' ;

LPAR	:	'(' ;
RPAR	:	')' ;
COLON	:	':' ;
DOT     :	'.' ;
COMMA	:	',' ;

NUM	:	DIGIT+ ;

ID	:	LETTER (LETTER | DIGIT)* ;

SPACE	:	(' ' | '\t')+   -> skip ;
EOL     :	'\r'? '\n'          -> skip ;
COMMENT :	'#' ~('\r' | '\n')* '\r'? '\n'  -> skip ;

fragment LETTER : 'a'..'z' | 'A'..'Z' ;
fragment DIGIT  : '0'..'9' ;