import java.awt.*;
import java.awt.event.*;

public class calculadora extends Frame implements ActionListener {
    public calculadora(){
        setTitle("Calculadora");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Expande los botones para llenar la celda
        gbc.weightx = 1.0; //Escala en la que se expanden los botones en eje x
        gbc.weighty = 1.0; //Escala en la que se expanden los botones en eje y
        gbc.insets = new Insets(5, 5, 5, 5); // Margen entre botones
        //Creo el visor
        TextField visor = new TextField("0");
        visor.setEditable(false);
        visor.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // El visor ocupa 4 columnas
        gbc.gridheight = 1;
        add(visor, gbc);
        //Creo el botón borrar a la derecha del visor
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(new Button("←"), gbc);
        //Uso una grilla de 4x5 para el resto de los botones
        String[][] botones = {
            {"7", "8", "9", "/", "CE"},
            {"4", "5", "6", "*", "C"},
            {"1", "2", "3", "-", "="},
            {"0", "", ".", "+", ""}
        };
        //Agrego los botones
        for (int fila = 0; fila < botones.length; fila++) {
            for (int col = 0; col < botones[fila].length; col++) {
                String etiqueta = botones[fila][col];
                if (!etiqueta.equals("")) {
                    Button boton = new Button(etiqueta);
                    gbc.gridx = col;
                    gbc.gridy = fila + 1; // +1 porque el visor está en fila 0
                    gbc.gridwidth = 1;
                    gbc.gridheight = 1;

                    // Casos especiales
                    if (etiqueta.equals("=")) {
                        gbc.gridheight = 2; // "=" ocupa 2 filas
                    } else if (etiqueta.equals("0")) {
                        gbc.gridwidth = 2; // "0" ocupa 2 columnas
                    }

                    add(boton, gbc);

                    // Reseteo a los calores iniciales depués de haber agregado el 0 o el =
                    gbc.gridwidth = 1;
                    gbc.gridheight = 1;
                }
            }
        }
        //Configuro la ventana
        setSize(300, 400);
        setResizable(false);
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed (ActionEvent evt){}
    public static void main (String[] Args){
        new calculadora();
    }
}
