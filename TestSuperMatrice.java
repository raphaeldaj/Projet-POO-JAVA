public class TestSuperMatrice {
    public static void afficherMatrice(SuperMatrice sm) {
        if (sm == null) {
            System.out.println("Matrice NULL");
            return;
        }

        for (int i = 0; i < sm.getNl(); i++) {
            for (int j = 0; j < sm.getNc(); j++) {
                System.out.printf("%6.2f ", sm.get(i, j));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Test d'allocation\n");
        SuperMatrice a = SuperMatrice.allouerSupermat(3, 2);
        if (a == null) {
            System.out.println("Échec d'allocation de a");
            return;
        }

        for (int i = 0; i < a.getNl(); i++) {
            for (int j = 0; j < a.getNc(); j++) {
                a.set(i, j, i * a.getNc() + j + 1);
            }
        }

        afficherMatrice(a);

        System.out.println("Test de produit matriciel\n");

        SuperMatrice b = SuperMatrice.allouerSupermat(2, 4);
        if (b == null) {
            System.out.println("Échec d'allocation de b pour le produit matriciel");
            return;
        }

        for (int i = 0; i < b.getNl(); i++) {
            for (int j = 0; j < b.getNc(); j++) {
                b.set(i, j, i * b.getNc() + j + 1);
            }
        }
        // afficherMatrice(b);

        System.out.println("la première matrice \n");
        afficherMatrice(a);
        System.out.println("la deuxième matrice \n");
        afficherMatrice(b);

        System.out.println("resultat du produit \n");
        SuperMatrice c = SuperMatrice.superProduit(a, b);
        afficherMatrice(c);

        System.out.println("Test de permutation de lignes\n");
        System.out.println("Avant permutation:\n");
        afficherMatrice(a);
        SuperMatrice.permuterLignes(a, 0, 1);
        System.out.println("Après permutation des lignes 0 et 1:\n");
        afficherMatrice(a);

        System.out.println("Test de sous-matrice\n");
        System.out.println("matrice d'origine\n");
        SuperMatrice sous = SuperMatrice.sousMatrice(c, 1, 2, 1, 3);
        afficherMatrice(c);
        System.out.println("sous-matrice extraite\n");
        afficherMatrice(sous);

        System.out.println("Test de conversion matrice en supermatrice\n");
        double[][] mat = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        SuperMatrice sm = SuperMatrice.matSupermat(mat, 3, 4);
        afficherMatrice(sm);

        System.out.println("Test de conversion supermatrice en matrice\n");
        double[][] mat2 = new double[3][4];
        SuperMatrice.supermatMat(sm, mat2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%6.2f ", mat2[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Test de contiguite\n");
        System.out.println("Contiguite de a                              : " + SuperMatrice.contiguite(a));
        System.out.println("Contiguite de b                              : " + SuperMatrice.contiguite(b));
        System.out.println("Contiguite de c                              : " + SuperMatrice.contiguite(c));
        System.out.println("Contiguite de sousmatrice                    : " + SuperMatrice.contiguite(sous));
        System.out.println("Contiguite de sm matrice issue de matSupermat: " + SuperMatrice.contiguite(sm));

        // En Java, le garbage collector s'occupe de la libération de mémoire
        // donc pas besoin d'équivalent à rendreSupermat
    }
}