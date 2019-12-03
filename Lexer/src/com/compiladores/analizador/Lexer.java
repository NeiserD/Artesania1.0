package com.compiladores.analizador;

import static com.compiladores.analizador.Token.Tipos;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.sun.org.apache.xml.internal.security.Init;

public class Lexer {

    public static void main(String[] args) {
      
        Scanner entrada = new Scanner(System.in);
    	System.out.println("Ingrese Cadena: ");
        String cadena = entrada.nextLine();
     
        try {
        	ArrayList<Token> tokens = lex(cadena);
            ArrayList<Token> tablaSimbolos = new ArrayList<Token>() ;
            for(Token token : tokens) {
            	if(token.getTipo()==Tipos.ID || token.getTipo()==Tipos.NUMERO ||token.getTipo()==Tipos.OPERADOR ||token.getTipo()==Tipos.REL) {
            		tablaSimbolos.add(token);
            	}	
            }
            System.out.println("\nTABLA DE SIMBOLOS\n");
            for (int i= 0 ; i < tablaSimbolos.size();i++ ) {
                System.out.println(i + "|" + tablaSimbolos.get(i).getValor());
            }
            
            System.out.println("\nTOKENS\n");
            for (Token token : tokens) {
            	int posicion = -1;
            	for (int i = 0; i < tablaSimbolos.size(); i++) {
            		if(token.getValor().equals(tablaSimbolos.get(i).getValor())) {
            			posicion = i;
            			break;
            		}
				}
            	if (posicion!=-1) {
            		System.out.println("<" + token.getTipo()+ ", " +  posicion + ">");
				}
                else {
                	System.out.println("<" + token.getTipo()+ ", "  + ">");
				}
            }
		} catch (Exception e) {
			
			System.out.println("Ingrese una cadena valida " + e.getMessage());
		}
    }

    private static ArrayList<Token> lex(String input) {
        final ArrayList<Token> tokens = new ArrayList<Token>();
        final StringTokenizer st = new StringTokenizer(input);

        while(st.hasMoreTokens()) {
            String palabra = st.nextToken();
            boolean matched = false;

            for (Tipos tokenTipo : Tipos.values()) {
                Pattern patron = Pattern.compile(tokenTipo.patron);
                Matcher matcher = patron.matcher(palabra);
                if(matcher.find()) {
                    Token tk = new Token();
                    tk.setTipo(tokenTipo);
                    tk.setValor(palabra);
                    matched = true;
                    if (tokens.size()>0) { //Identifica si el array de tokens está vacio
                    	 if (tokens.get(tokens.size()-1).getValor().equals(tk.getValor())); //busca si el ultimo elemento ya ha sido agregado
                         
                         else {
                         	tokens.add(tk); //si no existe dicho elemento lo agrega
                         	
                         }
					}else {
						tokens.add(tk); //si no tiene elementos agrega el elemento
					}
                }
            }
            if (!matched) {
                throw new RuntimeException("Se encontra un token invalido.");
            }
        }

        return tokens;
    }

}