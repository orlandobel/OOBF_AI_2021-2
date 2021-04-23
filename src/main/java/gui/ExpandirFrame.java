package gui;

import Espacial.Expansion;
import Espacial.Histograma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ExpandirFrame extends JInternalFrame implements ActionListener {

    private JLabel labelR1;
    private JLabel labelR2;
    private JLabel labelAlpha;
    private JLabel labelExp;

    private JSlider sliderR1;
    private JSlider sliderR2;
    private JSlider sliderAlpha;

    private JTextField expField;

    private JButton btn_log;
    private JButton btn_exp;
    private JButton btn_ecualizar;

    private JFramePrincipal jfp;

    public ExpandirFrame(JFramePrincipal jfp) {
        this.jfp = jfp;
        initComponents();
    }

    private void initComponents() {
        labelR1 = new JLabel();
        labelR2 = new JLabel();
        labelAlpha = new JLabel();
        labelExp = new JLabel();

        sliderR1 = new JSlider();
        sliderR2 = new JSlider();
        sliderAlpha = new JSlider();
        expField = new JTextField();

        btn_log = new JButton();
        btn_exp = new JButton();
        btn_ecualizar = new JButton();

        setTitle("Expancion de imagenes");
        setClosable(true);
        setIconifiable(true);

        initLabel(labelR1, "r1");
        initLabel(labelR2, "r2");
        initLabel(labelAlpha, "Alpha de logaritmo");
        initLabel(labelExp, "Potencia");

        initSlider(sliderR1);
        initSlider(sliderR2);
        initSlider(sliderAlpha);

        initButton(btn_log, "Expansión logaritmica", "log");
        initButton(btn_exp, "Expanción por potencia", "exp");
        initButton(btn_ecualizar, "Ecualizar", "ecualizar");

        initActionListeners();

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelR1, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(sliderR1, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                                .addContainerGap(18, Short.MAX_VALUE))

                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelR2, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                                .addContainerGap(18, Short.MAX_VALUE))

                        .addGroup(layout.createSequentialGroup()
                                .addComponent(sliderR2, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                                .addContainerGap(18, Short.MAX_VALUE))

                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelAlpha, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                                .addContainerGap(18, Short.MAX_VALUE))

                        .addGroup(layout.createSequentialGroup()
                                .addComponent(sliderAlpha, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                                .addContainerGap(18, Short.MAX_VALUE))

                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelExp, GroupLayout.DEFAULT_SIZE, 40, GroupLayout.DEFAULT_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(expField, GroupLayout.DEFAULT_SIZE, 15, GroupLayout.DEFAULT_SIZE)
                                .addContainerGap(8, Short.MAX_VALUE))

                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btn_log, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btn_exp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btn_ecualizar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18,18,18)
                                .addComponent(labelR1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)

                                .addGap(5,5,5)
                                .addComponent(sliderR1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)

                                .addGap(18,18,18)
                                .addComponent(labelR2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)

                                .addGap(5,5,5)
                                .addComponent(sliderR2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)

                                .addGap(18,18,18)
                                .addComponent(labelAlpha, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)

                                .addGap(5,5,5)
                                .addComponent(sliderAlpha, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)

                                .addGap(5,5,5)
                                .addGroup(layout.createParallelGroup()
                                    .addComponent(labelExp, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(expField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                )

                                .addGap(18,18,18)
                                .addComponent(btn_log, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(0,17,Short.MAX_VALUE)
                                .addComponent(btn_exp, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(0,17, Short.MAX_VALUE)
                                .addComponent(btn_ecualizar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(0,17, Short.MAX_VALUE))
        );

        pack();
    }

    private void initActionListeners() {
        sliderR1.addChangeListener(e -> updateRange());
        sliderR2.addChangeListener(e -> updateRange());

        sliderAlpha.addChangeListener(e -> updateAlpha());

        expField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                if(!Character.isDigit(e.getKeyChar()) && '.' != e.getKeyChar()) {
                    e.consume();
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void updateRange() {
        ImageFrame iframe = jfp.getActiveImageFrame();

        int r1 = sliderR1.getValue();
        int r2 = sliderR2.getValue();

        Image nimage = Expansion.lineal(iframe.getImagenOriginal(), r1, r2);

        iframe.setImage(nimage);
    }

    private void updateAlpha() {
        ImageFrame iframe = jfp.getActiveImageFrame();

        int alpha = sliderAlpha.getValue();
        Image nimage = Expansion.logaritmica(iframe.getImagenOriginal(), alpha);

        iframe.setImage(nimage);
    }

    private void initLabel(JLabel label, String text) {
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
    }

    private void initSlider(JSlider slider) {
        slider.setMinimum(0);
        slider.setMaximum(255);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);
    }

    private void initButton(JButton btn, String title, String name) {
        btn.setText(title);
        btn.setName(name);
        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        ImageFrame iframe = jfp.getActiveImageFrame();
        Image image = iframe.getImagenOriginal();
        Image nimage = null;

        switch(button.getName()) {
            case "log":
                nimage = Expansion.logaritmica(image);
                break;
            case "exp":
                String val = expField.getText();
                if(val.charAt(0) == '.')
                    val = 0 + val;

                double exp = Double.parseDouble(val);
                nimage = Expansion.exponencial(image, exp);
                break;
            case "ecualizar":
                Histograma h = new Histograma(image);
                h.calcularHistogramas();

                nimage = Expansion.ecualizar(image, h);
                break;
            default: break;
        }

        iframe.setImage(nimage);
    }
}
