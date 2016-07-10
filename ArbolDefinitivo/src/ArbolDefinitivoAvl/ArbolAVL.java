package ArbolDefinitivoAvl;
/**
*
* @author ShinjiEvil
* @author byJeans
* 
*/

import java.awt.Graphics;
import java.awt.Color;

public class ArbolAVL {
	protected NodoAVL rutaDeHoja;
	static final int x=580,y=0;// el nodo principal apaprece

 public void ingresarNodo(int d) {
	 // crea nodo
	 NodoAVL n = new NodoAVL(d);
	 // iniciar procedimiento recursivo para insertar el nodo
    ingresarAVL(this.rutaDeHoja,n);
    }

 public void ingresarAVL(NodoAVL a, NodoAVL b) {
	 // Si el nodo de comparar es nulo, se inserta el nodo. Si la raíz es nulo, es la raíz del árbol.
	 if(a==null) {
		 this.rutaDeHoja=b;
		 } 
	 else {
		 
   // Si el nodo comparar es más pequeña, continúe con el nodo izquierdo
		 if(b.dato<a.dato) {
			 if(a.hijoIzq==null) {
				 a.hijoIzq = b;
				 b.raiz = a;
				 b.estadodeNodo="NodoIzq";
				 // Nodo se inserta ahora, seguir comprobando el equilibrio
                equilibrioRecursivo(a);
                } 
			 else{
				 ingresarAVL(a.hijoIzq,b);
				 }
			 
   } 
		 else if(b.dato>a.dato) {
			 if(a.hijoDer==null) {
				 a.hijoDer = b;
				 b.raiz = a;
            b.estadodeNodo="NodoDer";
            // Nodo se inserta ahora, seguir comprobando el equilibrio
            equilibrioRecursivo(a);
            } 
			 else {
				 ingresarAVL(a.hijoDer,b);
				 }
			 } else {
				 // Hacer nada: Este nodo ya existe
				 }
		 } 
	 }

 public void equilibrioRecursivo(NodoAVL er) {
	 
  // No usamos el equilibrio en esta clase, pero la tienda de todos modos
	 obtEquilibrio(er);
	 int facEquilibrio = er.facEquilibrio;
	 
    //  Comprobar el saldo balan
	 if(facEquilibrio==-2) {
		 if(altura(er.hijoIzq.hijoIzq)>=altura(er.hijoIzq.hijoDer)) {
			 er = rotacionDerecha(er);
			 } else {
				 er = rotacionDobleIzquierda(er);
				 }
		 } else if(facEquilibrio==2) {
			 if(altura(er.hijoDer.hijoDer)>=altura(er.hijoDer.hijoIzq)) {
				 er = rotacionIzquierda(er);
				 } else {
					 er = rotacionDobleDerecha(er);
					 }
			 }
	 
    //  No llegamos a la raíz sin embargo,
	 if(er.raiz!=null) {
		 equilibrioRecursivo(er.raiz);
		 } else {
			 this.rutaDeHoja = er;
			 System.out.println("Equilibrado de datos Finalizado");
			 }
	 }
 
 
 //eliminar nodo sera llamado con un btn
 public void eliminar(int d) {
	 // Primero hay que encontrar el nodo, después de esto podemos eliminarlo.
	 eliminarAVL(this.rutaDeHoja,d);
	 }

 public void eliminarAVL(NodoAVL a,int b) {
	 if(a==null) {
		 // El valor no existe en este árbol, por lo que nada se puede hacer
		 return;
		 } else {
			 if(a.dato>b)  {
				 eliminarAVL(a.hijoIzq,b);
				 } else if(a.dato<b) {
					 eliminarAVL(a.hijoDer,b);
					 } else if(a.dato==b) {
						 // Encontramos el nodo en el árbol .. Ahora vamos a seguir!
						 eliminarNodo(a);
						 }
			 }
	 }

 public void eliminarNodo(NodoAVL q) {
	 NodoAVL r;
	 // Al menos un niño de q, q será eliminado directamente
	 if(q.hijoIzq==null || q.hijoDer==null) {
		 //  Se elimina la raíz
		 if(q.raiz==null) {
			 this.rutaDeHoja=null;
			 q=null;
			 return;
			 }
		 r = q;
		 } else {
			 // Q tiene dos hijos -> será reemplazado por el sucesor
			 r = successor(q);
			 q.dato = r.dato;
			 }
	 NodoAVL p;
	 if(r.hijoIzq!=null) {
		 p = r.hijoIzq;
		 } else {
			 p = r.hijoDer;
			 } 
	 if(p!=null) {
		 p.raiz = r.raiz;
		 }
	 if(r.raiz==null) {
		 this.rutaDeHoja = p;
		 } else {
			 if(r==r.raiz.hijoIzq) {
				 r.raiz.hijoIzq=p;
				 } else {
					 r.raiz.hijoDer = p;
					 }
   // equilibrio debe hacerse hasta que se alcanza la raíz.
			 equilibrioRecursivo(r.raiz);
			 }
	 r = null;
	 }
	//rotacion simple izquierda
 public NodoAVL rotacionIzquierda(NodoAVL n) {
	 NodoAVL v = n.hijoDer;
	 v.raiz = n.raiz;
	 n.hijoDer = v.hijoIzq;
	 if(n.hijoDer!=null) {
		 n.hijoDer.raiz=n;
		 }
	 v.hijoIzq = n;
	 n.raiz = v;
	 if(v.raiz!=null) {
		 if(v.raiz.hijoDer==n) {
			 v.raiz.hijoDer = v;
			 } else if(v.raiz.hijoIzq==n) {
				 v.raiz.hijoIzq = v;
				 }
		 }
	 obtEquilibrio(n);
	 obtEquilibrio(v);
	 v.estadodeNodo="NodoIzq";
	 return v;
	 }
 
