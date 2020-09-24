
public class Ponynome extends MiniJava {

  public static int[] quadraticFormula(double[] coefficients) {
    int n = 2;
    int[] solution = new int[n];
    double y, z;
    double t = Math.sqrt((Math.pow(coefficients[1], 2)) - (4 * coefficients[0] * coefficients[2]));

    if (t == 0) {
      n = 1;
      y = (-1 * coefficients[1]) / (2 * coefficients[0]);
      int i = (int) y;
      solution[0] = i;
    }
    if (t <= -1) {
      n = 0;
    }
    if (t >= 1) {
      y = ((-1 * coefficients[1] + t) / (2 * coefficients[0]));
      z = ((-1 * coefficients[1] - t) / (2 * coefficients[0]));
      int k = (int) y;
      int j = (int) z;
      solution[0] = k;
      solution[1] = j;
    }
    return solution;
  }


  public static double[] hornerschema(double[] coefficients, int x0) {

    double[] horner = new double[3];
    horner[0] = coefficients[0];
    horner[1] = (horner[0] * x0) + coefficients[1];
    horner[2] = (horner[1] * x0) + coefficients[2];
    return horner;
  }

  public static double calculateY(double[] coefficients, int x) {
    double y = 1;
    if (coefficients.length == 4) {
      y = coefficients[0] * Math.pow(x, 3) + coefficients[1] * Math.pow(x, 2) + coefficients[2] * x
          + coefficients[3];
    }
    if (coefficients.length == 3) {
      y = coefficients[0] * Math.pow(x, 2) + coefficients[1] * x + coefficients[2];
    }
    if (coefficients.length == 2) {
      y = coefficients[0] * x + coefficients[1];
    }
    if (coefficients.length == 1) {
      y = coefficients[0];
    }
    if (coefficients.length == 0) {
      y = 0;
    }
    return y;
  }



  public static int[] findIntervalRecursive(double[] coefficients, int a, int b, int factor) {
    double c = calculateY(coefficients, a);
    double d = calculateY(coefficients, b);
    int[] intervall = new int[2];
    if ((c * d) <= 0) {
      intervall[0] = a;
      intervall[1] = b;

    } else {
      return findIntervalRecursive(coefficients, a * factor, b * factor, factor);
    }
    return intervall;
  }


  public static int findRootRecursive(double[] coefficients, int a, int b) {

    double c = calculateY(coefficients, a);
    double d = calculateY(coefficients, b);
    int e = (a + b) / 2;
    int x = 50;
    double f = calculateY(coefficients, e);
    if (c == 0 || d == 0 || f == 0) {

      if (c == 0) {
        x = a;
      }
      if (d == 0) {
        x = b;
      }
      if (f == 0) {
        x = e;
      }

    }
    if (c != 0 && d != 0 && f != 0) {
      if (c * f < 0) {
        b = e;
      }
      if (d * f < 0) {
        a = e;
      }
      return findRootRecursive(coefficients, a, b);
    }
    return x;
  }


  public static void main(String[] args) {
    write("Bitte geben Sie 4 Dezimalzahlen ein"); // alle Zahlen eingeben eingeben
    double[] Koeffizienten = new double[4];
    for (int i = 0; i < 4; i++) {
      Koeffizienten[i] = readDouble("Bitte geben Sie Ihre " + (i + 1) + ". Zahl ein:");
    }
    int a = readInt("Bitte geben Sie die linke Grenze des Intervalls, kleiner 0 ein:");
    while (a >= 0) {
      a = readInt("Die Zahl muss kleiner 0 sein!");
    }
    int b = readInt("Bitte geben Sie die rechte Grenze des Intervalls, größer 0 ein:");
    while (b <= 0) {
      b = readInt("Die Zahl muss größer 0 sein!");
    }
    int f = readInt("Bitte geben Sie einen Faktor größer 0 ein:");
    while (f <= 0) {
      f = readInt("Die Zahl muss größer 0 sein!");
    }
    int[] intervall = findIntervalRecursive(Koeffizienten, a, b, f); // Intervall finden
    int Nullstelle1 = findRootRecursive(Koeffizienten, intervall[0], intervall[1]); // erste
                                                                                    // Nullstelle
                                                                                    // finden
    double[] reduzierteKoeffizienten = hornerschema(Koeffizienten, Nullstelle1); // neue
                                                                                 // Koeffizienten
                                                                                 // finden
    int[] solution = quadraticFormula(reduzierteKoeffizienten); // Mitternachtsformel

    String tmp = "\n Die Nullstellen sind " + Nullstelle1; // Lösung ausgeben
    for (int i = 0; i < solution.length; i++) {
      tmp += ", ";
      tmp += solution[i];
    }
    tmp += ".";
    System.out.println(tmp);

  }

}


