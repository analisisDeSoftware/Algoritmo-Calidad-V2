package algoritmodecalidad;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Cursor;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Aplicacion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<Criterio> criterios;
	private Criterio criterioActual;
	private JLabel lblAnterior;
	private JLabel lblActual;
	private JLabel lblEstadoActual;
	private Color rojo = new Color(200, 0,0);
	private Color verde = new Color(0, 200, 0);
	private Color azul = new Color(0, 0, 200);
	private Color [] colores = { rojo, azul, verde};
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Aplicacion frame = new Aplicacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Aplicacion() {
		setTitle("Algoritmo de Calidad");
		setResizable(false);

		generarCriterios();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 292, Short.MAX_VALUE)))
					.addGap(17))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(44))
		);
		
		
		JLabel lblResultadoFinal = new JLabel("NO SATISFACTORIO");
		lblResultadoFinal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResultadoFinal.setForeground(new Color(128, 0, 0));
		
		JProgressBar progressBar = new JProgressBar();
		
		progressBar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int aux=0;
				if(progressBar.getValue() >= 30) {
					for(int i=0;i<15;i++){
						if(criterios.get(i).GetNivelPonderado()==4)
							aux++;
					}
					if(aux==6){
						lblResultadoFinal.setText("SATISFACTORIO");
						lblResultadoFinal.setForeground(new Color(0, 128, 0));
						return;
					}
					
				}
					lblResultadoFinal.setText("NO SATISFACTORIO");
					lblResultadoFinal.setForeground(new Color(128, 0, 0));
				
			}
		});
		progressBar.setMaximum(42);
		
		JLabel lblSatisfactorio = new JLabel("Satisfactorio");
		lblSatisfactorio.setForeground(new Color(0, 128, 0));
		
		JTextPane txtpnCadaCaractersticaPosee = new JTextPane();
		txtpnCadaCaractersticaPosee.setText("Cada caracter\u00EDstica posee un puntaje y una subcaracteristica ponderada, la cual poseer\u00E1 un mayor puntaje que las dem\u00E1s subcaracter\u00EDsticas. La sumatoria de los resultados de la evaluaci\u00F3n ponderada de las subcaracter\u00EDsticas determinar\u00E1n el puntaje de cada caracter\u00EDstica.  ");
		txtpnCadaCaractersticaPosee.setEditable(false);
		txtpnCadaCaractersticaPosee.setBackground(SystemColor.menu);
		
		JLabel label = new JLabel("No Satisfactorio");
		label.setForeground(new Color(128, 0, 0));
		
		JLabel lblResultado = new JLabel("Resultado: ");
		lblResultado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResultado.setForeground(new Color(0, 0, 0));

		
		JLabel lblLaSumatoraDe = new JLabel("La sumator\u00EDa de los puntajes de las caracter\u00EDsticas es igual o mayor a 30.");
		lblLaSumatoraDe.setForeground(Color.BLACK);
		
		JLabel lblLaSumatoraDe_1 = new JLabel("La sumator\u00EDa de los puntajes de las caracter\u00EDsticas es menor a 30.");
		lblLaSumatoraDe_1.setForeground(Color.BLACK);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnCadaCaractersticaPosee, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblSatisfactorio, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLaSumatoraDe_1, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLaSumatoraDe, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)))
						.addComponent(progressBar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblResultado)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblResultadoFinal, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnCadaCaractersticaPosee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSatisfactorio)
						.addComponent(lblLaSumatoraDe))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(lblLaSumatoraDe_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblResultado)
						.addComponent(lblResultadoFinal))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JTextPane txtpnDescripcion = new JTextPane();
		txtpnDescripcion.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtpnDescripcion.setBackground(SystemColor.control);
		txtpnDescripcion.setEditable(false);
		txtpnDescripcion.setText("Capacidad del producto software para asegurar la integridad de los datos y la confidencialidad de estos.");
		
		JLabel lblCaractersticasAMedir = new JLabel("Caracter\u00EDsticas a Medir");
		lblCaractersticasAMedir.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JTextPane txtpnCaracteristicas = new JTextPane();
		txtpnCaracteristicas.setText("Encriptaci\u00F3n de la contrase\u00F1a y datos sensibles\r\nInicio de Sesi\u00F3n");
		txtpnCaracteristicas.setEditable(false);
		txtpnCaracteristicas.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtpnCaracteristicas.setBackground(SystemColor.menu);
		
		JLabel lblEvaluacin = new JLabel("Evaluaci\u00F3n");
		lblEvaluacin.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_2 = new JLabel("Aceptable");
		lblNewLabel_2.setForeground(new Color(0, 128, 0));
		
		JTextPane txtpnAceptable = new JTextPane();
		txtpnAceptable.setText("Encriptaci\u00F3n de la contrase\u00F1a y datos sensibles\r\nInicio de Sesi\u00F3n");
		txtpnAceptable.setEditable(false);
		txtpnAceptable.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtpnAceptable.setBackground(SystemColor.menu);
		
		JLabel lblMedianamenteAceptable = new JLabel("Medianamente Aceptable");
		lblMedianamenteAceptable.setForeground(new Color(0, 0, 128));
		
		JTextPane txtpnMedianamenteAceptable = new JTextPane();
		txtpnMedianamenteAceptable.setText("Encriptaci\u00F3n de la contrase\u00F1a y datos sensibles\r\nInicio de Sesi\u00F3n");
		txtpnMedianamenteAceptable.setEditable(false);
		txtpnMedianamenteAceptable.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtpnMedianamenteAceptable.setBackground(SystemColor.menu);
		
		JLabel lblInaceptable = new JLabel("No Aceptable");
		lblInaceptable.setForeground(new Color(128, 0, 0));
		
		JTextPane txtpnNoAceptable = new JTextPane();
		txtpnNoAceptable.setText("Encriptaci\u00F3n de la contrase\u00F1a y datos sensibles\r\nInicio de Sesi\u00F3n");
		txtpnNoAceptable.setEditable(false);
		txtpnNoAceptable.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtpnNoAceptable.setBackground(SystemColor.menu);
		
		JLabel lblNivelActual = new JLabel("Nivel Actual");
		lblNivelActual.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JSlider sliderNivelActual = new JSlider();
		sliderNivelActual.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(criterioActual != null ) {
					criterioActual.setNivelActual(sliderNivelActual.getValue());
					progressBar.setValue(criterios.stream().mapToInt(x -> x.GetNivelPonderado()).sum());
					lblEstadoActual.setForeground(colores[criterioActual.getNivelActual()]);
				}
			
			}
		});
		sliderNivelActual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		sliderNivelActual.setValue(1);
		sliderNivelActual.setMaximum(2);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(txtpnCaracteristicas, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(sliderNivelActual, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblCaractersticasAMedir, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
								.addGap(114))
							.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
								.addComponent(txtpnDescripcion, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
								.addComponent(txtpnAceptable, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
								.addComponent(txtpnMedianamenteAceptable, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
								.addComponent(txtpnNoAceptable, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))
						.addComponent(lblEvaluacin, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblMedianamenteAceptable)
						.addComponent(lblInaceptable, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescripcin, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNivelActual, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDescripcin)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnDescripcion, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCaractersticasAMedir)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnCaracteristicas, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEvaluacin)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnAceptable, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMedianamenteAceptable)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnMedianamenteAceptable, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblInaceptable)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnNoAceptable, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNivelActual)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sliderNivelActual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(58, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblSeguridadDeAccesoEstado = new JLabel("\u25CF");
		lblSeguridadDeAccesoEstado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSeguridadDeAccesoEstado.setForeground(rojo);
		
		JLabel lblExactitudEstado = new JLabel("\u25CF");
		lblExactitudEstado.setForeground(rojo);
		lblExactitudEstado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblAdecuacinEstado = new JLabel("\u25CF");
		lblAdecuacinEstado.setForeground(rojo);
		lblAdecuacinEstado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblUtilizacinDeLosEstado = new JLabel("\u25CF");
		lblUtilizacinDeLosEstado.setForeground(rojo);
		lblUtilizacinDeLosEstado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblComportamientoTemporalEstado = new JLabel("\u25CF");
		lblComportamientoTemporalEstado.setForeground(rojo);
		lblComportamientoTemporalEstado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblToleranciaAFallosEstado = new JLabel("\u25CF");
		lblToleranciaAFallosEstado.setForeground(rojo);
		lblToleranciaAFallosEstado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblCapacidadDeRecuperacinEstado = new JLabel("\u25CF");
		lblCapacidadDeRecuperacinEstado.setForeground(rojo);
		lblCapacidadDeRecuperacinEstado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblCapacidadDeSerEstado = new JLabel("\u25CF");
		lblCapacidadDeSerEstado.setForeground(rojo);
		lblCapacidadDeSerEstado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblCapacidadDeSer_1Estado = new JLabel("\u25CF");
		lblCapacidadDeSer_1Estado.setForeground(rojo);
		lblCapacidadDeSer_1Estado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblCapacidadDeSer_2Estado = new JLabel("\u25CF");
		lblCapacidadDeSer_2Estado.setForeground(rojo);
		lblCapacidadDeSer_2Estado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblCapacidadDeSer_3Estado = new JLabel("\u25CF");
		lblCapacidadDeSer_3Estado.setForeground(rojo);
		lblCapacidadDeSer_3Estado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblCapacidadDeSer_4Estado = new JLabel("\u25CF");
		lblCapacidadDeSer_4Estado.setForeground(rojo);
		lblCapacidadDeSer_4Estado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblInstalabilidadEstado = new JLabel("\u25CF");
		lblInstalabilidadEstado.setForeground(rojo);
		lblInstalabilidadEstado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblAdaptabilidadEstado = new JLabel("\u25CF");
		lblAdaptabilidadEstado.setForeground(rojo);
		lblAdaptabilidadEstado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblEstabilidadEstado = new JLabel("\u25CF");
		lblEstabilidadEstado.setForeground(rojo);
		lblEstabilidadEstado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblNewLabel_1 = new JLabel("1. Funcionalidad");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblSeguridadDeAcceso = new JLabel("Seguridad de Acceso");
		lblSeguridadDeAcceso.setForeground(new Color(0, 0, 0));
		lblSeguridadDeAcceso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleLabel(lblSeguridadDeAcceso, lblSeguridadDeAccesoEstado);
				mostrarCriterio(
						txtpnDescripcion,
						txtpnCaracteristicas,
						txtpnAceptable,
						txtpnMedianamenteAceptable,
						txtpnNoAceptable,
						sliderNivelActual,
						criterios.stream()
							.filter(x -> x.getNombre().equals(lblActual.getText()))
							.findFirst().get()
						);
			}
		});
		lblSeguridadDeAcceso.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lblExactitud = new JLabel("Exactitud");
		lblExactitud.setForeground(new Color(0, 0, 0));
		lblExactitud.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblExactitud.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleLabel(lblExactitud, lblExactitudEstado);
				mostrarCriterio(
						txtpnDescripcion,
						txtpnCaracteristicas,
						txtpnAceptable,
						txtpnMedianamenteAceptable,
						txtpnNoAceptable,
						sliderNivelActual,
						criterios.stream()
							.filter(x -> x.getNombre().equals(lblActual.getText()))
							.findFirst().get()
						);
			}
		});
		
		JLabel lblAdecuacin = new JLabel("Adecuaci\u00F3n");
		lblAdecuacin.setForeground(new Color(0, 0, 0));
		lblAdecuacin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAdecuacin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleLabel(lblAdecuacin, lblAdecuacinEstado);
				mostrarCriterio(
						txtpnDescripcion,
						txtpnCaracteristicas,
						txtpnAceptable,
						txtpnMedianamenteAceptable,
						txtpnNoAceptable,
						sliderNivelActual,
						criterios.stream()
							.filter(x -> x.getNombre().equals(lblActual.getText()))
							.findFirst().get()
						);
			}
		});
		
		JLabel lblEficiencia = new JLabel("2. Eficiencia");
		lblEficiencia.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblUtilizacinDeLos = new JLabel("Utilizaci\u00F3n de los Recursos");
		lblUtilizacinDeLos.setForeground(new Color(0, 0, 0));
		lblUtilizacinDeLos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblUtilizacinDeLos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleLabel(lblUtilizacinDeLos, lblUtilizacinDeLosEstado);
				mostrarCriterio(
						txtpnDescripcion,
						txtpnCaracteristicas,
						txtpnAceptable,
						txtpnMedianamenteAceptable,
						txtpnNoAceptable,
						sliderNivelActual,
						criterios.stream()
							.filter(x -> x.getNombre().equals(lblActual.getText()))
							.findFirst().get()
						);
			}
		});
		
		JLabel lblComportamientoTemporal = new JLabel("Comportamiento Temporal");
		lblComportamientoTemporal.setForeground(new Color(0, 0, 0));
		lblComportamientoTemporal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblComportamientoTemporal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleLabel(lblComportamientoTemporal, lblComportamientoTemporalEstado);
				mostrarCriterio(
						txtpnDescripcion,
						txtpnCaracteristicas,
						txtpnAceptable,
						txtpnMedianamenteAceptable,
						txtpnNoAceptable,
						sliderNivelActual,
						criterios.stream()
							.filter(x -> x.getNombre().equals(lblActual.getText()))
							.findFirst().get()
						);
			}
		});
		
		JLabel lblFiabilidad = new JLabel("3. Fiabilidad");
		lblFiabilidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblToleranciaAFallos = new JLabel("Tolerancia a Fallos");
		lblToleranciaAFallos.setForeground(new Color(0, 0, 0));
		lblToleranciaAFallos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblToleranciaAFallos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleLabel(lblToleranciaAFallos, lblToleranciaAFallosEstado);
				mostrarCriterio(
						txtpnDescripcion,
						txtpnCaracteristicas,
						txtpnAceptable,
						txtpnMedianamenteAceptable,
						txtpnNoAceptable,
						sliderNivelActual,
						criterios.stream()
							.filter(x -> x.getNombre().equals(lblActual.getText()))
							.findFirst().get()
						);
			}
		});
		
		JLabel lblCapacidadDeRecuperacin = new JLabel("Capacidad de Recuperaci\u00F3n de Errores");
		lblCapacidadDeRecuperacin.setForeground(new Color(0, 0, 0));
		lblCapacidadDeRecuperacin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCapacidadDeRecuperacin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleLabel(lblCapacidadDeRecuperacin, lblCapacidadDeRecuperacinEstado);
				mostrarCriterio(
						txtpnDescripcion,
						txtpnCaracteristicas,
						txtpnAceptable,
						txtpnMedianamenteAceptable,
						txtpnNoAceptable,
						sliderNivelActual,
						criterios.stream()
							.filter(x -> x.getNombre().equals(lblActual.getText()))
							.findFirst().get()
						);
			}
		});
		
		JLabel lblMantenibilidad = new JLabel("4. Mantenibilidad");
		lblMantenibilidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCapacidadDeSer = new JLabel("Capacidad de ser Analizado");
		lblCapacidadDeSer.setForeground(new Color(0, 0, 0));
		lblCapacidadDeSer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCapacidadDeSer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleLabel(lblCapacidadDeSer, lblCapacidadDeSerEstado);
				mostrarCriterio(
						txtpnDescripcion,
						txtpnCaracteristicas,
						txtpnAceptable,
						txtpnMedianamenteAceptable,
						txtpnNoAceptable,
						sliderNivelActual,
						criterios.stream()
							.filter(x -> x.getNombre().equals(lblActual.getText()))
							.findFirst().get()
						);
			}
		});
		
		JLabel lblCapacidadDeSer_1 = new JLabel("Capacidad para ser Modificado");
		lblCapacidadDeSer_1.setForeground(new Color(0, 0, 0));
		lblCapacidadDeSer_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCapacidadDeSer_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleLabel(lblCapacidadDeSer_1,lblCapacidadDeSer_1Estado);
				mostrarCriterio(
						txtpnDescripcion,
						txtpnCaracteristicas,
						txtpnAceptable,
						txtpnMedianamenteAceptable,
						txtpnNoAceptable,
						sliderNivelActual,
						criterios.stream()
							.filter(x -> x.getNombre().equals(lblActual.getText()))
							.findFirst().get()
						);
			}
		});
		
		JLabel lblEstabilidad = new JLabel("Estabilidad");
		lblEstabilidad.setForeground(new Color(0, 0, 0));
		lblEstabilidad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEstabilidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleLabel(lblEstabilidad,lblEstabilidadEstado);
				mostrarCriterio(
						txtpnDescripcion,
						txtpnCaracteristicas,
						txtpnAceptable,
						txtpnMedianamenteAceptable,
						txtpnNoAceptable,
						sliderNivelActual,
						criterios.stream()
							.filter(x -> x.getNombre().equals(lblActual.getText()))
							.findFirst().get()
						);
			}
		});
		
		JLabel lblUsabilidad = new JLabel("5. Usabilidad");
		lblUsabilidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCapacidadDeSer_2 = new JLabel("Capacidad de ser Entendido");
		lblCapacidadDeSer_2.setForeground(new Color(0, 0, 0));
		lblCapacidadDeSer_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCapacidadDeSer_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleLabel(lblCapacidadDeSer_2,lblCapacidadDeSer_2Estado);
				mostrarCriterio(
						txtpnDescripcion,
						txtpnCaracteristicas,
						txtpnAceptable,
						txtpnMedianamenteAceptable,
						txtpnNoAceptable,
						sliderNivelActual,
						criterios.stream()
							.filter(x -> x.getNombre().equals(lblActual.getText()))
							.findFirst().get()
						);
			}
		});
		
		JLabel lblCapacidadDeSer_3 = new JLabel("Capacidad de ser Operado");
		lblCapacidadDeSer_3.setForeground(new Color(0, 0, 0));
		lblCapacidadDeSer_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCapacidadDeSer_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleLabel(lblCapacidadDeSer_3,lblCapacidadDeSer_3Estado);
				mostrarCriterio(
						txtpnDescripcion,
						txtpnCaracteristicas,
						txtpnAceptable,
						txtpnMedianamenteAceptable,
						txtpnNoAceptable,
						sliderNivelActual,
						criterios.stream()
							.filter(x -> x.getNombre().equals(lblActual.getText()))
							.findFirst().get()
						);
			}
		});
		
		JLabel lblCapacidadDeSer_4 = new JLabel("Capacidad de ser Atractivo");
		lblCapacidadDeSer_4.setForeground(new Color(0, 0, 0));
		lblCapacidadDeSer_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCapacidadDeSer_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleLabel(lblCapacidadDeSer_4,lblCapacidadDeSer_4Estado);
				mostrarCriterio(
						txtpnDescripcion,
						txtpnCaracteristicas,
						txtpnAceptable,
						txtpnMedianamenteAceptable,
						txtpnNoAceptable,
						sliderNivelActual,
						criterios.stream()
							.filter(x -> x.getNombre().equals(lblActual.getText()))
							.findFirst().get()
						);
			}
		});
		
		JLabel lblPortabilidad = new JLabel("6. Portabilidad");
		lblPortabilidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblAdaptabilidad = new JLabel("Adaptabilidad");
		lblAdaptabilidad.setForeground(new Color(0, 0, 0));
		lblAdaptabilidad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAdaptabilidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleLabel(lblAdaptabilidad,lblAdaptabilidadEstado);
				mostrarCriterio(
						txtpnDescripcion,
						txtpnCaracteristicas,
						txtpnAceptable,
						txtpnMedianamenteAceptable,
						txtpnNoAceptable,
						sliderNivelActual,
						criterios.stream()
							.filter(x -> x.getNombre().equals(lblActual.getText()))
							.findFirst().get()
						);
			}
		});
		
		JLabel lblInstalabilidad = new JLabel("Instalabilidad");
		lblInstalabilidad.setForeground(new Color(0, 0, 0));
		lblInstalabilidad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblInstalabilidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleLabel(lblInstalabilidad,lblInstalabilidadEstado);
				mostrarCriterio(
						txtpnDescripcion,
						txtpnCaracteristicas,
						txtpnAceptable,
						txtpnMedianamenteAceptable,
						txtpnNoAceptable,
						sliderNivelActual,
						criterios.stream()
							.filter(x -> x.getNombre().equals(lblActual.getText()))
							.findFirst().get()
						);
			}
		});
		

		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblMantenibilidad)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCapacidadDeSer)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblCapacidadDeSerEstado, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCapacidadDeSer_1)
									.addGap(10)
									.addComponent(lblCapacidadDeSer_1Estado, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblEstabilidad)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblEstabilidadEstado, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))))
						.addComponent(lblFiabilidad, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsabilidad, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPortabilidad, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblCapacidadDeRecuperacin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblUtilizacinDeLos)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblUtilizacinDeLosEstado, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblComportamientoTemporal)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblComportamientoTemporalEstado, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblToleranciaAFallos)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblToleranciaAFallosEstado, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblCapacidadDeRecuperacinEstado, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEficiencia, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSeguridadDeAcceso)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblSeguridadDeAccesoEstado))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblExactitud)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblExactitudEstado, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblAdecuacin)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblAdecuacinEstado, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCapacidadDeSer_2)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblCapacidadDeSer_2Estado, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCapacidadDeSer_3)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblCapacidadDeSer_3Estado, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCapacidadDeSer_4)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblCapacidadDeSer_4Estado, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblInstalabilidad, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblAdaptabilidad, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAdaptabilidadEstado, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblInstalabilidadEstado, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSeguridadDeAcceso)
						.addComponent(lblSeguridadDeAccesoEstado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblExactitud)
						.addComponent(lblExactitudEstado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdecuacin)
						.addComponent(lblAdecuacinEstado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEficiencia)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUtilizacinDeLos)
						.addComponent(lblUtilizacinDeLosEstado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblComportamientoTemporal)
						.addComponent(lblComportamientoTemporalEstado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblFiabilidad)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblToleranciaAFallos)
						.addComponent(lblToleranciaAFallosEstado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCapacidadDeRecuperacin)
						.addComponent(lblCapacidadDeRecuperacinEstado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMantenibilidad)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCapacidadDeSer)
						.addComponent(lblCapacidadDeSerEstado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCapacidadDeSer_1)
						.addComponent(lblCapacidadDeSer_1Estado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstabilidad)
						.addComponent(lblEstabilidadEstado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUsabilidad)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCapacidadDeSer_2)
						.addComponent(lblCapacidadDeSer_2Estado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCapacidadDeSer_3)
						.addComponent(lblCapacidadDeSer_3Estado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCapacidadDeSer_4)
						.addComponent(lblCapacidadDeSer_4Estado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPortabilidad)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdaptabilidad)
						.addComponent(lblAdaptabilidadEstado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInstalabilidad)
						.addComponent(lblInstalabilidadEstado))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		// Mostrar el primer criterio
		lblAnterior = lblSeguridadDeAcceso;
		lblActual = lblSeguridadDeAcceso;
		lblEstadoActual = lblSeguridadDeAccesoEstado;
		Font fontActual = lblActual.getFont();
		lblActual.setFont(fontActual.deriveFont(fontActual.getStyle() ^ Font.ITALIC));
		mostrarCriterio(
				txtpnDescripcion,
				txtpnCaracteristicas,
				txtpnAceptable,
				txtpnMedianamenteAceptable,
				txtpnNoAceptable,
				sliderNivelActual,
				criterios.get(0)
				);
	}

	private void generarCriterios() {
		criterios = new ArrayList<>();
		
		// 1. Funcionalidad
		
		Criterio seguridadDeAcceso = new Criterio(
				"Seguridad de Acceso",
				"Capacidad del producto software para asegurar la integridad de los datos y la confidencialidad de estos.",
				"Encriptación de la contraseña y datos sensibles." + System.lineSeparator() + "Inicio de sesión.",
				"Ninguna característica cumplida.",
				"Una característica cumplida.",
				"Dos características cumplidas.",
				true);
		criterios.add(seguridadDeAcceso);
		
		Criterio exactitud = new Criterio(
				"Exactitud",
				"Es la capacidad del producto software para proporcionar los resultados con el grado necesario de precisión.",
				"",
				"No se realiza ningún informe.",
				"Se realiza 1 informe.",
				"Se realizan 2 o más informes.",
				false);
		criterios.add(exactitud);
		
		Criterio adecuacion = new Criterio(
				"Adecuación",
				"Capacidad del producto software para proporcionar un conjunto apropiado de funciones para tareas y objetivos de usuario especificados.",
				"",
				"El software no cumple con ninguna funcionalidad requerida por el usuario.",
				"El software cumple con una funcionalidad requerida.",
				"El software cumple con al menos dos funcionalidades requeridas.",
				false);
		criterios.add(adecuacion);
		
		// 2. Eficiencia
		
		Criterio utilizacionDeLosRecursos = new Criterio(
				"Utilización de los Recursos",
				"Se evaluará la eficiencia del producto software de acuerdo al porcentaje de uso de procesador y de memoria que realice.",
				"",
				"El producto consume 51% a 100% de uso de procesador y de uso de memoria.",
				"El producto consume 26% a 50% de uso de procesador y de memoria.",
				"El producto consume 25% o menos de uso de procesador y de memoria.",
				true);
		criterios.add(utilizacionDeLosRecursos);
		
		Criterio comportamientoTemporal = new Criterio(
				"Comportamiento Temporal",
				"Se evaluará el tiempo que está el producto software sin informarle al usuario del estado en que se encuentra la solicitud que realizó.",
				"",
				"El software demora más de 6 segundos en informar el estado de la solicitud.",
				"El software demora entre 2 a 5 segundos en informar el estado de la solicitud.",
				"El software demora menos de 2 segundos en informar el estado de la solicitud.",
				false);
		criterios.add(comportamientoTemporal);
		
		// 3. Fiabilidad
		
		Criterio toleranciaAFallos = new Criterio(
				"Tolerancia a Fallos",
				"Es la capacidad del producto software de mantener la integridad de los datos cuando se producen fallas del sistema.",
				"Cuando sucede un error se protegen los datos procesados." + System.lineSeparator() + "Se realiza un log de actividades que el sistema estaba haciendo.",
				"No se cumple con ninguna característica.",
				"Se cumple con 1 característica.",
				"Se cumple con las 2 características.",
				true);
		criterios.add(toleranciaAFallos);
		
		Criterio capacidadDeRecuperacionDeErrores = new Criterio(
				"Capacidad de Recuperación de Errores",
				"Es la capacidad del sistema de reanudar sus actividades cuando se producen errores críticos.",
				"El sistema reanuda las actividades si se produce una falla crítica." + System.lineSeparator() + "Reanuda sus actividades y vuelve al estado en que estaba.",
				"No se cumple con ninguna característica.",
				"Se cumple con 1 característica.",
				"Se cumple con las 2 características.",
				false);
		criterios.add(capacidadDeRecuperacionDeErrores);
		
		// 4. Mantenibilidad 
		
		Criterio capacidadDeSerAnalizado = new Criterio(
				"Capacidad de ser Analizado",
				"Para evaluar la capacidad que tiene el código para ser analizado se tiene en cuenta el promedio de comentarios de todos los métodos del programa.",
				"",
				"El programa posee un promedio de comentarios menor al 20%.",
				"El programa posee un promedio de comentarios entre el 20% y 49%. ",
				"El programa posee un promedio de comentarios mayor al 50%.",
				false);
		criterios.add(capacidadDeSerAnalizado);
		
		Criterio capacidadParaSerModificado = new Criterio(
				"Capacidad para ser Modificado",
				"Para evaluar la capacidad que tiene el código para ser cambiado se tomará en cuenta la complejidad ciclomática del método.",
				"",
				"La complejidad ciclomática es mayor a 20.",
				"La complejidad ciclomática es entre 11 y 20.",
				"La complejidad ciclomática es menor o igual a 10.",
				false);
		criterios.add(capacidadParaSerModificado);
		
		Criterio estabilidad = new Criterio(
				"Estabilidad",
				"Para determinar la estabilidad del software se evalúa el promedio de fallas que presenta el producto por prueba.",
				"",
				"El software presenta un promedio de 4 o más fallas por prueba.",
				"El software presenta un promedio de 3 o más fallas por prueba.",
				"El software presenta un promedio de 2 o menos fallas por prueba.",
				true);
		criterios.add(estabilidad);
		
		// 5. Usabilidad
		
		Criterio capacidadDeSerEntendido = new Criterio(
				"Capacidad de ser Entendido",
				"Capacidad que posee el software, para ayudar a los usuarios ante una determinada situación donde se necesite asistencia.",
				"Ayuda contextual sobre menús y botones de acción (tooltip)." + System.lineSeparator() + "Manual de ayuda al usuario incorporado en el sistema.",
				"No se cumple con ninguna característica.",
				"Se cumple con 1 característica.",
				"Se cumple con las 2 características.",
				false);
		criterios.add(capacidadDeSerEntendido);
		
		Criterio capacidadDeSerOperado = new Criterio(
				"Capacidad de ser Operado",
				"Es la capacidad del producto software de ser utilizado sin asistencia del manual de ayuda ni de un experto.",
				"",
				"El usuario requiere ayuda de un experto para operar el software.",
				"El usuario requiere usar el manual de ayuda para poder operar el software.",
				"El usuario no requiere usar el manual de ayuda para poder operar el software.",
				false);
		criterios.add(capacidadDeSerOperado);
		
		Criterio capacidadDeSerAtractivo = new Criterio(
				"Capacidad de ser Atractivo",
				"Es la agrupación correcta de funcionalidades dentro del programa en la interfaz gráfica para poder realizar una función específica.",
				"",
				"El usuario utiliza más de 5 pasos promedio para poder realizar una función específica.",
				"El usuario necesita entre 4 y 5 pasos promedio para poder realizar una función específica.",
				"El usuario solamente necesita de 3 o menos pasos promedio para poder realizar una función específica.",
				true);
		criterios.add(capacidadDeSerAtractivo);
				
		// 6. Adaptabilidad
		
		Criterio adaptabilidad = new Criterio(
				"Adaptabilidad",
				"Es la capacidad del producto software de adaptarse a diferentes sistemas operativos sin cambiar su estructura interna.",
				"",
				"El software solo es compatible con 1 sistema operativo en concreto.",
				"El software es compatible con 2 sistemas operativos.",
				"El software es compatible con 3 o más sistemas operativos.",
				false);
		criterios.add(adaptabilidad);
		
		Criterio instalabilidad = new Criterio(
				"Instalabilidad",
				"El producto software debe poder ser instalado en una cantidad mínima de pasos.",
				"",
				"El producto se instala en 7 o más pasos.",
				"El producto se instala entre 5 y 6 pasos.",
				"El producto se instala en 4 pasos o menos.",
				true);
		criterios.add(instalabilidad);
		
	}
	
	private void mostrarCriterio(
			JTextPane txtDescripcion,
			JTextPane txtCaracteristicas,
			JTextPane txtAceptable,
			JTextPane txtMedianamenteAceptable,
			JTextPane txtNoAceptable,
			JSlider sliderNivelActual,
			Criterio criterio) {
		criterioActual = criterio;
		txtDescripcion.setText(criterio.getDescripcion());
		txtCaracteristicas.setText(criterio.getCaracteristicas());
		txtAceptable.setText(criterio.getAceptable());
		txtMedianamenteAceptable.setText(criterio.getMedianamenteAceptable());
		txtNoAceptable.setText(criterio.getNoAceptable());
		sliderNivelActual.setValue(criterio.getNivelActual());
	}
	
	private void toggleLabel(JLabel labelActual, JLabel labelEstadoActual) {
		lblAnterior = lblActual;
		Font fontAnterior = lblAnterior.getFont();
		lblAnterior.setFont(fontAnterior.deriveFont(fontAnterior.getStyle() ^ Font.ITALIC));
		lblActual = labelActual;
		Font fontActual = lblActual.getFont();
		lblActual.setFont(fontActual.deriveFont(fontActual.getStyle() ^ Font.ITALIC));
		lblEstadoActual = labelEstadoActual;
	}
	
}
