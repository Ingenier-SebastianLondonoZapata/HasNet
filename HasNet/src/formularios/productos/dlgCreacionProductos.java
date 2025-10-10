package formularios.productos;

import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class dlgCreacionProductos extends javax.swing.JDialog {

    private Instancias instancias;
    DefaultTableModel modelo;
    TableRowSorter modeloOrdenado;
    metodosGenerales metodos = new metodosGenerales();
    Object[] prod;

    public dlgCreacionProductos(java.awt.Frame parent, boolean modal, Object[] productos) {
        super(parent, modal);
        initComponents();

        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        this.setLocationRelativeTo(null);
        this.setTitle("CREACIÓN DE PRODUCTOS");

        instancias = Instancias.getInstancias();

        modelo = (DefaultTableModel) tblProductos.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblProductos.setRowSorter(modeloOrdenado);

        tblProductos.getColumnModel().getColumn(6).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(6).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(6).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(7).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(7).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(7).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(8).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(8).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(8).setMaxWidth(0);
    }

    private ActionListener accion(final String opc, final JDialog ventana) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "cerrar":
                        ventana.dispose();
                        break;
                }
            }
        };
        return a;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFinalizar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JTextField();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CREACIÓN DE PRODUCTOS");

        txtFinalizar.setBackground(new java.awt.Color(46, 204, 113));
        txtFinalizar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        txtFinalizar.setText("REGISTRAR PRODUCTOS");
        txtFinalizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtFinalizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        txtFinalizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        txtFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFinalizarActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jButton3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton3.setText("IMPORTAR BASE DE EXCEL");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tblProductos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Iva Compra", "Iva Venta", "Precio Venta", "Proveedor", "Tipo Prod", "Marca", "Peso"
            }
        ));
        tblProductos.setComponentPopupMenu(jPopupMenu1);
        tblProductos.setRowHeight(19);
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        tblProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);
        if (tblProductos.getColumnModel().getColumnCount() > 0) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(2).setMinWidth(50);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblProductos.getColumnModel().getColumn(2).setMaxWidth(100);
            tblProductos.getColumnModel().getColumn(3).setMinWidth(50);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblProductos.getColumnModel().getColumn(3).setMaxWidth(100);
            tblProductos.getColumnModel().getColumn(4).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(4).setMaxWidth(100);
            tblProductos.getColumnModel().getColumn(5).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMinWidth(50);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(80);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(100);
            tblProductos.getColumnModel().getColumn(7).setMinWidth(50);
            tblProductos.getColumnModel().getColumn(7).setPreferredWidth(80);
            tblProductos.getColumnModel().getColumn(7).setMaxWidth(100);
            tblProductos.getColumnModel().getColumn(8).setMinWidth(30);
            tblProductos.getColumnModel().getColumn(8).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(8).setMaxWidth(70);
        }

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setText("CODIGO:");

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setText("DESCRIPCIÓN:");

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setText("PROVEEDOR:");

        txtGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGrupoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtFinalizar)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFinalizarActionPerformed
        Object[][] productoExistentes = new Object[tblProductos.getRowCount()][6];

        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {

            int ser = 0;
            for (int i = 0; i < tblProductos.getRowCount(); i++) {

                ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 0).toString(), "bdProductos");

                Boolean entro = false;
                if (nodo.getCodigo() != null) {
                    productoExistentes[ser][0] = tblProductos.getValueAt(i, 0);
                    productoExistentes[ser][1] = tblProductos.getValueAt(i, 1);
                    productoExistentes[ser][2] = tblProductos.getValueAt(i, 2);
                    productoExistentes[ser][3] = tblProductos.getValueAt(i, 3);
                    productoExistentes[ser][4] = tblProductos.getValueAt(i, 4);
                    productoExistentes[ser][5] = tblProductos.getValueAt(i, 5);
                    ser++;
                    entro = true;
                }

                if (!entro) {
                    String ivaVenta, ivaCompra, marca = "", tipoProd = "", peso = "";

                    try {
                        tipoProd = tblProductos.getValueAt(i, 6).toString();
                    } catch (Exception e) {
                    }

                    try {
                        marca = tblProductos.getValueAt(i, 7).toString();
                    } catch (Exception e) {
                    }

                    try {
                        peso = tblProductos.getValueAt(i, 8).toString();
                    } catch (Exception e) {
                    }

                    if (tblProductos.getValueAt(i, 2).equals("")) {
                        ivaCompra = "0";
                    } else {
                        ivaCompra = tblProductos.getValueAt(i, 2).toString();
                    }

                    if (tblProductos.getValueAt(i, 3).equals("")) {
                        ivaVenta = "0";
                    } else {
                        ivaVenta = tblProductos.getValueAt(i, 3).toString();
                    }

                    String consecutivo = "";
                    int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("PROD")[0].toString());
                    consecutivo = String.valueOf(num);
                    for (int j = 0; j < 8 - String.valueOf(num).length(); j++) {
                        consecutivo = "0" + consecutivo;
                    }

                    Object[] vector = {"PROD-" + consecutivo, tblProductos.getValueAt(i, 0).toString(), "", tblProductos.getValueAt(i, 1).toString(),
                        null, null, null, ivaVenta,
                        big.getMoneda(tblProductos.getValueAt(i, 4).toString()), big.getMoneda(tblProductos.getValueAt(i, 4).toString()),
                        big.getMoneda(tblProductos.getValueAt(i, 4).toString()), big.getMoneda(tblProductos.getValueAt(i, 4).toString()),
                        big.getMoneda(tblProductos.getValueAt(i, 4).toString()), big.getMoneda(tblProductos.getValueAt(i, 4).toString()),
                        big.getMoneda(tblProductos.getValueAt(i, 4).toString()), big.getMoneda(tblProductos.getValueAt(i, 4).toString()), instancias.getUsuario(), "1", "Und",
                        "", big.getMoneda("0"), 20, 30, "", tblProductos.getValueAt(i, 1).toString(), "", "", tblProductos.getValueAt(i, 1).toString(), "", "",
                        tblProductos.getValueAt(i, 1).toString(), "", "", tblProductos.getValueAt(i, 1).toString(),
                        "", "", tblProductos.getValueAt(i, 1).toString(), "", "", tblProductos.getValueAt(i, 1).toString(), "", "", tblProductos.getValueAt(i, 1).toString(), "", "", false, false, false, false, false, false, false,
                        ivaCompra, 0, instancias.getTerminal(), "0", "", true, "", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
                        false, "", "", peso, "", "", "", "", marca, tipoProd, "0", tblProductos.getValueAt(i, 0).toString() + "-2",
                        tblProductos.getValueAt(i, 0).toString() + "-3", tblProductos.getValueAt(i, 0).toString() + "-4",
                        tblProductos.getValueAt(i, 0).toString() + "-5", tblProductos.getValueAt(i, 0).toString() + "-6",
                        tblProductos.getValueAt(i, 0).toString() + "-7", tblProductos.getValueAt(i, 0).toString() + "-8", "", "", "", "", "", "", "1", "0", "0",
                        txtDescripcion.getText(), "0"};
                    nodo = metodos.llenarProducto(vector);

                    if (!instancias.getSql().agregarProducto(nodo, "bdProductos")) {
                        metodos.msgError(null, "Error al guardar el producto");
                        return;
                    }
                    if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega1")) {
                        metodos.msgError(null, "Error al guardar el producto");
                        return;
                    }
                    if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega2")) {
                        metodos.msgError(null, "Error al guardar el producto");
                        return;
                    }
                    if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega3")) {
                        metodos.msgError(null, "Error al guardar el producto");
                        return;
                    }
                    if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega4")) {
                        metodos.msgError(null, "Error al guardar el producto");
                        return;
                    }

                    if (!instancias.getSql().agregarPonderado(metodos.fechaConsulta(metodosGenerales.fecha()), "PROD-" + consecutivo,
                            BigDecimal.ZERO, "0", "0", BigDecimal.ZERO, "0", instancias.getUsuario(), BigDecimal.ZERO, "")) {
                        metodos.msgError(null, "Error al guardar ponderado");
                    }

                    if (!instancias.getSql().agregarUltimoPonderado(metodos.fechaConsulta(metodosGenerales.fecha()), "PROD-" + consecutivo,
                            BigDecimal.ZERO, "0", "0", BigDecimal.ZERO, "0", instancias.getUsuario(), BigDecimal.ZERO, "")) {
                        metodos.msgError(null, "Error al guardar Ult.Ponderado");
                    }

                    if (!instancias.getSql().aumentarConsecutivo("PROD", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("PROD")[0]) + 1)) {
                        metodos.msgError(null, "Error al aumentar consecutivo");
                    }
                }
            }

            metodos.msgExito(null, "Productos registrados con exito.");

            // LIMPIAR LA TABLA
            DefaultTableModel model = (DefaultTableModel) tblProductos.getModel();
            while (tblProductos.getRowCount() > 0) {
                model.removeRow(0);
            }
            // FIN DE LIMPIAR LA TABLA

            String validacion = "";
            try {
                validacion = productoExistentes[0][0].toString();
            } catch (Exception e) {
            }

            if (!validacion.equals("")) {
                metodos.msgAdvertencia(null, "Algunos de los productos ya existen!");

                for (int i = 0; i < productoExistentes.length; i++) {
                    String cod = "";
                    try {
                        cod = productoExistentes[i][0].toString();
                    } catch (Exception e) {
                    }

                    if (!cod.equals("")) {
                        model.addRow(new Object[]{productoExistentes[i][0], productoExistentes[i][1], productoExistentes[i][2], productoExistentes[i][3], productoExistentes[i][4],
                            productoExistentes[i][5]});
                    }
                }
            } else {
                this.dispose();
            }
        }
    }//GEN-LAST:event_txtFinalizarActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked

    }//GEN-LAST:event_tblProductosMouseClicked

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 0));
        txtDescripcion.setText("");
        txtGrupo.setText("");
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtDescripcion.getText(), 1));
        txtId.setText("");
        txtGrupo.setText("");
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void txtGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtGrupo.getText(), 2));
        txtId.setText("");
        txtDescripcion.setText("");
    }//GEN-LAST:event_txtGrupoKeyReleased

    private void tblProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tblProductos.getSelectedColumn() == 2) {
                if (tblProductos.getValueAt(tblProductos.getSelectedRow(), 2).equals("")) {
                    tblProductos.setValueAt("0", tblProductos.getSelectedRow(), 2);
                }
            }
            if (tblProductos.getSelectedColumn() == 3) {
                if (tblProductos.getValueAt(tblProductos.getSelectedRow(), 3).equals("")) {
                    tblProductos.setValueAt("0", tblProductos.getSelectedRow(), 3);
                }
            }
            if (tblProductos.getSelectedColumn() == 4) {
                tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(tblProductos.getValueAt(tblProductos.getSelectedRow(), 4))), tblProductos.getSelectedRow(), 4);
            }
        }
    }//GEN-LAST:event_tblProductosKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblProductos.getModel();

        JFileChooser examinar = new JFileChooser();
        examinar.setFileFilter(new FileNameExtensionFilter("Archivos excel", "xls", "xlsx"));
        int opcion = examinar.showOpenDialog(this);
        File archivoExcel = null;

        if (opcion == JFileChooser.APPROVE_OPTION) {
            archivoExcel = examinar.getSelectedFile().getAbsoluteFile();

            try {
                Workbook leerExcel = Workbook.getWorkbook(archivoExcel);

                for (int hoja = 0; hoja < leerExcel.getNumberOfSheets(); hoja++) {
                    Sheet hojaP = leerExcel.getSheet(hoja);
                    int columnas = hojaP.getColumns();
                    int filas = hojaP.getRows();

                    Object data[] = new Object[columnas];

                    for (int fila = 0; fila < filas; fila++) {
                        for (int columna = 0; columna < columnas; columna++) {
//                            if (fila == 0) {
//                                model.addColumn(hojaP.getCell(columna, fila).getContents());
//                            }
                            if (fila >= 1) {
                                data[columna] = hojaP.getCell(columna, fila).getContents();
                            }
                        }

                        if (fila > 0) {
                            if (!hojaP.getCell(0, fila).getContents().equals("")) {
                                model.addRow(data);
                                tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(tblProductos.getValueAt(tblProductos.getRowCount() - 1, 4).toString())),
                                        tblProductos.getRowCount() - 1, 4);
                            }
                        }
                    }
                }
//                model.removeRow(0);
            } catch (IOException | BiffException ex) {
                metodos.msgError(null, "Archivo incompatible");
                return;
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblProductos.getSelectedRow() > -1) {
            DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
            int fila = tblProductos.getSelectedRow();
            modelo.removeRow(fila);
        } else {
            metodos.msgAdvertencia(null, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dlgCreacionProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgCreacionProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgCreacionProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgCreacionProductos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgCreacionProductos dialog = new dlgCreacionProductos(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JButton txtFinalizar;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
