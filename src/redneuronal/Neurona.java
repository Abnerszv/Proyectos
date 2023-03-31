/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redneuronal;

/**
 *
 * @author abner
 */
public class Neurona {
   private double Color, Textura, Forma, Sabor, Olor, Interior;
   private double vTasa_de_aprendizaje;                                  //Es el nivel de aprendizage de la neurona artificial.
   private double vUmbral_de_activacion;                                //El umbral o maximo o minimo nivel de activacion de la neurona al recivir algun estimulo.
 
   public Neurona(double nuevo_Color,double  nuevo_Textura,double  nuevo_Forma, double  nuevo_Sabor, double  nuevo_Olor,double  nuevo_Interior)//Debo aclarar que el codigo original esta en c++ y yo estoy haciendo una traduccion y unas modificasiones a mi modo.
   {//En caso de usarse un vector para los pesos entonces, el parametro del cosntructor debe ser un vector llamado Vector mPesos.
 
      //Valores predefinidos para arrancar pero si conoces más de la tecnologia Neuroredes Artificiales puedes poner otros valores.
      this.Color=nuevo_Color;
      this.Textura=nuevo_Textura;
      this.Forma=nuevo_Forma;
      this.Sabor=nuevo_Sabor;
      this.Olor=nuevo_Olor;
      this.Olor=nuevo_Interior;
      
      this.vUmbral_de_activacion=9.5; //Empujón de referencia de cada neurona (queda libre a modificarse para obtener un mejor resultado)
      this.vTasa_de_aprendizaje=0.5; //
   }//Fin del constructor.
 
 
 
   public int fSalida (double nueColor,double  nueTextura,double  nueForma, double nueSabor, double nueOlor, double nueInterior)//Retornar datos 0 o 1.
   {
      double vSumatoria=  (nueColor * this.Color) + (nueTextura * this.Textura) +  (nueForma * this.Forma) + (nueSabor * this.Sabor) + (nueOlor * this.Olor) + (nueOlor * this.Interior);
 
      return (vSumatoria > this.vUmbral_de_activacion ? 1:0); //Esta linea a retornar es un if pero en modo nivel de maquina, un poco más efectivo que el if normal.
   }//Fin de fSalidav                                  si  no
 
 
   //Funcion principal de esta neurona. Aunque cuando se quiere hacer una red, se conecta una primera fila con las entradas para el usuario y las filas secundarias tienen su entrada conectada con la salida de la primera fila, la neurona de salida tiene su salida libre para mostrar los datos en pantalla.
   public int fEntrenamiento_supervisado(double nueColor,double  nueTextura,double  nueForma, double nueSabor, double nueOlor, double nueInterior,int nueva_salida_deseada_por_el_usuario)//La salida deseada por el usuario, es un valor que el usuario o programador pone en modo ejecusion o cuando hace una instancia de la neurona, si la neurona lanza una salida igual a la deseada se dice que a aprendido y aprovado el entrenamiento.
   {
      double vResultado = fSalida ( nueColor,  nueTextura, nueForma, nueSabor, nueOlor, nueInterior);
      
      int vRespuesta=0;//Salida de la funcion.
 
      if (vResultado != nueva_salida_deseada_por_el_usuario)
      {
 
         if (vResultado > nueva_salida_deseada_por_el_usuario)
         {
            //Actualizacion de los pesos (Sinapsis)
            this.Color = this.Color - (this.vTasa_de_aprendizaje * nueColor);
            this.Textura= this.Textura - (this.vTasa_de_aprendizaje * nueTextura);
            this.Forma= this.Forma - (this.vTasa_de_aprendizaje * nueForma);
            this.Sabor= this.Sabor - (this.vTasa_de_aprendizaje * nueSabor);
            this.Olor= this.Olor - (this.vTasa_de_aprendizaje * nueOlor);
             this.Interior= this.Interior - (this.vTasa_de_aprendizaje * nueInterior);
            //--------------------------------------------------------
 
            //Actualizacion del umbral de activacion
            this.vUmbral_de_activacion +=  this.vTasa_de_aprendizaje;
 
         }//Fin del if >
         else
         {
            //Actualizacion de los pesos (Sinapsis)
            this.Color = this.Color + (this.vTasa_de_aprendizaje * nueColor);
            this.Textura= this.Textura + (this.vTasa_de_aprendizaje * nueTextura);
            this.Forma= this.Forma + (this.vTasa_de_aprendizaje * nueForma);
            this.Sabor= this.Sabor + (this.vTasa_de_aprendizaje * nueSabor);
            this.Olor= this.Olor + (this.vTasa_de_aprendizaje * nueOlor);
            this.Interior= this.Interior - (this.vTasa_de_aprendizaje * nueInterior);
            //--------------------------------------------------------  
            //Actualizacion del umbral de activacion
            this.vUmbral_de_activacion -=  this.vTasa_de_aprendizaje;
 
         }//Fin del else anidado >
 
         vRespuesta=1;//Neurona activada y respuesta positiva.
 
         }//Fin del if !=
         else
         {
            vRespuesta=0;//Neurona desactivada y respuesta negativa.
       }//Fin del else !=
 
      return vRespuesta;
   }//Fin de fEntrenamiento_supervisado
 
   public double getUmbral_de_activacion()
   {
      return this.vUmbral_de_activacion;
   }//Fin de getUmbral_de_activacion
 
   public double getTasa_de_aprendizaje()
   {
      return this.vTasa_de_aprendizaje;
   }//Fin de getTasa_de_aprendizaje
 
   public String getPesos()
   {
      return "" +  this.Color + "\n" + this.Textura + "\n" + this.Forma + "\n" + this.Sabor + "\n" + this.Olor + "\n" + this.Interior;
   }//Fin de getPesos
}
