package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Graphe.Arete;
import Graphe.GrapheNonOrientePondere;

public class InterfaceNonOrientePondere extends JPanel {
	private static final long serialVersionUID = 1L;
	private GrapheNonOrientePondere graphe;
	private JLabel erreur;
	private JTextArea resultat;
	private JButton afficherMatrice, afficherFsAps, importerCout,  afficherGrapheKruskal, afficherArcOuAretes, Rang, Distance,  Kruskal, afficheGraphe, ajoutSommet, supSommet, ajoutArc, supArc;
	JComboBox<String> combo;
	JButton BtSais;
	public InterfaceNonOrientePondere() {
		super(null);
		this.setBackground(new Color(70,70,70));
        this.setLayout(null);
        JLabel sais = new JLabel("Importer le graphe");
        sais.setForeground(Color.WHITE);
        sais.setBounds(10, 10, 150, 30);
        add(sais);
		erreur = new JLabel("Erreur");
		erreur.setBounds(340, 30, 130, 21);
		erreur.setVisible(false);
		add(erreur);
		afficherMatrice = new JButton("Afficher matrice");
        afficherMatrice.setBounds(10, 80, 150, 30);
		afficherMatrice.setBackground(new Color(47,47,47));
		afficherMatrice.setForeground(Color.WHITE);
        add(afficherMatrice);
		
        afficherFsAps = new JButton("Afficher FS et APS");
        afficherFsAps.setBounds(150, 80, 150, 30);
		afficherFsAps.setBackground(new Color(47,47,47));
		afficherFsAps.setForeground(Color.WHITE);
        add(afficherFsAps);
		
		afficherArcOuAretes = new JButton("Afficher les arêtes");
		afficherArcOuAretes.setBounds(300, 80, 150, 30);
		afficherArcOuAretes.setBackground(new Color(47,47,47));
		afficherArcOuAretes.setForeground(Color.WHITE);
		add(afficherArcOuAretes);
		
		Rang = new JButton("Le rang du graphe");
		Rang.setBounds(300, 150, 150, 30);
		Rang.setBackground(new Color(47,47,47));
		Rang.setForeground(Color.WHITE);
		add(Rang);
		
		Distance = new JButton("Distance");
		Distance.setBounds(150, 150, 150, 30);
		Distance.setBackground(new Color(47,47,47));
		Distance.setForeground(Color.WHITE);
		add(Distance);
		
		ajoutSommet = new JButton("Ajouter un sommet");
		ajoutSommet.setBounds(10, 115, 150, 30);
		ajoutSommet.setBackground(new Color(47,47,47));
		ajoutSommet.setForeground(Color.WHITE);
		add(ajoutSommet);
		
		supSommet = new JButton("Supprimer un sommet");
		supSommet.setBounds(150, 115, 150, 30);
		supSommet.setBackground(new Color(47,47,47));
		supSommet.setForeground(Color.WHITE);
		add(supSommet);

		ajoutArc = new JButton("Ajouter une arête");
		ajoutArc.setBounds(300, 115, 150, 30);
		ajoutArc.setBackground(new Color(47,47,47));
		ajoutArc.setForeground(Color.WHITE);
		add(ajoutArc);
		
		supArc = new JButton("Supprimer une arête");
		supArc.setBounds(100, 115, 150, 30);
		supArc.setBackground(new Color(47,47,47));
		supArc.setForeground(Color.WHITE);
		add(supArc);
		
		
		
		combo = new JComboBox<String>();
        combo.addItem("Veuillez choisir");
        combo.addItem("Importer la matrice d'adjascence");
        combo.addItem("Importer FS et APS");
        combo.addItem("Importer les arêtes");
        combo.setBounds(150, 10, 150, 30);
        combo.setEditable(false);
		combo.setBackground(new Color(70,70,70));
		combo.setForeground(Color.WHITE);
        add(combo);
        
        BtSais = new JButton("Choisir");
        BtSais.setBounds(300, 10, 150, 30);
        BtSais.setEnabled(false);
		BtSais.setBackground(new Color(47,47,47));
		BtSais.setForeground(Color.WHITE);
        add(BtSais);
        
        importerCout = new JButton("Importer la matrice des coûts");
        importerCout.setBounds(150, 45, 300, 30);
		importerCout.setBackground(new Color(47,47,47));
		importerCout.setForeground(Color.WHITE);
        
        add(importerCout);
        
		Kruskal = new JButton("Kruskal");
		Kruskal.setBounds(450, 80, 150, 30);
		Kruskal.setBackground(new Color(47,47,47));
		Kruskal.setForeground(Color.WHITE);
		add(Kruskal);
		
        resultat = new JTextArea(10, 10);
        resultat.setBounds(10, 200, 520, 421);
        resultat.setOpaque(true);
        resultat.setBackground(new Color(100,128,128));
        resultat.setBackground(Color.LIGHT_GRAY);
        resultat.setAutoscrolls(true);
        resultat.setEditable(false);
        add(resultat);
        
        afficheGraphe = new JButton("Afficher le graphe");
        afficheGraphe.setBounds(10, 620, 150, 30);
		afficheGraphe.setBackground(new Color(47,47,47));
		afficheGraphe.setForeground(Color.WHITE);
		add(afficheGraphe);
		
		
		
		afficherGrapheKruskal = new JButton("Graphe Kruskal");
		afficherGrapheKruskal.setBounds(300, 620, 150, 30);
		afficherGrapheKruskal.setBackground(new Color(47,47,47));
		afficherGrapheKruskal.setForeground(Color.WHITE);
		add(afficherGrapheKruskal);
		
		event();
	}

