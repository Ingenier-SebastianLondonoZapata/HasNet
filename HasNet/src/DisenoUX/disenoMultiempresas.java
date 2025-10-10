package DisenoUX;

import Vista.InicioSesion.vistaInicioSesionMultiempresa;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class disenoMultiempresas extends JPanel {

    private int numeroEmpresas;
    private disenoOpcionMultiempresa empresa[];

    public disenoMultiempresas(Object[][] empresas, vistaInicioSesionMultiempresa ventana) {
        this.setLayout(null);
        numeroEmpresas = empresas.length;
        empresa = new disenoOpcionMultiempresa[numeroEmpresas];
        
        int i = 0;
        for (Object[] producto : empresas) {
            empresa[i] = new disenoOpcionMultiempresa(producto[1].toString(), producto[2].toString(), ventana);
            empresa[i].setBounds(0, 0, empresa[i].getWidth(), empresa[i].getHeight());
            this.add(empresa[i]);
            i++;
        } 
        
        this.setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        int x = 10, y = 10;
        for (int i = 0; i < numeroEmpresas; i++) {
            empresa[i].setBounds(x, y, empresa[i].getWidth(), empresa[i].getHeight());

            this.add(empresa[i]);
            x += +empresa[i].getWidth() + 10;
            if (x >= (this.getWidth() - 200)) {
                y += empresa[i].getHeight() + 10;
                x = 10;
            }
        }
        super.paint(g);
    }
    
}
