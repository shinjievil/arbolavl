package ArbolDefinitivoAvl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazAVL extends JPanel implements ActionListener {
    private JTextField txtDato;
    private JButton btnInsertar,dato;
    ArbolAVL at = new ArbolAVL();
    int a;
    
    public InterfazAVL(){
    	
    	setForeground(UIManager.getColor("CheckBox.foreground"));
    	setBorder(null);
    	setBackground(SystemColor.activeCaption);
        JScrollPane frameScroll = new JScrollPane(this,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frameScroll.setLocation(15,70);
        frameScroll.setSize(1215,540);
        //aqui se define el jframe donde se grafica los arbolesAVL
        
        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame JFrameAVL = new JFrame("AVL TREE");
        JFrameAVL.setBackground(SystemColor.desktop);
        JFrameAVL.getContentPane().setBackground(SystemColor.activeCaption);
        JFrameAVL.setTitle("ARBOL AVL");
        JFrameAVL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrameAVL.getContentPane().setLayout(null);
        JFrameAVL.getContentPane().add(frameScroll);
        JFrameAVL.setSize(1256, 660);
        JFrameAVL.setLocationRelativeTo(null);    
        
        txtDato = new JTextField();
        txtDato.setBackground(SystemColor.text);
        txtDato.setBounds(15,21,120,30);
        JFrameAVL.getContentPane().add(txtDato);
        btnInsertar=new JButton("Insertar");
        btnInsertar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnInsertar.setBounds(145,21,120,30);
        JFrameAVL.getContentPane().add(btnInsertar);
        btnInsertar.addActionListener(this);
        
        JButton btnLimpiar = new JButton("LimpiarARB");
        btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnLimpiar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		at = null;
                at = new ArbolAVL();
                paint(frameScroll.getGraphics());        	
        		
        	}
        });
        btnLimpiar.setBounds(275, 21, 120, 30);
        JFrameAVL.getContentPane().add(btnLimpiar);
        
        JButton btnEliminar = new JButton("Eliminar Nodo");
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent d) {
        		at.eliminar(Integer.parseInt(txtDato.getText()));
        		repaint();
        		txtDato.setText("");
        		
        	}
        });
        btnEliminar.setBounds(405, 21, 120, 30);
        JFrameAVL.getContentPane().add(btnEliminar);
        JFrameAVL.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        at.ingresarNodo(Integer.parseInt(txtDato.getText()));
        a=1;
        repaint();
        txtDato.setText("");
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(a==1)at.preOrden(at.rutaDeHoja, g);
    } 

}

