public class SuperMatrice {
    private final int nl;
    private final int nc;
    private double[][] ligne;

    private SuperMatrice(int nl, int nc) {
        this.nl = nl;
        this.nc = nc;
        this.ligne = new double[nl][nc];
    }

    public static SuperMatrice allouerSupermat(int Ql, int Qc) {
        if (Ql <= 0 || Qc <= 0) {
            return null;
        }
        return new SuperMatrice(Ql, Qc);
    }

    public static SuperMatrice superProduit(SuperMatrice a, SuperMatrice b) {
        if (a == null || b == null || a.nc != b.nl) {
            return null;
        }

        SuperMatrice resultat = allouerSupermat(a.nl, b.nc);
        if (resultat == null) {
            return null;
        }

        for (int i = 0; i < a.nl; i++) {
            for (int j = 0; j < b.nc; j++) {
                double somme = 0.0;
                for (int k = 0; k < a.nc; k++) {
                    somme += a.get(i, k) * b.get(k, j);
                }
                resultat.set(i, j, somme);
            }
        }

        return resultat;
    }

    public static void permuterLignes(SuperMatrice a, int i, int j) {
        if (a == null || i < 0 || j < 0 || i >= a.nl || j >= a.nl) {
            return;
        }

        double[] tempon = a.ligne[i];
        a.ligne[i] = a.ligne[j];
        a.ligne[j] = tempon;
    }

    public static SuperMatrice sousMatrice(SuperMatrice a, int L1, int L2, int c1, int c2) {
        if (a == null || L1 < 0 || L2 >= a.nl || c1 < 0 || c2 >= a.nc || L1 > L2 || c1 > c2) {
            return null;
        }

        SuperMatrice sm = new SuperMatrice(L2 - L1 + 1, c2 - c1 + 1);

        for (int i = 0; i < sm.nl; i++) {
            for (int j = 0; j < sm.nc; j++) {
                sm.set(i, j, a.get(L1 + i, c1 + j));
            }
        }

        return sm;
    }

    public static SuperMatrice matSupermat(double[][] m, int Qle, int Qce) {
        if (m == null || Qle <= 0 || Qce <= 0 || Qle > m.length || Qce > m[0].length) {
            return null;
        }

        SuperMatrice sm = new SuperMatrice(Qle, Qce);

        for (int i = 0; i < Qle; i++) {
            for (int j = 0; j < Qce; j++) {
                sm.set(i, j, m[i][j]);
            }
        }

        return sm;
    }

    public static void supermatMat(SuperMatrice sm, double[][] m) {
        if (sm == null || m == null || sm.nl > m.length || sm.nc > m[0].length) {
            return;
        }

        for (int i = 0; i < sm.nl; i++) {
            for (int j = 0; j < sm.nc; j++) {
                m[i][j] = sm.get(i, j);
            }
        }
    }

    public static int contiguite(SuperMatrice a) {
        if (a == null || a.ligne == null || a.nl == 0 || a.nc == 0) {
            return 0;
        }

        boolean memeTableau = true;
        for (int i = 1; i < a.nl; i++) {
            if (a.ligne[i] != a.ligne[0]) {
                memeTableau = false;
                break;
            }
        }
        if (memeTableau) {
            return 2;
        }

        return 0;
    }

    public double get(int i, int j) {
        return ligne[i][j];
    }

    public void set(int i, int j, double value) {
        ligne[i][j] = value;
    }

    public int getNl() {
        return nl;
    }

    public int getNc() {
        return nc;
    }
}