	//rotacion simple derecha
 public NodoAVL rotacionDerecha(NodoAVL n) {
	 NodoAVL v = n.hijoIzq;	
	 v.raiz = n.raiz;
	 n.hijoIzq = v.hijoDer;
	 if(n.hijoIzq!=null) {
		 n.hijoIzq.raiz=n;
		 }
	 v.hijoDer = n;
	 n.raiz = v;
	 if(v.raiz!=null) {
		 if(v.raiz.hijoDer==n) {
			 v.raiz.hijoDer = v;
			 } else if(v.raiz.hijoIzq==n) {
				 v.raiz.hijoIzq = v;
				 }
		 }
	 v.estadodeNodo="NodoDer";
	 obtEquilibrio(n);
	 obtEquilibrio(v);
	 return v;
	 }
	//Rotacion doble a la Izquierda
 public NodoAVL rotacionDobleIzquierda(NodoAVL u) {
	 u.hijoIzq = rotacionIzquierda(u.hijoIzq);
	 return rotacionDerecha(u);
	 }
	//Rotacion doble a la derecha
 public NodoAVL rotacionDobleDerecha(NodoAVL u) {
	 u.hijoDer = rotacionDerecha(u.hijoDer);
	 return rotacionIzquierda(u);
	 }

// nodo anterior
 public NodoAVL successor(NodoAVL q) {
	 if(q.hijoDer!=null) {
		 NodoAVL r = q.hijoDer;
		 while(r.hijoIzq!=null) {
			 r = r.hijoIzq;
			 }
		 return r;
		 } else {
			 NodoAVL p = q.raiz;
			 while(p!=null && q==p.hijoDer) {
				 q = p;
				 p = q.raiz;
				 }
			 return p;
			 }
	 }
 //altura de nodos 
 private int altura(NodoAVL alt) {
	 if(alt==null) {
		 return -1;
		 }
	 if(alt.hijoIzq==null && alt.hijoDer==null) {
		 return 0;
		 } else if(alt.hijoIzq==null) {
			 return 1+altura(alt.hijoDer);
			 } else if(alt.hijoDer==null) {
				 return 1+altura(alt.hijoIzq);
				 } else {
					 return 1+maximo(altura(alt.hijoIzq),altura(alt.hijoDer));
					 }
	 }

// aquie retorna el maximo de dos enteros :v
 private int maximo(int a, int b) {
	 if(a>=b) {
		 return a;
		 } else {
	  return b;
	  }
	 }

//se hace equilibrio de nodods
 private void obtEquilibrio(NodoAVL obtE) {
	 obtE.facEquilibrio = altura(obtE.hijoDer)-altura(obtE.hijoIzq);
	 }

 //descripcion del arbol preOrden para graficarlo :v
 void preOrden(NodoAVL n, Graphics g){
	 if(n==null)return;
	 
     if(n==rutaDeHoja){
    	 n.x=0;
         n.y=0;
         g.drawOval(x+n.x, y+n.y, 30, 30);
         g.drawString(n.toString(), x+n.x+10, y+n.y+20);
         
     }
     if(n.raiz!=null){
            if(izq(n)) {
                n.x=n.raiz.x-90;
                n.y=n.raiz.y+75;
                g.drawOval(x+n.x, y+n.y, 30, 30);
                g.drawString(n.toString(), x+n.x+10, y+n.y+20);
                g.drawLine(x+n.x+10, y+n.y, x+n.raiz.x+10, y+n.raiz.y+30);
            }
            if(der(n)){
                n.x=n.raiz.x+90;
                n.y=n.raiz.y+75;
                g.drawOval(x+n.x, y+n.y, 30, 30);
                g.drawString(n.toString(), x+n.x+10, y+n.y+20);
                g.drawLine(x+n.x+10, y+n.y, x+n.raiz.x+10, y+n.raiz.y+30);
            }
          

     }
     preOrden(n.hijoIzq, g);
     preOrden(n.hijoDer, g);

 }
 void pO(Graphics g){
	 preOrden(rutaDeHoja,g);
 }
 public boolean izq(NodoAVL n){
     return n==n.raiz.hijoIzq;
 }
 public boolean der(NodoAVL n){
     return n==n.raiz.hijoDer;
 }
 public static void main(String[] args) {
        new InterfazAVL();
    }

}
