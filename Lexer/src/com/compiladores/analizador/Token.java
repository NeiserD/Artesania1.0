package com.compiladores.analizador;


public class Token {

	private Tipos tipo;
	private String valor;
	    
    public Tipos getTipo() {
        return tipo;
    }

    public void setTipo(Tipos tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    enum Tipos {
    	
    	NUMERO("^\\d+$"),
        OPERADOR("[*|/|+|-]$"),
        ASIG("^\\=$"),
        REL("[<|>|>=|<=|==]"),
        
        AGRUP("[(|)|[|]|{|}]"),
      
	    INT ("int") ,
	    IF ("if") ,
	    ELSE ("else"),
	    CASE ("case"),
	    FOR ("for"),
	    BREAK ("break"),
	    RETURN ("return"),
	    FLOAT("float"),
	    STRING ("String"),
	    DOUBLE ("double"),
	       
        ID("^[A-Za-z]"),
        FIN_DE_LINEA("[;]$");
    	
        public final String patron;
        Tipos(String s) {
            this.patron = s;
        }
    }

}