	public InterfaceNonOrientePondere(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public InterfaceNonOrientePondere(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public InterfaceNonOrientePondere(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	public void event() {
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combo.getSelectedIndex() == 0)
		        	BtSais.setEnabled(false);
		        else
		        	BtSais.setEnabled(true);
			}
		});
		BtSais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphe = new GrapheNonOrientePondere(combo.getSelectedIndex());
			}
		});
		importerCout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
					graphe.chargerMatriceCoutFromFichier();
			}
		});
		Rang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					
		        	int[] rang = graphe.rang();
		        	int n = rang[0];
		        	StringBuilder data=new StringBuilder();
		    		data.append(">>>>>>RANG DES SOMMETS<<<<<<\n");
		    		  for(int i=1;i<=n;i++)
		    		  {
		    			  data.append("Le rang du sommet "+i+" est = "+rang[i]+" \n");
		    		  }
		            int max = rang[1];
		    	    for (int i = 2; i <= n ; ++i) {
		    	        if(rang[i] > max)
		    	            max = rang[i];
		    	    }
		    	    if(max == Integer.MAX_VALUE)
		    	    	data.append(">>Le rang du graphe est plus l'infini");
		    	    else
		    	    	data.append(">>Le rang du graphe est égal à "+max);
					resultat.setText(data.toString());
				}
			}
		});
		afficherMatrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					graphe.afficheMatricetext(resultat);
				}
			}
		});
		afficherFsAps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					graphe.afficheFsApstext(resultat);
				}
			}
		});
		afficherArcOuAretes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					graphe.afficheAretestext(resultat);
				}
			}
		});
		Distance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
		        	int[][] dist = graphe.distance();
					int n = dist[0][0];
					StringBuilder data=new StringBuilder();
		        	data.append(">>>>>>DISTANCE <<<<<<\n");
		            for(int i=1; i<=n; i++) {
		            	data.append("Sommet "+i+" : [ ");
		            	for(int j=1; j<=n; j++) {
		            		if(dist[i][j] == -1)
		            			data.append("∞ ");
		            		else
		            			data.append(dist[i][j]+" ");
		            	}
		            	data.append(" ]\n");
		            }
		            resultat.setText(data.toString());
				}
			}
		});
		
		
		Kruskal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					if(graphe.getD_cout() == null)
						resultat.setText("Importer d'abord la matrice des coûts.");
					else
						graphe.kurskalText(resultat);
				}
			}
		});
		ajoutSommet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					JFrame frame1 = new JFrame();
					frame1.setLayout(new GridLayout(2, 4));
					frame1.setTitle("Ajouter un nouveau sommet");
					frame1.setLocationRelativeTo(null);
				    frame1.setMinimumSize(new Dimension(500, 150));
					JLabel pred = new JLabel("Les voisins");
					frame1.add(pred);
					JComboBox<String> CheckPred = new JComboBox<String>();
					CheckPred.addItem("choisir");
					for(int i=1; i<=graphe.getD_nb_sommet();i++) {
						CheckPred.addItem("Sommet "+i);
					}
					
					frame1.add(CheckPred);
					JButton addPred = new JButton("Ajouter");
					addPred.setSize(new Dimension(150, 30));
					addPred.setEnabled(false);
					frame1.add(addPred);
					JTextField PredT = new JTextField();
					PredT.setSize(new Dimension(150, 30));
					PredT.setEditable(false);
					PredT.setText("");
					frame1.add(PredT);
					
					CheckPred.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(CheckPred.getSelectedIndex() == 0)
					        	addPred.setEnabled(false);
					        else
					        	addPred.setEnabled(true);
						}
					});
					JButton Add = new JButton("Ajouter");
					addPred.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(PredT.getText() == "") {
								PredT.setText(""+CheckPred.getSelectedIndex());
								Add.setEnabled(false);
							}
							else {
								Add.setEnabled(true);
								String[] pred = PredT.getText().split(" ");
								int i=0;
								while(i<pred.length && !(String.valueOf(CheckPred.getSelectedIndex()).equals(pred[i])))
									i++;
								if(i == pred.length)
									PredT.setText(PredT.getText()+" "+CheckPred.getSelectedIndex());
							}
								
						}
					});
					
					frame1.add(new JLabel());
					
					Add.setSize(new Dimension(150, 30));
					Add.setEnabled(false);
					Add.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String[] pred = PredT.getText().split(" ");
							graphe.ajoutNouveauSommet(pred);
						}
					});
					frame1.add(Add);
					frame1.add(new JLabel());
					
					frame1.setVisible(true);
				}
			}
		});
		supSommet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					JFrame frame1 = new JFrame();
					frame1.setLayout(new GridLayout(1, 3));
					frame1.setTitle("Supprimer un nouveau sommet");
					frame1.setLocationRelativeTo(null);
				    frame1.setMinimumSize(new Dimension(500, 150));
					JLabel pred = new JLabel("Sommet à supprimer");
					frame1.add(pred);
					JComboBox<String> CheckPred = new JComboBox<String>();
					CheckPred.addItem("choisir");
					for(int i=1; i<=graphe.getD_nb_sommet();i++) {
						CheckPred.addItem("Sommet "+i);
					}
					
					frame1.add(CheckPred);
					JButton addPred = new JButton("Supprimer");
					addPred.setSize(new Dimension(150, 30));
					addPred.setEnabled(false);
					frame1.add(addPred);
					
					CheckPred.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(CheckPred.getSelectedIndex() == 0)
					        	addPred.setEnabled(false);
					        else
					        	addPred.setEnabled(true);
						}
					});
					addPred.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							graphe.supprimerSommet(CheckPred.getSelectedIndex());
						}
					});
					
					frame1.setVisible(true);
				}
			}
		});
		ajoutArc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					JFrame frame1 = new JFrame();
					frame1.setLayout(new GridLayout(4, 3));
					frame1.setTitle("Ajouter une nouvelle arête");
					frame1.setLocationRelativeTo(null);
				    frame1.setMinimumSize(new Dimension(500, 150));
					JLabel pred = new JLabel("Extrémité 1");
					frame1.add(pred);
					JComboBox<String> CheckPred = new JComboBox<String>();
					CheckPred.addItem("choisir");
					for(int i=1; i<=graphe.getD_nb_sommet();i++) {
						CheckPred.addItem("Sommet "+i);
					}
					
					frame1.add(CheckPred);
					frame1.add(new JLabel());
					
					JButton Add = new JButton("Ajouter");
					
					
					JLabel succ = new JLabel("Extremité 2");
					frame1.add(succ);
					JComboBox<String> CheckSucc = new JComboBox<String>();
					CheckSucc.addItem("choisir");
					for(int i=1; i<=graphe.getD_nb_sommet();i++) {
						CheckSucc.addItem("Sommet "+i);
					}
					frame1.add(CheckSucc);
					frame1.add(new JLabel());
					
					CheckPred.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(CheckPred.getSelectedIndex() == 0 || CheckSucc.getSelectedIndex() == 0)
					        	Add.setEnabled(false);
					        else
					        	Add.setEnabled(true);
						}
					});
					CheckSucc.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(CheckPred.getSelectedIndex() == 0 || CheckSucc.getSelectedIndex() == 0)
					        	Add.setEnabled(false);
					        else
					        	Add.setEnabled(true);
						}
					});
					
					JLabel Lpoids = new JLabel("Le poids de l'arête");
					frame1.add(Lpoids);
					JTextField Tpoids = new JTextField();
					Tpoids.setText("0");
					frame1.add(Tpoids);
					JLabel erreur = new JLabel();
					erreur.setVisible(false);
					frame1.add(erreur);
					
					
					
					Add.setSize(new Dimension(150, 30));
					Add.setEnabled(false);
					Add.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int poids;
							try {
								poids = Integer.parseInt(Tpoids.getText());
								graphe.ajoutNouvelArete(CheckPred.getSelectedIndex(), CheckSucc.getSelectedIndex(), poids);
							}catch(NumberFormatException ex) {
								erreur.setVisible(true);
								erreur.setText("Vous devez taper un nombre");
								erreur.setForeground(Color.RED);
							}
							
						}
					});
					frame1.add(Add);
					frame1.add(new JLabel());
					
					frame1.setVisible(true);
				}
			}
		});
		supArc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					JFrame frame1 = new JFrame();
					frame1.setLayout(new GridLayout(2, 2));
					frame1.setTitle("Supprimer une arête");
					frame1.setLocationRelativeTo(null);
				    frame1.setMinimumSize(new Dimension(500, 150));
					JLabel pred = new JLabel("Arête à supprimer");
					frame1.add(pred);
					JComboBox<String> CheckPred = new JComboBox<String>();
					CheckPred.addItem("choisir");
					for(int i=0; i<graphe.getD_nb_aretes();i++) {
						CheckPred.addItem("Arc [ "+graphe.getAretePos(i).getD_sommet_depart().getD_numero()+" "+graphe.getAretePos(i).getD_sommet_arrive().getD_numero()+" ]");
					}
					
					frame1.add(CheckPred);
					
					JButton Add = new JButton("Supprimer");
					CheckPred.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(CheckPred.getSelectedIndex() == 0)
					        	Add.setEnabled(false);
					        else
					        	Add.setEnabled(true);
						}
					});
					
					
					Add.setSize(new Dimension(150, 30));
					Add.setEnabled(false);
					Add.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							graphe.supprimerArete(CheckPred.getSelectedIndex()-1);
						}
					});
					frame1.add(Add);
					frame1.add(new JLabel());
					
					frame1.setVisible(true);
				}
			}
		});
		afficheGraphe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else {
					JFrame frame1 = new JFrame();
					
					frame1.setBackground(Color.white);
					frame1.setTitle("Graphe".toUpperCase());
					frame1.setSize(new Dimension(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height));
					
					DessinGraphe de = new DessinGraphe(graphe.getD_nb_sommet(), graphe.getAretes(), "nov");
					frame1.add(de);
					de.setSize(getMaximumSize());
					
					frame1.setVisible(true);
				}
			}
		});
		
		
		afficherGrapheKruskal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else {
					JFrame frame1 = new JFrame();
					
					frame1.setBackground(Color.white);
					frame1.setTitle("Graphe".toUpperCase());
					frame1.setSize(new Dimension(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height));
					
					Arete[] f = graphe.kurskalText(resultat);
					
					DessinGrapheKruskal de = new DessinGrapheKruskal(graphe.getD_nb_sommet(), graphe.getAretes(), f);
					frame1.add(de);
					de.setSize(getMaximumSize());
					
					frame1.setVisible(true);
				}
			}
		});
	}
}
