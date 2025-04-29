import java.awt.*;
import java.awt.event.*;

public class calculadora extends Panel implements ActionListener {
    public calculadora(){
        //Creo el Frame para la calculadora
        Frame frame = new Frame();
        frame.setLayout(new GridBagLayout());
        //GridBagConstraints() me permite el posicionamiento manual de los botones
        GridBagConstraints gbc = new GridBagConstraints();
        //Hago que los botones se expandan en todas las direcciones
        gbc.fill = GridBagConstraints.BOTH;
        //Define el margen de espacios entre celdas
        gbc.insets = new Insets(5, 5, 5, 5);
        //Creo los botones
        String[][] etiquetas = {
            {"7", "8", "9", "/", "CE"},
            {"4", "5", "6", "*", "C"}, 
            {"1", "2", "3", "-", "="},
            {"0", "", ",", "+", ""}
        };
        //Agrego en la primer fila del frame el display
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        TextField display = new TextField("0");
        display.setEditable(false);
        frame.add(display, new GridBagConstraints(gbc));
        //Agrego el <- a la derecha del display
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        frame.add(new Button("<<"), new GridBagConstraints(gbc));
        //Inserto los botones en el frame
        for(int fila = 1; fila < etiquetas.length; fila++){
            for(int col = 0; col < etiquetas[fila].length; col++){
                if(!etiquetas[fila][col].equals("")){

                }
            }
        }
    }

    public void actionPerformed (ActionEvent evt){}
}
