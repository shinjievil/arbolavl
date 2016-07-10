package ArbolDefinitivoAvl;
/**
*
* @author byJeans
* @author ShinjiEvil
* 
*/

public class NodoAVL {
	public NodoAVL raiz;
	public NodoAVL hijoIzq;
	public NodoAVL hijoDer;
	public int facEquilibrio,x,y;
	public int dato;
		String estadodeNodo;
	
	public NodoAVL(int d) {
		hijoIzq = hijoDer = raiz = null;
		facEquilibrio = 0;
		dato = d;
		}
	
	public String toString() {
		return "" + dato;
		}
	}