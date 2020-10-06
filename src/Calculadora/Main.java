/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author logra
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static final Integer SPACE = 32;
    
    public static String convertir(Cola res){
        StringBuilder str = new StringBuilder();
        
        while(!res.isEmpty()){
            char letra = (char)res.pop();
            str.append(letra);
        }
        str.append("\n");
        String posfijo = str.toString();
        return posfijo;
    } 
    
    public static String ShuntingYard(String ecuacion){
        Cola resultado = new Cola();
        Pila operador = new Pila();

        for(int i = 0; i < ecuacion.length(); i++){
            int ascii = (int)ecuacion.charAt(i);

            if(ascii == SPACE) continue;
            else if( (ascii <= 90 && ascii >= 65) || (ascii <= 122 && ascii >= 97) ) {
                resultado.push(ascii);
            }
            else if( ascii <= 57 && ascii >= 48 ) {
                resultado.push(ascii);
            }
            else{
                if(operador.isEmpty() || ascii == 40){
                    operador.push(ascii);
                }
                else{
                    if(ascii == 43 || ascii == 45){
                        while(!operador.isEmpty() && (operador.peek() != ascii && operador.peek() != 40)){
                            resultado.push(operador.pop());
                        }
                        operador.push(ascii);
                    }
                    else if(ascii == 42 || ascii == 47){
                        while(!operador.isEmpty() && ((operador.peek() != 43 && operador.peek() != 45) && operador.peek() != 40)){
                            resultado.push(operador.pop());
                        }
                        operador.push(ascii);
                    }
                    else if(ascii == 41){
                        while(!operador.isEmpty() && operador.peek() != 40){
                            resultado.push(operador.pop());
                        }
                        operador.pop();
                    }
                    else{
                        operador.push(ascii);
                    }
                }

            }
        }

        while(!operador.isEmpty()) resultado.push(operador.pop());
        String resultado_final = convertir(resultado);
        return resultado_final;
    }
    
    public static void main(String[] args) {
        
        // TODO code application logic here
        //C:\Users\logra\Desktop
        
        Main programa = new Main();
        try{           
            Scanner sc = new Scanner(System.in);
            System.out.print("\nEscriba la direccion del archivo: ");
            String direccion = sc.nextLine();
            String ecuacion = new String();
            
            File archivo = new File(direccion, "INPUT.TXT");           
            BufferedReader origen = new BufferedReader(new FileReader(archivo));
            
            
            System.out.print("\nEscriba la direccion donde quiere guardar el archivo: ");
            String dest_dirr = sc.nextLine();
            
            File writer = new File(dest_dirr, "OUTPUT.TXT");
            FileWriter destino = new FileWriter(writer, false);
            
            while( (ecuacion = origen.readLine()) != null){
                String final_res = programa.ShuntingYard(ecuacion);
                destino.write(final_res);
            }
            
            origen.close();
            destino.close();
            
        }catch(Exception e){
            System.out.println("No se encontro el archivo");
        } 
    }
}
