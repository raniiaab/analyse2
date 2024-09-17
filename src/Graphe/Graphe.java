package Graphe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class Graphe 
{
	protected int[][] matrice_adjascence;
	protected int[] d_fs, d_aps, ddi, dde, app, fp, prem, pilch, cfc, num, ro, tarj, apsr, fsr, br, pred;
	protected boolean []entarj;
	protected Arete[] aretes;
	protected int d_nb_sommet;
	protected int d_nb_aretes;

	public Graphe(boolean oriente) 
	{
		saisir_graphe(oriente);
	}

	public Graphe(int val) {}

	public Graphe() {}

	public Graphe(Arete[] aretes, int nb_sommet, int nb_aretes)
	{
		this.aretes = new Arete[aretes.length];

		for(int i=0; i<aretes.length; i++)
			this.aretes[i] = new Arete(aretes[i].getD_sommet_depart(), aretes[i].getD_sommet_arrive(), aretes[i].getD_poids());
		
		this.d_nb_sommet = nb_sommet;
		this.d_nb_aretes = nb_aretes;
		aretesToMatrice();
		matriceToFsAps();
	}

	protected void empiler(int x, int[] pilch) 
	{
		pilch[x] = pilch[0];
	    pilch[0] = x;
	}

    public void ajoutSommet()
    {
    	Scanner in = new Scanner(System.in);
        int n = d_aps[0];
        int np;
        int[][] mat = new int[n+2][n+2];
        mat[0] = new int[2];
        mat[0][0] = n+1;
        mat[0][1] = matrice_adjascence[0][1];

        for (int i = 1; i <= n+1 ; ++i)
            mat[i] = new int[n+2];

        for (int i = 1; i <= n ; ++i) 
		{
            for (int j = 1; j <= n ; ++j) 
                mat[i][j] = matrice_adjascence[i][j];
        }

        for (int i = 1; i <=n+1 ; ++i) 
            mat[n+1][i] = 0;
        
        for (int i = 1; i <=n+1 ; ++i) 
            mat[i][n+1] = 0;
        
        System.out.print("Quel est le nombre de nombre de prédécésseurs du nouveau sommet n " +(n+1)+" : ");

        while(true)
		{
			String input = in.nextLine();
			try
			{	
				np = Integer.parseInt(input);
				while (np<0)
		        {
		        	System.out.print("Les nombres négatifs ne sont pas autorisés. Retaper : ");
		        	input = in.nextLine();
		        	np = Integer.parseInt(input);
		        }
				break;
			}
			catch(NumberFormatException ex)
			{
				System.out.print("Vous devez taper une valeur numérique:");
			}
		}
        
        for (int i = 1; i <= np ; ++i) 
		{
            int k;
            System.out.print(i+"Le prédécésseur : ");
            while(true)
    		{
    			String input = in.nextLine();
    			try
    			{	
    				k = Integer.parseInt(input);
    				while (k<=0 || k>n+1)
    		        {
    					System.out.printf("Ce nombre doit être compris entre 1 et %d. Retaper : ", n+1);
    		        	input = in.nextLine();
    		        	k = Integer.parseInt(input);
    		        }
    				break;
    			}
    			catch(NumberFormatException ex)
    			{
    				System.out.print("Vous devez taper une valeur numérique:");
    			}
    		}
            mat[i][k] = 1;
            mat[0][1]++;
        }
        int ns;
        System.out.print("Quel est le nombre de nombre de successeurs du nouveau sommet n "+n+1+" : ");
        while(true)
		{
			String input = in.nextLine();
			try
			{	
				ns = Integer.parseInt(input);
				while (ns<0)
		        {
		        	System.out.print("Les nombres négatifs ne sont pas autorisés. Retaper : ");
		        	input = in.nextLine();
		        	ns = Integer.parseInt(input);
		        }
				break;
			}
			catch(NumberFormatException ex)
			{
				System.out.print("Vous devez taper une valeur numérique:");
			}
		}

        for (int i = 1; i <= ns ; ++i) 
		{
            int k;
            System.out.print(i+"e successeur : ");
            while(true)
    		{
    			String input = in.nextLine();
    			try
    			{	
    				k = Integer.parseInt(input);
    				while (k<=0 || k>n+1)
    		        {
    					System.out.printf("Ce nombre doit être compris entre 1 et %d. Retaper : ", n+1);
    		        	input = in.nextLine();
    		        	k = Integer.parseInt(input);
    		        }
    				break;
    			}
    			catch(NumberFormatException ex)
    			{
    				System.out.print("Vous devez taper une valeur numérique:");
    			}
    		}
            mat[i][k] = 1;
            mat[0][1]++;
        }
        if(ns == 0 && np == 0)
        	System.out.println("Ce nouveau sommet ne peut être ajouté car il n'a aucun lien avec le graphe précédent.");
        
		else
        {
            matrice_adjascence = new int[n+2][n+2];
            for (int i = 0; i <= n+1 ; ++i) {
                matrice_adjascence[i] = mat[i];
            }
            matriceToFsAps();
            matriceToAretes();
        }
    }
    
    public void saisir_graphe(boolean oriente)
    {
    	Scanner in = new Scanner(System.in);
        int choix;

        System.out.println("----------------------------- MENU PRINCIPAL -----------------------------");
        System.out.println("Comment souhaitez-vous saisir votre graphe? ");
        System.out.println("1 - Matrice d'adjascence");
        System.out.println("2 - Importation de fichier contenant la matrice d'adjascence");
        System.out.println("3 - FS");
        System.out.println("4 - Importation de fichier contenant FS et APS");
        System.out.println("5 - Tableau d'arcs / d'arêtes");
        System.out.println("6 - Importation de fichier contenant les arcs / arêtes");
        System.out.println("7 - Quitter" );

        while(true)
		{
			String input = in.nextLine();
			try
			{	
				choix = Integer.parseInt(input);
				while(choix < 1 || choix > 7)
		        {
		        	System.out.print("Saisir un nombre correct : ");
		        	input = in.nextLine();
		        	choix = Integer.parseInt(input);
		        }
				break;
			}
			catch(NumberFormatException ex)
			{
				System.out.print("Vous devez taper une valeur numérique:");
			}
		}
        String fichier;
        switch (choix) 
        {
        	
	        case 1: saisir_matrice(); break;

	        case 2: 
	        	System.out.print("Veuillez saisir le nom du fichier sans l'extension : ");
	        	fichier = in.nextLine();
	        	chargerMatriceFromFichier("../GrapheAlgo/"+fichier+".txt"); break;

	        case 3: saisir_fs_aps();break;

	        case 4:
	        	System.out.print("Veuillez saisir le nom du fichier sans l'extension : ");
	        	fichier = in.nextLine();
	        	chargerFsApsFromFichier("../GrapheAlgo/"+fichier+".txt"); break;

	        case 5: 
	        	if(oriente) saisir_arc();
	        	else saisir_aretes();
	        	break;

	        case 6:
	        	System.out.print("Veuillez saisir le nom du fichier sans l'extension : ");
	        	fichier = in.nextLine();
	        	if(oriente) chargerArcsFromFichier("../GrapheAlgo/"+fichier+".txt");
	        	else chargerAretesFromFichier("../GrapheAlgo/"+fichier+".txt"); break;

	        default: System.exit(0);
	    }
    }
    
    public void matriceToFsAps()
    {
        int NombreSommet=matrice_adjascence[0][0];
        int NombreArc=matrice_adjascence[0][1];

        d_fs=new int [NombreSommet+NombreArc+1];
        d_aps=new int[NombreSommet+1];
        d_fs[0]=NombreSommet+NombreArc;
        d_aps=new int[NombreSommet+1];
        d_aps[0]=NombreSommet;

        int k = 1;

        for(int i=1;i<=NombreSommet;i++)
        {
        	d_aps[i] = k;
            for(int j=1;j<=NombreSommet;j++)
            {
                if(matrice_adjascence[i][j]==1)
                {
                    d_fs[k]=j;
                    k++;
                }
            }
            d_fs[k]=0;
            k++;
        }
    }

    public static void matriceToFsAps(int [][] mat, int []fs, int[]aps)
    {
        int NombreSommet=mat[0][0];
        int NombreArc=mat[0][1];

        fs=new int [NombreSommet+NombreArc+1];
        aps=new int[NombreSommet+1];
        fs[0]=NombreSommet+NombreArc;
        aps=new int[NombreSommet+1];
        aps[0]=NombreSommet;

        int k = 1;

        for(int i=1;i<=NombreSommet;i++)
        {
        	aps[i] = k;
            for(int j=1;j<=NombreSommet;j++)
            {
                if(mat[i][j]==1)
                {
                    fs[k]=j;
                    k++;
                }
            }
            fs[k]=0;
            k++;
        }
    }
    
    public void fsApsToMatrice()
    {
    	int n = d_aps[0];
        matrice_adjascence = new int [n+1][n+1];

        for (int i = 0; i <= n; i++)
		matrice_adjascence[i] = new int[n + 1];
        
        matrice_adjascence[0][0] = n;
        matrice_adjascence[0][1] = d_fs[0] - n;
        
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
            	matrice_adjascence[i][j] = 0;
        
        for (int i = 1; i <= n; i++)
        {
            int k = d_aps[i];
            while (d_fs[k] != 0)
            {
            	matrice_adjascence[i][d_fs[k]] = 1;
                k++;
            }
        }
    }
    
    public void demi_degre_int()
    {
        int n = d_aps[0];
        int taille_fs = d_fs[0];
        ddi = new int [n+1];
        ddi[0] = n;

        for (int i = 1; i <= n; ++i)
            ddi[i] = 0;

        for (int i = 1; i < taille_fs; ++i)
            if (d_fs[i] != 0)
                ddi[d_fs[i]]++;
    }
    
    public void demi_degre_ext()
    {
        int n = d_aps[0];
        dde = new int [n+1];
        dde[0] = n;

        for (int i = 1; i < n; ++i)
            dde[i] = d_aps[i + 1] - d_aps[i] - 1;

        dde[n] = d_fs[0] - d_aps[n];
    }
    
    public void saisir_matrice()
    {
    	Scanner in = new Scanner(System.in);
    	System.out.println( "Saisie d'un graphe par sa matrice d'adjascence \n");
        int n, m = 0;
        System.out.print("Donner le nombre de sommets du graphe : ");
        
        while(true)
		{
			String input = in.nextLine();
			try
			{	
				n = Integer.parseInt(input);
				while (n<=0)
		        {
					System.out.printf("Le nombre de sommets minimum pour former un graphe doit être supérieur ou égal à 2, retaper : ");
		        	input = in.nextLine();
		        	n = Integer.parseInt(input);
		        }
				break;
			}
			catch(NumberFormatException ex)
			{
				System.out.print("Vous devez taper une valeur numérique:");
			}
		}
        
        matrice_adjascence = new int[n+1][n+1];
        matrice_adjascence[0] = new int[2];

        for (int k = 1; k <= n ; ++k)
            matrice_adjascence[k] = new int[n+1];
        
        for (int k = 1; k <= n ; ++k) 
		{
            for (int l = 1; l <= n ; ++l)
                matrice_adjascence[k][l] = 0;
        }

        matrice_adjascence[0][0] = n;

        for (int i = 1; i <= n; i++)
        {
            int ns;
            System.out.printf("Donner le nombre de successeurs du sommet %d : ", i);
            
            while(true)
    		{
    			String input = in.nextLine();
    			try
    			{	
    				ns = Integer.parseInt(input);
    				while (ns<0)
    		        {
    					System.out.printf("Le nombre de successeurs d'un sommet est au minimum 0 : ");
    					input = in.nextLine();
    		        	ns = Integer.parseInt(input);
    		        }
    				break;
    			}
    			catch(NumberFormatException ex)
    			{
    				System.out.print("Vous devez taper une valeur numérique:");
    			}
    		}

            for (int j = 1; j <= ns; j++)
            {
                int k;
                System.out.printf(j+"e successeur : ");
                
                while(true)
        		{
        			String input = in.nextLine();
        			try
        			{	
        				k = Integer.parseInt(input);
        				while (k<=0 || k>n)
        		        {
                            System.out.printf("Le numéro du successeur doit être compris entre 1 et %d, retaper : ", n);
        					input = in.nextLine();
        		        	k = Integer.parseInt(input);
        		        }
        				break;
        			}
        			catch(NumberFormatException ex)
        			{
        				System.out.print("Vous devez taper une valeur numérique:");
        			}
        		}
                matrice_adjascence[i][k] = 1;
                m++;
            }
        }
        matrice_adjascence[0][1] = m;
        matriceToFsAps();
        matriceToAretes();
    }
    
    public void saisir_fs_aps()
    {
    	Scanner in = new Scanner(System.in);
    	System.out.println( "Saisie d'un graphe par FS \n");
        int n, m;
        System.out.print("Donner le nombre de sommets du graphe : ");
        
        while(true)
		{
			String input = in.nextLine();
			try
			{	
				n = Integer.parseInt(input);
				while (n<=0)
		        {
		            System.out.printf("Le nombre de sommets minimum pour former un graphe doit être supérieur ou égal à 2, retaper : ");
					input = in.nextLine();
		        	n = Integer.parseInt(input);
		        }
				break;
			}
			catch(NumberFormatException ex)
			{
				System.out.print("Vous devez taper une valeur numérique:");
			}
		}

        System.out.print("Donner le nombre d'arêtes ou d'arcs du graphe : ");
        
        while(true)
		{
			String input = in.nextLine();
			try
			{	
				m = Integer.parseInt(input);
				while (m<=0)
		        {
		            System.out.printf("Le nombre d'arêtes ou d'arcs doit etre positif ou nul, retaper : ");
					input = in.nextLine();
		        	m = Integer.parseInt(input);
		        }
				break;
			}
			catch(NumberFormatException ex)
			{
				System.out.print("Vous devez taper une valeur numérique:");
			}
		}

        d_fs = new int[n+m+1];
        d_fs[0] = n+m;
        d_aps = new int[n+1];
        d_aps[0] = n;
        d_aps[1] = 1;

        int i = 1;
        int iAps = 1;

        while(i < n+m)
        {
            int ns;
            System.out.printf("Donner le nombre de successeurs du sommet %d : ", iAps);
            
            while(true)
    		{
    			String input = in.nextLine();
    			try
    			{	
    				ns = Integer.parseInt(input);
    				while (ns<0)
    		        {
    	            	System.out.printf("Le nombre de successeurs d'un sommet est au minimum 0 : ");
    					input = in.nextLine();
    		        	ns = Integer.parseInt(input);
    		        }
    				break;
    			}
    			catch(NumberFormatException ex)
    			{
    				System.out.print("Vous devez taper une valeur numérique:");
    			}
    		}

            for (int j = 1; j <= ns; j++)
            {
                int k;
                System.out.print(j+"e successeur : ");
                
                while(true)
        		{
        			String input = in.nextLine();
        			try
        			{	
        				k = Integer.parseInt(input);
        				while (k<=0 || k>n)
        		        {
                        	System.out.printf("Le numéro du successeur doit être compris entre 1 et %d, retaper : ", n);
        					input = in.nextLine();
        		        	k = Integer.parseInt(input);
        		        }
        				break;
        			}
        			catch(NumberFormatException ex)
        			{
        				System.out.print("Vous devez taper une valeur numérique:");
        			}
        		}
                d_fs[i] = k;
                i++;
            }
            d_fs[i] = 0;
            i++;
            if(iAps != 5)
            	d_aps[++iAps] = i;
        }
        fsApsToMatrice();
        matriceToAretes();
    }
    
    public void saisir_aretes()
    {
    	Scanner in = new Scanner(System.in);
        int s, t, n, m;
        System.out.println("Saisie d'un graphe arête par arête " );
        System.out.print("Donnez le nombre de sommets : ");
        
        while(true)
		{
			String input = in.nextLine();
			try
			{	
				n = Integer.parseInt(input);
				while (n<2)
		        {
		            System.out.printf("Le nombre de sommets minimum pour former un graphe doit être supérieur ou égal à 2, retaper : ");
					input = in.nextLine();
		        	n = Integer.parseInt(input);
		        }
				break;
			}
			catch(NumberFormatException ex)
			{
				System.out.print("Vous devez taper une valeur numérique:");
			}
		}

        setD_nb_sommet(n);
        
        matrice_adjascence = new int[n+1][n+1];

        for (int i = 1; i <= n ; ++i)
            matrice_adjascence[i] = new int[n+1];
        
        matrice_adjascence[0] = new int[2];

        for (int i = 1; i <= n ; ++i) 
		{
            for (int j = 1; j <= n ; ++j)
                matrice_adjascence[i][j] = 0;
        }

        matrice_adjascence[0][0] = n;
        System.out.print( "Donnez le nombre d'aretes : ");
        
        while(true)
		{
			String input = in.nextLine();
			try
			{	
				m = Integer.parseInt(input);
				while (m<=0)
		        {
		            System.out.printf("Le nombre d'arêtes doit etre positif ou nul, retaper : ");
					input = in.nextLine();
		        	m = Integer.parseInt(input);
		        }
				break;
			}
			catch(NumberFormatException ex)
			{
				System.out.print("Vous devez taper une valeur numérique:");
			}
		}

        matrice_adjascence[0][1] = 2*m;
        setD_nb_aretes(m);
        setAretes(new Arete[m]);

        System.out.println("Saisissez les " + m + " arêtes");

        for (int i = 0; i < m; i++)
        {
        	System.out.println( "arête n " + (i + 1) + " :" );
        	System.out.print( "extrémité 1 (entre 1 et " + n + " ) : ");
            
            while(true)
    		{
    			String input = in.nextLine();
    			try
    			{	
    				s = Integer.parseInt(input);
    				while ((s < 1) && (s > n))
    				{
    	            	System.out.print( "Le numéro doit être compris entre 1 et : "+n+", retaper :");
    					input = in.nextLine();
    		        	s = Integer.parseInt(input);
    		        }
    				break;
    			}
    			catch(NumberFormatException ex)
    			{
    				System.out.print("Vous devez taper une valeur numérique:");
    			}
    		}
            System.out.print( "extrémité 2 (entre 1 et " + n + " ) : ");
            
            while(true)
    		{
    			String input = in.nextLine();
    			try
    			{	
    				t = Integer.parseInt(input);
    				while ((t < 1) && (t > n))
    				{
    	            	System.out.print( "Le numéro doit être compris entre 1 et : "+n+", retaper :");
    					input = in.nextLine();
    		        	t = Integer.parseInt(input);
    		        }
    				break;
    			}
    			catch(NumberFormatException ex)
    			{
    				System.out.print("Vous devez taper une valeur numérique:");
    			}
    		}
            getAretes()[i] = new Arete(new Sommet(s), new Sommet(t));
            matrice_adjascence[s][t] = 1;
            matrice_adjascence[t][s] = 1;
        }
        matriceToFsAps();
        afficheMatrice();
        afficheFsAps();
        afficheAretes();
    }
    
    public void saisir_arc()
    {
    	Scanner in = new Scanner(System.in);
        int s, t, n, m;
        System.out.println("Saisie d'un graphe arc par arc " );
        System.out.print("Donnez le nombre de sommets : ");
        
        while(true)
		{
			String input = in.nextLine();
			try
			{	
				n = Integer.parseInt(input);
				while (n < 2)
				{
		            System.out.printf("Le nombre de sommets minimum pour former un graphe doit être supérieur ou égal à 2, retaper : ");
					input = in.nextLine();
		        	n = Integer.parseInt(input);
		        }
				break;
			}
			catch(NumberFormatException ex)
			{
				System.out.print("Vous devez taper une valeur numérique:");
			}
		}
        setD_nb_sommet(n);
        matrice_adjascence = new int[n+1][n+1];

        for (int i = 1; i <= n ; ++i)
            matrice_adjascence[i] = new int[n+1];
        
        matrice_adjascence[0] = new int[2];

        for (int i = 1; i <= n ; ++i) 
		{
            for (int j = 1; j <= n ; ++j)
                matrice_adjascence[i][j] = 0;
        }

        matrice_adjascence[0][0] = n;
        System.out.print( "Donnez le nombre d'arcs : ");
        
        while(true)
		{
			String input = in.nextLine();
			try
			{	
				m = Integer.parseInt(input);
				while (m <= 0)
				{
		            System.out.printf("Le nombre d'arcs doit etre positif ou nul, retaper : ");
					input = in.nextLine();
		        	m = Integer.parseInt(input);
		        }
				break;
			}
			catch(NumberFormatException ex)
			{
				System.out.print("Vous devez taper une valeur numérique:");
			}
		}
        matrice_adjascence[0][1] = m;
        setD_nb_aretes(m);
        setAretes(new Arete[m]);

        System.out.printf("Saisissez les " + m + " arcs");

        for (int i = 0; i < m; i++)
        {
        	System.out.println( "arc n " + (i + 1) + " :" );
        	System.out.print( "extrémité 1 (entre 1 et " + n + " ) : ");
        	while(true)
    		{
    			String input = in.nextLine();
    			try
    			{	
    				s = Integer.parseInt(input);
    				while ((s < 1) && (s > n))
    				{
    	            	System.out.print( "Le numéro doit être compris entre 1 et : "+n+", retaper :");
    					input = in.nextLine();
    		        	s = Integer.parseInt(input);
    		        }
    				break;
    			}
    			catch(NumberFormatException ex)
    			{
    				System.out.print("Vous devez taper une valeur numérique:");
    			}
    		}
            
            System.out.print( "extrémité 2 (entre 1 et " + n + " ) : ");
            while(true)
    		{
    			String input = in.nextLine();
    			try
    			{	
    				t = Integer.parseInt(input);
    				while ((t < 1) && (t > n))
    				{
    	            	System.out.print( "Le numéro doit être compris entre 1 et : "+n+", retaper :");
    					input = in.nextLine();
    		        	t = Integer.parseInt(input);
    		        }
    				break;
    			}
    			catch(NumberFormatException ex)
    			{
    				System.out.print("Vous devez taper une valeur numérique:");
    			}
    		}
            getAretes()[i] = new Arete(new Sommet(s), new Sommet(t));
            matrice_adjascence[s][t] = 1;
        }
        matriceToFsAps();
    }

    public void afficheAretes()
    {
    	System.out.println("Aretes/Arcs : ");
    	for(int i=0; i<getD_nb_aretes(); ++i)
    		System.out.println("[ " + getAretes()[i].getD_sommet_depart().getD_numero() + " " + getAretes()[i].getD_sommet_arrive().getD_numero() + " " +getAretes()[i].getD_poids() + " ]");
    }

    public void distance(int r, int[] dist)
    {
    	int n = d_aps[0];
        int []fa = new int[n+1];
        fa[0] = n;
        int i = 0, j = 1, k = 0, ifin, s, t, it;
        fa[1] = r;

        for(int h = 1; h <= n; h++)
            dist[h] = -1;

        dist[r] = 0;
        while(i < j)
        {
            k++;
            ifin = j;
            while (i < ifin)
            {
                i++;
                s = fa[i];
                it = d_aps[s];
                t = d_fs[it];
                while (t > 0)
                {
                    if (dist[t] == -1)
                    {
                        j++;
                        fa[j] = t;
                        dist[t] = k;
                    }
                    t = d_fs[++it];
                }
            }
        }
    }

    public int[][] distance()
    {
        int nbSommet=d_aps[0];
        int [][]mat;
        mat=new int[nbSommet+1][nbSommet+1];
        mat[0][0]=nbSommet;
        mat[0][1]=d_fs[0]-nbSommet;

        for(int i=1;i<=nbSommet;i++)
            distance(i, mat[i]);
        
        return mat;
    }

    public void readFile(String fileName) {}

    public void afficheFsAps()
    {
    	System.out.print("FS : [ ");

        for (int i = 1; i <= d_fs[0] ; ++i)
        	System.out.print(d_fs[i]+" ");

        System.out.println("]");
        System.out.print("APS : [ ");

        for (int i = 1; i <= d_aps[0] ; ++i)
        	System.out.print(d_aps[i]+" ");

        System.out.println("]");
    }

    public void afficheMatrice()
    {
    	System.out.println("Matrice d'adjascence : ");

        for(int i=1;i<=matrice_adjascence[0][0];i++)
		{
        	System.out.print("[ ");

            for(int j=1;j<=matrice_adjascence[0][0];j++)
            	System.out.print(matrice_adjascence[i][j]+" ");

            System.out.println("]");
        }
    }

    public int[] getFs()
    {
        return d_fs;
    }

    public int[] getAps()
    {
        return d_aps;
    }

    public int[][] getMatrice()
    {
        return matrice_adjascence;
    }

    public int[] rang()
    {
    	int []rang;
    	int n = d_aps[0], taillefs = d_fs[0], s, k,h,t;
        rang = new int[n+1];
        rang[0] = n;
        int[] d_ddi= new int[n+1];
        pilch= new int[n+1];
        prem= new int[n+1];

        for(int i=1; i <=n ; i++)
			d_ddi[i]=0;
        
        for(int i=1; i <=taillefs ; i++)
        {
            s=d_fs[i];
            if (s > 0)
                d_ddi[s]++;
        }
        
        pilch[0]=0;
        for(s = 1; s <= n; s++)
        {
            rang[s] = Integer.MAX_VALUE;
            if (d_ddi[s] == 0)
                empiler(s,pilch);
        }

        k=-1;
        s=pilch[0];
        prem[0] = s;
        while (pilch[0] > 0)
        {
            k++;
            pilch[0] = 0;
            while (s > 0)
            {
                rang[s] = k;
                h = d_aps[s]; t = d_fs[h];
                while (t > 0)
                {
                    d_ddi[t]--;
                    if (d_ddi[t] == 0) 
                    	empiler(t,pilch);
                    h++;
                    t=d_fs[h];
                }
                s = pilch[s];
            }
            s = pilch[0];
            prem[k+1] = s;
        }
        return rang;
    }
 
    public void afficheDDE()
    {
        int n = d_aps[0];
        System.out.print(">>DDE : [ ");

        for (int i = 1; i <= n; ++i)
        	System.out.print(dde[i]+" ");

        System.out.print("]");
    }

    public void afficheDDI()
    {
        int n = d_aps[0];
        System.out.print(">>DDI : [ ");

        for (int i = 1; i <= n; ++i)
        	System.out.print(ddi[i]+" ");

        System.out.println("]");
    }

    public void afficheMatrice(int[][] m)
    {
        for(int i=0;i<=m[0][0];i++)
		{
            for(int j=0;j<=m[0][0];j++)
            	System.out.println(" "+m[i][j]);
            
			System.out.println();

        }
		
		System.out.println();
    }

    public void matriceToAretes()
    {
        int m = matrice_adjascence[0][1], n = matrice_adjascence[0][0];
        setAretes(new Arete[m]);
        int k = 0;

        for (int i = 1; i <= n ; ++i) 
		{
            for (int j = 1; j <= n ; ++j) 
			{
                if(matrice_adjascence[i][j] == 1)
                {
                    getAretes()[k] = new Arete(new Sommet(i), new Sommet(j));
                    k++;
                }
            }
        }
    }
    
    public void aretesToMatrice()
    {
        matrice_adjascence = new int[getD_nb_sommet()+1][getD_nb_sommet()+1];
        matrice_adjascence[0][0] = getD_nb_sommet();
        matrice_adjascence[0][1] = 0;
        
        for (int i = 1; i <= getD_nb_sommet() ; ++i) 
		{
            for (int j = 1; j <= getD_nb_sommet() ; ++j)
                matrice_adjascence[i][j] = 0;
            
        }

        for (int i = 0; i < getD_nb_aretes() ; ++i) 
		{
            matrice_adjascence[aretes[i].getD_sommet_depart().getD_numero()][aretes[i].getD_sommet_arrive().getD_numero()] = 1;
            matrice_adjascence[0][1]++;
        }
    }

    public static int[][] aretesToMatrice(Arete[] aretes, int nb_sommet)
    {
        int [][] mat = new int[nb_sommet+1][nb_sommet+1];
        mat[0][0] = nb_sommet;
        mat[0][1] = 0;
        
        for (int i = 1; i <= nb_sommet ; ++i) 
		{
            for (int j = 1; j <= nb_sommet ; ++j)
                mat[i][j] = 0;
            
        }

        for (int i = 0; i < aretes.length ; ++i) 
		{
            mat[aretes[i].getD_sommet_depart().getD_numero()][aretes[i].getD_sommet_arrive().getD_numero()] = 1;
            mat[0][1]++;
        }

        return mat;
    }
    
    public void trier()
    {
        Arete p;
        int m = getD_nb_aretes();
        
        for (int i = 0; i < m - 1; i++) 
		{
            for (int j = i + 1; j < m; j++) 
			{
                if ((aretes[j].getD_poids() < aretes[i].getD_poids()) || (aretes[j].getD_poids() == aretes[i].getD_poids() && aretes[j].getD_sommet_depart().getD_numero() < aretes[i].getD_sommet_depart().getD_numero())) 
				{
                    p = aretes[j];
                    aretes[j] = aretes[i];
                    aretes[i] = p;
                }
            }
        }
    }
    
    protected void fs_aps_2_fp_app()
    {
        int n = d_aps[0], m = d_fs[0];
        fp = new int [m+1];
        fp[0] = m;
        app = new int[n+1];
        demi_degre_int();
        app[0] = n;
        app[1] = 1;

        for (int i = 2; i <= n; ++i)
            app[i] = app[i-1] + ddi[i-1] + 1;

        int i = 1;

        for (int j = 1; j < m; ++j)
        {
            if(d_fs[j] != 0)
            {
                fp[app[d_fs[j]]] = i;
                app[d_fs[j]]++;
            }
            else
                i++;
        }

        for (int j = 1; j <= n; ++j)
            fp[app[j]] = 0;

        for (int j = n; j >= 1; --j)
            app[j] = app[j-1] + 1;

        app[1] = 1;
    }
    
    public int depiler(int[] pile)
    {
        int x = pile[0];
        pile[0] = pile[x];
        return x;
    }

    public void creerUnFichierMatrice() {}

    public void chargerMatriceFromFichier(String nomFichier) 
	{
		try
		{
			FileReader file1=new FileReader(nomFichier);
			BufferedReader in = new BufferedReader(file1);
			String line;

			line = in.readLine();
			setD_nb_sommet(Integer.parseInt(line.split(" ")[0]));
			setD_nb_aretes(Integer.parseInt(line.split(" ")[1]));
			line = in.readLine();

			matrice_adjascence = new int[getD_nb_sommet()+1][getD_nb_sommet()+1];
			matrice_adjascence[0][0] = getD_nb_sommet();
			matrice_adjascence[0][1] = getD_nb_aretes();

			for(int i=1; i<=getD_nb_sommet(); i++)
				matrice_adjascence[i] = new int[getD_nb_sommet()+1];

			int i = 1;

			while(line != null)
			{
				String []tab = line.split(" ");
				for(int j=0; j<=getD_nb_sommet(); j++)
					matrice_adjascence[i][j] = Integer.parseInt(tab[j]);
				i++;
				line = in.readLine();
			}

			in.close();	
			file1.close();
		}
		catch(Exception e)
		{
			System.out.println ("Fichier introuvable. "+e.getMessage()+".\nL'application va se fermer...");
			System.exit(0);
		}

		if(matrice_adjascence != null)
		{
			matriceToFsAps();
			matriceToAretes();
		}
    }
    
    public void chargerAretesFromFichier(String nomFichier)
	{
    	try
		{
			FileReader file1=new FileReader(nomFichier);
			BufferedReader in = new BufferedReader(file1);
			String line;

			line = in.readLine();
			setD_nb_sommet(Integer.parseInt(line));
			line = in.readLine();
			setD_nb_aretes(Integer.parseInt(line));
			aretes = new Arete[Integer.parseInt(line)];

			matrice_adjascence = new int[getD_nb_sommet()+1][getD_nb_sommet()+1];
			matrice_adjascence[0][0] = getD_nb_sommet();
			matrice_adjascence[0][1] = 2*getD_nb_aretes();

			for(int i=1; i<=getD_nb_sommet(); i++)
				for(int j=1; j<=getD_nb_sommet(); j++)
					matrice_adjascence[i][j] = 0;

			int k = 0;
			line = in.readLine();

			while(line != null)
			{
				String []tab = line.split(" ");
				int s = Integer.parseInt(tab[0]);
				int t = Integer.parseInt(tab[1]);
				int p = Integer.parseInt(tab[2]);
			
				aretes[k] = new Arete(new Sommet(s), new Sommet(t), p);
				matrice_adjascence[s][t] = 1;
				matrice_adjascence[t][s] = 1;
				k++;
				line = in.readLine();
			}

			in.close();	
			file1.close();
        	matriceToFsAps();
		}
		catch(Exception e)
		{
			System.out.println ("Fichier introuvable. "+e.getMessage()+".\nL'application va se fermer...");
			System.exit(0);
		}
    }

    public void chargerArcsFromFichier(String nomFichier)
	{
    	try
		{
			FileReader file1=new FileReader(nomFichier);
			BufferedReader in = new BufferedReader(file1);
			String line;

			line = in.readLine();
			setD_nb_sommet(Integer.parseInt(line));
			line = in.readLine();
			setD_nb_aretes(Integer.parseInt(line));
			setAretes(new Arete[getD_nb_aretes()]);

			matrice_adjascence = new int[getD_nb_sommet()+1][getD_nb_sommet()+1];
			matrice_adjascence[0][0] = getD_nb_sommet();
			matrice_adjascence[0][1] = getD_nb_aretes();

			for(int i=1; i<=getD_nb_sommet(); i++)
				for(int j=1; j<=getD_nb_sommet(); j++)
					matrice_adjascence[i][j] = 0;

			int k = 0;
			line = in.readLine();

			while(line != null)
			{
				String []tab = line.split(" ");
				int s = Integer.parseInt(tab[0]);
				int t = Integer.parseInt(tab[1]);
				int p = Integer.parseInt(tab[2]);
				getAretes()[k] = new Arete(new Sommet(s), new Sommet(t), p);
				matrice_adjascence[s][t] = 1;
				k++;
				line = in.readLine();
			}

			in.close();	
			file1.close();
        	matriceToFsAps();
		}
		catch(Exception e)
		{
			System.out.println ("Fichier introuvable. "+e.getMessage()+".\nL'application va se fermer...");
			System.exit(0);
		}
    }

    public void chargerFsApsFromFichier(String nomFichier)
	{
		try
		{
			FileReader file1=new FileReader(nomFichier);
			BufferedReader in = new BufferedReader(file1);
			String line;
			
			line = in.readLine();
			String []tab1 = line.split(" ");
			int m = Integer.parseInt(tab1[0]);
			d_fs = new int[m+1];
			d_fs[0] = m;

			for(int j=1; j<=m; j++)
				d_fs[j] = Integer.parseInt(tab1[j]);
			
			line = in.readLine();
			String []tab2 = line.split(" ");
			int n = Integer.parseInt(tab2[0]);
			d_aps = new int[n+1];
			d_aps[0] = n;

			for(int j=1; j<=n; j++)
				d_aps[j] = Integer.parseInt(tab2[j]);
			
			setD_nb_sommet(n);
			setD_nb_aretes(m - n);
			in.close();	
			file1.close();
			fsApsToMatrice();
			matriceToAretes();
		}
		catch(Exception e)
		{
			System.out.println ("Fichier introuvable. "+e.getMessage()+".\nL'application va se fermer...");
			System.exit(0);
		}
    }

    public void chargerMatriceFromFichier() 
	{
    	JFileChooser file=new JFileChooser();
		int reponse=file.showOpenDialog(null);
		String nomFichier="";

		if(reponse==JFileChooser.APPROVE_OPTION)
			nomFichier=file.getSelectedFile().getAbsolutePath();
		
		chargerMatriceFromFichier(nomFichier);
    }
    
    public void chargerFsApsFromFichier() 
	{
    	JFileChooser file=new JFileChooser();
		int reponse=file.showOpenDialog(null);
		String nomFichier="";

		if(reponse==JFileChooser.APPROVE_OPTION)
			nomFichier=file.getSelectedFile().getAbsolutePath();
		
		chargerFsApsFromFichier(nomFichier);
    }

    public void chargerAretesFromFichier() 
	{
    	JFileChooser file=new JFileChooser();
		int reponse=file.showOpenDialog(null);
		String nomFichier="";

		if(reponse==JFileChooser.APPROVE_OPTION)
			nomFichier=file.getSelectedFile().getAbsolutePath();
		
		chargerAretesFromFichier(nomFichier);
    }

    public void chargerArcsFromFichier() 
	{
    	JFileChooser file=new JFileChooser();
		int reponse=file.showOpenDialog(null);
		String nomFichier="";

		if(reponse==JFileChooser.APPROVE_OPTION)
			nomFichier=file.getSelectedFile().getAbsolutePath();
		
		chargerArcsFromFichier(nomFichier);
    }

    public void afficheMatricetext(JTextArea textArea)
	{	
		StringBuilder data=new StringBuilder();
		data.append("-------- Matrice d'adjascence --------\n");

		for(int i=0;i<=matrice_adjascence[0][0];i++)
		{
			for(int j=0;j<=matrice_adjascence[0][0];j++)
			{
				data.append(matrice_adjascence[i][j]+" ");
			}
			data.append("\n");
		}
		textArea.setText(data.toString());
	}

    public void afficheFsApstext(JTextArea textArea)
	{	
		StringBuilder data=new StringBuilder();
		data.append("-------- FS --------\n");

		for(int i=0;i<=d_fs[0];i++)
		{
			data.append(d_fs[i]+" ");
		}

		data.append("\n-------- APS --------\n");

		for(int i=0;i<=d_aps[0];i++)
		{
			data.append(d_aps[i]+" ");
		}

		data.append("\n");
		textArea.setText(data.toString());
	}

    public void afficheAretestext(JTextArea textArea)
	{	
		StringBuilder data=new StringBuilder();
		data.append("-------- ARETES / ARCS --------\n");
		data.append("Nombre d'arêtes ou d'arcs = "+getD_nb_aretes()+"\n");

		for(int i=0;i<getD_nb_aretes();i++)
		{
			data.append("[ "+getAretes()[i].getD_sommet_depart().getD_numero()+" "+getAretes()[i].getD_sommet_arrive().getD_numero()+" ]\n");
		}
		textArea.setText(data.toString());
	}

    public boolean estUnArbre() 
	{
    	for(int i=1; i<=getD_nb_sommet(); i++) 
		{
    		int k = d_aps[i];
    		int n = 0;
    		while(k<=d_fs[0] && d_fs[k] != 0) 
			{
    			n++;
    			k++;
    		}
    		if( n == 1 && getD_nb_aretes() == getD_nb_sommet()-1)
    			return true;
    	}
    	return false;
    }

	public int getD_nb_sommet() 
	{
		return d_nb_sommet;
	}

	public void setD_nb_sommet(int d_nb_sommet) 
	{
		this.d_nb_sommet = d_nb_sommet;
	}

	public Arete[] getAretes() 
	{
		return aretes;
	}

	public void setAretes(Arete[] aretes) 
	{
		this.aretes = aretes;
	}

	public int getD_nb_aretes() 
	{
		return d_nb_aretes;
	}

	public void setD_nb_aretes(int d_nb_aretes) 
	{
		this.d_nb_aretes = d_nb_aretes;
	}

	public Arete getAretePos(int pos) 
	{
		return aretes[pos];
	}
}