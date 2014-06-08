package Simplex;

import java.util.*; 
public class Estado 
{ 

public Estado (String nameThread){ 
Date fecha = new Date(); 
NombreSO = System.getProperty("os.name"); 
VerSO = System.getProperty("os.version"); 
Arquitectura = System.getProperty("os.arch"); 
Fecha = fecha.toString(); 
NombreHebra = nameThread; 
} 

public String toString(){ 
String Estado = new String(""); 
Estado = NombreSO + " Version " + VerSO + "\nArquitectura: " + Arquitectura + "\n" + Fecha + " \n" + NombreHebra; 
return Estado; 
} 

String Arquitectura; 
String Fecha; 
String NombreHebra; 
String NombreSO; 
String VerSO; 

} 
