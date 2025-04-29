import java.awt.*;
import java.awt.event.*;

public class calculadora extends Frame implements ActionListener {
    //Creo las variables a utilizar
    TextField visor;
    String operador = "";
    double acumulador = 0;
    boolean nuevoNumero = true;

    public calculadora(){
        //Título del Frame y tipo de layouta
        setTitle("Calculadora");
        setLayout(new GridBagLayout());
        //Código para modificar manualmente los botones en el layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Expande los botones para llenar la celda
        gbc.weightx = 1.0; //Escala en la que se expanden los botones en eje x
        gbc.weighty = 1.0; //Escala en la que se expanden los botones en eje y
        gbc.insets = new Insets(5, 5, 5, 5); // Margen entre botones
        //Modifico el visor
        visor = new TextField("0");
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
        Button borrar = new Button("<<");
        add(borrar, gbc);
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
                    // Casos especiales del 0 y el =
                    if (etiqueta.equals("=")) {
                        gbc.gridheight = 2; // "=" ocupa 2 filas
                    } else if (etiqueta.equals("0")) {
                        gbc.gridwidth = 2; // "0" ocupa 2 columnas
                    }

                    add(boton, gbc);
                    //Listener para que la calcu detecte las teclas o clicks sobre la interface
                    boton.addActionListener(this);


                    // Reseteo a los valores iniciales depués de haber agregado el 0 o el =
                    gbc.gridwidth = 1;
                    gbc.gridheight = 1;
                }
            }
        }
        //Agrego el keyListener al visor que contiene la función para detectar si se presiona una tecla
        visor.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                char tecla = e.getKeyChar();
                // Si es número o punto
                if (Character.isDigit(tecla) || tecla == '.') {
                    agregarAlVisor(String.valueOf(tecla));
                } else if (tecla == '+' || tecla == '-' || tecla == '*' || tecla == '/') {
                    procesarOperacion(String.valueOf(tecla));
                } else if (tecla == '\n' || tecla == '=') {
                    calcularResultado();
                } else if (tecla == 'c' || tecla == 'C') {
                    limpiarTodo();
                }
            }
        });
        //Configuro la ventana
        setSize(300, 400);
        setResizable(false);
        setVisible(true);
        //Agrego el windows listener
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed (ActionEvent evt){
        String cmd = ((Button) evt.getSource()).getLabel();
        if (cmd.matches("[0-9]") || cmd.equals(".")) {
            agregarAlVisor(cmd);
        } else if (cmd.equals("C") || cmd.equals("CE")) {
            limpiarTodo();
        } else if (cmd.equals("=")) {
            calcularResultado();
        } else if (cmd.equals("<<")) {
            borrarUltimo();
        } else {
            procesarOperacion(cmd);
        }
    }
    //Lógica de la calculadora
    //variables a usar
    void agregarAlVisor(String valor) {
        if (nuevoNumero || visor.getText().equals("0")) {
            visor.setText(valor);
            nuevoNumero = false;
        } else {
            visor.setText(visor.getText() + valor);
        }
    }    

    void procesarOperacion(String op) {
        acumulador = Double.parseDouble(visor.getText());
        operador = op;
        nuevoNumero = true;
    }

    void calcularResultado() {
        double actual = Double.parseDouble(visor.getText());
        double resultado = switch (operador) {
            case "+" -> acumulador + actual;
            case "-" -> acumulador - actual;
            case "*" -> acumulador * actual;
            case "/" -> actual != 0 ? acumulador / actual : 0;
            default -> actual;
        };
        visor.setText(String.valueOf(resultado));
        acumulador = resultado;
        nuevoNumero = true;
    }

    void limpiarTodo() {
        visor.setText("0");
        acumulador = 0;
        operador = "";
       nuevoNumero = true;
    }

    void borrarUltimo() {
        String texto = visor.getText();
        if (texto.length() > 1) {
            visor.setText(texto.substring(0, texto.length() - 1));
        } else {
            visor.setText("0");
        }
    }
    public static void main (String[] Args){
        new calculadora();
    }
}
