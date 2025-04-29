import java.awt.*;
import java.awt.event.*;

public class Botones extends Panel implements ActionListener {

    Button b1,b2,b3;
    TextField c1, c2;
    TextArea ta1;

    public Botones() {
        //Creo los objetos botones
        b1 = new Button( "Boton B1" );
        b2 = new Button( "Boton B2" );
        b3 = new Button( "Boton B3" );
        //Creo los objetos campo de texto y area de texto
        c1 = new TextField(20);
        c2 = new TextField(20);
        ta1 = new TextArea(5,20);
        //Agrego los componentes al panel
        this.add( b1 );
        this.add( b2 );
        this.add( b3 );
        this.add( c1 );
        this.add( c2 );
        this.add( ta1 );
        //Asocio los listeners a los botones
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        }

    public static void main(String[] args) {
        //Crea una nueva ventana
        Frame f = new Frame("Botones Nuevo");

        WindowListener l=new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                        System.exit(0);
                };
        };
        f.addWindowListener(l);

        //Crea una instancia de Botones

        Botones b = new Botones();
        //Agrega el objeto para que se muestre por la ventana.
        f.add("Center", b);
        //Redimensiona la ventana a su tama�o natural
        f.pack();
        f.setVisible(true);
    }

    public void actionPerformed (ActionEvent evt) {
        if(evt.getActionCommand()==b1.getActionCommand())
            c1.setText( "Se ha pulsado el boton B1" );
        if(evt.getActionCommand()==b2.getActionCommand())
            c2.setText( "Se ha pulsado el boton B2" );
        if(evt.getActionCommand()==b3.getActionCommand())
            //.setTExt sobreescribe todo lo que había antes en el text area
            ta1.setText( "Se ha pulsado el boton B3\n" );
            //.append concatena, agrega al final del contenido actual del text area
            //ta1.append( "Se ha pulsado el boton B3" );
    }
}