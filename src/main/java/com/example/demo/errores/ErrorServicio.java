
package com.example.demo.errores;

//SE CREA ESTA CLASE PARA DIFERENCIAR LOS ERRORES DE NUESTRA LOGICA DE NEGOCIOS DE LOS DEL SISTEMA

public class ErrorServicio extends Exception{
    public ErrorServicio(String msn) {
        super(msn);
    }
    
}
